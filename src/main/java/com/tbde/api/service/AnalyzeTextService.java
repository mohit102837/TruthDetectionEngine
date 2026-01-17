package com.tbde.api.service;

import com.tbde.api.adapter.TrustReportAdapter;
import com.tbde.api.dto.*;
import com.tbde.engine.TruthBiasDetectionEngine;

public class AnalyzeTextService {

    private final TruthBiasDetectionEngine engine;

    public AnalyzeTextService(TruthBiasDetectionEngine engine) {
        this.engine = engine;
    }

    public Object analyze(AnalyzeTextRequest req) {
        var report = engine.analyze(req.text);
        if (req.detailed) {
            return TrustReportAdapter.toDetailed(report);
        }
        return TrustReportAdapter.toSimple(report);
    }
}
