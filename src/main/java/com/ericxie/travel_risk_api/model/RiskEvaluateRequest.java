package com.ericxie.travel_risk_api.model;

public class RiskEvaluateRequest {
    private String departureAirport;
    private String arrivalAirport;

    public String getDepartureAirport(){
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}