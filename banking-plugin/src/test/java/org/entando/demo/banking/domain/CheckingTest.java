package org.entando.demo.banking.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.entando.demo.banking.web.rest.TestUtil;

public class CheckingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Checking.class);
        Checking checking1 = new Checking();
        checking1.setId(1L);
        Checking checking2 = new Checking();
        checking2.setId(checking1.getId());
        assertThat(checking1).isEqualTo(checking2);
        checking2.setId(2L);
        assertThat(checking1).isNotEqualTo(checking2);
        checking1.setId(null);
        assertThat(checking1).isNotEqualTo(checking2);
    }
}
