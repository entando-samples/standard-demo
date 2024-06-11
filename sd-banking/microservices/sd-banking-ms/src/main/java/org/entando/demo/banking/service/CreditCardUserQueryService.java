package org.entando.demo.banking.service;

import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.CreditCardUser;
import org.entando.demo.banking.domain.CreditCardUser_;
import org.entando.demo.banking.repository.CreditCardUserRepository;
import org.entando.demo.banking.service.dto.CreditCardUserCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link CreditCardUser} entities in the database.
 * The main input is a {@link CreditCardUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CreditCardUser} or a {@link Page} of {@link CreditCardUser} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CreditCardUserQueryService extends QueryService<CreditCardUser> {

    private final Logger log = LoggerFactory.getLogger(CreditCardUserQueryService.class);

    private final CreditCardUserRepository creditCardUserRepository;

    public CreditCardUserQueryService(CreditCardUserRepository creditCardUserRepository) {
        this.creditCardUserRepository = creditCardUserRepository;
    }

    /**
     * Return a {@link List} of {@link CreditCardUser} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CreditCardUser> findByCriteria(CreditCardUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CreditCardUser> specification = createSpecification(criteria);
        return creditCardUserRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CreditCardUser} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CreditCardUser> findByCriteria(CreditCardUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CreditCardUser> specification = createSpecification(criteria);
        return creditCardUserRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CreditCardUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CreditCardUser> specification = createSpecification(criteria);
        return creditCardUserRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CreditCardUser> createSpecification(CreditCardUserCriteria criteria) {
        Specification<CreditCardUser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CreditCardUser_.id));
            }
            if (criteria.getFirstname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstname(), CreditCardUser_.firstname));
            }
            if (criteria.getLastname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastname(), CreditCardUser_.lastname));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), CreditCardUser_.phone));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), CreditCardUser_.email));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), CreditCardUser_.createdAt));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), CreditCardUser_.status));
            }
        }
        return specification;
    }
}
