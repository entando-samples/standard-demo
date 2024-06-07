

package org.entando.demo.banking.web.rest.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator;
import org.entando.demo.banking.domain.SavingsTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/schemas")
public class SavingsTransactionSchemaResource {

    private final Logger log = LoggerFactory.getLogger(SavingsTransactionSchemaResource.class);

    @GetMapping(value = "/savingstransaction", produces = "application/schema+json")
    public String getJsonSchemaFormConfiguration() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(objectMapper);

        JsonNode jsonSchema = jsonSchemaGenerator.generateJsonSchema(SavingsTransaction.class);

        return objectMapper.writeValueAsString(jsonSchema);
    }
}