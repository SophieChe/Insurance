package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {
    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl requestValidator;
    TravelCalculatePremiumRequest request;
    TravelRequestValidation validation1;
    TravelRequestValidation validation2;

    @BeforeEach
    public void makeRequestMock() {
        request = mock(TravelCalculatePremiumRequest.class);
        validation1 = mock(TravelRequestValidation.class);
        validation2 = mock(TravelRequestValidation.class);
    }

    @Test
    public void shouldSucceed() {
        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation2.validate(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnError() {
        when(validation1.validate(request)).thenReturn(Optional.of(new ValidationError("errorCode", "description")));
        when(validation2.validate(request)).thenReturn(Optional.of(new ValidationError("errorCode", "description")));
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
    }
}
