package org.entando.demo.banking.service;

import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.Checking;
import org.entando.demo.banking.domain.Checking_;
import org.entando.demo.banking.repository.CheckingRepository;
import org.entando.demo.banking.service.dto.CheckingCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link Checking} entities in the database.
 * The main input is a {@link CheckingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Checking} or a {@link Page} of {@link Checking} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CheckingQueryService extends QueryService<Checking> {

    private final Logger log = LoggerFactory.getLogger(CheckingQueryService.class);

    private final CheckingRepository checkingRepository;

    public CheckingQueryService(CheckingRepository checkingRepository) {
        this.checkingRepository = checkingRepository;
    }

    /**
     * Return a {@link List} of {@link Checking} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Checking> findByCriteria(CheckingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Checking> specification = createSpecification(criteria);
        return checkingRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Checking} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Checking> findByCriteria(CheckingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Checking> specification = createSpecification(criteria);
        return checkingRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CheckingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Checking> specification = createSpecification(criteria);
        return checkingRepository.count(specification);
    }

    /**
     * Function to convert {@link CheckingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Checking> createSpecification(CheckingCriteria criteria) {
        Specification<Checking> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Checking_.id));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), Checking_.accountNumber));
            }
            if (criteria.getBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBalance(), Checking_.balance));
            }
            if (criteria.getUserID() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserID(), Checking_.userID));
            }
        }
        return specification;
    }
}
