package org.entando.demo.banking.service.creditcard.impl;

import java.util.Optional;
import org.entando.demo.banking.domain.Creditcard;
import org.entando.demo.banking.repository.CreditcardRepository;
import org.entando.demo.banking.service.creditcard.CreditcardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Creditcard}.
 */
@Service
@Transactional
public class CreditcardServiceImpl implements CreditcardService {

    private final Logger log = LoggerFactory.getLogger(CreditcardServiceImpl.class);

    private final CreditcardRepository creditcardRepository;

    public CreditcardServiceImpl(CreditcardRepository creditcardRepository) {
        this.creditcardRepository = creditcardRepository;
    }

    /**
     * Save a creditcard.
     *
     * @param creditcard the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Creditcard save(Creditcard creditcard) {
        log.debug("Request to save Creditcard : {}", creditcard);
        return creditcardRepository.save(creditcard);
    }

    /**
     * Get all the creditcards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Creditcard> findAll(Pageable pageable) {
        log.debug("Request to get all Creditcards");
        return creditcardRepository.findAll(pageable);
    }


    /**
     * Get one creditcard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Creditcard> findOne(Long id) {
        log.debug("Request to get Creditcard : {}", id);
        return creditcardRepository.findById(id);
    }

    /**
     * Delete the creditcard by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Creditcard : {}", id);
        creditcardRepository.deleteById(id);
    }

    /**
     * Get one checking by user ID.
     *
     * @param userID the user ID as fk of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Creditcard> findOneWithUserID(String userID) {
        log.debug("Request to get Creditcard with userID: {}", userID);
        return creditcardRepository.findByUserID(userID);
    }

}
