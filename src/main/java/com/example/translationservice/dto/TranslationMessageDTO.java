package com.example.translationservice.dto;

import java.io.Serializable;

public class TranslationMessageDTO implements Serializable {
    private String docId;
    private String docName;
    private String docContent;
    private String translatedContent;

    public TranslationMessageDTO() {
    }

    public TranslationMessageDTO(String docId, String docName, String docContent) {
        this.docId = docId;
        this.docName = docName;
        this.docContent = docContent;
    }

    // For response
    public TranslationMessageDTO(String docId, String translatedContent) {
        this.docId = docId;
        this.translatedContent = translatedContent;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

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

    public String getTranslatedContent() {
        return translatedContent;
    }

    public void setTranslatedContent(String translatedContent) {
        this.translatedContent = translatedContent;
    }
}