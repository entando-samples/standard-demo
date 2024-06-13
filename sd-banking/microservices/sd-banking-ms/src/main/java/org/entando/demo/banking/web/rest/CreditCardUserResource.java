package org.entando.demo.banking.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.entando.demo.banking.domain.CreditCardUser;
import org.entando.demo.banking.domain.CreditCardUserConsts;
import org.entando.demo.banking.repository.CreditCardUserRepository;
import org.entando.demo.banking.service.CreditCardUserHelper;
import org.entando.demo.banking.service.CreditCardUserQueryService;
import org.entando.demo.banking.service.CreditCardUserService;
import org.entando.demo.banking.service.dto.CreditCardUserCriteria;
import org.entando.demo.banking.service.dto.CreditCardUserPost;
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
 * REST controller for managing {@link org.entando.demo.banking.domain.CreditCardUser}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CreditCardUserResource {

    private final Logger log = LoggerFactory.getLogger(CreditCardUserResource.class);

    private static final String ENTITY_NAME = "bankingCreditCardUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditCardUserService creditCardUserService;

    private final CreditCardUserQueryService creditCardUserQueryService;

    private final CreditCardUserRepository creditCardUserRepository;

    public CreditCardUserResource(CreditCardUserService creditCardUserService, CreditCardUserQueryService creditCardUserQueryService, CreditCardUserRepository creditCardUserRepository) {
        this.creditCardUserService = creditCardUserService;
        this.creditCardUserQueryService = creditCardUserQueryService;
        this.creditCardUserRepository = creditCardUserRepository;
    }

    /**
     * {@code POST  /credit-card-users} : Create a new creditCardUser.
     *
     * @param creditCardUser the creditCardUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditCardUser, or with status {@code 400 (Bad Request)} if the creditCardUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/credit-card-users")
    public ResponseEntity<CreditCardUser> createCreditCardUser(@RequestBody CreditCardUser creditCardUser) throws URISyntaxException {
        log.debug("REST request to save CreditCardUser : {}", creditCardUser);
        if (creditCardUser.getId() != null) {
            throw new BadRequestAlertException("A new creditCardUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreditCardUser result = creditCardUserRepository.save(creditCardUser);
        return ResponseEntity.created(new URI("/api/credit-card-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /credit-card-users/defaults} : Create a new creditCardUser.
     *
     * @param creditCardUser the creditCardUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditCardUser, or with status {@code 400 (Bad Request)} if the creditCardUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/credit-card-users/defaults")
    public ResponseEntity<CreditCardUser> createCreditCardUserWithDefaults(@Valid @RequestBody CreditCardUserPost creditCardUser) throws URISyntaxException {
        log.debug("REST request to save CreditCardUserPost : {}", creditCardUser);
        /* Setting default values for CreditCardUserPost.createdAt - CreditCardUserPost.status */
        LocalDate creationTime = LocalDate.now();
        creditCardUser.setCreatedAt(creationTime);
        creditCardUser.setStatus(CreditCardUserConsts.PENDING_APPROVAL_STATUS);
        /*                                                                                     */
        CreditCardUser result = creditCardUserService.save(CreditCardUserHelper.convert(creditCardUser));
        return ResponseEntity.created(new URI("/api/credit-card-users/defaults" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /credit-card-users} : Updates an existing creditCardUser.
     *
     * @param creditCardUser the creditCardUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditCardUser,
     * or with status {@code 400 (Bad Request)} if the creditCardUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditCardUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/credit-card-users")
    public ResponseEntity<CreditCardUser> updateCreditCardUser(@RequestBody CreditCardUser creditCardUser) throws URISyntaxException {
        log.debug("REST request to update CreditCardUser : {}", creditCardUser);
        if (creditCardUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CreditCardUser result = creditCardUserRepository.save(creditCardUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, creditCardUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /credit-card-users} : get all the creditCardUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditCardUsers in body.
     */
    @GetMapping("/credit-card-users")
    public ResponseEntity<List<CreditCardUser>> getAllCreditCardUsers(Pageable pageable) {
        log.debug("REST request to get a page of CreditCardUsers");
        Page<CreditCardUser> page = creditCardUserRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /credit-card-users/count} : count all the creditCardUsers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/credit-card-users/count")
    public ResponseEntity<Long> countCreditCardUsers(CreditCardUserCriteria criteria) {
        log.debug("REST request to count CreditCardUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(creditCardUserQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /credit-card-users/:id} : get the "id" creditCardUser.
     *
     * @param id the id of the creditCardUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditCardUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/credit-card-users/{id}")
    public ResponseEntity<CreditCardUser> getCreditCardUser(@PathVariable Long id) {
        log.debug("REST request to get CreditCardUser : {}", id);
        Optional<CreditCardUser> creditCardUser = creditCardUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(creditCardUser);
    }

    /**
     * {@code DELETE  /credit-card-users/:id} : delete the "id" creditCardUser.
     *
     * @param id the id of the creditCardUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/credit-card-users/{id}")
    public ResponseEntity<Void> deleteCreditCardUser(@PathVariable Long id) {
        log.debug("REST request to delete CreditCardUser : {}", id);

        creditCardUserRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
