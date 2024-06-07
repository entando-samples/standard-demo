package org.entando.demo.banking.service.savingsTransaction;

import java.util.Optional;
import org.entando.demo.banking.domain.SavingsTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link SavingsTransaction}.
 */
public interface SavingsTransactionService {

    /**
     * Save a SavingsTransaction.
     *
     * @param SavingsTransaction the entity to save.
     * @return the persisted entity.
     */
    SavingsTransaction save(SavingsTransaction SavingsTransaction);

    /**
     * Get all the SavingsTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SavingsTransaction> findAll(Pageable pageable);


    /**
     * Get the "id" SavingsTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SavingsTransaction> findOne(Long id);

    /**
     * Delete the "id" SavingsTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
