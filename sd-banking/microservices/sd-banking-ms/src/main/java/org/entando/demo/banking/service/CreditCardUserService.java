package org.entando.demo.banking.service;

import java.util.Optional;
import org.entando.demo.banking.domain.CreditCardUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link CreditCardUser}.
 */
public interface CreditCardUserService {

    /**
     * Save a creditCardUser.
     *
     * @param creditCardUser the entity to save.
     * @return the persisted entity.
     */
    CreditCardUser save(CreditCardUser creditCardUser);

    /**
     * Get all the creditCardUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CreditCardUser> findAll(Pageable pageable);


    /**
     * Get the "id" creditCardUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CreditCardUser> findOne(Long id);

    /**
     * Delete the "id" creditCardUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
