package com.ericxie.travel_risk_api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String username;
    private String email;
    private Long userId;
    private String token;
}
