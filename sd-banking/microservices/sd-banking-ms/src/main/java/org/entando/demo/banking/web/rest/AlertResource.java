package org.entando.demo.banking.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.entando.demo.banking.domain.Alert;
import org.entando.demo.banking.service.alert.AlertQueryService;
import org.entando.demo.banking.service.alert.AlertService;
import org.entando.demo.banking.service.alert.dto.AlertCriteria;
import org.entando.demo.banking.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link org.entando.demo.banking.domain.Alert}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AlertResource {

    private final Logger log = LoggerFactory.getLogger(AlertResource.class);

    private static final String ENTITY_NAME = "seedsdashboardAlert";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlertService alertService;

    private final AlertQueryService alertQueryService;

    public AlertResource(AlertService alertService, AlertQueryService alertQueryService) {
        this.alertService = alertService;
        this.alertQueryService = alertQueryService;
    }

    /**
     * {@code POST  /alerts} : Create a new alert.
     *
     * @param alert the alert to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alert, or with status {@code 400 (Bad Request)} if the alert has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alerts")
    public ResponseEntity<Alert> createAlert(@Valid @RequestBody Alert alert) throws URISyntaxException {
        log.debug("REST request to save Alert : {}", alert);
        if (alert.getId() != null) {
            throw new BadRequestAlertException("A new alert cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Alert result = alertService.save(alert);
        return ResponseEntity.created(new URI("/api/alerts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alerts} : Updates an existing alert.
     *
     * @param alert the alert to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alert,
     * or with status {@code 400 (Bad Request)} if the alert is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alert couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alerts")
    public ResponseEntity<Alert> updateAlert(@Valid @RequestBody Alert alert) throws URISyntaxException {
        log.debug("REST request to update Alert : {}", alert);
        if (alert.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Alert result = alertService.save(alert);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alert.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /alerts} : get all the alerts.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alerts in body.
     */
    @GetMapping("/alerts")
    public ResponseEntity<List<Alert>> getAllAlerts(AlertCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Alerts by criteria: {}", criteria);
        Page<Alert> page = alertQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alerts/count} : count all the alerts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/alerts/count")
    public ResponseEntity<Long> countAlerts(AlertCriteria criteria) {
        log.debug("REST request to count Alerts by criteria: {}", criteria);
        return ResponseEntity.ok().body(alertQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /alerts/:id} : get the "id" alert.
     *
     * @param id the id of the alert to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alert, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alerts/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable Long id) {
        log.debug("REST request to get Alert : {}", id);
        Optional<Alert> alert = alertService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alert);
    }

    /**
     * {@code DELETE  /alerts/:id} : delete the "id" alert.
     *
     * @param id the id of the alert to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alerts/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        log.debug("REST request to delete Alert : {}", id);
        alertService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
