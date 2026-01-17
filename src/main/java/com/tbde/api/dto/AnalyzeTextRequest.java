package com.tbde.api.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnalyzeTextRequest {

    @NotBlank(message = "Text cannot be empty")
    private String text;

    @NotNull(message = "Detailed flag must be true or false")
    private Boolean detailed;

    public String getText() {
        return text;
    }

    public Boolean getDetailed() {
        return detailed;
    }
}
