package com.tbde.config;

import com.tbde.indicator.IndicatorType;

import java.util.EnumMap;
import java.util.Map;

public class IndicatorWeightConfig {

    private static final Map<IndicatorType, Double> WEIGHTS = new EnumMap<>(IndicatorType.class);

    static {
        WEIGHTS.put(IndicatorType.EMOTIONAL_LANGUAGE, 0.35);
        WEIGHTS.put(IndicatorType.ABSOLUTE_CLAIMS, 0.25);
        WEIGHTS.put(IndicatorType.OPINION_FACT_RATIO, 0.20);
        WEIGHTS.put(IndicatorType.EVIDENCE_PRESENCE, 0.10);
        WEIGHTS.put(IndicatorType.URGENCY_FEAR, 0.10);
    }

    public static double getWeight(IndicatorType type) {
        return WEIGHTS.getOrDefault(type, 0.0);
    }
}
