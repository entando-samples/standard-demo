package org.entando.demo.banking.service.creditcard;

import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.Creditcard;
import org.entando.demo.banking.domain.Creditcard_;
import org.entando.demo.banking.repository.CreditcardRepository;
import org.entando.demo.banking.service.creditcard.dto.CreditcardCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link Creditcard} entities in the database.
 * The main input is a {@link CreditcardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Creditcard} or a {@link Page} of {@link Creditcard} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CreditcardQueryService extends QueryService<Creditcard> {

    private final Logger log = LoggerFactory.getLogger(CreditcardQueryService.class);

    private final CreditcardRepository creditcardRepository;

    public CreditcardQueryService(CreditcardRepository creditcardRepository) {
        this.creditcardRepository = creditcardRepository;
    }

    /**
     * Return a {@link List} of {@link Creditcard} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Creditcard> findByCriteria(CreditcardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Creditcard> specification = createSpecification(criteria);
        return creditcardRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Creditcard} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Creditcard> findByCriteria(CreditcardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Creditcard> specification = createSpecification(criteria);
        return creditcardRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CreditcardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Creditcard> specification = createSpecification(criteria);
        return creditcardRepository.count(specification);
    }

    /**
     * Function to convert {@link CreditcardCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Creditcard> createSpecification(CreditcardCriteria criteria) {
        Specification<Creditcard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Creditcard_.id));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), Creditcard_.accountNumber));
            }
            if (criteria.getBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBalance(), Creditcard_.balance));
            }
            if (criteria.getRewardPoints() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardPoints(), Creditcard_.rewardPoints));
            }
            if (criteria.getUserID() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserID(), Creditcard_.userID));
            }
        }
        return specification;
    }
}
