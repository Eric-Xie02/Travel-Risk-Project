package com.ericxie.travel_risk_api.model.trip;

import com.ericxie.travel_risk_api.model.risk.RiskLevel;
import com.ericxie.travel_risk_api.model.auth.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int riskScore;
    private RiskLevel riskLevel;

    @ElementCollection
    private List<String> factors;

    @CreatedDate
    private LocalDateTime createdAt;

}