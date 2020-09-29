package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.domain.Checkingtransaction;
import org.entando.demo.banking.repository.CheckingtransactionRepository;
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
 * REST controller for managing {@link org.entando.demo.banking.domain.Checkingtransaction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CheckingtransactionResource {

    private final Logger log = LoggerFactory.getLogger(CheckingtransactionResource.class);

    private static final String ENTITY_NAME = "bankingCheckingtransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckingtransactionRepository checkingtransactionRepository;

    public CheckingtransactionResource(CheckingtransactionRepository checkingtransactionRepository) {
        this.checkingtransactionRepository = checkingtransactionRepository;
    }

    /**
     * {@code POST  /checkingtransactions} : Create a new checkingtransaction.
     *
     * @param checkingtransaction the checkingtransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkingtransaction, or with status {@code 400 (Bad Request)} if the checkingtransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/checkingtransactions")
    public ResponseEntity<Checkingtransaction> createCheckingtransaction(@RequestBody Checkingtransaction checkingtransaction) throws URISyntaxException {
        log.debug("REST request to save Checkingtransaction : {}", checkingtransaction);
        if (checkingtransaction.getId() != null) {
            throw new BadRequestAlertException("A new checkingtransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Checkingtransaction result = checkingtransactionRepository.save(checkingtransaction);
        return ResponseEntity.created(new URI("/api/checkingtransactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
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
    public ResponseEntity<Checkingtransaction> updateCheckingtransaction(@RequestBody Checkingtransaction checkingtransaction) throws URISyntaxException {
        log.debug("REST request to update Checkingtransaction : {}", checkingtransaction);
        if (checkingtransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Checkingtransaction result = checkingtransactionRepository.save(checkingtransaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, checkingtransaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /checkingtransactions} : get all the checkingtransactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkingtransactions in body.
     */
    @GetMapping("/checkingtransactions")
    public ResponseEntity<List<Checkingtransaction>> getAllCheckingtransactions(Pageable pageable) {
        log.debug("REST request to get a page of Checkingtransactions");
        Page<Checkingtransaction> page = checkingtransactionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /checkingtransactions/:id} : get the "id" checkingtransaction.
     *
     * @param id the id of the checkingtransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkingtransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/checkingtransactions/{id}")
    public ResponseEntity<Checkingtransaction> getCheckingtransaction(@PathVariable Long id) {
        log.debug("REST request to get Checkingtransaction : {}", id);
        Optional<Checkingtransaction> checkingtransaction = checkingtransactionRepository.findById(id);
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

        checkingtransactionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
