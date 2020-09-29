package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.domain.Savings;
import org.entando.demo.banking.repository.SavingsRepository;
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
 * REST controller for managing {@link org.entando.demo.banking.domain.Savings}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SavingsResource {

    private final Logger log = LoggerFactory.getLogger(SavingsResource.class);

    private static final String ENTITY_NAME = "bankingSavings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SavingsRepository savingsRepository;

    public SavingsResource(SavingsRepository savingsRepository) {
        this.savingsRepository = savingsRepository;
    }

    /**
     * {@code POST  /savings} : Create a new savings.
     *
     * @param savings the savings to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new savings, or with status {@code 400 (Bad Request)} if the savings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/savings")
    public ResponseEntity<Savings> createSavings(@RequestBody Savings savings) throws URISyntaxException {
        log.debug("REST request to save Savings : {}", savings);
        if (savings.getId() != null) {
            throw new BadRequestAlertException("A new savings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Savings result = savingsRepository.save(savings);
        return ResponseEntity.created(new URI("/api/savings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /savings} : Updates an existing savings.
     *
     * @param savings the savings to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated savings,
     * or with status {@code 400 (Bad Request)} if the savings is not valid,
     * or with status {@code 500 (Internal Server Error)} if the savings couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/savings")
    public ResponseEntity<Savings> updateSavings(@RequestBody Savings savings) throws URISyntaxException {
        log.debug("REST request to update Savings : {}", savings);
        if (savings.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Savings result = savingsRepository.save(savings);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, savings.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /savings} : get all the savings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of savings in body.
     */
    @GetMapping("/savings")
    public ResponseEntity<List<Savings>> getAllSavings(Pageable pageable) {
        log.debug("REST request to get a page of Savings");
        Page<Savings> page = savingsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /savings/:id} : get the "id" savings.
     *
     * @param id the id of the savings to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the savings, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/savings/{id}")
    public ResponseEntity<Savings> getSavings(@PathVariable Long id) {
        log.debug("REST request to get Savings : {}", id);
        Optional<Savings> savings = savingsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(savings);
    }

    /**
     * {@code DELETE  /savings/:id} : delete the "id" savings.
     *
     * @param id the id of the savings to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/savings/{id}")
    public ResponseEntity<Void> deleteSavings(@PathVariable Long id) {
        log.debug("REST request to delete Savings : {}", id);

        savingsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
