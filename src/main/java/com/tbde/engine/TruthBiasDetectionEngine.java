package com.tbde.engine;

import com.tbde.input.InputNormalizer;
import com.tbde.preprocess.TextPreprocessor;
import com.tbde.aggregate.IndicatorAggregator;
import com.tbde.scoring.ScoringEngine;
import com.tbde.explain.*;
import com.tbde.report.TrustReport;

public class TruthBiasDetectionEngine {

    private final InputNormalizer inputNormalizer;
    private final TextPreprocessor textPreprocessor;
    private final IndicatorAggregator indicatorAggregator;
    private final ScoringEngine scoringEngine;
    private final ExplanationEngine explanationEngine;
    private final UserFriendlyExplanationBuilder userExplanationBuilder;


    public TruthBiasDetectionEngine(
            InputNormalizer inputNormalizer,
            TextPreprocessor textPreprocessor,
            IndicatorAggregator indicatorAggregator,
            ScoringEngine scoringEngine,
            ExplanationEngine explanationEngine,
            UserFriendlyExplanationBuilder userExplanationBuilder
    ) {
        this.inputNormalizer = inputNormalizer;
        this.textPreprocessor = textPreprocessor;
        this.indicatorAggregator = indicatorAggregator;
        this.scoringEngine = scoringEngine;
        this.explanationEngine = explanationEngine;
        this.userExplanationBuilder = userExplanationBuilder;
    }

    public TrustReport analyze(String rawText) {
        String normalized = inputNormalizer.normalize(rawText);
        var processed = textPreprocessor.preprocess(normalized);
        var indicators = indicatorAggregator.collect(processed);
        var report = scoringEngine.score(indicators);
        var explained = explanationEngine.explain(report);

        String simpleSummary =
                userExplanationBuilder.buildSimpleSummary(explained);

        // Replace explanation with simple one (Phase-2 rule)
        return new TrustReport(
                explained.getOverallScore(),
                explained.getBiasLevel(),
                explained.getIndicatorScores(),
                explained.getManipulationSignals(),
                simpleSummary
        );
    }
    public static void main(String[] args) {

        TruthBiasDetectionEngine engine =
                new TruthBiasDetectionEngine(
                        new com.tbde.input.InputNormalizer(),
                        new com.tbde.preprocess.TextPreprocessor(),
                        new com.tbde.aggregate.IndicatorAggregator(),
                        new com.tbde.scoring.ScoringEngine(),
                        new com.tbde.explain.ExplanationEngine(),
                        new com.tbde.explain.UserFriendlyExplanationBuilder()
                );

        String testText =
                "This is the best ever guaranteed solution. Everyone must follow this.";

        var report = engine.analyze(testText);

        System.out.println("=== USER FRIENDLY OUTPUT ===");
        System.out.println(report.getExplanation());
    }



}

