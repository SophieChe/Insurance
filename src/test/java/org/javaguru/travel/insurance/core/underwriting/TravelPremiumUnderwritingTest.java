package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {
    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelCalculateUnderwritingImpl premiumUnderwriting;

    @Test
    void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 12, 27));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2026, 1, 12));
        when(dateTimeUtil.calculateDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(16L);
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);
        assertEquals(premium, new BigDecimal(16));
    }


}
