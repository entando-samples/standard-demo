package org.entando.demo.banking.service.alert.impl;

import java.util.Optional;
import org.entando.demo.banking.domain.Statement;
import org.entando.demo.banking.repository.StatementRepository;
import org.entando.demo.banking.service.alert.StatementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Statement}.
 */
@Service
@Transactional
public class StatementServiceImpl implements StatementService {

    private final Logger log = LoggerFactory.getLogger(StatementServiceImpl.class);

    private final StatementRepository statementRepository;

    public StatementServiceImpl(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    /**
     * Save a statement.
     *
     * @param statement the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Statement save(Statement statement) {
        log.debug("Request to save Statement : {}", statement);
        return statementRepository.save(statement);
    }

    /**
     * Get all the statements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Statement> findAll(Pageable pageable) {
        log.debug("Request to get all Statements");
        return statementRepository.findAll(pageable);
    }


    /**
     * Get one statement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Statement> findOne(Long id) {
        log.debug("Request to get Statement : {}", id);
        return statementRepository.findById(id);
    }

    /**
     * Delete the statement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Statement : {}", id);
        statementRepository.deleteById(id);
    }
}
