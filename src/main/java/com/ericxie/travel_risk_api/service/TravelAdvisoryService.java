package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.model.risk.TravelAdvisoryEntry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelAdvisoryService {

    private List<TravelAdvisoryEntry> advisoryEntries;

    public TravelAdvisoryService() {
        // fetch and deserialize once here
        // store in advisoryEntries

        try {
            RestClient restClient = RestClient.create();

            String response = restClient.get()
                    .uri("https://cadataapi.state.gov/api/TravelAdvisories")
                    .retrieve()
                    .body(String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            advisoryEntries = objectMapper.readValue(response, new TypeReference<List<TravelAdvisoryEntry>>() {
            });


        } catch (Exception e) {
            System.err.println("Failed to fetch advisory data: " + e.getMessage());
            advisoryEntries = new ArrayList<>();
        }
    }

    //Returns advisory level from ISO code
    public int getAdvisoryLevel(String isoCode) {
        //1. Get the fips code
        String fipsCode = FipsToIsoMapper.toFips(isoCode);

        if (!advisoryEntries.isEmpty()) {
            System.out.println("First entry country code: " + advisoryEntries.get(0).getCountryCode());
        }

        //2. Find the travelAdvisoryEntry with countryCode = fipsCode
        TravelAdvisoryEntry travelAdvisoryEntry = advisoryEntries.stream()
                .filter(entry -> entry.getCountryCode() != null && entry.getCountryCode().equals(fipsCode))
                .findFirst()
                .orElse(null);

        if (travelAdvisoryEntry == null) {
            return -1; // or throw an exception
        }

        //3. Parse the title
        String title = travelAdvisoryEntry.getTitle();
        int levelIndex = title.indexOf("Level ") + 6;
        char levelChar = title.charAt(levelIndex);
        int advisoryLevel = Character.getNumericValue(levelChar);

        return advisoryLevel;
    }
}
