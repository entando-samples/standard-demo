package org.entando.demo.banking.service.alert;

import org.entando.demo.banking.domain.Statement;
import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.Statement_;
import org.entando.demo.banking.repository.StatementRepository;
import org.entando.demo.banking.service.alert.dto.StatementCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link Statement} entities in the database.
 * The main input is a {@link StatementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Statement} or a {@link Page} of {@link Statement} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class StatementQueryService extends QueryService<Statement> {

    private final Logger log = LoggerFactory.getLogger(StatementQueryService.class);

    private final StatementRepository statementRepository;

    public StatementQueryService(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    /**
     * Return a {@link List} of {@link Statement} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Statement> findByCriteria(StatementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Statement> specification = createSpecification(criteria);
        return statementRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Statement} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Statement> findByCriteria(StatementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Statement> specification = createSpecification(criteria);
        return statementRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StatementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Statement> specification = createSpecification(criteria);
        return statementRepository.count(specification);
    }

    /**
     * Function to convert {@link StatementCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Statement> createSpecification(StatementCriteria criteria) {
        Specification<Statement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Statement_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Statement_.description));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Statement_.createdAt));
            }
            if (criteria.getRead() != null) {
                specification = specification.and(buildSpecification(criteria.getRead(), Statement_.read));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserId(), Statement_.userId));
            }
        }
        return specification;
    }
}
