package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.exception.FlightNotFoundException;
import com.ericxie.travel_risk_api.model.flight.AirlabsRoutesResponse;
import com.ericxie.travel_risk_api.model.flight.RouteInfo;
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

    public CreateTripRequest buildTripFromFlights(List<String> flightNumbers, LocalDate departureDate) {

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
            String url = "https://airlabs.co/api/v9/routes?flight_iata=" + flightNumber + "&api_key=" + key;

            try {
                String response = restClient.get()
                        .uri(url)
                        .retrieve()
                        .body(String.class);

                AirlabsRoutesResponse airlabsRoutesResponse = objectMapper.readValue(response, AirlabsRoutesResponse.class);

                if (airlabsRoutesResponse.getResponse() == null || airlabsRoutesResponse.getResponse().isEmpty()) {
                    throw new FlightNotFoundException("Could not find flight: " + flightNumber);
                }

                RouteInfo route = airlabsRoutesResponse.getResponse().get(0);

                flightIataCodes.add(route.getFlightIata());

                if (i == 0) {
                    createTripRequest.setDepartureAirport(route.getDepIata());
                    createTripRequest.setDepartureDate(departureDate);
                }

                if (i == flightNumbers.size() - 1) {
                    createTripRequest.setArrivalAirport(route.getArrIata());
                }

                if (i < flightNumbers.size() - 1) {
                    layoverAirports.add(route.getArrIata());
                }

                if (i > 0 && previousArrIata != null && !route.getDepIata().equals(previousArrIata)) {
                    layoverAirports.add(route.getDepIata());
                }

                previousArrIata = route.getArrIata();

            } catch (FlightNotFoundException e) {
                throw e;
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
