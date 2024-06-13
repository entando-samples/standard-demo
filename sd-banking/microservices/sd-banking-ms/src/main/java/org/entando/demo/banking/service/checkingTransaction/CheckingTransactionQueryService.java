package org.entando.demo.banking.service.checkingTransaction;

import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.CheckingTransaction;
import org.entando.demo.banking.domain.CheckingTransaction_;
import org.entando.demo.banking.repository.CheckingTransactionRepository;
import org.entando.demo.banking.service.checkingTransaction.dto.CheckingTransactionCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link CheckingTransaction} entities in the database.
 * The main input is a {@link CheckingTransactionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CheckingTransaction} or a {@link Page} of {@link CheckingTransaction} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CheckingTransactionQueryService extends QueryService<CheckingTransaction> {

    private final Logger log = LoggerFactory.getLogger(CheckingTransactionQueryService.class);

    private final CheckingTransactionRepository checkingtransactionRepository;

    public CheckingTransactionQueryService(CheckingTransactionRepository checkingtransactionRepository) {
        this.checkingtransactionRepository = checkingtransactionRepository;
    }

    /**
     * Return a {@link List} of {@link CheckingTransaction} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CheckingTransaction> findByCriteria(CheckingTransactionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CheckingTransaction> specification = createSpecification(criteria);
        return checkingtransactionRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link CheckingTransaction} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CheckingTransaction> findByCriteria(CheckingTransactionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CheckingTransaction> specification = createSpecification(criteria);
        return checkingtransactionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CheckingTransactionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CheckingTransaction> specification = createSpecification(criteria);
        return checkingtransactionRepository.count(specification);
    }

    /**
     * Function to convert {@link CheckingTransactionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CheckingTransaction> createSpecification(CheckingTransactionCriteria criteria) {
        Specification<CheckingTransaction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CheckingTransaction_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), CheckingTransaction_.date));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CheckingTransaction_.description));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), CheckingTransaction_.amount));
            }
            if (criteria.getBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBalance(), CheckingTransaction_.balance));
            }
            if (criteria.getAccountID() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAccountID(), CheckingTransaction_.accountID));
            }
        }
        return specification;
    }
}
