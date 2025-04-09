package org.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {
    private TravelCalculatePremiumService calculatePremiumService;
    private TravelCalculatePremiumRequestLogger requestLogger;
    private TravelCalculatePremiumResponseLogger responseLogger;
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;

    public TravelCalculatePremiumController(
            TravelCalculatePremiumService calculatePremiumService
            , TravelCalculatePremiumRequestLogger requestLogger
            , TravelCalculatePremiumResponseLogger responseLogger
            , TravelCalculatePremiumRequestExecutionTimeLogger timeLogger) {
        this.calculatePremiumService = calculatePremiumService;
        this.requestLogger = requestLogger;
        this.responseLogger = responseLogger;
        this.timeLogger = timeLogger;
    }

    //    обработка HTTP POST запроса в контроллере Spring
    @PostMapping(path = "/",
            consumes = "application/json", //Метод ожидает, что запрос будет содержать тело в формате JSON
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted(); //Stopwatch — утилита библиотеки Google Guava,для отслеживания времени выполнения кода
        TravelCalculatePremiumResponse response = processRequest(request);
        timeLogger.logExecutionTime(stopwatch); //содержит информацию о времени, которое прошло с момента вызова createStarted()
        return response;
    }

    private TravelCalculatePremiumResponse processRequest(TravelCalculatePremiumRequest request) {
        requestLogger.logTravelCalculatePremiumRequest(request);
        TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
        responseLogger.logTravelCalculatePremiumResponse(response);
        return response;
    }
}