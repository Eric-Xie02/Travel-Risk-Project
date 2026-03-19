package com.ericxie.travel_risk_api.controller;

import com.ericxie.travel_risk_api.model.CreateTripRequest;
import com.ericxie.travel_risk_api.model.Trip;
import com.ericxie.travel_risk_api.model.UpdateTripRequest;
import com.ericxie.travel_risk_api.model.User;
import com.ericxie.travel_risk_api.repository.UserRepository;
import com.ericxie.travel_risk_api.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;
    private final UserRepository userRepository;

    public TripController(TripService tripService, UserRepository userRepository) {

        this.tripService = tripService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody CreateTripRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        Trip created = tripService.createTrip(request, user);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable("id") Long tripId, @RequestBody UpdateTripRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        Trip updated = tripService.updateTrip(tripId, request, user);
        return ResponseEntity.status(200).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        tripService.deleteTrip(id, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        Trip trip = tripService.getTripById(id, user);
        return ResponseEntity.ok(trip);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Trip>> getUserTrips() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        List<Trip> trips = tripService.getUserTrips(user);
        return ResponseEntity.ok(trips);
    }
}
