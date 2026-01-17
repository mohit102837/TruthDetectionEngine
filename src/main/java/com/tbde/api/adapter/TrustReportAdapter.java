package com.tbde.api.adapter;

import com.tbde.api.dto.*;
import com.tbde.report.TrustReport;

import java.util.stream.Collectors;

public class TrustReportAdapter {

    public static SimpleTrustResponse toSimple(TrustReport report) {
        SimpleTrustResponse res = new SimpleTrustResponse();
        res.trustScore = report.getOverallScore();
        res.riskLevel = report.getBiasLevel().name();
        res.summary = report.getExplanation();
        return res;
    }

    public static DetailedTrustResponse toDetailed(TrustReport report) {
        DetailedTrustResponse res = new DetailedTrustResponse();
        res.trustScore = report.getOverallScore();
        res.riskLevel = report.getBiasLevel().name();
        res.explanation = report.getExplanation();
        res.indicatorScores =
                report.getIndicatorScores().entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                e -> e.getKey().name(),
                                e -> e.getValue()
                        ));
        return res;
    }
}
