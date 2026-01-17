package com.tbde.ai;

import java.util.ArrayList;
import java.util.List;

public class SimulatedAiAssistant extends AiAssistant {

    @Override
    public String refineLanguage(String explanation) {
        // Simulated "AI" polish (rule-based)
        if (explanation == null || explanation.isBlank()) {
            return explanation;
        }

        return "In simple terms: " + explanation;
    }

    @Override
    public List<String> suggestContextHints(String text) {
        List<String> hints = new ArrayList<>();

        if (text == null) return hints;

        if (text.length() > 200) {
            hints.add("Long-form content detected");
        }

        if (text.contains("!") || text.contains("must")) {
            hints.add("Persuasive or emotional tone detected");
        }

        if (text.contains("?")) {
            hints.add("Rhetorical questioning present");
        }

        return hints;
    }

    @Override
    public boolean isSafe(String generatedText) {
        // Always safe in simulation
        return true;
    }
}
