package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class CreditcardtransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Creditcardtransaction.class);
        Creditcardtransaction creditcardtransaction1 = new Creditcardtransaction();
        creditcardtransaction1.setId(1L);
        Creditcardtransaction creditcardtransaction2 = new Creditcardtransaction();
        creditcardtransaction2.setId(creditcardtransaction1.getId());
        assertThat(creditcardtransaction1).isEqualTo(creditcardtransaction2);
        creditcardtransaction2.setId(2L);
        assertThat(creditcardtransaction1).isNotEqualTo(creditcardtransaction2);
        creditcardtransaction1.setId(null);
        assertThat(creditcardtransaction1).isNotEqualTo(creditcardtransaction2);
    }
}
