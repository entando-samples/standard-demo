package org.entando.demo.banking.service.checkingTransaction.impl;

import java.util.Optional;
import org.entando.demo.banking.domain.CheckingTransaction;
import org.entando.demo.banking.repository.CheckingTransactionRepository;
import org.entando.demo.banking.service.checkingTransaction.CheckingTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CheckingTransaction}.
 */
@Service
@Transactional
public class CheckingTransactionServiceImpl implements CheckingTransactionService {

    private final Logger log = LoggerFactory.getLogger(CheckingTransactionServiceImpl.class);

    private final CheckingTransactionRepository checkingtransactionRepository;

    public CheckingTransactionServiceImpl(CheckingTransactionRepository checkingtransactionRepository) {
        this.checkingtransactionRepository = checkingtransactionRepository;
    }

    /**
     * Save a checkingtransaction.
     *
     * @param checkingtransaction the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CheckingTransaction save(CheckingTransaction checkingtransaction) {
        log.debug("Request to save Checkingtransaction : {}", checkingtransaction);
        return checkingtransactionRepository.save(checkingtransaction);
    }

    /**
     * Get all the checkingtransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CheckingTransaction> findAll(Pageable pageable) {
        log.debug("Request to get all Checkingtransactions");
        return checkingtransactionRepository.findAll(pageable);
    }


    /**
     * Get one checkingtransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CheckingTransaction> findOne(Long id) {
        log.debug("Request to get Checkingtransaction : {}", id);
        return checkingtransactionRepository.findById(id);
    }

    /**
     * Delete the checkingtransaction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Checkingtransaction : {}", id);
        checkingtransactionRepository.deleteById(id);
    }
}
