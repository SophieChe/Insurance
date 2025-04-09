package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.underwriting.TravelCalculateUnderwriting;
import org.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelCalculatePremiumRequestValidator validator;
    @Mock
    private TravelCalculateUnderwriting underwriting;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    TravelCalculatePremiumRequest request;

    @BeforeEach
    public void makeRequestMock() {
        request = mock(TravelCalculatePremiumRequest.class);
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        List<ValidationError> errors = buildValidationErrorList();
        when(validator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        List<ValidationError> errors = buildValidationErrorList();
        when(validator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, response.getErrors().size()),
                () -> Assertions.assertEquals("field", response.getErrors().get(0).getErrorCode()),
                () -> Assertions.assertEquals("errorMessage", response.getErrors().get(0).getDescription()));
    }

    @Test
    public void shouldFillClientFirstName() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("personFirstName", response.getPersonFirstName());
    }

    @Test
    public void shouldFillClientLastname() {
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("personLastName", response.getPersonLastName());
    }

    @Test
    public void shouldFillDateFrom() {
        LocalDate dateFrom = LocalDate.of(2024, 12, 27);
        when(request.getAgreementDateFrom()).thenReturn(dateFrom);
        when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(dateFrom, response.getAgreementDateFrom());
    }

    @Test
    public void shouldFillDateTo() {
        LocalDate dateTo = LocalDate.of(2024, 12, 27);
        when(request.getAgreementDateTo()).thenReturn(dateTo);
        when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(dateTo, response.getAgreementDateTo());
    }

    @Test
    public void shouldFillAgreementPrice() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2024, 12, 27));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 1, 12));
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(new BigDecimal(16));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(new BigDecimal(16), response.getAgreementPrice());
    }

    @Test
    public void shouldReturnResponseWithCorrectErrorCount() {
        ValidationError validationError = new ValidationError("field", "message");
        when(validator.validate(request)).thenReturn(List.of(validationError));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(1, response.getErrors().size());
    }

    @Disabled
    @Test
    public void deleteMe() {

    }

    private List<ValidationError> buildValidationErrorList() {
        return List.of(
                new ValidationError("field", "errorMessage"));
    }
}