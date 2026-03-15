package com.ericxie.travel_risk_api.repository;

import com.ericxie.travel_risk_api.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Airport findByIataCode(String iataCode);

}
