package org.entando.demo.banking.service.savingsTransaction.impl;

import java.util.Optional;
import org.entando.demo.banking.domain.SavingsTransaction;
import org.entando.demo.banking.repository.SavingsTransactionRepository;
import org.entando.demo.banking.service.savingsTransaction.SavingsTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SavingsTransaction}.
 */
@Service
@Transactional
public class SavingsTransactionServiceImpl implements SavingsTransactionService {

    private final Logger log = LoggerFactory.getLogger(SavingsTransactionServiceImpl.class);

    private final SavingsTransactionRepository savingsTransactionRepository;

    public SavingsTransactionServiceImpl(SavingsTransactionRepository savingsTransactionRepository) {
        this.savingsTransactionRepository = savingsTransactionRepository;
    }

    /**
     * Save a SavingsTransaction.
     *
     * @param SavingsTransaction the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SavingsTransaction save(SavingsTransaction SavingsTransaction) {
        log.debug("Request to save SavingsTransaction : {}", SavingsTransaction);
        return savingsTransactionRepository.save(SavingsTransaction);
    }

    /**
     * Get all the SavingsTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SavingsTransaction> findAll(Pageable pageable) {
        log.debug("Request to get all SavingsTransactions");
        return savingsTransactionRepository.findAll(pageable);
    }


    /**
     * Get one SavingsTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SavingsTransaction> findOne(Long id) {
        log.debug("Request to get SavingsTransaction : {}", id);
        return savingsTransactionRepository.findById(id);
    }

    /**
     * Delete the SavingsTransaction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SavingsTransaction : {}", id);
        savingsTransactionRepository.deleteById(id);
    }
}
