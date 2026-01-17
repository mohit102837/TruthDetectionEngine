package com.tbde.api.dto;

import java.util.Map;

public class DetailedTrustResponse {
    public int trustScore;
    public String riskLevel;
    public Map<String, Double> indicatorScores;
    public String explanation; // detailed reasoning
}
