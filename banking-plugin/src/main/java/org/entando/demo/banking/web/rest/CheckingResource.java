package org.entando.demo.banking.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.entando.demo.banking.domain.Checking;
import org.entando.demo.banking.service.CheckingQueryService;
import org.entando.demo.banking.service.CheckingService;
import org.entando.demo.banking.service.dto.CheckingCriteria;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link org.entando.demo.banking.domain.Checking}.
 */
@RestController
//@RequestMapping("/api")
@Transactional
public class CheckingResource {

    private final Logger log = LoggerFactory.getLogger(CheckingResource.class);

    private static final String ENTITY_NAME = "checkingChecking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckingService checkingService;

    private final CheckingQueryService checkingQueryService;

    public CheckingResource(CheckingService checkingService, CheckingQueryService checkingQueryService) {
        this.checkingService = checkingService;
        this.checkingQueryService = checkingQueryService;
    }

    /**
     * {@code POST  /checkings} : Create a new checking.
     *
     * @param checking the checking to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checking, or with status {@code 400 (Bad Request)} if the checking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/add/checkings")
    public ResponseEntity<Checking> createChecking(@RequestBody Checking checking) throws URISyntaxException {
        log.debug("REST request to save Checking : {}", checking);
        if (checking.getId() != null) {
            throw new BadRequestAlertException("A new checking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Checking result = checkingService.save(checking);
        return ResponseEntity.created(new URI("/api/checkings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
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
    @PutMapping("/api/checkings")
    public ResponseEntity<Checking> updateChecking(@RequestBody Checking checking) throws URISyntaxException {
        log.debug("REST request to update Checking : {}", checking);
        if (checking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Checking result = checkingService.save(checking);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checking.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /checkings} : get all the checkings.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkings in body.
     */
    @GetMapping("/api/checkings")
    public ResponseEntity<List<Checking>> getAllCheckings(CheckingCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Checkings by criteria: {}", criteria);
        Page<Checking> page = checkingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /checkings/count} : count all the checkings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/api/checkings/count")
    public ResponseEntity<Long> countCheckings(CheckingCriteria criteria) {
        log.debug("REST request to count Checkings by criteria: {}", criteria);
        return ResponseEntity.ok().body(checkingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /checkings/:id} : get the "id" checking.
     *
     * @param id the id of the checking to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checking, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/api/checkings/{id}")
    public ResponseEntity<Checking> getChecking(@PathVariable Long id) {
        log.debug("REST request to get Checking : {}", id);
        Optional<Checking> checking = checkingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checking);
    }

    /**
     * {@code DELETE  /checkings/:id} : delete the "id" checking.
     *
     * @param id the id of the checking to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/api/checkings/{id}")
    public ResponseEntity<Void> deleteChecking(@PathVariable Long id) {
        log.debug("REST request to delete Checking : {}", id);
        checkingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /checking/user/:userID} : get the t by user ID.
     *
     * @param userID the id of the user who has the t to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the t, or with status {@code 404 (Not Found)}.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/checkings/user/{userID}")
    public ResponseEntity<Checking> getCheckingByUserID(@PathVariable String userID) {
        log.debug("REST request to get Checking by user ID: {}", userID);
        Optional<Checking> checking = checkingService.findOneWithUserID(userID);
        return ResponseUtil.wrapOrNotFound(checking);
    }
}
