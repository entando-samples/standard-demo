package org.entando.demo.banking.repository;

import org.entando.demo.banking.domain.CreditcardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Creditcardtransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditcardTransactionRepository extends JpaRepository<CreditcardTransaction, Long>, JpaSpecificationExecutor<CreditcardTransaction> {

}
