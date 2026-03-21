package com.ericxie.travel_risk_api.controller;

import com.ericxie.travel_risk_api.model.auth.User;
import com.ericxie.travel_risk_api.model.news.NewsArticle;
import com.ericxie.travel_risk_api.model.trip.Trip;
import com.ericxie.travel_risk_api.repository.UserRepository;
import com.ericxie.travel_risk_api.service.NewsService;
import com.ericxie.travel_risk_api.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;
    private final TripService tripService;
    private final UserRepository userRepository;

    public NewsController(NewsService newsService, TripService tripService, UserRepository userRepository) {
        this.newsService = newsService;
        this.tripService = tripService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<List<NewsArticle>> getTripNews(@PathVariable Long tripId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Trip trip = tripService.getTripById(tripId, user);

        return ResponseEntity.ok(newsService.getTripNews(trip, NewsService.DEFAULT_NEWS_DAYS));
    }

}
