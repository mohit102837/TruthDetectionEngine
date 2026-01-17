package com.tbde.engine;

import com.tbde.input.InputNormalizer;
import com.tbde.preprocess.TextPreprocessor;
import com.tbde.aggregate.IndicatorAggregator;
import com.tbde.scoring.ScoringEngine;
import com.tbde.explain.ExplanationEngine;
import com.tbde.report.TrustReport;

public class TruthBiasDetectionEngine {

    private final InputNormalizer inputNormalizer;
    private final TextPreprocessor textPreprocessor;
    private final IndicatorAggregator indicatorAggregator;
    private final ScoringEngine scoringEngine;
    private final ExplanationEngine explanationEngine;

    public TruthBiasDetectionEngine(
            InputNormalizer inputNormalizer,
            TextPreprocessor textPreprocessor,
            IndicatorAggregator indicatorAggregator,
            ScoringEngine scoringEngine,
            ExplanationEngine explanationEngine
    ) {
        this.inputNormalizer = inputNormalizer;
        this.textPreprocessor = textPreprocessor;
        this.indicatorAggregator = indicatorAggregator;
        this.scoringEngine = scoringEngine;
        this.explanationEngine = explanationEngine;
    }

    public TrustReport analyze(String rawText) {
        String normalized = inputNormalizer.normalize(rawText);
        var processed = textPreprocessor.preprocess(normalized);
        var indicators = indicatorAggregator.collect(processed);
        var report = scoringEngine.score(indicators);
        return explanationEngine.explain(report);
    }


}

