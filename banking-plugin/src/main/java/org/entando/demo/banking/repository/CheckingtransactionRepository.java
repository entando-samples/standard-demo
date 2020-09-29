package org.entando.demo.banking.repository;

import org.entando.demo.banking.domain.Checkingtransaction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Checkingtransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CheckingtransactionRepository extends JpaRepository<Checkingtransaction, Long> {
}
