package com.ericxie.travel_risk_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "iata_code")
    private String iataCode;
    @Column(name = "iso_country")
    private String isoCountry;
    private String name;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setIataCode(String iataCode){
        this.iataCode = iataCode;
    }

    public String getIataCode(){
        return iataCode;
    }

    public void setIsoCountry(String isoCountry){
        this.isoCountry = isoCountry;
    }

    public String getIsoCountry(){
        return isoCountry;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
