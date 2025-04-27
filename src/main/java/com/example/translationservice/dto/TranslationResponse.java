package com.example.translationservice.dto;

public class TranslationResponse {

    private String translatedContent;

    public TranslationResponse(String translatedContent) {
        this.translatedContent = translatedContent;
    }

    public String getTranslatedContent() {
        return translatedContent;
    }

    public void setTranslatedContent(String translatedContent) {
        this.translatedContent = translatedContent;
    }
}
