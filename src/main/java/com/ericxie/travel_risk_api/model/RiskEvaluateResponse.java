package com.ericxie.travel_risk_api.model;
import java.util.List;


public class RiskEvaluateResponse {
    private int riskScore;
    private RiskLevel riskLevel;
    private List<String> factors;

    public int getRiskScore(){
        return riskScore;
    }

    public void setRiskScore(int riskScore){
        this.riskScore = riskScore;
    }

    public RiskLevel getRiskLevel(){
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel){
        this.riskLevel = riskLevel;
    }

    public List<String> getFactors(){
        return factors;
    }

    public void setFactors(List<String> factors){
        this.factors = factors;
    }
}