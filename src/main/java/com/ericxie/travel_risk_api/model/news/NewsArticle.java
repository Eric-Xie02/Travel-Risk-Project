package com.ericxie.travel_risk_api.model.news;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsArticle {

    private String author;
    private String title;
    private String url;

    @JsonProperty("publishedAt")
    private String publishDate;

    @JsonProperty(value = "source", access = JsonProperty.Access.WRITE_ONLY)
    private Source source;

    // manual getters for fields you want serialized
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getPublishDate() {
        return publishDate;
    }

    // hides source, exposes just the name
    @JsonIgnore
    public Source getSource() {
        return source;
    }

    @JsonProperty("sourceName")
    public String getSourceName() {
        return source != null ? source.getName() : null;
    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Source {
        @JsonProperty("name")
        private String name;
    }
}