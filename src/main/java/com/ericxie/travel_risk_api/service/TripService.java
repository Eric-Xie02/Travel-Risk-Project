package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.model.*;
import com.ericxie.travel_risk_api.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    private final RiskService riskService;
    private final TripRepository tripRepository;

    public TripService(RiskService riskService, TripRepository tripRepository) {
        this.tripRepository = tripRepository;
        this.riskService = riskService;
    }

    public Trip createTrip(CreateTripRequest createTripRequest) {
        Trip trip = new Trip();

        trip.setDepartureAirport(createTripRequest.getDepartureAirport());
        trip.setArrivalAirport(createTripRequest.getArrivalAirport());
        trip.setDepartureDate(createTripRequest.getDepartureDate());
        trip.setFlightNumber(createTripRequest.getFlightNumber());
        trip.setLayoverAirports(createTripRequest.getLayoverAirports());
        trip.setUserId(createTripRequest.getUserId());

        evaluateAndSetRisk(trip);

        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long tripId, UpdateTripRequest updateTripRequest) {
        Trip trip = this.tripRepository.findById(tripId).orElseThrow();

        if (updateTripRequest.getDepartureDate() != null) {
            trip.setDepartureDate(updateTripRequest.getDepartureDate());
        }

        if (updateTripRequest.getDepartureAirport() != null) {
            trip.setDepartureAirport(updateTripRequest.getDepartureAirport());
        }

        if (updateTripRequest.getArrivalAirport() != null) {
            trip.setArrivalAirport(updateTripRequest.getArrivalAirport());
        }

        if (updateTripRequest.getLayoverAirports() != null) {
            trip.setLayoverAirports(updateTripRequest.getLayoverAirports());
        }

        if (updateTripRequest.getFlightNumber() != null) {
            trip.setFlightNumber(updateTripRequest.getFlightNumber());
        }

        evaluateAndSetRisk(trip);

        return this.tripRepository.save(trip);
    }

    public void deleteTrip(Long tripId) {
        this.tripRepository.deleteById(tripId);
    }

    public Trip getTripById(Long tripId) {
        return this.tripRepository.findById(tripId).orElseThrow();
    }

    public List<Trip> getUserTrips(Long userId) {
        return this.tripRepository.findByUserId(userId);
    }

    private void evaluateAndSetRisk(Trip trip) {
        RiskEvaluateRequest riskEvaluateRequest = new RiskEvaluateRequest();
        riskEvaluateRequest.setDepartureAirport(trip.getDepartureAirport());
        riskEvaluateRequest.setArrivalAirport(trip.getArrivalAirport());
        riskEvaluateRequest.setLayoverAirports(trip.getLayoverAirports());

        RiskEvaluateResponse riskEvaluateResponse = this.riskService.getRisk(riskEvaluateRequest);

        trip.setRiskScore(riskEvaluateResponse.getRiskScore());
        trip.setRiskLevel(riskEvaluateResponse.getRiskLevel());
        trip.setFactors(riskEvaluateResponse.getFactors());
    }
}
