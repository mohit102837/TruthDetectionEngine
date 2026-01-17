package com.tbde.indicator;

import java.util.List;

public class IndicatorResult {

    private final IndicatorType type;
    private final double score; // 0.0 – 1.0
    private final List<String> evidence;

    public IndicatorResult(IndicatorType type, double score, List<String> evidence) {
        this.type = type;
        this.score = score;
        this.evidence = evidence;
    }

    public IndicatorType getType() {
        return type;
    }

    public double getScore() {
        return score;
    }

    public List<String> getEvidence() {
        return evidence;
    }
}
