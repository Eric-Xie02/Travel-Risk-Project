package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.model.news.NewsApiResponse;
import com.ericxie.travel_risk_api.model.news.NewsArticle;
import com.ericxie.travel_risk_api.model.trip.Trip;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class NewsService {

    @Value("${newsapi.key}")
    private String key;
    private final TripService tripService;
    private final RestClient restClient;

    public NewsService(TripService tripService) {
        this.tripService = tripService;
        this.restClient = RestClient.create();
    }

    public List<NewsArticle> getTripNews(Trip trip) {

        List<NewsArticle> newsArticles = new ArrayList<>();
        List<String> countryNames = tripService.getCountryNames(trip);

        for (String countryName : countryNames) {
            newsArticles.addAll(getCountryNews(countryName));
        }

        return newsArticles;
    }

    public List<NewsArticle> getCountryNews(String countryName) {

        List<NewsArticle> newsArticles = new ArrayList<>();

        List<String> sources = List.of("associated-press", "bbc-news");
        String sourcesParam = String.join(",", sources);

        List<String> keywords = List.of("travel", "airline", "airport", "tourist", "flight", "visa");
        String keywordsParam = String.join(" OR ", keywords);

        LocalDate fromDate = LocalDate.now().minusDays(28);
        int pageSize = 5;

        String url = "https://newsapi.org/v2/everything?"
                + "q=" + countryName + " AND (" + keywordsParam + ")"
                + "&sources=" + sourcesParam
                + "&from=" + fromDate
                + "&sortBy=relevancy"
                + "&pageSize=" + pageSize
                + "&apiKey=" + key;

        try {

            String response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            NewsApiResponse apiResponse = objectMapper.readValue(response, NewsApiResponse.class);
            newsArticles = apiResponse.getArticles();
        } catch (Exception e) {
            System.err.println("Failed to fetch news articles: " + e.getMessage());
            newsArticles = new ArrayList<>();
        }

        return newsArticles;
    }
}
