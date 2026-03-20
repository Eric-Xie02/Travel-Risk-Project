package com.ericxie.travel_risk_api.model.flight;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightTripRequest {
    private List<String> flightNumbers;
}
