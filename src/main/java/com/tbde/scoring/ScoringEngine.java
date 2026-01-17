package com.tbde.scoring;

import com.tbde.config.IndicatorWeightConfig;
import com.tbde.indicator.IndicatorResult;
import com.tbde.indicator.IndicatorType;
import com.tbde.report.TrustReport;

import java.util.*;

public class ScoringEngine {

    public TrustReport score(List<IndicatorResult> indicators) {

        Map<IndicatorType, Double> scores = new EnumMap<>(IndicatorType.class);
        List<String> signals = new ArrayList<>();

        double weightedSum = 0.0;
        double weightTotal = 0.0;

        for (IndicatorResult r : indicators) {
            double weight = IndicatorWeightConfig.getWeight(r.getType());
            double contribution = r.getScore() * weight;

            weightedSum += contribution;
            weightTotal += weight;

            scores.put(r.getType(), r.getScore());

            if (r.getScore() > 0.15) {
                signals.add("High " + r.getType().name().toLowerCase().replace('_', ' '));
            }
        }

        double normalizedRisk = (weightTotal == 0.0) ? 0.0 : (weightedSum / weightTotal);
        int trustScore = (int) Math.round(100 * (1.0 - Math.min(normalizedRisk, 1.0)));

        BiasLevel biasLevel = determineBiasLevel(trustScore);

        String explanation = buildExplanation(trustScore, biasLevel, signals);

        return new TrustReport(
                trustScore,
                biasLevel,
                scores,
                signals,
                explanation
        );
    }

    private BiasLevel determineBiasLevel(int trustScore) {
        if (trustScore >= 70) return BiasLevel.LOW;
        if (trustScore >= 40) return BiasLevel.MEDIUM;
        return BiasLevel.HIGH;
    }

    private String buildExplanation(int score, BiasLevel level, List<String> signals) {
        if (signals.isEmpty()) {
            return "Content shows low manipulation signals. Overall trust is " + score + ".";
        }
        return "Detected signals: " + String.join(", ", signals)
                + ". Overall trust is " + score + " (" + level + ").";
    }
}
