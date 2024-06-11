package org.entando.demo.banking.service.alert;

import org.entando.demo.banking.domain.Statement;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Statement}.
 */
public interface StatementService {

    /**
     * Save a statement.
     *
     * @param statement the entity to save.
     * @return the persisted entity.
     */
    Statement save(Statement statement);

    /**
     * Get all the statements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Statement> findAll(Pageable pageable);


    /**
     * Get the "id" statement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Statement> findOne(Long id);

    /**
     * Delete the "id" statement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
