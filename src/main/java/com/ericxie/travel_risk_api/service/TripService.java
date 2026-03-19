package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.exception.ResourceNotFoundException;
import com.ericxie.travel_risk_api.exception.UnauthorizedAccessException;
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

    public Trip createTrip(CreateTripRequest createTripRequest, User user) {
        Trip trip = new Trip();

        trip.setDepartureAirport(createTripRequest.getDepartureAirport());
        trip.setArrivalAirport(createTripRequest.getArrivalAirport());
        trip.setDepartureDate(createTripRequest.getDepartureDate());
        trip.setFlightNumber(createTripRequest.getFlightNumber());
        trip.setLayoverAirports(createTripRequest.getLayoverAirports());
        trip.setUser(user);

        evaluateAndSetRisk(trip);

        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long tripId, UpdateTripRequest updateTripRequest, User user) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        if (!trip.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("This trip does not belong to you");
        }

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

    public void deleteTrip(Long tripId, User user) {

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        if (!trip.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("This trip does not belong to you");
        }

        this.tripRepository.deleteById(tripId);
    }

    public Trip getTripById(Long tripId, User user) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        if (!trip.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("This trip does not belong to you");
        }
        return trip;
    }


    public List<Trip> getUserTrips(User user) {
        return this.tripRepository.findByUserId(user.getId());
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
