package org.ekwateur.model;

import org.ekwateur.core.exception.InvalidClientRefException;
import org.ekwateur.core.model.ClientRef;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ClientRefTest {

    @Test
    void shouldParseValueWithValidFormat() {

        // Given
        String validClientRefStr = "EKW62053914";

        // When
        ClientRef clientRef = ClientRef.parse(validClientRefStr);

        // Then
        assertThat(clientRef)
                .extracting(ClientRef::getValue)
                .isEqualTo(validClientRefStr);
    }

    @Test
    void shouldThrowExceptionForInvalidClientRefFormat() {

        // Given
        String invalidClientRefStr = "invalid client ref";

        // Then
        assertThatExceptionOfType(InvalidClientRefException.class)
                // When
                .isThrownBy(() -> ClientRef.parse(invalidClientRefStr));
    }



}
