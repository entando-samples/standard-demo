package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class CreditCardUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditCardUser.class);
        CreditCardUser creditCardUser1 = new CreditCardUser();
        creditCardUser1.setId(1L);
        CreditCardUser creditCardUser2 = new CreditCardUser();
        creditCardUser2.setId(creditCardUser1.getId());
        assertThat(creditCardUser1).isEqualTo(creditCardUser2);
        creditCardUser2.setId(2L);
        assertThat(creditCardUser1).isNotEqualTo(creditCardUser2);
        creditCardUser1.setId(null);
        assertThat(creditCardUser1).isNotEqualTo(creditCardUser2);
    }
}
