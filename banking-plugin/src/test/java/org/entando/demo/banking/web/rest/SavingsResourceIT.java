package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.BankingApp;
import org.entando.demo.banking.config.TestSecurityConfiguration;
import org.entando.demo.banking.domain.Savings;
import org.entando.demo.banking.repository.SavingsRepository;

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
 * Integration tests for the {@link SavingsResource} REST controller.
 */
@SpringBootTest(classes = { BankingApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class SavingsResourceIT {

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSavingsMockMvc;

    private Savings savings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Savings createEntity(EntityManager em) {
        Savings savings = new Savings()
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .balance(DEFAULT_BALANCE);
        return savings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Savings createUpdatedEntity(EntityManager em) {
        Savings savings = new Savings()
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .balance(UPDATED_BALANCE);
        return savings;
    }

    @BeforeEach
    public void initTest() {
        savings = createEntity(em);
    }

    @Test
    @Transactional
    public void createSavings() throws Exception {
        int databaseSizeBeforeCreate = savingsRepository.findAll().size();
        // Create the Savings
        restSavingsMockMvc.perform(post("/api/savings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savings)))
            .andExpect(status().isCreated());

        // Validate the Savings in the database
        List<Savings> savingsList = savingsRepository.findAll();
        assertThat(savingsList).hasSize(databaseSizeBeforeCreate + 1);
        Savings testSavings = savingsList.get(savingsList.size() - 1);
        assertThat(testSavings.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testSavings.getBalance()).isEqualTo(DEFAULT_BALANCE);
    }

    @Test
    @Transactional
    public void createSavingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = savingsRepository.findAll().size();

        // Create the Savings with an existing ID
        savings.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSavingsMockMvc.perform(post("/api/savings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savings)))
            .andExpect(status().isBadRequest());

        // Validate the Savings in the database
        List<Savings> savingsList = savingsRepository.findAll();
        assertThat(savingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSavings() throws Exception {
        // Initialize the database
        savingsRepository.saveAndFlush(savings);

        // Get all the savingsList
        restSavingsMockMvc.perform(get("/api/savings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(savings.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())));
    }
    
    @Test
    @Transactional
    public void getSavings() throws Exception {
        // Initialize the database
        savingsRepository.saveAndFlush(savings);

        // Get the savings
        restSavingsMockMvc.perform(get("/api/savings/{id}", savings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(savings.getId().intValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSavings() throws Exception {
        // Get the savings
        restSavingsMockMvc.perform(get("/api/savings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSavings() throws Exception {
        // Initialize the database
        savingsRepository.saveAndFlush(savings);

        int databaseSizeBeforeUpdate = savingsRepository.findAll().size();

        // Update the savings
        Savings updatedSavings = savingsRepository.findById(savings.getId()).get();
        // Disconnect from session so that the updates on updatedSavings are not directly saved in db
        em.detach(updatedSavings);
        updatedSavings
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .balance(UPDATED_BALANCE);

        restSavingsMockMvc.perform(put("/api/savings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSavings)))
            .andExpect(status().isOk());

        // Validate the Savings in the database
        List<Savings> savingsList = savingsRepository.findAll();
        assertThat(savingsList).hasSize(databaseSizeBeforeUpdate);
        Savings testSavings = savingsList.get(savingsList.size() - 1);
        assertThat(testSavings.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testSavings.getBalance()).isEqualTo(UPDATED_BALANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingSavings() throws Exception {
        int databaseSizeBeforeUpdate = savingsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSavingsMockMvc.perform(put("/api/savings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savings)))
            .andExpect(status().isBadRequest());

        // Validate the Savings in the database
        List<Savings> savingsList = savingsRepository.findAll();
        assertThat(savingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSavings() throws Exception {
        // Initialize the database
        savingsRepository.saveAndFlush(savings);

        int databaseSizeBeforeDelete = savingsRepository.findAll().size();

        // Delete the savings
        restSavingsMockMvc.perform(delete("/api/savings/{id}", savings.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Savings> savingsList = savingsRepository.findAll();
        assertThat(savingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
