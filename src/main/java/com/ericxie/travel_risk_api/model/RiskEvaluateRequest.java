package com.ericxie.travel_risk_api.model;

import java.util.List;

public class RiskEvaluateRequest {
    private String departureAirport;
    private String arrivalAirport;
    private List<String> layoverAirports;

    public String getDepartureAirport() {
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

    public List<String> getLayoverAirports() {
        return layoverAirports;
    }

    public void setLayoverAirports(List<String> layoverAirports) {
        this.layoverAirports = layoverAirports;
    }
}