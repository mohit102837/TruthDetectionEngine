package com.tbde.indicator.detectors;

import com.tbde.indicator.*;
import com.tbde.preprocess.ProcessedText;

import java.util.ArrayList;
import java.util.List;

public class AbsoluteClaimDetector implements IndicatorDetector {

    private static final String[] ABSOLUTE_WORDS = {
            "always", "never", "everyone", "nobody",
            "guaranteed", "100%", "proven",
            "impossible", "best ever", "worst ever"
    };

    @Override
    public IndicatorResult detect(ProcessedText text) {

        List<String> evidence = new ArrayList<>();
        int matchCount = 0;

        for (String token : text.getTokens()) {
            for (String abs : ABSOLUTE_WORDS) {
                if (token.equalsIgnoreCase(abs)) {
                    matchCount++;
                    evidence.add(token);
                }
            }
        }

        double score = text.getTokens().isEmpty()
                ? 0.0
                : (double) matchCount / text.getTokens().size();

        return new IndicatorResult(
                IndicatorType.ABSOLUTE_CLAIMS,
                Math.min(score, 1.0),
                evidence
        );
    }

    @Override
    public IndicatorType getType() {
        return IndicatorType.ABSOLUTE_CLAIMS;
    }
}
