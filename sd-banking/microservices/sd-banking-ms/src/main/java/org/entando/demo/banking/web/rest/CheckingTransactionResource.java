package org.entando.demo.banking.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.entando.demo.banking.domain.CheckingTransaction;
import org.entando.demo.banking.service.checkingTransaction.dto.CheckingTransactionCriteria;
import org.entando.demo.banking.service.checkingTransaction.CheckingTransactionQueryService;
import org.entando.demo.banking.service.checkingTransaction.CheckingTransactionService;
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
 * REST controller for managing {@link CheckingTransaction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CheckingTransactionResource {

    private final Logger log = LoggerFactory.getLogger(CheckingTransactionResource.class);

    private static final String ENTITY_NAME = "checkingtransactionCheckingtransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckingTransactionService checkingtransactionService;

    private final CheckingTransactionQueryService checkingtransactionQueryService;

    public CheckingTransactionResource(CheckingTransactionService checkingtransactionService, CheckingTransactionQueryService checkingtransactionQueryService) {
        this.checkingtransactionService = checkingtransactionService;
        this.checkingtransactionQueryService = checkingtransactionQueryService;
    }

    /**
     * {@code POST  /checkingtransactions} : Create a new checkingtransaction.
     *
     * @param checkingtransaction the checkingtransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkingtransaction, or with status {@code 400 (Bad Request)} if the checkingtransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/checkingtransactions")
    public ResponseEntity<CheckingTransaction> createCheckingtransaction(@RequestBody CheckingTransaction checkingtransaction) throws URISyntaxException {
        log.debug("REST request to save Checkingtransaction : {}", checkingtransaction);
        if (checkingtransaction.getId() != null) {
            throw new BadRequestAlertException("A new checkingtransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CheckingTransaction result = checkingtransactionService.save(checkingtransaction);
        return ResponseEntity.created(new URI("/api/checkingtransactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /checkingtransactions} : Updates an existing checkingtransaction.
     *
     * @param checkingtransaction the checkingtransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkingtransaction,
     * or with status {@code 400 (Bad Request)} if the checkingtransaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checkingtransaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/checkingtransactions")
    public ResponseEntity<CheckingTransaction> updateCheckingtransaction(@RequestBody CheckingTransaction checkingtransaction) throws URISyntaxException {
        log.debug("REST request to update Checkingtransaction : {}", checkingtransaction);
        if (checkingtransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CheckingTransaction result = checkingtransactionService.save(checkingtransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checkingtransaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /checkingtransactions} : get all the checkingtransactions.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkingtransactions in body.
     */
    @GetMapping("/checkingtransactions")
    public ResponseEntity<List<CheckingTransaction>> getAllCheckingtransactions(CheckingTransactionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Checkingtransactions by criteria: {}", criteria);
        Page<CheckingTransaction> page = checkingtransactionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /checkingtransactions/count} : count all the checkingtransactions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/checkingtransactions/count")
    public ResponseEntity<Long> countCheckingtransactions(CheckingTransactionCriteria criteria) {
        log.debug("REST request to count Checkingtransactions by criteria: {}", criteria);
        return ResponseEntity.ok().body(checkingtransactionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /checkingtransactions/:id} : get the "id" checkingtransaction.
     *
     * @param id the id of the checkingtransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkingtransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/checkingtransactions/{id}")
    public ResponseEntity<CheckingTransaction> getCheckingtransaction(@PathVariable Long id) {
        log.debug("REST request to get Checkingtransaction : {}", id);
        Optional<CheckingTransaction> checkingtransaction = checkingtransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checkingtransaction);
    }

    /**
     * {@code DELETE  /checkingtransactions/:id} : delete the "id" checkingtransaction.
     *
     * @param id the id of the checkingtransaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/checkingtransactions/{id}")
    public ResponseEntity<Void> deleteCheckingtransaction(@PathVariable Long id) {
        log.debug("REST request to delete Checkingtransaction : {}", id);
        checkingtransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
