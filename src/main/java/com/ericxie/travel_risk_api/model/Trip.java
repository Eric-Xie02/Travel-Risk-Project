package com.ericxie.travel_risk_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;

    @ElementCollection
    private List<String> flightNumber;

    @ElementCollection
    private List<String> layoverAirports;

    private Long userId;
    private int riskScore;
    private RiskLevel riskLevel;

    @ElementCollection
    private List<String> factors;

    @CreatedDate
    private LocalDateTime createdAt;

}