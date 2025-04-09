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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonFirstNameValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private PersonFirstNameValidation validation;
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
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_7")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        Assertions.assertAll(
                () -> Assertions.assertFalse(validationErrors.isEmpty()),
                () -> Assertions.assertEquals(validationErrorFields, validationErrors.get()));
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_7")).thenReturn(validationErrorFields);
        Optional<ValidationError> validationErrors = validation.validate(request);
        Assertions.assertAll(
                () -> Assertions.assertFalse(validationErrors.isEmpty()),
                () -> Assertions.assertEquals(validationErrorFields, validationErrors.get()));
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        when(request.getPersonFirstName()).thenReturn("firstname");
        Optional<ValidationError> validationErrors = validation.validate(request);
        assertTrue(validationErrors.isEmpty());
    }
}
