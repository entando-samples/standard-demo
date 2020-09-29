package org.entando.demo.banking.service;

import java.util.Optional;
import org.entando.demo.banking.domain.CreditCardUser;
import org.entando.demo.banking.repository.CreditCardUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreditCardUser}.
 */
@Service
@Transactional
public class CreditCardUserServiceImpl implements CreditCardUserService {

    private final Logger log = LoggerFactory.getLogger(CreditCardUserServiceImpl.class);

    private final CreditCardUserRepository creditCardUserRepository;

    public CreditCardUserServiceImpl(CreditCardUserRepository creditCardUserRepository) {
        this.creditCardUserRepository = creditCardUserRepository;
    }

    /**
     * Save a creditCardUser.
     *
     * @param creditCardUser the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CreditCardUser save(CreditCardUser creditCardUser) {
        log.debug("Request to save CreditCardUser : {}", creditCardUser);
        return creditCardUserRepository.save(creditCardUser);
    }

    /**
     * Get all the creditCardUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CreditCardUser> findAll(Pageable pageable) {
        log.debug("Request to get all CreditCardUsers");
        return creditCardUserRepository.findAll(pageable);
    }


    /**
     * Get one creditCardUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CreditCardUser> findOne(Long id) {
        log.debug("Request to get CreditCardUser : {}", id);
        return creditCardUserRepository.findById(id);
    }

    /**
     * Delete the creditCardUser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CreditCardUser : {}", id);
        creditCardUserRepository.deleteById(id);
    }
}
