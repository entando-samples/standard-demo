package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class CheckingtransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Checkingtransaction.class);
        Checkingtransaction checkingtransaction1 = new Checkingtransaction();
        checkingtransaction1.setId(1L);
        Checkingtransaction checkingtransaction2 = new Checkingtransaction();
        checkingtransaction2.setId(checkingtransaction1.getId());
        assertThat(checkingtransaction1).isEqualTo(checkingtransaction2);
        checkingtransaction2.setId(2L);
        assertThat(checkingtransaction1).isNotEqualTo(checkingtransaction2);
        checkingtransaction1.setId(null);
        assertThat(checkingtransaction1).isNotEqualTo(checkingtransaction2);
    }
}
