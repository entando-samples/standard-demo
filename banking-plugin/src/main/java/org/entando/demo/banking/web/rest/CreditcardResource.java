package org.entando.demo.banking.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.entando.demo.banking.domain.Creditcard;
import org.entando.demo.banking.service.creditcard.CreditcardQueryService;
import org.entando.demo.banking.service.creditcard.CreditcardService;
import org.entando.demo.banking.service.creditcard.dto.CreditcardCriteria;
import org.entando.demo.banking.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * REST controller for managing {@link org.entando.demo.banking.domain.Creditcard}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CreditcardResource {

    private final Logger log = LoggerFactory.getLogger(CreditcardResource.class);

    private static final String ENTITY_NAME = "creditcardCreditcard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditcardService creditcardService;

    private final CreditcardQueryService creditcardQueryService;

    public CreditcardResource(CreditcardService creditcardService, CreditcardQueryService creditcardQueryService) {
        this.creditcardService = creditcardService;
        this.creditcardQueryService = creditcardQueryService;
    }

    /**
     * {@code POST  /creditcards} : Create a new creditcard.
     *
     * @param creditcard the creditcard to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditcard, or with status {@code 400 (Bad Request)} if the creditcard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/creditcards")
    public ResponseEntity<Creditcard> createCreditcard(@RequestBody Creditcard creditcard) throws URISyntaxException {
        log.debug("REST request to save Creditcard : {}", creditcard);
        if (creditcard.getId() != null) {
            throw new BadRequestAlertException("A new creditcard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Creditcard result = creditcardService.save(creditcard);
        return ResponseEntity.created(new URI("/api/creditcards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /creditcards} : Updates an existing creditcard.
     *
     * @param creditcard the creditcard to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditcard,
     * or with status {@code 400 (Bad Request)} if the creditcard is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditcard couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/creditcards")
    public ResponseEntity<Creditcard> updateCreditcard(@RequestBody Creditcard creditcard) throws URISyntaxException {
        log.debug("REST request to update Creditcard : {}", creditcard);
        if (creditcard.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Creditcard result = creditcardService.save(creditcard);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creditcard.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /creditcards} : get all the creditcards.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditcards in body.
     */
    @GetMapping("/creditcards")
    public ResponseEntity<List<Creditcard>> getAllCreditcards(CreditcardCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Creditcards by criteria: {}", criteria);
        Page<Creditcard> page = creditcardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /creditcards/count} : count all the creditcards.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/creditcards/count")
    public ResponseEntity<Long> countCreditcards(CreditcardCriteria criteria) {
        log.debug("REST request to count Creditcards by criteria: {}", criteria);
        return ResponseEntity.ok().body(creditcardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /creditcards/:id} : get the "id" creditcard.
     *
     * @param id the id of the creditcard to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditcard, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/creditcards/{id}")
    public ResponseEntity<Creditcard> getCreditcard(@PathVariable Long id) {
        log.debug("REST request to get Creditcard : {}", id);
        Optional<Creditcard> creditcard = creditcardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditcard);
    }

    /**
     * {@code DELETE  /creditcards/:id} : delete the "id" creditcard.
     *
     * @param id the id of the creditcard to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/creditcards/{id}")
    public ResponseEntity<Void> deleteCreditcard(@PathVariable Long id) {
        log.debug("REST request to delete Creditcard : {}", id);
        creditcardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /creditcard/user/:userID} : get the t by user ID.
     *
     * @param userID the id of the user who has the t to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the t, or with status {@code 404 (Not Found)}.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/creditcards/user/{userID}")
    public ResponseEntity<Creditcard> getCreditcardByUserID(@PathVariable String userID) {
        log.debug("REST request to get Creditcard by user ID: {}", userID);
        Optional<Creditcard> creditcard = creditcardService.findOneWithUserID(userID);
        return ResponseUtil.wrapOrNotFound(creditcard);
    }
}
