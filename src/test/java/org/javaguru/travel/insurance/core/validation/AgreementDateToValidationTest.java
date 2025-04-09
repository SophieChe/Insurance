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

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateToValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private AgreementDateToValidation validation;
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
    public void shouldReturnErrorWhenAgreementDateFromIsNull() {
        when(request.getAgreementDateTo()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_4")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        Assertions.assertAll(
                () -> Assertions.assertTrue(validationErrors.isPresent()),
                () -> Assertions.assertEquals(validationErrorFields, validationErrors.get()));
    }

    @Test
    public void shouldNotReturnErrorWhenDateFromIsPresent() {
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2024, 12, 27));
        Optional<ValidationError> validationErrors = validation.validate(request);
        assertTrue(validationErrors.isEmpty());
    }
}
