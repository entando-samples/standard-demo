package org.entando.demo.banking.service.impl;

import java.util.Optional;
import org.entando.demo.banking.domain.Checking;
import org.entando.demo.banking.repository.CheckingRepository;
import org.entando.demo.banking.service.CheckingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Checking}.
 */
@Service
@Transactional
public class CheckingServiceImpl implements CheckingService {

    private final Logger log = LoggerFactory.getLogger(CheckingServiceImpl.class);

    private final CheckingRepository checkingRepository;

    public CheckingServiceImpl(CheckingRepository checkingRepository) {
        this.checkingRepository = checkingRepository;
    }

    /**
     * Save a checking.
     *
     * @param checking the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Checking save(Checking checking) {
        log.debug("Request to save Checking : {}", checking);
        return checkingRepository.save(checking);
    }

    /**
     * Get all the checkings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Checking> findAll(Pageable pageable) {
        log.debug("Request to get all Checkings");
        return checkingRepository.findAll(pageable);
    }


    /**
     * Get one checking by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Checking> findOne(Long id) {
        log.debug("Request to get Checking : {}", id);
        return checkingRepository.findById(id);
    }

    /**
     * Delete the checking by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Checking : {}", id);
        checkingRepository.deleteById(id);
    }

    /**
     * Get one checking by user ID.
     *
     * @param userID the user ID as fk of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Checking> findOneWithUserID(String userID) {
        log.debug("Request to get Checking with userID: {}", userID);
        return checkingRepository.findByUserID(userID);
    }
}
