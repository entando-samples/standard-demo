package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class CreditcardTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Creditcard.class);
        Creditcard creditcard1 = new Creditcard();
        creditcard1.setId(1L);
        Creditcard creditcard2 = new Creditcard();
        creditcard2.setId(creditcard1.getId());
        assertThat(creditcard1).isEqualTo(creditcard2);
        creditcard2.setId(2L);
        assertThat(creditcard1).isNotEqualTo(creditcard2);
        creditcard1.setId(null);
        assertThat(creditcard1).isNotEqualTo(creditcard2);
    }
}
