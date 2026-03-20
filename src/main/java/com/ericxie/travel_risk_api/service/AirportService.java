package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.model.airport.Airport;

import com.ericxie.travel_risk_api.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public String getCountryIsoByIataCode(String iataCode) {

        Airport airport = airportRepository.findByIataCode(iataCode);

        if (airport == null) {
            throw new IllegalArgumentException("Can't find airport with matching iata code:" + iataCode);
        }

        return airport.getIsoCountry();
    }

    public String getCountryNameByIataCode(String iataCode) {
        Airport airport = airportRepository.findByIataCode(iataCode);

        if (airport == null) {
            throw new IllegalArgumentException("Can't find airport with matching iata code:" + iataCode);
        }

        Locale locale = Locale.of("", airport.getIsoCountry());
        String countryName = locale.getDisplayCountry();

        return countryName;
    }

    public Boolean isValidIataCode(String iataCode) {
        Airport airport = airportRepository.findByIataCode(iataCode);

        if (airport == null) {
            return false;
        }

        return true;
    }
}
