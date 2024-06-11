package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class CheckingTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckingTransaction.class);
        CheckingTransaction checkingTransaction1 = new CheckingTransaction();
        checkingTransaction1.setId(1L);
        CheckingTransaction checkingTransaction2 = new CheckingTransaction();
        checkingTransaction2.setId(checkingTransaction1.getId());
        assertThat(checkingTransaction1).isEqualTo(checkingTransaction2);
        checkingTransaction2.setId(2L);
        assertThat(checkingTransaction1).isNotEqualTo(checkingTransaction2);
        checkingTransaction1.setId(null);
        assertThat(checkingTransaction1).isNotEqualTo(checkingTransaction2);
    }
}
