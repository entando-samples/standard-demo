package org.entando.demo.banking.service.creditCardTransaction;

import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.CreditcardTransaction;
import org.entando.demo.banking.domain.CreditcardTransaction_;
import org.entando.demo.banking.repository.CreditcardTransactionRepository;
import org.entando.demo.banking.service.creditCardTransaction.dto.CreditcardTransactionCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link CreditcardTransaction} entities in the database.
 * The main input is a {@link CreditcardTransactionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CreditcardTransaction} or a {@link Page} of {@link CreditcardTransaction} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CreditcardTransactionQueryService extends QueryService<CreditcardTransaction> {

    private final Logger log = LoggerFactory.getLogger(CreditcardTransactionQueryService.class);

    private final CreditcardTransactionRepository creditcardtransactionRepository;

    public CreditcardTransactionQueryService(CreditcardTransactionRepository creditcardtransactionRepository) {
        this.creditcardtransactionRepository = creditcardtransactionRepository;
    }

    /**
     * Return a {@link List} of {@link CreditcardTransaction} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CreditcardTransaction> findByCriteria(CreditcardTransactionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CreditcardTransaction> specification = createSpecification(criteria);
        return creditcardtransactionRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CreditcardTransaction} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CreditcardTransaction> findByCriteria(CreditcardTransactionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CreditcardTransaction> specification = createSpecification(criteria);
        return creditcardtransactionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CreditcardTransactionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CreditcardTransaction> specification = createSpecification(criteria);
        return creditcardtransactionRepository.count(specification);
    }

    /**
     * Function to convert {@link CreditcardTransactionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CreditcardTransaction> createSpecification(CreditcardTransactionCriteria criteria) {
        Specification<CreditcardTransaction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CreditcardTransaction_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), CreditcardTransaction_.date));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CreditcardTransaction_.description));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), CreditcardTransaction_.amount));
            }
            if (criteria.getBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBalance(), CreditcardTransaction_.balance));
            }
            if (criteria.getAccountID() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAccountID(), CreditcardTransaction_.accountID));
            }
        }
        return specification;
    }
}
