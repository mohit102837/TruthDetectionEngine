package com.tbde.ai;

import java.util.List;

public abstract class AiAssistant {

    public abstract String refineLanguage(String explanation);

    public abstract List<String> suggestContextHints(String text);

    public abstract boolean isSafe(String generatedText);
}
