package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.model.auth.User;
import com.ericxie.travel_risk_api.model.news.NewsArticle;
import com.ericxie.travel_risk_api.model.trip.Trip;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final NewsService newsService;
    private final TripService tripService;
    private static final int WEEKLY_NEWS_DAYS = 7;

    public EmailService(JavaMailSender javaMailSender, NewsService newsService, TripService tripService) {
        this.javaMailSender = javaMailSender;
        this.newsService = newsService;
        this.tripService = tripService;
    }

    public void sendWeeklyDigest(User user) {
        List<Trip> userTrips = tripService.getUserTrips(user);

        StringBuilder html = new StringBuilder();

        // Outer wrapper
        html.append("<div style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 30px;'>");
        html.append("<div style='max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.1);'>");

        // Header
        html.append("<div style='background-color: #1a1a2e; padding: 24px 32px;'>");
        html.append("<h1 style='color: #ffffff; margin: 0; font-size: 22px;'>✈ Weekly Travel Safety Digest</h1>");
        html.append("<p style='color: #aaaacc; margin: 6px 0 0 0; font-size: 14px;'>Your personalized travel risk update</p>");
        html.append("</div>");

        // Body
        html.append("<div style='padding: 28px 32px;'>");
        html.append("<p style='color: #333; font-size: 15px;'>Hello <strong>").append(user.getUsername()).append("</strong>,</p>");
        html.append("<p style='color: #555; font-size: 14px;'>Here is your weekly travel safety update for your upcoming trips.</p>");

        if (userTrips.isEmpty()) {
            html.append("<p style='color: #888; font-style: italic;'>You have no upcoming trips saved.</p>");
        }

        for (Trip trip : userTrips) {

            // Risk level color
            String riskColor = switch (trip.getRiskLevel().toString()) {
                case "LEVEL_1_NORMAL" -> "#27ae60";
                case "LEVEL_2_CAUTION" -> "#f39c12";
                case "LEVEL_3_RECONSIDER" -> "#e67e22";
                case "LEVEL_4_DO_NOT_TRAVEL" -> "#e74c3c";
                default -> "#95a5a6";
            };

            // Trip card
            html.append("<div style='border: 1px solid #e0e0e0; border-radius: 8px; padding: 20px; margin: 20px 0;'>");

            // Trip header
            html.append("<div style='display: flex; justify-content: space-between; align-items: center; margin-bottom: 12px;'>");
            html.append("<h2 style='color: #1a1a2e; font-size: 20px; margin: 0;'>")
                    .append(trip.getDepartureAirport()).append(" → ").append(trip.getArrivalAirport())
                    .append("</h2>");
            html.append("<span style='background-color: ").append(riskColor)
                    .append("; color: white; padding: 4px 12px; border-radius: 20px; font-size: 12px; font-weight: bold;'>")
                    .append(trip.getRiskLevel().toString().replace("_", " "))
                    .append("</span>");
            html.append("</div>");

            html.append("<p style='color: #666; font-size: 13px; margin: 4px 0;'>📅 Departure: <strong>")
                    .append(trip.getDepartureDate()).append("</strong></p>");

            // News section
            List<NewsArticle> articles = newsService.getTripNews(trip, WEEKLY_NEWS_DAYS);

            html.append("<h3 style='color: #333; font-size: 15px; margin: 16px 0 10px 0; border-top: 1px solid #eee; padding-top: 12px;'>📰 Recent Travel News</h3>");

            if (articles.isEmpty()) {
                html.append("<p style='color: #888; font-style: italic; font-size: 13px;'>No recent travel news found for this route.</p>");
            } else {
                for (NewsArticle article : articles) {
                    html.append("<div style='margin-bottom: 12px; padding-bottom: 12px; border-bottom: 1px solid #f0f0f0;'>");
                    html.append("<a href='").append(article.getUrl())
                            .append("' style='color: #2980b9; font-size: 14px; font-weight: bold; text-decoration: none;'>")
                            .append(article.getTitle()).append("</a>");
                    html.append("<p style='color: #999; font-size: 12px; margin: 4px 0 0 0;'>")
                            .append(article.getSourceName()).append(" — ").append(article.getPublishDate())
                            .append("</p>");
                    html.append("</div>");
                }
            }

            html.append("</div>"); // end trip card
        }

        // Footer
        html.append("<p style='color: #888; font-size: 13px; margin-top: 24px;'>Stay safe and travel smart. ✈</p>");
        html.append("</div>"); // end body
        html.append("</div>"); // end card
        html.append("</div>"); // end outer wrapper

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setFrom("noreply@travelrisk.com");
            helper.setSubject("✈ Your Weekly Travel Safety Digest");
            helper.setText(html.toString(), true);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email to: " + user.getEmail() + " - " + e.getMessage());
        }
    }
}
