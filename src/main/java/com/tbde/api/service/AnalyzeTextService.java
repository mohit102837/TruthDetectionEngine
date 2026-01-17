package com.tbde.api.service;

import com.tbde.api.adapter.TrustReportAdapter;
import com.tbde.api.dto.AnalyzeTextRequest;
import com.tbde.engine.TruthBiasDetectionEngine;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeTextService {

    private final TruthBiasDetectionEngine engine;

    public AnalyzeTextService(TruthBiasDetectionEngine engine) {
        this.engine = engine;
    }

    public Object analyze(AnalyzeTextRequest req) {
        var report = engine.analyze(req.getText());
        return req.getDetailed()
                ? TrustReportAdapter.toDetailed(report)
                : TrustReportAdapter.toSimple(report);
    }
}
