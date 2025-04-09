package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectedRisksValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private SelectedRisksValidation validation;
    TravelCalculatePremiumRequest request;
    ValidationError validationErrorFields;

    @BeforeEach
    public void makeRequestMock() {
        request = mock(TravelCalculatePremiumRequest.class);
    }

    @BeforeEach
    public void makeValidationErrorMock() {
        validationErrorFields = mock(ValidationError.class);
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsNull() {
        when(request.getSelectedRisks()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        Assertions.assertAll(
                () -> Assertions.assertFalse(validationErrors.isEmpty()),
                () -> Assertions.assertEquals(validationErrorFields, validationErrors.get()));
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsEmpty() {
        when(request.getSelectedRisks()).thenReturn(List.of());
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        Assertions.assertAll(
                () -> Assertions.assertFalse(validationErrors.isEmpty()),
                () -> Assertions.assertEquals(validationErrorFields, validationErrors.get()));
    }

    @Test
    public void shouldNotReturnErrorWhenSelectedRisksIsPresent() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        Optional<ValidationError> validationErrors = validation.validate(request);
        assertTrue(validationErrors.isEmpty());
    }
}
