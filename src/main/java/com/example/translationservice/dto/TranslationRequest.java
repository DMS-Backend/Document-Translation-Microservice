package com.example.translationservice.dto;

import jakarta.validation.constraints.NotBlank;

public class TranslationRequest {

    @NotBlank
    private String docName;

    @NotBlank
    private String docContent;

    // Getters and setters
    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocContent() {
        return docContent;
    }

    public void setDocContent(String docContent) {
        this.docContent = docContent;
    }
}
