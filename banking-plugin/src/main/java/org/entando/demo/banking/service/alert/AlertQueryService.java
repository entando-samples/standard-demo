package org.entando.demo.banking.service.alert;

import io.github.jhipster.service.QueryService;
import java.util.List;
import org.entando.demo.banking.domain.Alert;
import org.entando.demo.banking.domain.Alert_;
import org.entando.demo.banking.repository.AlertRepository;
import org.entando.demo.banking.service.alert.dto.AlertCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link Alert} entities in the database.
 * The main input is a {@link AlertCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Alert} or a {@link Page} of {@link Alert} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AlertQueryService extends QueryService<Alert> {

    private final Logger log = LoggerFactory.getLogger(AlertQueryService.class);

    private final AlertRepository alertRepository;

    public AlertQueryService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    /**
     * Return a {@link List} of {@link Alert} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Alert> findByCriteria(AlertCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Alert> specification = createSpecification(criteria);
        return alertRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Alert} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Alert> findByCriteria(AlertCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Alert> specification = createSpecification(criteria);
        return alertRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AlertCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Alert> specification = createSpecification(criteria);
        return alertRepository.count(specification);
    }

    /**
     * Function to convert {@link AlertCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Alert> createSpecification(AlertCriteria criteria) {
        Specification<Alert> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Alert_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Alert_.description));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Alert_.createdAt));
            }
            if (criteria.getRead() != null) {
                specification = specification.and(buildSpecification(criteria.getRead(), Alert_.read));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserId(), Alert_.userId));
            }
        }
        return specification;
    }
}
