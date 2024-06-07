package org.entando.demo.banking.repository;

import java.util.Optional;
import org.entando.demo.banking.domain.Creditcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Creditcard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditcardRepository extends JpaRepository<Creditcard, Long>, JpaSpecificationExecutor<Creditcard> {
    Optional<Creditcard> findByUserID(String userID);
}
