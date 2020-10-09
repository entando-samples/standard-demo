package org.entando.alerts;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.entando.alerts.model.Alert;

@Path("/alerts")
public class AlertResource {

    @Inject
    AlertService alertService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alert> getAlerts() {
        return alertService.getAlerts();
    }
}
