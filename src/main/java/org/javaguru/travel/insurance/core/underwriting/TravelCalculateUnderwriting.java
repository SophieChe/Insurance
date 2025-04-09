package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelCalculateUnderwriting {
    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);
}
