package com.ericxie.travel_risk_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelAdvisoryEntry {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Category")
    private List<String> category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getCountryCode() {
        return category != null && !category.isEmpty() ? category.get(0) : null;
    }
}