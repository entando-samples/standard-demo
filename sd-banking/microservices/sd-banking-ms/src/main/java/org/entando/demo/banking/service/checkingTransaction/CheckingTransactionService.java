package org.entando.demo.banking.service.checkingTransaction;

import java.util.Optional;
import org.entando.demo.banking.domain.CheckingTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link CheckingTransaction}.
 */
public interface CheckingTransactionService {

    /**
     * Save a checkingtransaction.
     *
     * @param checkingtransaction the entity to save.
     * @return the persisted entity.
     */
    CheckingTransaction save(CheckingTransaction checkingtransaction);

    /**
     * Get all the checkingtransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CheckingTransaction> findAll(Pageable pageable);


    /**
     * Get the "id" checkingtransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CheckingTransaction> findOne(Long id);

    /**
     * Delete the "id" checkingtransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
