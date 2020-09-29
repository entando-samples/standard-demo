package org.entando.demo.banking.repository;

import org.entando.demo.banking.domain.Savings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Savings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
