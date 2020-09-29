package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.domain.SavingsTransaction;
import org.entando.demo.banking.repository.SavingsTransactionRepository;
import org.entando.demo.banking.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.entando.demo.banking.domain.SavingsTransaction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SavingsTransactionResource {

    private final Logger log = LoggerFactory.getLogger(SavingsTransactionResource.class);

    private static final String ENTITY_NAME = "bankingSavingsTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SavingsTransactionRepository savingsTransactionRepository;

    public SavingsTransactionResource(SavingsTransactionRepository savingsTransactionRepository) {
        this.savingsTransactionRepository = savingsTransactionRepository;
    }

    /**
     * {@code POST  /savings-transactions} : Create a new savingsTransaction.
     *
     * @param savingsTransaction the savingsTransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new savingsTransaction, or with status {@code 400 (Bad Request)} if the savingsTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/savings-transactions")
    public ResponseEntity<SavingsTransaction> createSavingsTransaction(@RequestBody SavingsTransaction savingsTransaction) throws URISyntaxException {
        log.debug("REST request to save SavingsTransaction : {}", savingsTransaction);
        if (savingsTransaction.getId() != null) {
            throw new BadRequestAlertException("A new savingsTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SavingsTransaction result = savingsTransactionRepository.save(savingsTransaction);
        return ResponseEntity.created(new URI("/api/savings-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /savings-transactions} : Updates an existing savingsTransaction.
     *
     * @param savingsTransaction the savingsTransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated savingsTransaction,
     * or with status {@code 400 (Bad Request)} if the savingsTransaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the savingsTransaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/savings-transactions")
    public ResponseEntity<SavingsTransaction> updateSavingsTransaction(@RequestBody SavingsTransaction savingsTransaction) throws URISyntaxException {
        log.debug("REST request to update SavingsTransaction : {}", savingsTransaction);
        if (savingsTransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SavingsTransaction result = savingsTransactionRepository.save(savingsTransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, savingsTransaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /savings-transactions} : get all the savingsTransactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of savingsTransactions in body.
     */
    @GetMapping("/savings-transactions")
    public ResponseEntity<List<SavingsTransaction>> getAllSavingsTransactions(Pageable pageable) {
        log.debug("REST request to get a page of SavingsTransactions");
        Page<SavingsTransaction> page = savingsTransactionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /savings-transactions/:id} : get the "id" savingsTransaction.
     *
     * @param id the id of the savingsTransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the savingsTransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/savings-transactions/{id}")
    public ResponseEntity<SavingsTransaction> getSavingsTransaction(@PathVariable Long id) {
        log.debug("REST request to get SavingsTransaction : {}", id);
        Optional<SavingsTransaction> savingsTransaction = savingsTransactionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(savingsTransaction);
    }

    /**
     * {@code DELETE  /savings-transactions/:id} : delete the "id" savingsTransaction.
     *
     * @param id the id of the savingsTransaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/savings-transactions/{id}")
    public ResponseEntity<Void> deleteSavingsTransaction(@PathVariable Long id) {
        log.debug("REST request to delete SavingsTransaction : {}", id);

        savingsTransactionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
