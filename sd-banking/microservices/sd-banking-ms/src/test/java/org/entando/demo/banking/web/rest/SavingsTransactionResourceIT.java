package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.BankingApp;
import org.entando.demo.banking.config.TestSecurityConfiguration;
import org.entando.demo.banking.domain.SavingsTransaction;
import org.entando.demo.banking.repository.SavingsTransactionRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SavingsTransactionResource} REST controller.
 */
@SpringBootTest(classes = { BankingApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class SavingsTransactionResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final Long DEFAULT_ACCOUNT_ID = 1L;
    private static final Long UPDATED_ACCOUNT_ID = 2L;

    @Autowired
    private SavingsTransactionRepository savingsTransactionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSavingsTransactionMockMvc;

    private SavingsTransaction savingsTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SavingsTransaction createEntity(EntityManager em) {
        SavingsTransaction savingsTransaction = new SavingsTransaction()
            .date(DEFAULT_DATE)
            .description(DEFAULT_DESCRIPTION)
            .amount(DEFAULT_AMOUNT)
            .balance(DEFAULT_BALANCE)
            .accountID(DEFAULT_ACCOUNT_ID);
        return savingsTransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SavingsTransaction createUpdatedEntity(EntityManager em) {
        SavingsTransaction savingsTransaction = new SavingsTransaction()
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .balance(UPDATED_BALANCE)
            .accountID(UPDATED_ACCOUNT_ID);
        return savingsTransaction;
    }

    @BeforeEach
    public void initTest() {
        savingsTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createSavingsTransaction() throws Exception {
        int databaseSizeBeforeCreate = savingsTransactionRepository.findAll().size();
        // Create the SavingsTransaction
        restSavingsTransactionMockMvc.perform(post("/api/savings-transactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsTransaction)))
            .andExpect(status().isCreated());

        // Validate the SavingsTransaction in the database
        List<SavingsTransaction> savingsTransactionList = savingsTransactionRepository.findAll();
        assertThat(savingsTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        SavingsTransaction testSavingsTransaction = savingsTransactionList.get(savingsTransactionList.size() - 1);
        assertThat(testSavingsTransaction.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSavingsTransaction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSavingsTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testSavingsTransaction.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testSavingsTransaction.getAccountID()).isEqualTo(DEFAULT_ACCOUNT_ID);
    }

    @Test
    @Transactional
    public void createSavingsTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = savingsTransactionRepository.findAll().size();

        // Create the SavingsTransaction with an existing ID
        savingsTransaction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSavingsTransactionMockMvc.perform(post("/api/savings-transactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsTransaction)))
            .andExpect(status().isBadRequest());

        // Validate the SavingsTransaction in the database
        List<SavingsTransaction> savingsTransactionList = savingsTransactionRepository.findAll();
        assertThat(savingsTransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSavingsTransactions() throws Exception {
        // Initialize the database
        savingsTransactionRepository.saveAndFlush(savingsTransaction);

        // Get all the savingsTransactionList
        restSavingsTransactionMockMvc.perform(get("/api/savings-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(savingsTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].accountID").value(hasItem(DEFAULT_ACCOUNT_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getSavingsTransaction() throws Exception {
        // Initialize the database
        savingsTransactionRepository.saveAndFlush(savingsTransaction);

        // Get the savingsTransaction
        restSavingsTransactionMockMvc.perform(get("/api/savings-transactions/{id}", savingsTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(savingsTransaction.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.accountID").value(DEFAULT_ACCOUNT_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSavingsTransaction() throws Exception {
        // Get the savingsTransaction
        restSavingsTransactionMockMvc.perform(get("/api/savings-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSavingsTransaction() throws Exception {
        // Initialize the database
        savingsTransactionRepository.saveAndFlush(savingsTransaction);

        int databaseSizeBeforeUpdate = savingsTransactionRepository.findAll().size();

        // Update the savingsTransaction
        SavingsTransaction updatedSavingsTransaction = savingsTransactionRepository.findById(savingsTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedSavingsTransaction are not directly saved in db
        em.detach(updatedSavingsTransaction);
        updatedSavingsTransaction
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .balance(UPDATED_BALANCE)
            .accountID(UPDATED_ACCOUNT_ID);

        restSavingsTransactionMockMvc.perform(put("/api/savings-transactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSavingsTransaction)))
            .andExpect(status().isOk());

        // Validate the SavingsTransaction in the database
        List<SavingsTransaction> savingsTransactionList = savingsTransactionRepository.findAll();
        assertThat(savingsTransactionList).hasSize(databaseSizeBeforeUpdate);
        SavingsTransaction testSavingsTransaction = savingsTransactionList.get(savingsTransactionList.size() - 1);
        assertThat(testSavingsTransaction.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSavingsTransaction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSavingsTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testSavingsTransaction.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testSavingsTransaction.getAccountID()).isEqualTo(UPDATED_ACCOUNT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSavingsTransaction() throws Exception {
        int databaseSizeBeforeUpdate = savingsTransactionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSavingsTransactionMockMvc.perform(put("/api/savings-transactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsTransaction)))
            .andExpect(status().isBadRequest());

        // Validate the SavingsTransaction in the database
        List<SavingsTransaction> savingsTransactionList = savingsTransactionRepository.findAll();
        assertThat(savingsTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSavingsTransaction() throws Exception {
        // Initialize the database
        savingsTransactionRepository.saveAndFlush(savingsTransaction);

        int databaseSizeBeforeDelete = savingsTransactionRepository.findAll().size();

        // Delete the savingsTransaction
        restSavingsTransactionMockMvc.perform(delete("/api/savings-transactions/{id}", savingsTransaction.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SavingsTransaction> savingsTransactionList = savingsTransactionRepository.findAll();
        assertThat(savingsTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
