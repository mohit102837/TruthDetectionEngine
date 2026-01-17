package com.tbde.preprocess;

import java.util.List;

public class ProcessedText {

    private List<String> tokens;

    public ProcessedText() {}

    public ProcessedText(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<String> getTokens() {
        return tokens;
    }
}
