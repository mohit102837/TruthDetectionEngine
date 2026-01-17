package com.tbde.report;

import com.tbde.scoring.BiasLevel;
import com.tbde.indicator.IndicatorType;

import java.util.List;
import java.util.Map;

public class TrustReport {

    private final int overallScore; // 0–100
    private final BiasLevel biasLevel;
    private final Map<IndicatorType, Double> indicatorScores;
    private final List<String> manipulationSignals;
    private final String explanation;

    public TrustReport(
            int overallScore,
            BiasLevel biasLevel,
            Map<IndicatorType, Double> indicatorScores,
            List<String> manipulationSignals,
            String explanation
    ) {
        this.overallScore = overallScore;
        this.biasLevel = biasLevel;
        this.indicatorScores = indicatorScores;
        this.manipulationSignals = manipulationSignals;
        this.explanation = explanation;
    }

    public int getOverallScore() { return overallScore; }
    public BiasLevel getBiasLevel() { return biasLevel; }
    public Map<IndicatorType, Double> getIndicatorScores() { return indicatorScores; }
    public List<String> getManipulationSignals() { return manipulationSignals; }
    public String getExplanation() { return explanation; }
}
