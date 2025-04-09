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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonLastNameValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private PersonLastNameValidation validation;
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
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        when(request.getPersonLastName()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        Assertions.assertAll(
                () -> assertFalse(validationErrors.isEmpty()),
                () -> Assertions.assertEquals(validationErrorFields, validationErrors.get()));
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        when(request.getPersonLastName()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        Assertions.assertAll(
                () -> assertFalse(validationErrors.isEmpty()),
                () -> Assertions.assertEquals(validationErrorFields, validationErrors.get()));
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        when(request.getPersonLastName()).thenReturn("lastname");
        Optional<ValidationError> validationErrors = validation.validate(request);
        assertTrue(validationErrors.isEmpty());
    }
}
