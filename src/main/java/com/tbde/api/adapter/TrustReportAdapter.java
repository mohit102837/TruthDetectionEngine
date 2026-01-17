package com.tbde.api.adapter;

import com.tbde.api.dto.DetailedTrustResponse;
import com.tbde.api.dto.SimpleTrustResponse;
import com.tbde.report.TrustReport;

import java.util.stream.Collectors;

public class TrustReportAdapter {

    // ---------- SIMPLE RESPONSE ----------
    public static SimpleTrustResponse toSimple(TrustReport report) {
        SimpleTrustResponse res = new SimpleTrustResponse();
        res.trustScore = report.getOverallScore();
        res.riskLevel = verdictFor(report.getOverallScore());
        res.summary = buildUserFriendlySummary(report);
        return res;
    }

    // ---------- DETAILED RESPONSE ----------
    public static DetailedTrustResponse toDetailed(TrustReport report) {
        DetailedTrustResponse res = new DetailedTrustResponse();
        res.trustScore = report.getOverallScore();
        res.riskLevel = verdictFor(report.getOverallScore());
        res.explanation = buildUserFriendlySummary(report);
        res.indicatorScores =
                report.getIndicatorScores()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                e -> e.getKey().name(),
                                e -> e.getValue()
                        ));
        return res;
    }

    // ---------- HUMAN FRIENDLY VERDICT ----------
    private static String verdictFor(double score) {
        if (score >= 0.75) {
            return "This content appears trustworthy";
        } else if (score >= 0.5) {
            return "This content should be read with caution";
        } else {
            return "This content may not be reliable";
        }
    }

    // ---------- HUMAN FRIENDLY SUMMARY ----------
    private static String buildUserFriendlySummary(TrustReport report) {

        if (report.getOverallScore() >= 0.75) {
            return "The text presents information in a balanced and factual manner, which increases its reliability.";
        }

        if (report.getOverallScore() >= 0.5) {
            return "The text contains some strong or emotional language, so it is better to read it carefully.";
        }

        return "The text uses strong claims or emotional wording, which can reduce how reliable it is.";
    }
}
