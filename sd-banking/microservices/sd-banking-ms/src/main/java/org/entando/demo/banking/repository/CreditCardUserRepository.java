package org.entando.demo.banking.repository;

import org.entando.demo.banking.domain.CreditCardUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CreditCardUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditCardUserRepository extends JpaRepository<CreditCardUser, Long>, JpaSpecificationExecutor<CreditCardUser> {
}
