package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class SavingsTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SavingsTransaction.class);
        SavingsTransaction savingsTransaction1 = new SavingsTransaction();
        savingsTransaction1.setId(1L);
        SavingsTransaction savingsTransaction2 = new SavingsTransaction();
        savingsTransaction2.setId(savingsTransaction1.getId());
        assertThat(savingsTransaction1).isEqualTo(savingsTransaction2);
        savingsTransaction2.setId(2L);
        assertThat(savingsTransaction1).isNotEqualTo(savingsTransaction2);
        savingsTransaction1.setId(null);
        assertThat(savingsTransaction1).isNotEqualTo(savingsTransaction2);
    }
}
