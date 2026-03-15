package com.ericxie.travel_risk_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.ericxie.travel_risk_api.model.RiskEvaluateResponse;
import com.ericxie.travel_risk_api.model.RiskEvaluateRequest;
import com.ericxie.travel_risk_api.model.RiskLevel;

@Service
public class RiskService {

    private final AirportService airportService;

    public RiskService(AirportService airportService){
        this.airportService = airportService;
    }

    public RiskEvaluateResponse getRisk(RiskEvaluateRequest request){
        RiskEvaluateResponse response = new RiskEvaluateResponse();

        List<String> factors = new ArrayList<>();

        String departureCountry = this.airportService.getCountryByIataCode(request.getDepartureAirport());
        String arrivalCountry = this.airportService.getCountryByIataCode(request.getArrivalAirport());

        factors.add(departureCountry);
        factors.add(arrivalCountry);

        response.setRiskLevel(RiskLevel.LEVEL_1_NORMAL);
        response.setFactors(factors);
        response.setRiskScore(20);

        return response;
    }
}
