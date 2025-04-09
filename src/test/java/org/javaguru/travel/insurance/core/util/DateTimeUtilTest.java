package org.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeUtilTest {
    DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Test
    public void shouldDaysBetweenDatesBe16() {
        LocalDate dateFrom = LocalDate.of(2024, 12, 27);
        LocalDate dateTo = LocalDate.of(2025, 1, 12);
        assertEquals(16, dateTimeUtil.calculateDaysBetweenDates(dateFrom, dateTo));
    }

    @Test
    public void shouldDaysBetweenDatesBeNegative() {
        LocalDate dateFrom = LocalDate.of(2024, 12, 27);
        LocalDate dateTo = LocalDate.of(2025, 1, 12);
        assertTrue(dateTimeUtil.calculateDaysBetweenDates(dateTo, dateFrom) < 0);
    }

    @Test
    public void shouldDaysBetweenDatesBePositive() {
        LocalDate dateFrom = LocalDate.of(2024, 12, 27);
        LocalDate dateTo = LocalDate.of(2025, 1, 12);
        assertTrue(dateTimeUtil.calculateDaysBetweenDates(dateFrom, dateTo) > 0);
    }

    @Test
    public void shouldDaysBetweenDatesBeZero() {
        LocalDate dateFrom = LocalDate.of(2025, 1, 12);
        LocalDate dateTo = LocalDate.of(2025, 1, 12);
        assertTrue(dateTimeUtil.calculateDaysBetweenDates(dateFrom, dateTo) == 0);
    }
}
