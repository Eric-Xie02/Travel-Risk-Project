package com.ericxie.travel_risk_api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.ericxie.travel_risk_api.model.RiskEvaluateResponse;
import com.ericxie.travel_risk_api.model.RiskEvaluateRequest;
import com.ericxie.travel_risk_api.model.RiskLevel;

@Service
public class RiskService {

    private final AirportService airportService;
    private final TravelAdvisoryService travelAdvisoryService;

    public RiskService(AirportService airportService, TravelAdvisoryService travelAdvisoryService) {
        this.airportService = airportService;
        this.travelAdvisoryService = travelAdvisoryService;
    }

    public RiskEvaluateResponse getRisk(RiskEvaluateRequest request) {
        RiskEvaluateResponse response = new RiskEvaluateResponse();

        List<String> factors = new ArrayList<>();

        String departureCountry = this.airportService.getCountryByIataCode(request.getDepartureAirport());
        factors.add(departureCountry);
        int departureCountryLevel = travelAdvisoryService.getAdvisoryLevel(departureCountry);
        factors.add(String.valueOf(departureCountryLevel));

        //airport (IATA) -> country (ISO)
        List<String> layoverCountryCodes = request
                .getLayoverAirports()
                .stream()
                .map(iataCode -> {
                    String countryCode = this.airportService.getCountryByIataCode(iataCode);
                    factors.add(countryCode);
                    return countryCode;
                })
                .toList();

        //country (ISO) -> advisory level
        List<Integer> layoverAdvisoryLevels = layoverCountryCodes
                .stream()
                .map(countryCode -> {
                    int advisoryLevel = this.travelAdvisoryService.getAdvisoryLevel(countryCode);
                    factors.add(String.valueOf(advisoryLevel));
                    return advisoryLevel;
                })
                .toList();

        String arrivalCountry = this.airportService.getCountryByIataCode(request.getArrivalAirport());
        factors.add(arrivalCountry);
        int arrivalCountryLevel = travelAdvisoryService.getAdvisoryLevel(arrivalCountry);
        factors.add(String.valueOf(arrivalCountryLevel));

        // Combine all levels
        List<Integer> allAdvisoryLevels = new ArrayList<>(layoverAdvisoryLevels);
        allAdvisoryLevels.add(departureCountryLevel);
        allAdvisoryLevels.add(arrivalCountryLevel);

        // Max level across all stops
        int maxLevel = allAdvisoryLevels.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        // Risk score: square each level so higher levels are weighted more
        int rawScore = allAdvisoryLevels.stream()
                .mapToInt(level -> level * level)
                .sum();

        int maxPossibleScore = allAdvisoryLevels.size() * 4 * 4;
        int riskScore = (int) Math.round((rawScore / (double) maxPossibleScore) * 100);

        // Floor rules: ensure high risk legs are always reflected in the score
        if (maxLevel >= 4) riskScore = Math.max(riskScore, 80);
        if (maxLevel >= 3) riskScore = Math.max(riskScore, 50);

        response.setRiskLevel(RiskLevel.fromLevel(maxLevel));
        response.setRiskScore(riskScore);
        response.setFactors(factors);

        return response;
    }
}
