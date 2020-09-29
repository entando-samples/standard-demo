package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.BankingApp;
import org.entando.demo.banking.config.TestSecurityConfiguration;
import org.entando.demo.banking.domain.Checking;
import org.entando.demo.banking.repository.CheckingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CheckingResource} REST controller.
 */
@SpringBootTest(classes = { BankingApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CheckingResourceIT {

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCheckingMockMvc;

    private Checking checking;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Checking createEntity(EntityManager em) {
        Checking checking = new Checking()
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .balance(DEFAULT_BALANCE)
            .userID(DEFAULT_USER_ID);
        return checking;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Checking createUpdatedEntity(EntityManager em) {
        Checking checking = new Checking()
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .balance(UPDATED_BALANCE)
            .userID(UPDATED_USER_ID);
        return checking;
    }

    @BeforeEach
    public void initTest() {
        checking = createEntity(em);
    }

    @Test
    @Transactional
    public void createChecking() throws Exception {
        int databaseSizeBeforeCreate = checkingRepository.findAll().size();
        // Create the Checking
        restCheckingMockMvc.perform(post("/api/checkings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(checking)))
            .andExpect(status().isCreated());

        // Validate the Checking in the database
        List<Checking> checkingList = checkingRepository.findAll();
        assertThat(checkingList).hasSize(databaseSizeBeforeCreate + 1);
        Checking testChecking = checkingList.get(checkingList.size() - 1);
        assertThat(testChecking.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testChecking.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testChecking.getUserID()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createCheckingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = checkingRepository.findAll().size();

        // Create the Checking with an existing ID
        checking.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCheckingMockMvc.perform(post("/api/checkings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(checking)))
            .andExpect(status().isBadRequest());

        // Validate the Checking in the database
        List<Checking> checkingList = checkingRepository.findAll();
        assertThat(checkingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCheckings() throws Exception {
        // Initialize the database
        checkingRepository.saveAndFlush(checking);

        // Get all the checkingList
        restCheckingMockMvc.perform(get("/api/checkings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checking.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].userID").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    @Transactional
    public void getChecking() throws Exception {
        // Initialize the database
        checkingRepository.saveAndFlush(checking);

        // Get the checking
        restCheckingMockMvc.perform(get("/api/checkings/{id}", checking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(checking.getId().intValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.userID").value(DEFAULT_USER_ID));
    }
    @Test
    @Transactional
    public void getNonExistingChecking() throws Exception {
        // Get the checking
        restCheckingMockMvc.perform(get("/api/checkings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChecking() throws Exception {
        // Initialize the database
        checkingRepository.saveAndFlush(checking);

        int databaseSizeBeforeUpdate = checkingRepository.findAll().size();

        // Update the checking
        Checking updatedChecking = checkingRepository.findById(checking.getId()).get();
        // Disconnect from session so that the updates on updatedChecking are not directly saved in db
        em.detach(updatedChecking);
        updatedChecking
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .balance(UPDATED_BALANCE)
            .userID(UPDATED_USER_ID);

        restCheckingMockMvc.perform(put("/api/checkings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedChecking)))
            .andExpect(status().isOk());

        // Validate the Checking in the database
        List<Checking> checkingList = checkingRepository.findAll();
        assertThat(checkingList).hasSize(databaseSizeBeforeUpdate);
        Checking testChecking = checkingList.get(checkingList.size() - 1);
        assertThat(testChecking.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testChecking.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testChecking.getUserID()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingChecking() throws Exception {
        int databaseSizeBeforeUpdate = checkingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckingMockMvc.perform(put("/api/checkings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(checking)))
            .andExpect(status().isBadRequest());

        // Validate the Checking in the database
        List<Checking> checkingList = checkingRepository.findAll();
        assertThat(checkingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChecking() throws Exception {
        // Initialize the database
        checkingRepository.saveAndFlush(checking);

        int databaseSizeBeforeDelete = checkingRepository.findAll().size();

        // Delete the checking
        restCheckingMockMvc.perform(delete("/api/checkings/{id}", checking.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Checking> checkingList = checkingRepository.findAll();
        assertThat(checkingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
