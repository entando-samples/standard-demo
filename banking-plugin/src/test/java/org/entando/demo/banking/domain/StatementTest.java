package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class StatementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Statement.class);
        Statement statement1 = new Statement();
        statement1.setId(1L);
        Statement statement2 = new Statement();
        statement2.setId(statement1.getId());
        assertThat(statement1).isEqualTo(statement2);
        statement2.setId(2L);
        assertThat(statement1).isNotEqualTo(statement2);
        statement1.setId(null);
        assertThat(statement1).isNotEqualTo(statement2);
    }
}
