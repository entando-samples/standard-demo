package org.entando.demo.banking.repository;

import org.entando.demo.banking.domain.CheckingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Checkingtransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CheckingTransactionRepository extends JpaRepository<CheckingTransaction, Long>, JpaSpecificationExecutor<CheckingTransaction> {

}
