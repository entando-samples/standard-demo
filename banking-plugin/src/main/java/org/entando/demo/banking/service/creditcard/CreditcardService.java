package org.entando.demo.banking.service.creditcard;

import java.util.Optional;
import org.entando.demo.banking.domain.Creditcard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Creditcard}.
 */
public interface CreditcardService {

    /**
     * Save a creditcard.
     *
     * @param creditcard the entity to save.
     * @return the persisted entity.
     */
    Creditcard save(Creditcard creditcard);

    /**
     * Get all the creditcards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Creditcard> findAll(Pageable pageable);


    /**
     * Get the "id" creditcard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Creditcard> findOne(Long id);

    /**
     * Delete the "id" creditcard.
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
    Optional<Creditcard> findOneWithUserID(String userID);

}
