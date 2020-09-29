package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.domain.Creditcardtransaction;
import org.entando.demo.banking.repository.CreditcardtransactionRepository;
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
 * REST controller for managing {@link org.entando.demo.banking.domain.Creditcardtransaction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CreditcardtransactionResource {

    private final Logger log = LoggerFactory.getLogger(CreditcardtransactionResource.class);

    private static final String ENTITY_NAME = "bankingCreditcardtransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditcardtransactionRepository creditcardtransactionRepository;

    public CreditcardtransactionResource(CreditcardtransactionRepository creditcardtransactionRepository) {
        this.creditcardtransactionRepository = creditcardtransactionRepository;
    }

    /**
     * {@code POST  /creditcardtransactions} : Create a new creditcardtransaction.
     *
     * @param creditcardtransaction the creditcardtransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditcardtransaction, or with status {@code 400 (Bad Request)} if the creditcardtransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/creditcardtransactions")
    public ResponseEntity<Creditcardtransaction> createCreditcardtransaction(@RequestBody Creditcardtransaction creditcardtransaction) throws URISyntaxException {
        log.debug("REST request to save Creditcardtransaction : {}", creditcardtransaction);
        if (creditcardtransaction.getId() != null) {
            throw new BadRequestAlertException("A new creditcardtransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Creditcardtransaction result = creditcardtransactionRepository.save(creditcardtransaction);
        return ResponseEntity.created(new URI("/api/creditcardtransactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
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
    public ResponseEntity<Creditcardtransaction> updateCreditcardtransaction(@RequestBody Creditcardtransaction creditcardtransaction) throws URISyntaxException {
        log.debug("REST request to update Creditcardtransaction : {}", creditcardtransaction);
        if (creditcardtransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Creditcardtransaction result = creditcardtransactionRepository.save(creditcardtransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, creditcardtransaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /creditcardtransactions} : get all the creditcardtransactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditcardtransactions in body.
     */
    @GetMapping("/creditcardtransactions")
    public ResponseEntity<List<Creditcardtransaction>> getAllCreditcardtransactions(Pageable pageable) {
        log.debug("REST request to get a page of Creditcardtransactions");
        Page<Creditcardtransaction> page = creditcardtransactionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /creditcardtransactions/:id} : get the "id" creditcardtransaction.
     *
     * @param id the id of the creditcardtransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditcardtransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/creditcardtransactions/{id}")
    public ResponseEntity<Creditcardtransaction> getCreditcardtransaction(@PathVariable Long id) {
        log.debug("REST request to get Creditcardtransaction : {}", id);
        Optional<Creditcardtransaction> creditcardtransaction = creditcardtransactionRepository.findById(id);
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

        creditcardtransactionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
