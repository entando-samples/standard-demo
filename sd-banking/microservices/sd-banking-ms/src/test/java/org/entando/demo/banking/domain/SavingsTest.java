package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class SavingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Savings.class);
        Savings savings1 = new Savings();
        savings1.setId(1L);
        Savings savings2 = new Savings();
        savings2.setId(savings1.getId());
        assertThat(savings1).isEqualTo(savings2);
        savings2.setId(2L);
        assertThat(savings1).isNotEqualTo(savings2);
        savings1.setId(null);
        assertThat(savings1).isNotEqualTo(savings2);
    }
}
