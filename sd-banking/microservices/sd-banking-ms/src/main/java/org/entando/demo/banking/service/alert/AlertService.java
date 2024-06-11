package org.entando.demo.banking.service.alert;

import java.util.Optional;
import org.entando.demo.banking.domain.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Alert}.
 */
public interface AlertService {

    /**
     * Save a alert.
     *
     * @param alert the entity to save.
     * @return the persisted entity.
     */
    Alert save(Alert alert);

    /**
     * Get all the alerts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Alert> findAll(Pageable pageable);


    /**
     * Get the "id" alert.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Alert> findOne(Long id);

    /**
     * Delete the "id" alert.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
