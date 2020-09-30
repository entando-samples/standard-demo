package org.entando.demo.banking.repository;

import java.util.Optional;
import org.entando.demo.banking.domain.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Savings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long>, JpaSpecificationExecutor<Savings> {
    Optional<Savings> findByUserID(String userID);
}
