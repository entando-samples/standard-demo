package org.entando.demo.banking.service.creditCardTransaction;

import java.util.Optional;
import org.entando.demo.banking.domain.CreditcardTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link CreditcardTransaction}.
 */
public interface CreditcardTransactionService {

    /**
     * Save a creditcardtransaction.
     *
     * @param creditcardtransaction the entity to save.
     * @return the persisted entity.
     */
    CreditcardTransaction save(CreditcardTransaction creditcardtransaction);

    /**
     * Get all the creditcardtransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CreditcardTransaction> findAll(Pageable pageable);


    /**
     * Get the "id" creditcardtransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CreditcardTransaction> findOne(Long id);

    /**
     * Delete the "id" creditcardtransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
