package org.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);

    void logExecutionTime(Stopwatch stopwatch) {
        stopwatch.stop();
        long executionTimeMillis = stopwatch.elapsed().toMillis();
        logger.info("Request processing time (ms): " + executionTimeMillis);

    }
}
