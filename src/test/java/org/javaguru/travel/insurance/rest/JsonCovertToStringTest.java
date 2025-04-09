package org.javaguru.travel.insurance.rest;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JsonCovertToStringTest {
    private JsonCovertToString jsonCovertToString = new JsonCovertToString();
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void makeRequestMock() {
        request = mock(TravelCalculatePremiumRequest.class);
    }

    @Test
    public void ShouldConvertFromJsonToString() {
        when(request.getPersonFirstName()).thenReturn("Ivan");
        when(request.getPersonLastName()).thenReturn("Petrov");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2024, 12, 27));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 01, 12));
        when(request.getSelectedRisks()).thenReturn(Arrays.asList("Risk1", "Risk2", "Risk3"));
        String str = jsonCovertToString.jsonCovertToString(request);
        String expectedJson = "{\"personFirstName\":\"Ivan\",\"personLastName\":\"Petrov\",\"agreementDateFrom\":\"2024-12-27\",\"agreementDateTo\":\"2025-01-12\",\"selectedRisks\":[\"Risk1\",\"Risk2\",\"Risk3\"]}";
        assertTrue(str.contains(expectedJson));
    }
}
