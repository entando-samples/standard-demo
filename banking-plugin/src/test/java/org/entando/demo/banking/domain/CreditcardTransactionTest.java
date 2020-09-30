package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class CreditcardTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditcardTransaction.class);
        CreditcardTransaction creditcardTransaction1 = new CreditcardTransaction();
        creditcardTransaction1.setId(1L);
        CreditcardTransaction creditcardTransaction2 = new CreditcardTransaction();
        creditcardTransaction2.setId(creditcardTransaction1.getId());
        assertThat(creditcardTransaction1).isEqualTo(creditcardTransaction2);
        creditcardTransaction2.setId(2L);
        assertThat(creditcardTransaction1).isNotEqualTo(creditcardTransaction2);
        creditcardTransaction1.setId(null);
        assertThat(creditcardTransaction1).isNotEqualTo(creditcardTransaction2);
    }
}
