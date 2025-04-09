package org.javaguru.travel.insurance.rest;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);

    private JsonCovertToString jsonCovertToString;

    public TravelCalculatePremiumRequestLogger(JsonCovertToString jsonCovertToString) {
        this.jsonCovertToString = jsonCovertToString;
    }

    void logTravelCalculatePremiumRequest(TravelCalculatePremiumRequest request) {
            String jsonString = jsonCovertToString.jsonCovertToString(request);
            logger.info("TravelCalculatePremiumRequest: " + jsonString);
    }
}
