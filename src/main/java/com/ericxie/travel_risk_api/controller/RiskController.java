package com.ericxie.travel_risk_api.controller;

import com.ericxie.travel_risk_api.model.RiskEvaluateResponse;
import com.ericxie.travel_risk_api.model.RiskEvaluateRequest;
import com.ericxie.travel_risk_api.service.RiskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk")
public class RiskController {

    private final RiskService riskService;

    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }

    @PostMapping("/evaluate")
    public RiskEvaluateResponse getRisk(@RequestBody RiskEvaluateRequest request){
       return riskService.getRisk(request);
    }
}
