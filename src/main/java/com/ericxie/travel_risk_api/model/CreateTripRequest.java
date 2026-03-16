package com.ericxie.travel_risk_api.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateTripRequest {
    private String departureAirport;
    private String arrivalAirport;
    private List<String> layoverAirports;
    private LocalDate departureDate;
    private Long userId;
    private List<String> flightNumber;
}
