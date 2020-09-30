package org.entando.demo.banking.service.savings.impl;

import java.util.Optional;
import org.entando.demo.banking.domain.Savings;
import org.entando.demo.banking.repository.SavingsRepository;
import org.entando.demo.banking.service.savings.SavingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Savings}.
 */
@Service
@Transactional
public class SavingsServiceImpl implements SavingsService {

    private final Logger log = LoggerFactory.getLogger(SavingsServiceImpl.class);

    private final SavingsRepository savingsRepository;

    public SavingsServiceImpl(SavingsRepository savingsRepository) {
        this.savingsRepository = savingsRepository;
    }

    /**
     * Save a savings.
     *
     * @param savings the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Savings save(Savings savings) {
        log.debug("Request to save Savings : {}", savings);
        return savingsRepository.save(savings);
    }

    /**
     * Get all the savings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Savings> findAll(Pageable pageable) {
        log.debug("Request to get all Savings");
        return savingsRepository.findAll(pageable);
    }


    /**
     * Get one savings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Savings> findOne(Long id) {
        log.debug("Request to get Savings : {}", id);
        return savingsRepository.findById(id);
    }

    /**
     * Delete the savings by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Savings : {}", id);
        savingsRepository.deleteById(id);
    }

    /**
     * Get one savings by user ID.
     *
     * @param userID the user ID as fk of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Savings> findOneWithUserID(String userID) {
        log.debug("Request to get Savings with userID: {}", userID);
        return savingsRepository.findByUserID(userID);
    }
}
