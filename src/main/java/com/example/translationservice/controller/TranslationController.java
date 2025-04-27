package com.example.translationservice.controller;

import com.example.translationservice.dto.TranslationRequest;
import com.example.translationservice.dto.TranslationResponse;
import com.example.translationservice.service.TranslationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/translate")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping
    public TranslationResponse translateDocument(@Valid @RequestBody TranslationRequest request) {
        String translated = translationService.translateToSpanish(request.getDocContent());
        return new TranslationResponse(translated);
    }
}
