package com.tbde.indicator.detectors;

import com.tbde.indicator.*;
import com.tbde.preprocess.ProcessedText;

import java.util.ArrayList;
import java.util.List;

public class EmotionalLanguageDetector implements IndicatorDetector {

    private static final String[] EMOTIONAL_WORDS = {
            "shocking", "unbelievable", "outrageous", "disaster",
            "terrifying", "amazing", "hate", "angry", "panic"
    };

    @Override
    public IndicatorResult detect(ProcessedText text) {

        List<String> evidence = new ArrayList<>();
        int matchCount = 0;

        for (String token : text.getTokens()) {
            for (String emotional : EMOTIONAL_WORDS) {
                if (token.equalsIgnoreCase(emotional)) {
                    matchCount++;
                    evidence.add(token);
                }
            }
        }

        double score = text.getTokens().isEmpty()
                ? 0.0
                : (double) matchCount / text.getTokens().size();

        return new IndicatorResult(
                IndicatorType.EMOTIONAL_LANGUAGE,
                Math.min(score, 1.0),
                evidence
        );
    }

    @Override
    public IndicatorType getType() {
        return IndicatorType.EMOTIONAL_LANGUAGE;
    }
}
