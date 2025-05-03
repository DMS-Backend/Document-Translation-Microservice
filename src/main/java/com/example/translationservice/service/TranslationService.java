package com.example.translationservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TranslationService {

    // Updated to use the correct model name in v1 API
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=AIzaSyApMz09WdA9QgXAybJpIouMyhBunlkhpeg";

    public String translateToSpanish(String content) {
        log.info("Translating content to Spanish: {}", content);
        
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", "Translate the following document title to Spanish:\n" + content);

        payload.put("contents", new Object[] {
            Map.of("parts", new Object[] { textPart })
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        log.info("Sending request to Gemini API");
        ResponseEntity<Map> response = restTemplate.postForEntity(GEMINI_API_URL, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("candidates")) {
                var candidates = (java.util.List<Map<String, Object>>) responseBody.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> firstCandidate = candidates.get(0);
                    Map<String, Object> contentObj = (Map<String, Object>) firstCandidate.get("content");
                    var parts = (java.util.List<Map<String, Object>>) contentObj.get("parts");
                    if (!parts.isEmpty()) {
                        String translation = (String) parts.get(0).get("text");
                        log.info("Translation completed successfully: {}", translation);
                        return translation;
                    }
                }
            }
        }

        log.error("Failed to get translation from Gemini API");
        throw new RuntimeException("Failed to get translation from Gemini API");
    }
}