package com.ericxie.travel_risk_api.model.risk;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RiskEvaluateRequest {
    private String departureAirport;
    private String arrivalAirport;
    private List<String> layoverAirports;
}