package com.ericxie.travel_risk_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ericxie.travel_risk_api.model.RiskEvaluateResponse;
import com.ericxie.travel_risk_api.model.RiskEvaluateRequest;
import com.ericxie.travel_risk_api.model.RiskLevel;

@Service
public class RiskService {

    public RiskEvaluateResponse getRisk(RiskEvaluateRequest request){
        RiskEvaluateResponse response = new RiskEvaluateResponse();

        List<String> factors = List.of("None");

        response.setRiskLevel(RiskLevel.LEVEL_1_NORMAL);
        response.setFactors(factors);
        response.setRiskScore(20);

        return response;
    }
}
