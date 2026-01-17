package com.tbde.preprocess;

import java.util.Arrays;
import java.util.List;

public class TextPreprocessor {

    public ProcessedText preprocess(String normalizedText) {
        List<String> tokens = Arrays.asList(normalizedText.split("\\s+"));
        return new ProcessedText(tokens);
    }
}
