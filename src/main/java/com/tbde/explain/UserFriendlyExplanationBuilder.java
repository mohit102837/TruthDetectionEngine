package com.tbde.explain;

import com.tbde.indicator.IndicatorType;
import com.tbde.report.TrustReport;
import com.tbde.scoring.BiasLevel;

import java.util.Map;

public class UserFriendlyExplanationBuilder {

    public String buildSimpleSummary(TrustReport report) {

        StringBuilder sb = new StringBuilder();

        // Line 1: Overall message
        sb.append(overallLine(report.getBiasLevel(), report.getOverallScore()));
        sb.append(" ");

        // Line 2: Main reasons (simple)
        for (Map.Entry<IndicatorType, Double> e : report.getIndicatorScores().entrySet()) {
            String reason = simpleReason(e.getKey(), e.getValue());
            if (reason != null) {
                sb.append(reason).append(" ");
            }
        }

        // Line 3: Closing (calm guidance)
        sb.append(closingLine(report.getBiasLevel()));

        return sb.toString().trim();
    }

    private String overallLine(BiasLevel level, int score) {
        switch (level) {
            case LOW:
                return "Is content par bharosa karna kaafi safe lagta hai.";
            case MEDIUM:
                return "Is content mein kuch cheezein dhyaan dene layak hain.";
            case HIGH:
                return "Is content par bina check kiye bharosa karna risky ho sakta hai.";
            default:
                return "";
        }
    }

    private String simpleReason(IndicatorType type, double score) {
        if (score < 0.10) return null;

        switch (type) {
            case EMOTIONAL_LANGUAGE:
                return "Isme zyada emotional words use hue hain.";
            case ABSOLUTE_CLAIMS:
                return "Isme extreme ya pakke claims kiye gaye hain (jaise always, guaranteed).";
            case EVIDENCE_PRESENCE:
                return "Isme proper evidence ya source kam dikh raha hai.";
            case OPINION_FACT_RATIO:
                return "Facts ke muqable opinions zyada lag rahe hain.";
            case URGENCY_FEAR:
                return "Language thodi urgency ya fear create karti hai.";
            default:
                return null;
        }
    }

    private String closingLine(BiasLevel level) {
        switch (level) {
            case LOW:
                return "Phir bhi important points ko cross-check karna hamesha achha hota hai.";
            case MEDIUM:
                return "Better hoga ki aap is topic par ek-do aur sources bhi dekhein.";
            case HIGH:
                return "Aage badhne se pehle is information ko verify karna sahi rahega.";
            default:
                return "";
        }
    }
}
