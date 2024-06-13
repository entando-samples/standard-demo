package org.entando.demo.banking.service.creditCardTransaction.impl;

import java.util.Optional;
import org.entando.demo.banking.domain.CreditcardTransaction;
import org.entando.demo.banking.repository.CreditcardTransactionRepository;
import org.entando.demo.banking.service.creditCardTransaction.CreditcardTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreditcardTransaction}.
 */
@Service
@Transactional
public class CreditcardTransactionServiceImpl implements CreditcardTransactionService {

    private final Logger log = LoggerFactory.getLogger(CreditcardTransactionServiceImpl.class);

    private final CreditcardTransactionRepository creditcardTransactionRepository;

    public CreditcardTransactionServiceImpl(CreditcardTransactionRepository creditcardTransactionRepository) {
        this.creditcardTransactionRepository = creditcardTransactionRepository;
    }

    /**
     * Save a creditcardtransaction.
     *
     * @param creditcardtransaction the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CreditcardTransaction save(CreditcardTransaction creditcardtransaction) {
        log.debug("Request to save Creditcardtransaction : {}", creditcardtransaction);
        return creditcardTransactionRepository.save(creditcardtransaction);
    }

    /**
     * Get all the creditcardtransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CreditcardTransaction> findAll(Pageable pageable) {
        log.debug("Request to get all Creditcardtransactions");
        return creditcardTransactionRepository.findAll(pageable);
    }


    /**
     * Get one creditcardtransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CreditcardTransaction> findOne(Long id) {
        log.debug("Request to get Creditcardtransaction : {}", id);
        return creditcardTransactionRepository.findById(id);
    }

    /**
     * Delete the creditcardtransaction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Creditcardtransaction : {}", id);
        creditcardTransactionRepository.deleteById(id);
    }
}
