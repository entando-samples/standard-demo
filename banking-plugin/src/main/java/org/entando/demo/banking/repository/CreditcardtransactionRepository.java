package org.entando.demo.banking.repository;

import org.entando.demo.banking.domain.Creditcardtransaction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Creditcardtransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditcardtransactionRepository extends JpaRepository<Creditcardtransaction, Long> {
}
