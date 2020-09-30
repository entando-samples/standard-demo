package org.entando.demo.banking.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.entando.demo.banking.domain.CreditcardTransaction;
import org.entando.demo.banking.service.creditCardTransaction.CreditcardTransactionQueryService;
import org.entando.demo.banking.service.creditCardTransaction.CreditcardTransactionService;
import org.entando.demo.banking.service.creditCardTransaction.dto.CreditcardTransactionCriteria;
import org.entando.demo.banking.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
 * REST controller for managing {@link CreditcardTransaction}.
 */
@RestController
@RequestMapping("/api")
public class CreditcardTransactionResource {

    private final Logger log = LoggerFactory.getLogger(CreditcardTransactionResource.class);

    private static final String ENTITY_NAME = "creditcardtransactionCreditcardtransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditcardTransactionService creditcardTransactionService;

    private final CreditcardTransactionQueryService creditcardtransactionQueryService;

    public CreditcardTransactionResource(CreditcardTransactionService creditcardTransactionService, CreditcardTransactionQueryService creditcardtransactionQueryService) {
        this.creditcardTransactionService = creditcardTransactionService;
        this.creditcardtransactionQueryService = creditcardtransactionQueryService;
    }

    /**
     * {@code POST  /creditcardtransactions} : Create a new creditcardtransaction.
     *
     * @param creditcardtransaction the creditcardtransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditcardtransaction, or with status {@code 400 (Bad Request)} if the creditcardtransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/creditcardtransactions")
    public ResponseEntity<CreditcardTransaction> createCreditcardtransaction(@RequestBody CreditcardTransaction creditcardtransaction) throws URISyntaxException {
        log.debug("REST request to save Creditcardtransaction : {}", creditcardtransaction);
        if (creditcardtransaction.getId() != null) {
            throw new BadRequestAlertException("A new creditcardtransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreditcardTransaction result = creditcardTransactionService.save(creditcardtransaction);
        return ResponseEntity.created(new URI("/api/creditcardtransactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /creditcardtransactions} : Updates an existing creditcardtransaction.
     *
     * @param creditcardtransaction the creditcardtransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditcardtransaction,
     * or with status {@code 400 (Bad Request)} if the creditcardtransaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditcardtransaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/creditcardtransactions")
    public ResponseEntity<CreditcardTransaction> updateCreditcardtransaction(@RequestBody CreditcardTransaction creditcardtransaction) throws URISyntaxException {
        log.debug("REST request to update Creditcardtransaction : {}", creditcardtransaction);
        if (creditcardtransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CreditcardTransaction result = creditcardTransactionService.save(creditcardtransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creditcardtransaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /creditcardtransactions} : get all the creditcardtransactions.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditcardtransactions in body.
     */
    @GetMapping("/creditcardtransactions")
    public ResponseEntity<List<CreditcardTransaction>> getAllCreditcardtransactions(
        CreditcardTransactionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Creditcardtransactions by criteria: {}", criteria);
        Page<CreditcardTransaction> page = creditcardtransactionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /creditcardtransactions/count} : count all the creditcardtransactions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/creditcardtransactions/count")
    public ResponseEntity<Long> countCreditcardtransactions(CreditcardTransactionCriteria criteria) {
        log.debug("REST request to count Creditcardtransactions by criteria: {}", criteria);
        return ResponseEntity.ok().body(creditcardtransactionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /creditcardtransactions/:id} : get the "id" creditcardtransaction.
     *
     * @param id the id of the creditcardtransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditcardtransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/creditcardtransactions/{id}")
    public ResponseEntity<CreditcardTransaction> getCreditcardtransaction(@PathVariable Long id) {
        log.debug("REST request to get Creditcardtransaction : {}", id);
        Optional<CreditcardTransaction> creditcardtransaction = creditcardTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditcardtransaction);
    }

    /**
     * {@code DELETE  /creditcardtransactions/:id} : delete the "id" creditcardtransaction.
     *
     * @param id the id of the creditcardtransaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/creditcardtransactions/{id}")
    public ResponseEntity<Void> deleteCreditcardtransaction(@PathVariable Long id) {
        log.debug("REST request to delete Creditcardtransaction : {}", id);
        creditcardTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
