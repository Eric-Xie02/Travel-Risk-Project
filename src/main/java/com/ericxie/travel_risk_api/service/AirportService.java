package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.model.Airport;

import com.ericxie.travel_risk_api.repository.AirportRepository;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public String getCountryByIataCode(String iataCode){

        Airport airport = airportRepository.findByIataCode(iataCode);

        if(airport == null){
            throw new IllegalArgumentException("Can't find airport with matching iata code:" + iataCode);
        }

        return airport.getIsoCountry();
    }
}
