package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.BankingApp;
import org.entando.demo.banking.config.TestSecurityConfiguration;
import org.entando.demo.banking.domain.CreditCardUser;
import org.entando.demo.banking.repository.CreditCardUserRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CreditCardUserResource} REST controller.
 */
@SpringBootTest(classes = { BankingApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CreditCardUserResourceIT {

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private CreditCardUserRepository creditCardUserRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreditCardUserMockMvc;

    private CreditCardUser creditCardUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditCardUser createEntity(EntityManager em) {
        CreditCardUser creditCardUser = new CreditCardUser()
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .createdAt(DEFAULT_CREATED_AT)
            .status(DEFAULT_STATUS);
        return creditCardUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditCardUser createUpdatedEntity(EntityManager em) {
        CreditCardUser creditCardUser = new CreditCardUser()
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .createdAt(UPDATED_CREATED_AT)
            .status(UPDATED_STATUS);
        return creditCardUser;
    }

    @BeforeEach
    public void initTest() {
        creditCardUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreditCardUser() throws Exception {
        int databaseSizeBeforeCreate = creditCardUserRepository.findAll().size();
        // Create the CreditCardUser
        restCreditCardUserMockMvc.perform(post("/api/credit-card-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditCardUser)))
            .andExpect(status().isCreated());

        // Validate the CreditCardUser in the database
        List<CreditCardUser> creditCardUserList = creditCardUserRepository.findAll();
        assertThat(creditCardUserList).hasSize(databaseSizeBeforeCreate + 1);
        CreditCardUser testCreditCardUser = creditCardUserList.get(creditCardUserList.size() - 1);
        assertThat(testCreditCardUser.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testCreditCardUser.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testCreditCardUser.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testCreditCardUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCreditCardUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCreditCardUser.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCreditCardUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creditCardUserRepository.findAll().size();

        // Create the CreditCardUser with an existing ID
        creditCardUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditCardUserMockMvc.perform(post("/api/credit-card-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditCardUser)))
            .andExpect(status().isBadRequest());

        // Validate the CreditCardUser in the database
        List<CreditCardUser> creditCardUserList = creditCardUserRepository.findAll();
        assertThat(creditCardUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCreditCardUsers() throws Exception {
        // Initialize the database
        creditCardUserRepository.saveAndFlush(creditCardUser);

        // Get all the creditCardUserList
        restCreditCardUserMockMvc.perform(get("/api/credit-card-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditCardUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getCreditCardUser() throws Exception {
        // Initialize the database
        creditCardUserRepository.saveAndFlush(creditCardUser);

        // Get the creditCardUser
        restCreditCardUserMockMvc.perform(get("/api/credit-card-users/{id}", creditCardUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(creditCardUser.getId().intValue()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingCreditCardUser() throws Exception {
        // Get the creditCardUser
        restCreditCardUserMockMvc.perform(get("/api/credit-card-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreditCardUser() throws Exception {
        // Initialize the database
        creditCardUserRepository.saveAndFlush(creditCardUser);

        int databaseSizeBeforeUpdate = creditCardUserRepository.findAll().size();

        // Update the creditCardUser
        CreditCardUser updatedCreditCardUser = creditCardUserRepository.findById(creditCardUser.getId()).get();
        // Disconnect from session so that the updates on updatedCreditCardUser are not directly saved in db
        em.detach(updatedCreditCardUser);
        updatedCreditCardUser
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .createdAt(UPDATED_CREATED_AT)
            .status(UPDATED_STATUS);

        restCreditCardUserMockMvc.perform(put("/api/credit-card-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCreditCardUser)))
            .andExpect(status().isOk());

        // Validate the CreditCardUser in the database
        List<CreditCardUser> creditCardUserList = creditCardUserRepository.findAll();
        assertThat(creditCardUserList).hasSize(databaseSizeBeforeUpdate);
        CreditCardUser testCreditCardUser = creditCardUserList.get(creditCardUserList.size() - 1);
        assertThat(testCreditCardUser.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testCreditCardUser.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testCreditCardUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testCreditCardUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCreditCardUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCreditCardUser.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCreditCardUser() throws Exception {
        int databaseSizeBeforeUpdate = creditCardUserRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditCardUserMockMvc.perform(put("/api/credit-card-users").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(creditCardUser)))
            .andExpect(status().isBadRequest());

        // Validate the CreditCardUser in the database
        List<CreditCardUser> creditCardUserList = creditCardUserRepository.findAll();
        assertThat(creditCardUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCreditCardUser() throws Exception {
        // Initialize the database
        creditCardUserRepository.saveAndFlush(creditCardUser);

        int databaseSizeBeforeDelete = creditCardUserRepository.findAll().size();

        // Delete the creditCardUser
        restCreditCardUserMockMvc.perform(delete("/api/credit-card-users/{id}", creditCardUser.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CreditCardUser> creditCardUserList = creditCardUserRepository.findAll();
        assertThat(creditCardUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
