package com.tbde.aggregate;

import com.tbde.indicator.*;
import com.tbde.indicator.detectors.*;
import com.tbde.preprocess.ProcessedText;

import java.util.ArrayList;
import java.util.List;

public class IndicatorAggregator {

    private final List<IndicatorDetector> detectors = List.of(
            new EmotionalLanguageDetector(),
            new AbsoluteClaimDetector(),
            new EvidencePresenceDetector()
    );


    public List<IndicatorResult> collect(ProcessedText text) {
        List<IndicatorResult> results = new ArrayList<>();
        for (IndicatorDetector detector : detectors) {
            results.add(detector.detect(text));
        }
        return results;
    }
}
