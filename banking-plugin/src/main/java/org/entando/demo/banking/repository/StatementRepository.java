package org.entando.demo.banking.repository;

import org.entando.demo.banking.domain.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Statement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatementRepository extends JpaRepository<Statement, Long>, JpaSpecificationExecutor<Statement> {

}
