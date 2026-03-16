package com.ericxie.travel_risk_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "airports")
@Getter
@Setter
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "iata_code")
    private String iataCode;
    @Column(name = "iso_country")
    private String isoCountry;
    private String name;
}
