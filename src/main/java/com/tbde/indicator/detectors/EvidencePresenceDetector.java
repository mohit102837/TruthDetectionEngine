package com.tbde.indicator.detectors;

import com.tbde.indicator.*;
import com.tbde.preprocess.ProcessedText;

import java.util.ArrayList;
import java.util.List;

public class EvidencePresenceDetector implements IndicatorDetector {

    private static final String[] EVIDENCE_KEYWORDS = {
            "according", "reported", "study", "research",
            "data", "survey", "report", "statistics",
            "http", "https", "www"
    };

    @Override
    public IndicatorResult detect(ProcessedText text) {

        List<String> evidenceFound = new ArrayList<>();
        int matches = 0;

        for (String token : text.getTokens()) {
            for (String key : EVIDENCE_KEYWORDS) {
                if (token.toLowerCase().contains(key)) {
                    matches++;
                    evidenceFound.add(token);
                }
            }
        }

        // IMPORTANT:
        // Low evidence = higher risk score
        double score;
        if (matches == 0) {
            score = 1.0; // no evidence at all → high risk
        } else if (matches < 3) {
            score = 0.6; // weak evidence
        } else {
            score = 0.2; // reasonable evidence
        }

        return new IndicatorResult(
                IndicatorType.EVIDENCE_PRESENCE,
                score,
                evidenceFound
        );
    }

    @Override
    public IndicatorType getType() {
        return IndicatorType.EVIDENCE_PRESENCE;
    }
}
