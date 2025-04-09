package org.javaguru.travel.insurance.rest;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);
    private JsonCovertToString jsonCovertToString;

    public TravelCalculatePremiumResponseLogger(JsonCovertToString jsonCovertToString) {
        this.jsonCovertToString = jsonCovertToString;
    }

    void logTravelCalculatePremiumResponse(TravelCalculatePremiumResponse response) {
        String jsonString = jsonCovertToString.jsonCovertToString(response);
        logger.info("TravelCalculatePremiumRequest: " + jsonString);
    }
}
