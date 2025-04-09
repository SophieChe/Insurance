package org.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeUtil {
    public long calculateDaysBetweenDates(LocalDate dateFrom, LocalDate dateTo) {
        return ChronoUnit.DAYS.between(dateFrom, dateTo);
    }
}
