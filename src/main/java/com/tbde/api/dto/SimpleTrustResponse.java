package com.tbde.api.dto;

public class SimpleTrustResponse {
    public int trustScore;   // 0–100
    public String riskLevel; // LOW / MEDIUM / HIGH
    public String summary;   // user-friendly explanation
}
