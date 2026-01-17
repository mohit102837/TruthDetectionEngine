package com.tbde.api.config;

import com.tbde.ai.AiAssistant;
import com.tbde.ai.MockAiAssistant;
import com.tbde.ai.SimulatedAiAssistant;
import com.tbde.engine.TruthBiasDetectionEngine;
import com.tbde.input.InputNormalizer;
import com.tbde.preprocess.TextPreprocessor;
import com.tbde.aggregate.IndicatorAggregator;
import com.tbde.scoring.ScoringEngine;
import com.tbde.explain.ExplanationEngine;
import com.tbde.explain.UserFriendlyExplanationBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Bean
    public AiAssistant aiAssistant(
            @Value("${ai.enabled:false}") boolean enabled,
            @Value("${ai.mode:OFF}") String mode
    ) {
        if (!enabled) {
            return new MockAiAssistant();
        }

        if ("SIMULATION".equalsIgnoreCase(mode)) {
            return new SimulatedAiAssistant();
        }

        // Fallback safety
        return new MockAiAssistant();
    }



    @Bean
    public TruthBiasDetectionEngine truthBiasDetectionEngine() {
        return new TruthBiasDetectionEngine(
                new InputNormalizer(),
                new TextPreprocessor(),
                new IndicatorAggregator(),
                new ScoringEngine(),
                new ExplanationEngine(),
                new UserFriendlyExplanationBuilder()
        );
    }
}
