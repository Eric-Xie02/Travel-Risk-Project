package com.ericxie.travel_risk_api.model.flight;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightInfo {

    @JsonProperty("flight_iata")
    private String flightIata;

    @JsonProperty("airline_name")
    private String airlineName;

    @JsonProperty("dep_iata")
    private String depIata;

    @JsonProperty("dep_name")
    private String depName;

    @JsonProperty("arr_iata")
    private String arrIata;

    @JsonProperty("arr_name")
    private String arrName;

    @JsonProperty("dep_time")
    private String depTime;

    @JsonProperty("arr_time")
    private String arrTime;

    @JsonProperty("status")
    private String status;

    @JsonProperty("delayed")
    private Integer delayed;
}