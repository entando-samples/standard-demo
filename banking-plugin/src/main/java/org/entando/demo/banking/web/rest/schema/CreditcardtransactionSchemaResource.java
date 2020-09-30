

package org.entando.demo.banking.web.rest.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator;
import org.entando.demo.banking.domain.CreditcardTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/schemas")
public class CreditcardtransactionSchemaResource {

    private final Logger log = LoggerFactory.getLogger(CreditcardtransactionSchemaResource.class);

    @GetMapping(value = "/creditcardtransaction", produces = "application/schema+json")
    public String getJsonSchemaFormConfiguration() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(objectMapper);

        JsonNode jsonSchema = jsonSchemaGenerator.generateJsonSchema(CreditcardTransaction.class);

        return objectMapper.writeValueAsString(jsonSchema);
    }
}
