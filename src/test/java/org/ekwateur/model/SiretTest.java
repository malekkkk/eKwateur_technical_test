package org.ekwateur.model;

import org.ekwateur.core.exception.InvalidSiretException;
import org.ekwateur.core.model.Siret;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SiretTest {

    @Test
    void shouldParseValueWithValidSiretFormat() {

        // Given
        String siretStr = "47203472983569";

        // When
        Siret siret = Siret.parse(siretStr);

        // Then
        assertThat(siret)
                .extracting(Siret::getValue)
                .isEqualTo(siretStr);
    }

    @Test
    void shouldThrowExceptionForInvalidSiretFormat() {

        // Given
        String invalidSiretStr = "invalid siret";

        // Then
        assertThatExceptionOfType(InvalidSiretException.class)
                // When
                .isThrownBy(() -> Siret.parse(invalidSiretStr));
    }

}
