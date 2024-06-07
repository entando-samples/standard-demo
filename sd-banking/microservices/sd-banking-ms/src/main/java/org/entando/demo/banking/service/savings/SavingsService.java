package org.entando.demo.banking.service.savings;

import java.util.Optional;
import org.entando.demo.banking.domain.Savings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Savings}.
 */
public interface SavingsService {

    /**
     * Save a savings.
     *
     * @param savings the entity to save.
     * @return the persisted entity.
     */
    Savings save(Savings savings);

    /**
     * Get all the savings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Savings> findAll(Pageable pageable);


    /**
     * Get the "id" savings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Savings> findOne(Long id);

    /**
     * Delete the "id" savings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get one savings by user ID.
     *
     * @param userID the user ID as fk of the entity.
     * @return the entity.
     */
    Optional<Savings> findOneWithUserID(String userID);

}
