package com.configurationpc.PCConfigurator.exceptions;

import java.util.List;

public class RecommendationException extends RuntimeException {
    private final List<String> recommendations;
    public RecommendationException(final List<String> recommendations) {
        super("Build is incomplete: " + String.join(", ", recommendations));
        this.recommendations = recommendations;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

}
