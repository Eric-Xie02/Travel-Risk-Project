package com.ericxie.travel_risk_api.model.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String username;
    private String password;
}
