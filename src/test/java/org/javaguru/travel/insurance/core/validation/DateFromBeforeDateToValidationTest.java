package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateFromBeforeDateToValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private DateFromBeforeDateToValidation validation;
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
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2026, 1, 12));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2026, 1, 10));
        when(errorFactory.buildError("ERROR_CODE_5")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        assertTrue(validationErrors.isPresent());
        assertEquals(validationErrorFields, validationErrors.get());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2026, 1, 12));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2026, 1, 12));
        when(errorFactory.buildError("ERROR_CODE_5")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        assertTrue(validationErrors.isPresent());
        assertEquals(validationErrorFields, validationErrors.get());
    }

    @Test
    public void shouldNotReturnErrorWhenDateFromIsLessDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2026, 1, 12));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2026, 1, 14));
        Optional<ValidationError> validationErrors = validation.validate(request);
        assertTrue(validationErrors.isEmpty());
    }
}
