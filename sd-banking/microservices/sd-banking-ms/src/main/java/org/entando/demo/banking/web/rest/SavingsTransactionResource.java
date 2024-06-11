package org.entando.demo.banking.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.entando.demo.banking.domain.SavingsTransaction;
import org.entando.demo.banking.service.savingsTransaction.SavingsTransactionQueryService;
import org.entando.demo.banking.service.savingsTransaction.SavingsTransactionService;
import org.entando.demo.banking.service.savingsTransaction.dto.SavingsTransactionCriteria;
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
 * REST controller for managing {@link org.entando.demo.banking.domain.SavingsTransaction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SavingsTransactionResource {

    private final Logger log = LoggerFactory.getLogger(SavingsTransactionResource.class);

    private static final String ENTITY_NAME = "SavingsTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SavingsTransactionService savingsTransactionService;

    private final SavingsTransactionQueryService savingsTransactionQueryService;

    public SavingsTransactionResource(SavingsTransactionService savingsTransactionService, SavingsTransactionQueryService savingsTransactionQueryService) {
        this.savingsTransactionService = savingsTransactionService;
        this.savingsTransactionQueryService = savingsTransactionQueryService;
    }

    /**
     * {@code POST  /savingstransactions} : Create a new SavingsTransaction.
     *
     * @param SavingsTransaction the SavingsTransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new SavingsTransaction, or with status {@code 400 (Bad Request)} if the SavingsTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/savingstransactions")
    public ResponseEntity<SavingsTransaction> createSavingsTransaction(@RequestBody SavingsTransaction SavingsTransaction) throws URISyntaxException {
        log.debug("REST request to save SavingsTransaction : {}", SavingsTransaction);
        if (SavingsTransaction.getId() != null) {
            throw new BadRequestAlertException("A new SavingsTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SavingsTransaction result = savingsTransactionService.save(SavingsTransaction);
        return ResponseEntity.created(new URI("/api/savingstransactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /savingstransactions} : Updates an existing SavingsTransaction.
     *
     * @param SavingsTransaction the SavingsTransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated SavingsTransaction,
     * or with status {@code 400 (Bad Request)} if the SavingsTransaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the SavingsTransaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/savingstransactions")
    public ResponseEntity<SavingsTransaction> updateSavingsTransaction(@RequestBody SavingsTransaction SavingsTransaction) throws URISyntaxException {
        log.debug("REST request to update SavingsTransaction : {}", SavingsTransaction);
        if (SavingsTransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SavingsTransaction result = savingsTransactionService.save(SavingsTransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, SavingsTransaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /savingstransactions} : get all the SavingsTransactions.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of SavingsTransactions in body.
     */
    @GetMapping("/savingstransactions")
    public ResponseEntity<List<SavingsTransaction>> getAllSavingsTransactions(SavingsTransactionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SavingsTransactions by criteria: {}", criteria);
        Page<SavingsTransaction> page = savingsTransactionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /savingstransactions/count} : count all the SavingsTransactions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/savingstransactions/count")
    public ResponseEntity<Long> countSavingsTransactions(SavingsTransactionCriteria criteria) {
        log.debug("REST request to count SavingsTransactions by criteria: {}", criteria);
        return ResponseEntity.ok().body(savingsTransactionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /savingstransactions/:id} : get the "id" SavingsTransaction.
     *
     * @param id the id of the SavingsTransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the SavingsTransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/savingstransactions/{id}")
    public ResponseEntity<SavingsTransaction> getSavingsTransaction(@PathVariable Long id) {
        log.debug("REST request to get SavingsTransaction : {}", id);
        Optional<SavingsTransaction> SavingsTransaction = savingsTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(SavingsTransaction);
    }

    /**
     * {@code DELETE  /savingstransactions/:id} : delete the "id" SavingsTransaction.
     *
     * @param id the id of the SavingsTransaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/savingstransactions/{id}")
    public ResponseEntity<Void> deleteSavingsTransaction(@PathVariable Long id) {
        log.debug("REST request to delete SavingsTransaction : {}", id);
        savingsTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
