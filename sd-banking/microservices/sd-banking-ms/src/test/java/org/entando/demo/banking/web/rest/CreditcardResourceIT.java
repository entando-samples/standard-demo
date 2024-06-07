package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.BankingApp;
import org.entando.demo.banking.config.TestSecurityConfiguration;
import org.entando.demo.banking.domain.Creditcard;
import org.entando.demo.banking.repository.CreditcardRepository;

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
 * Integration tests for the {@link CreditcardResource} REST controller.
 */
@SpringBootTest(classes = { BankingApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CreditcardResourceIT {

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final Long DEFAULT_REWARD_POINTS = 1L;
    private static final Long UPDATED_REWARD_POINTS = 2L;

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    @Autowired
    private CreditcardRepository creditcardRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreditcardMockMvc;

    private Creditcard creditcard;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creditcard createEntity(EntityManager em) {
        Creditcard creditcard = new Creditcard()
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .balance(DEFAULT_BALANCE)
            .rewardPoints(DEFAULT_REWARD_POINTS)
            .userID(DEFAULT_USER_ID);
        return creditcard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creditcard createUpdatedEntity(EntityManager em) {
        Creditcard creditcard = new Creditcard()
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .balance(UPDATED_BALANCE)
            .rewardPoints(UPDATED_REWARD_POINTS)
            .userID(UPDATED_USER_ID);
        return creditcard;
    }

    @BeforeEach
    public void initTest() {
        creditcard = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreditcard() throws Exception {
        int databaseSizeBeforeCreate = creditcardRepository.findAll().size();
        // Create the Creditcard
        restCreditcardMockMvc.perform(post("/api/creditcards").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcard)))
            .andExpect(status().isCreated());

        // Validate the Creditcard in the database
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeCreate + 1);
        Creditcard testCreditcard = creditcardList.get(creditcardList.size() - 1);
        assertThat(testCreditcard.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testCreditcard.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testCreditcard.getRewardPoints()).isEqualTo(DEFAULT_REWARD_POINTS);
        assertThat(testCreditcard.getUserID()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createCreditcardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creditcardRepository.findAll().size();

        // Create the Creditcard with an existing ID
        creditcard.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditcardMockMvc.perform(post("/api/creditcards").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcard)))
            .andExpect(status().isBadRequest());

        // Validate the Creditcard in the database
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCreditcards() throws Exception {
        // Initialize the database
        creditcardRepository.saveAndFlush(creditcard);

        // Get all the creditcardList
        restCreditcardMockMvc.perform(get("/api/creditcards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditcard.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].rewardPoints").value(hasItem(DEFAULT_REWARD_POINTS.intValue())))
            .andExpect(jsonPath("$.[*].userID").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    @Transactional
    public void getCreditcard() throws Exception {
        // Initialize the database
        creditcardRepository.saveAndFlush(creditcard);

        // Get the creditcard
        restCreditcardMockMvc.perform(get("/api/creditcards/{id}", creditcard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(creditcard.getId().intValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.rewardPoints").value(DEFAULT_REWARD_POINTS.intValue()))
            .andExpect(jsonPath("$.userID").value(DEFAULT_USER_ID));
    }
    @Test
    @Transactional
    public void getNonExistingCreditcard() throws Exception {
        // Get the creditcard
        restCreditcardMockMvc.perform(get("/api/creditcards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreditcard() throws Exception {
        // Initialize the database
        creditcardRepository.saveAndFlush(creditcard);

        int databaseSizeBeforeUpdate = creditcardRepository.findAll().size();

        // Update the creditcard
        Creditcard updatedCreditcard = creditcardRepository.findById(creditcard.getId()).get();
        // Disconnect from session so that the updates on updatedCreditcard are not directly saved in db
        em.detach(updatedCreditcard);
        updatedCreditcard
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .balance(UPDATED_BALANCE)
            .rewardPoints(UPDATED_REWARD_POINTS)
            .userID(UPDATED_USER_ID);

        restCreditcardMockMvc.perform(put("/api/creditcards").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCreditcard)))
            .andExpect(status().isOk());

        // Validate the Creditcard in the database
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeUpdate);
        Creditcard testCreditcard = creditcardList.get(creditcardList.size() - 1);
        assertThat(testCreditcard.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testCreditcard.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testCreditcard.getRewardPoints()).isEqualTo(UPDATED_REWARD_POINTS);
        assertThat(testCreditcard.getUserID()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCreditcard() throws Exception {
        int databaseSizeBeforeUpdate = creditcardRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditcardMockMvc.perform(put("/api/creditcards").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcard)))
            .andExpect(status().isBadRequest());

        // Validate the Creditcard in the database
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCreditcard() throws Exception {
        // Initialize the database
        creditcardRepository.saveAndFlush(creditcard);

        int databaseSizeBeforeDelete = creditcardRepository.findAll().size();

        // Delete the creditcard
        restCreditcardMockMvc.perform(delete("/api/creditcards/{id}", creditcard.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Creditcard> creditcardList = creditcardRepository.findAll();
        assertThat(creditcardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
