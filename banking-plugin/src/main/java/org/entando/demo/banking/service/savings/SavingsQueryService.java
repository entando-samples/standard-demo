package org.entando.demo.banking.service.savings;

import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.Savings;
import org.entando.demo.banking.domain.Savings_;
import org.entando.demo.banking.repository.SavingsRepository;
import org.entando.demo.banking.service.savings.dto.SavingsCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link Savings} entities in the database.
 * The main input is a {@link SavingsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Savings} or a {@link Page} of {@link Savings} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SavingsQueryService extends QueryService<Savings> {

    private final Logger log = LoggerFactory.getLogger(SavingsQueryService.class);

    private final SavingsRepository savingsRepository;

    public SavingsQueryService(SavingsRepository savingsRepository) {
        this.savingsRepository = savingsRepository;
    }

    /**
     * Return a {@link List} of {@link Savings} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Savings> findByCriteria(SavingsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Savings> specification = createSpecification(criteria);
        return savingsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Savings} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Savings> findByCriteria(SavingsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Savings> specification = createSpecification(criteria);
        return savingsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SavingsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Savings> specification = createSpecification(criteria);
        return savingsRepository.count(specification);
    }

    /**
     * Function to convert {@link SavingsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Savings> createSpecification(SavingsCriteria criteria) {
        Specification<Savings> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Savings_.id));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), Savings_.accountNumber));
            }
            if (criteria.getBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBalance(), Savings_.balance));
            }
            if (criteria.getUserID() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserID(), Savings_.userID));
            }
        }
        return specification;
    }
}
