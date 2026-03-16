package com.ericxie.travel_risk_api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RiskEvaluateResponse {
    private int riskScore;
    private RiskLevel riskLevel;
    private List<String> factors;
}