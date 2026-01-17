package com.tbde.ai;

import java.util.List;

public class MockAiAssistant extends AiAssistant {

    public String refineLanguage(String explanation) {
        // No AI, return as-is
        return explanation;
    }

    public List<String> suggestContextHints(String text) {
        return List.of(); // no hints
    }

    public boolean isSafe(String generatedText) {
        return true;
    }
}
