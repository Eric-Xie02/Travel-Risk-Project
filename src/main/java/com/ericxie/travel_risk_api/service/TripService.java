package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.exception.AirportNotFoundException;
import com.ericxie.travel_risk_api.exception.ResourceNotFoundException;
import com.ericxie.travel_risk_api.exception.UnauthorizedAccessException;
import com.ericxie.travel_risk_api.model.auth.User;
import com.ericxie.travel_risk_api.model.risk.RiskEvaluateRequest;
import com.ericxie.travel_risk_api.model.risk.RiskEvaluateResponse;
import com.ericxie.travel_risk_api.model.trip.CreateTripRequest;
import com.ericxie.travel_risk_api.model.trip.Trip;
import com.ericxie.travel_risk_api.model.trip.UpdateTripRequest;
import com.ericxie.travel_risk_api.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    private final RiskService riskService;
    private final TripRepository tripRepository;
    private final AirportService airportService;

    public TripService(RiskService riskService, TripRepository tripRepository, AirportService airportService) {
        this.tripRepository = tripRepository;
        this.riskService = riskService;
        this.airportService = airportService;
    }

    public Trip createTrip(CreateTripRequest createTripRequest, User user) {
        Trip trip = new Trip();

        trip.setDepartureAirport(createTripRequest.getDepartureAirport());
        trip.setArrivalAirport(createTripRequest.getArrivalAirport());
        trip.setDepartureDate(createTripRequest.getDepartureDate());
        trip.setFlightNumber(createTripRequest.getFlightNumber());
        trip.setLayoverAirports(createTripRequest.getLayoverAirports());
        trip.setUser(user);

        if (!validateAirportCodes(trip)) {
            throw new AirportNotFoundException("One or more airport codes are invalid");
        }

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

        if (!validateAirportCodes(trip)) {
            throw new AirportNotFoundException("One or more airport codes are invalid");
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

    private Boolean validateAirportCodes(Trip trip) {
        String departureAirport = trip.getDepartureAirport();
        String arrivalAirport = trip.getArrivalAirport();
        List<String> layoverAirports = trip.getLayoverAirports();

        List<String> allAirports = new ArrayList<>();
        allAirports.add(departureAirport);
        allAirports.add(arrivalAirport);
        allAirports.addAll(layoverAirports);

        for (String airportCode : allAirports) {
            if (!airportService.isValidIataCode(airportCode)) {
                return false;
            }
        }
        return true;
    }

    //Returns in a list the name of unique countries in a trip
    public List<String> getCountryNames(Trip trip) {
        List<String> countryNames = new ArrayList<>();

        List<String> allAirportCodes = new ArrayList<>();
        allAirportCodes.add(trip.getDepartureAirport());
        allAirportCodes.add(trip.getArrivalAirport());
        allAirportCodes.addAll(trip.getLayoverAirports());

        for (String airportCode : allAirportCodes) {
            String countryName = airportService.getCountryNameByIataCode(airportCode);
            if (!countryNames.contains(countryName)) {
                countryNames.add(countryName);
            }
        }

        return countryNames;
    }
}
