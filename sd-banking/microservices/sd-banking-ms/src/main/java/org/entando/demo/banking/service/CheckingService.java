package org.entando.demo.banking.service;

import java.util.Optional;
import org.entando.demo.banking.domain.Checking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Checking}.
 */
public interface CheckingService {

    /**
     * Save a checking.
     *
     * @param checking the entity to save.
     * @return the persisted entity.
     */
    Checking save(Checking checking);

    /**
     * Get all the checkings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Checking> findAll(Pageable pageable);


    /**
     * Get the "id" checking.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Checking> findOne(Long id);

    /**
     * Delete the "id" checking.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get one cheking by user ID.
     *
     * @param userID the user ID as fk of the entity.
     * @return the entity.
     */
    Optional<Checking> findOneWithUserID(String userID);

}
