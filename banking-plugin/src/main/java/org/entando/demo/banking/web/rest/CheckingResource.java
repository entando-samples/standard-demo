package org.entando.demo.banking.web.rest;

import org.entando.demo.banking.domain.Checking;
import org.entando.demo.banking.repository.CheckingRepository;
import org.entando.demo.banking.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.entando.demo.banking.domain.Checking}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CheckingResource {

    private final Logger log = LoggerFactory.getLogger(CheckingResource.class);

    private static final String ENTITY_NAME = "bankingChecking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckingRepository checkingRepository;

    public CheckingResource(CheckingRepository checkingRepository) {
        this.checkingRepository = checkingRepository;
    }

    /**
     * {@code POST  /checkings} : Create a new checking.
     *
     * @param checking the checking to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checking, or with status {@code 400 (Bad Request)} if the checking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/checkings")
    public ResponseEntity<Checking> createChecking(@RequestBody Checking checking) throws URISyntaxException {
        log.debug("REST request to save Checking : {}", checking);
        if (checking.getId() != null) {
            throw new BadRequestAlertException("A new checking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Checking result = checkingRepository.save(checking);
        return ResponseEntity.created(new URI("/api/checkings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /checkings} : Updates an existing checking.
     *
     * @param checking the checking to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checking,
     * or with status {@code 400 (Bad Request)} if the checking is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checking couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/checkings")
    public ResponseEntity<Checking> updateChecking(@RequestBody Checking checking) throws URISyntaxException {
        log.debug("REST request to update Checking : {}", checking);
        if (checking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Checking result = checkingRepository.save(checking);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, checking.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /checkings} : get all the checkings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkings in body.
     */
    @GetMapping("/checkings")
    public List<Checking> getAllCheckings() {
        log.debug("REST request to get all Checkings");
        return checkingRepository.findAll();
    }

    /**
     * {@code GET  /checkings/:id} : get the "id" checking.
     *
     * @param id the id of the checking to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checking, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/checkings/{id}")
    public ResponseEntity<Checking> getChecking(@PathVariable Long id) {
        log.debug("REST request to get Checking : {}", id);
        Optional<Checking> checking = checkingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(checking);
    }

    /**
     * {@code DELETE  /checkings/:id} : delete the "id" checking.
     *
     * @param id the id of the checking to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/checkings/{id}")
    public ResponseEntity<Void> deleteChecking(@PathVariable Long id) {
        log.debug("REST request to delete Checking : {}", id);

        checkingRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
