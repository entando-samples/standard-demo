package org.entando.alerts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.entando.alerts.model.Alert;

@ApplicationScoped
public class AlertService {

    private static List<String> descriptions = Arrays.asList("Recurring Charge", "Over $100", "New Purchase Alert", "Over $200", "New Purchase Alert", "Low Balance", "Updated Contact Info" );

    public List<Alert> getAlerts(){

        List<Alert> alerts = new ArrayList<>();
        for(int i=0;i<6;i++) {
            Alert alert = new Alert();
            alert.setId((long)i+10);
            alert.setDescription(descriptions.get(i));
            alert.setCreatedAt(LocalDate.now());
            alert.setUserId("admin");
            alerts.add(alert);
        }

        return alerts;
    }
}
