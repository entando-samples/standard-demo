package org.entando.demo.banking.service.savingsTransaction;

import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.SavingsTransaction;
import org.entando.demo.banking.domain.SavingsTransaction_;
import org.entando.demo.banking.repository.SavingsTransactionRepository;
import org.entando.demo.banking.service.savingsTransaction.dto.SavingsTransactionCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link SavingsTransaction} entities in the database.
 * The main input is a {@link SavingsTransactionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SavingsTransaction} or a {@link Page} of {@link SavingsTransaction} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SavingsTransactionQueryService extends QueryService<SavingsTransaction> {

    private final Logger log = LoggerFactory.getLogger(SavingsTransactionQueryService.class);

    private final SavingsTransactionRepository savingsTransactionRepository;

    public SavingsTransactionQueryService(SavingsTransactionRepository SavingsTransactionRepository) {
        this.savingsTransactionRepository = SavingsTransactionRepository;
    }

    /**
     * Return a {@link List} of {@link SavingsTransaction} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SavingsTransaction> findByCriteria(SavingsTransactionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SavingsTransaction> specification = createSpecification(criteria);
        return savingsTransactionRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link SavingsTransaction} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SavingsTransaction> findByCriteria(SavingsTransactionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SavingsTransaction> specification = createSpecification(criteria);
        return savingsTransactionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SavingsTransactionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SavingsTransaction> specification = createSpecification(criteria);
        return savingsTransactionRepository.count(specification);
    }

    /**
     * Function to convert {@link SavingsTransactionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SavingsTransaction> createSpecification(SavingsTransactionCriteria criteria) {
        Specification<SavingsTransaction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SavingsTransaction_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), SavingsTransaction_.date));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SavingsTransaction_.description));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), SavingsTransaction_.amount));
            }
            if (criteria.getBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBalance(), SavingsTransaction_.balance));
            }
            if (criteria.getAccountID() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAccountID(), SavingsTransaction_.accountID));
            }
        }
        return specification;
    }
}
