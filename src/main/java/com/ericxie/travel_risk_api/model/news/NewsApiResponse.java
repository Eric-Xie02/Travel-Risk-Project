package com.ericxie.travel_risk_api.model.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiResponse {

    @JsonProperty("articles")
    private List<NewsArticle> articles;

}