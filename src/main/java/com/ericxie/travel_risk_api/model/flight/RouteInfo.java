package com.ericxie.travel_risk_api.model.flight;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteInfo {

    @JsonProperty("flight_iata")
    private String flightIata;

    @JsonProperty("dep_iata")
    private String depIata;

    @JsonProperty("arr_iata")
    private String arrIata;

    @JsonProperty("airline_iata")
    private String airlineIata;

    @JsonProperty("duration")
    private int duration;
}