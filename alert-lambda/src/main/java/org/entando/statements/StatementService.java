package org.entando.statements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.entando.statements.model.Statement;

@ApplicationScoped
public class StatementService {

    private static List<String> descriptions = Arrays
            .asList("May 2020", "June 2020", "July 2020", "August 2020", "September 2020");

    public List<Statement> getStatements(){

        List<Statement> statements = new ArrayList<>();
        for(int i=0;i<5;i++) {
            Statement statement = new Statement();
            statement.setId((long)i+10);
            statement.setDescription(descriptions.get(i));
            statement.setCreatedAt(LocalDate.now());
            statement.setUserId("admin");
            statements.add(statement);
        }

        return statements;
    }

}
