package com.tbde.explain;

import com.tbde.indicator.IndicatorType;
import com.tbde.report.TrustReport;
import com.tbde.scoring.BiasLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExplanationEngine {

    public TrustReport explain(TrustReport report) {

        List<String> lines = new ArrayList<>();

        // Headline summary
        lines.add("Overall trust score is " + report.getOverallScore()
                + " with bias level " + report.getBiasLevel() + ".");

        // Indicator-wise reasoning
        for (Map.Entry<IndicatorType, Double> e : report.getIndicatorScores().entrySet()) {
            String line = explainIndicator(e.getKey(), e.getValue());
            if (line != null) {
                lines.add(line);
            }
        }

        // Bias guidance (not advice)
        lines.add(biasGuidance(report.getBiasLevel()));

        String explanation = String.join(" ", lines);

        // Return a NEW TrustReport (immutability)
        return new TrustReport(
                report.getOverallScore(),
                report.getBiasLevel(),
                report.getIndicatorScores(),
                report.getManipulationSignals(),
                explanation
        );
    }

    private String explainIndicator(IndicatorType type, double score) {
        if (score < 0.10) return null; // ignore negligible signals

        switch (type) {
            case EMOTIONAL_LANGUAGE:
                return "Noticeable emotional language was detected, which can influence perception.";
            case ABSOLUTE_CLAIMS:
                return "Presence of absolute claims suggests reduced nuance.";
            case OPINION_FACT_RATIO:
                return "High opinion-to-fact ratio indicates subjective framing.";
            case EVIDENCE_PRESENCE:
                return "Limited supporting evidence weakens reliability.";
            case URGENCY_FEAR:
                return "Urgent or fear-inducing language may pressure quick acceptance.";
            default:
                return null;
        }
    }

    private String biasGuidance(BiasLevel level) {
        switch (level) {
            case LOW:
                return "Content appears largely neutral with minimal manipulation risk.";
            case MEDIUM:
                return "Content shows mixed signals; consider cross-checking key claims.";
            case HIGH:
                return "Content shows strong manipulation signals; approach with caution.";
            default:
                return "";
        }
    }
}
