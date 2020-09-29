package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.BankingApp;
import org.entando.demo.banking.config.TestSecurityConfiguration;
import org.entando.demo.banking.domain.Statement;
import org.entando.demo.banking.repository.StatementRepository;

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
 * Integration tests for the {@link StatementResource} REST controller.
 */
@SpringBootTest(classes = { BankingApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class StatementResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_READ = false;
    private static final Boolean UPDATED_READ = true;

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatementMockMvc;

    private Statement statement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statement createEntity(EntityManager em) {
        Statement statement = new Statement()
            .description(DEFAULT_DESCRIPTION)
            .createdAt(DEFAULT_CREATED_AT)
            .read(DEFAULT_READ)
            .userId(DEFAULT_USER_ID);
        return statement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statement createUpdatedEntity(EntityManager em) {
        Statement statement = new Statement()
            .description(UPDATED_DESCRIPTION)
            .createdAt(UPDATED_CREATED_AT)
            .read(UPDATED_READ)
            .userId(UPDATED_USER_ID);
        return statement;
    }

    @BeforeEach
    public void initTest() {
        statement = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatement() throws Exception {
        int databaseSizeBeforeCreate = statementRepository.findAll().size();
        // Create the Statement
        restStatementMockMvc.perform(post("/api/statements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statement)))
            .andExpect(status().isCreated());

        // Validate the Statement in the database
        List<Statement> statementList = statementRepository.findAll();
        assertThat(statementList).hasSize(databaseSizeBeforeCreate + 1);
        Statement testStatement = statementList.get(statementList.size() - 1);
        assertThat(testStatement.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testStatement.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testStatement.isRead()).isEqualTo(DEFAULT_READ);
        assertThat(testStatement.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createStatementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statementRepository.findAll().size();

        // Create the Statement with an existing ID
        statement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatementMockMvc.perform(post("/api/statements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statement)))
            .andExpect(status().isBadRequest());

        // Validate the Statement in the database
        List<Statement> statementList = statementRepository.findAll();
        assertThat(statementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStatements() throws Exception {
        // Initialize the database
        statementRepository.saveAndFlush(statement);

        // Get all the statementList
        restStatementMockMvc.perform(get("/api/statements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statement.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].read").value(hasItem(DEFAULT_READ.booleanValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    @Transactional
    public void getStatement() throws Exception {
        // Initialize the database
        statementRepository.saveAndFlush(statement);

        // Get the statement
        restStatementMockMvc.perform(get("/api/statements/{id}", statement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statement.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.read").value(DEFAULT_READ.booleanValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID));
    }
    @Test
    @Transactional
    public void getNonExistingStatement() throws Exception {
        // Get the statement
        restStatementMockMvc.perform(get("/api/statements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatement() throws Exception {
        // Initialize the database
        statementRepository.saveAndFlush(statement);

        int databaseSizeBeforeUpdate = statementRepository.findAll().size();

        // Update the statement
        Statement updatedStatement = statementRepository.findById(statement.getId()).get();
        // Disconnect from session so that the updates on updatedStatement are not directly saved in db
        em.detach(updatedStatement);
        updatedStatement
            .description(UPDATED_DESCRIPTION)
            .createdAt(UPDATED_CREATED_AT)
            .read(UPDATED_READ)
            .userId(UPDATED_USER_ID);

        restStatementMockMvc.perform(put("/api/statements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatement)))
            .andExpect(status().isOk());

        // Validate the Statement in the database
        List<Statement> statementList = statementRepository.findAll();
        assertThat(statementList).hasSize(databaseSizeBeforeUpdate);
        Statement testStatement = statementList.get(statementList.size() - 1);
        assertThat(testStatement.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testStatement.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testStatement.isRead()).isEqualTo(UPDATED_READ);
        assertThat(testStatement.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingStatement() throws Exception {
        int databaseSizeBeforeUpdate = statementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatementMockMvc.perform(put("/api/statements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statement)))
            .andExpect(status().isBadRequest());

        // Validate the Statement in the database
        List<Statement> statementList = statementRepository.findAll();
        assertThat(statementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatement() throws Exception {
        // Initialize the database
        statementRepository.saveAndFlush(statement);

        int databaseSizeBeforeDelete = statementRepository.findAll().size();

        // Delete the statement
        restStatementMockMvc.perform(delete("/api/statements/{id}", statement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Statement> statementList = statementRepository.findAll();
        assertThat(statementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
