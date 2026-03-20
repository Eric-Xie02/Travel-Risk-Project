package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.exception.FlightNotFoundException;
import com.ericxie.travel_risk_api.model.flight.AirlabsResponse;
import com.ericxie.travel_risk_api.model.flight.FlightInfo;
import com.ericxie.travel_risk_api.model.trip.CreateTripRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Value("${airlabs.key}")
    private String key;
    private final RestClient restClient;

    public FlightService() {
        this.restClient = RestClient.create();
    }

    public CreateTripRequest buildTripFromFlights(List<String> flightNumbers) {

        if (flightNumbers == null || flightNumbers.isEmpty()) {
            throw new IllegalArgumentException("Flight numbers list cannot be empty");
        }

        CreateTripRequest createTripRequest = new CreateTripRequest();
        List<String> layoverAirports = new ArrayList<>();
        List<String> flightIataCodes = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        String previousArrIata = null;

        for (int i = 0; i < flightNumbers.size(); i++) {
            String flightNumber = flightNumbers.get(i).replace(" ", "").toUpperCase();
            String url = "https://airlabs.co/api/v9/flight?flight_iata=" + flightNumber + "&api_key=" + key;

            try {
                String response = restClient.get()
                        .uri(url)
                        .retrieve()
                        .body(String.class);

                AirlabsResponse airlabsResponse = objectMapper.readValue(response, AirlabsResponse.class);
                FlightInfo flight = airlabsResponse.getResponse();

                flightIataCodes.add(flight.getFlightIata());

                if (i == 0) {
                    createTripRequest.setDepartureAirport(flight.getDepIata());
                    createTripRequest.setDepartureDate(LocalDate.parse(flight.getDepTime().substring(0, 10)));
                }

                if (i == flightNumbers.size() - 1) {
                    createTripRequest.setArrivalAirport(flight.getArrIata());
                }

                if (i < flightNumbers.size() - 1) {
                    layoverAirports.add(flight.getArrIata());
                }

                // add departure airport if it differs from previous flight's arrival (ground transfer)
                if (i > 0 && previousArrIata != null && !flight.getDepIata().equals(previousArrIata)) {
                    layoverAirports.add(flight.getDepIata());
                }

                previousArrIata = flight.getArrIata();

            } catch (Exception e) {
                System.err.println("Failed to fetch flight: " + e.getMessage());
                throw new FlightNotFoundException("Could not find flight: " + flightNumber);
            }
        }

        createTripRequest.setLayoverAirports(layoverAirports);
        createTripRequest.setFlightNumber(flightIataCodes);

        return createTripRequest;
    }
}
