package org.entando.demo.banking.repository;

import org.entando.demo.banking.domain.SavingsTransaction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SavingsTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SavingsTransactionRepository extends JpaRepository<SavingsTransaction, Long> {
}
