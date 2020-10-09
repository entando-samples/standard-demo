package org.entando.statements;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.entando.statements.model.Statement;

@Path("/statements")
public class StatementResource {

    @Inject
    StatementService statementService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Statement> getStatements() {
        return statementService.getStatements();
    }
}
