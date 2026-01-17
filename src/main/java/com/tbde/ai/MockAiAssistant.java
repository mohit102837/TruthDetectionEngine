package com.tbde.ai;

import java.util.List;

public class MockAiAssistant extends AiAssistant {

    @Override
    public String refineLanguage(String explanation) {
        return explanation;
    }

    @Override
    public List<String> suggestContextHints(String text) {
        return List.of();
    }

    @Override
    public boolean isSafe(String generatedText) {
        return true;
    }
}
