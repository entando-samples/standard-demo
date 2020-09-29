package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.domain.Creditcard;
import org.entando.demo.banking.repository.CreditcardRepository;
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
 * REST controller for managing {@link org.entando.demo.banking.domain.Creditcard}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CreditcardResource {

    private final Logger log = LoggerFactory.getLogger(CreditcardResource.class);

    private static final String ENTITY_NAME = "bankingCreditcard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditcardRepository creditcardRepository;

    public CreditcardResource(CreditcardRepository creditcardRepository) {
        this.creditcardRepository = creditcardRepository;
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
        Creditcard result = creditcardRepository.save(creditcard);
        return ResponseEntity.created(new URI("/api/creditcards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
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
        Creditcard result = creditcardRepository.save(creditcard);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, creditcard.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /creditcards} : get all the creditcards.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditcards in body.
     */
    @GetMapping("/creditcards")
    public ResponseEntity<List<Creditcard>> getAllCreditcards(Pageable pageable) {
        log.debug("REST request to get a page of Creditcards");
        Page<Creditcard> page = creditcardRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
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
        Optional<Creditcard> creditcard = creditcardRepository.findById(id);
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

        creditcardRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
