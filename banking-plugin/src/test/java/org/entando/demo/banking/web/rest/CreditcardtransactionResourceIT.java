package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.BankingApp;
import org.entando.demo.banking.config.TestSecurityConfiguration;
import org.entando.demo.banking.domain.Creditcardtransaction;
import org.entando.demo.banking.repository.CreditcardtransactionRepository;

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
 * Integration tests for the {@link CreditcardtransactionResource} REST controller.
 */
@SpringBootTest(classes = { BankingApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CreditcardtransactionResourceIT {

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
    private CreditcardtransactionRepository creditcardtransactionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreditcardtransactionMockMvc;

    private Creditcardtransaction creditcardtransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creditcardtransaction createEntity(EntityManager em) {
        Creditcardtransaction creditcardtransaction = new Creditcardtransaction()
            .date(DEFAULT_DATE)
            .description(DEFAULT_DESCRIPTION)
            .amount(DEFAULT_AMOUNT)
            .balance(DEFAULT_BALANCE)
            .accountID(DEFAULT_ACCOUNT_ID);
        return creditcardtransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creditcardtransaction createUpdatedEntity(EntityManager em) {
        Creditcardtransaction creditcardtransaction = new Creditcardtransaction()
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .balance(UPDATED_BALANCE)
            .accountID(UPDATED_ACCOUNT_ID);
        return creditcardtransaction;
    }

    @BeforeEach
    public void initTest() {
        creditcardtransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreditcardtransaction() throws Exception {
        int databaseSizeBeforeCreate = creditcardtransactionRepository.findAll().size();
        // Create the Creditcardtransaction
        restCreditcardtransactionMockMvc.perform(post("/api/creditcardtransactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcardtransaction)))
            .andExpect(status().isCreated());

        // Validate the Creditcardtransaction in the database
        List<Creditcardtransaction> creditcardtransactionList = creditcardtransactionRepository.findAll();
        assertThat(creditcardtransactionList).hasSize(databaseSizeBeforeCreate + 1);
        Creditcardtransaction testCreditcardtransaction = creditcardtransactionList.get(creditcardtransactionList.size() - 1);
        assertThat(testCreditcardtransaction.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCreditcardtransaction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCreditcardtransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCreditcardtransaction.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testCreditcardtransaction.getAccountID()).isEqualTo(DEFAULT_ACCOUNT_ID);
    }

    @Test
    @Transactional
    public void createCreditcardtransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creditcardtransactionRepository.findAll().size();

        // Create the Creditcardtransaction with an existing ID
        creditcardtransaction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditcardtransactionMockMvc.perform(post("/api/creditcardtransactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcardtransaction)))
            .andExpect(status().isBadRequest());

        // Validate the Creditcardtransaction in the database
        List<Creditcardtransaction> creditcardtransactionList = creditcardtransactionRepository.findAll();
        assertThat(creditcardtransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCreditcardtransactions() throws Exception {
        // Initialize the database
        creditcardtransactionRepository.saveAndFlush(creditcardtransaction);

        // Get all the creditcardtransactionList
        restCreditcardtransactionMockMvc.perform(get("/api/creditcardtransactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditcardtransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].accountID").value(hasItem(DEFAULT_ACCOUNT_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCreditcardtransaction() throws Exception {
        // Initialize the database
        creditcardtransactionRepository.saveAndFlush(creditcardtransaction);

        // Get the creditcardtransaction
        restCreditcardtransactionMockMvc.perform(get("/api/creditcardtransactions/{id}", creditcardtransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(creditcardtransaction.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.accountID").value(DEFAULT_ACCOUNT_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCreditcardtransaction() throws Exception {
        // Get the creditcardtransaction
        restCreditcardtransactionMockMvc.perform(get("/api/creditcardtransactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreditcardtransaction() throws Exception {
        // Initialize the database
        creditcardtransactionRepository.saveAndFlush(creditcardtransaction);

        int databaseSizeBeforeUpdate = creditcardtransactionRepository.findAll().size();

        // Update the creditcardtransaction
        Creditcardtransaction updatedCreditcardtransaction = creditcardtransactionRepository.findById(creditcardtransaction.getId()).get();
        // Disconnect from session so that the updates on updatedCreditcardtransaction are not directly saved in db
        em.detach(updatedCreditcardtransaction);
        updatedCreditcardtransaction
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .balance(UPDATED_BALANCE)
            .accountID(UPDATED_ACCOUNT_ID);

        restCreditcardtransactionMockMvc.perform(put("/api/creditcardtransactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCreditcardtransaction)))
            .andExpect(status().isOk());

        // Validate the Creditcardtransaction in the database
        List<Creditcardtransaction> creditcardtransactionList = creditcardtransactionRepository.findAll();
        assertThat(creditcardtransactionList).hasSize(databaseSizeBeforeUpdate);
        Creditcardtransaction testCreditcardtransaction = creditcardtransactionList.get(creditcardtransactionList.size() - 1);
        assertThat(testCreditcardtransaction.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCreditcardtransaction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCreditcardtransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCreditcardtransaction.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testCreditcardtransaction.getAccountID()).isEqualTo(UPDATED_ACCOUNT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCreditcardtransaction() throws Exception {
        int databaseSizeBeforeUpdate = creditcardtransactionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditcardtransactionMockMvc.perform(put("/api/creditcardtransactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditcardtransaction)))
            .andExpect(status().isBadRequest());

        // Validate the Creditcardtransaction in the database
        List<Creditcardtransaction> creditcardtransactionList = creditcardtransactionRepository.findAll();
        assertThat(creditcardtransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCreditcardtransaction() throws Exception {
        // Initialize the database
        creditcardtransactionRepository.saveAndFlush(creditcardtransaction);

        int databaseSizeBeforeDelete = creditcardtransactionRepository.findAll().size();

        // Delete the creditcardtransaction
        restCreditcardtransactionMockMvc.perform(delete("/api/creditcardtransactions/{id}", creditcardtransaction.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Creditcardtransaction> creditcardtransactionList = creditcardtransactionRepository.findAll();
        assertThat(creditcardtransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
