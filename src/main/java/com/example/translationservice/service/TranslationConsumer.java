package com.example.translationservice.service;

import com.example.translationservice.dto.TranslationMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslationConsumer {

    private final TranslationService translationService;
    private final TranslationProducer translationProducer;

    @KafkaListener(topics = "${kafka.topic.translation-request:translation-requests}", 
                   containerFactory = "kafkaListenerContainerFactory",
                   groupId = "${spring.kafka.consumer.group-id:translation-service}")
    public void consumeTranslationRequest(TranslationMessageDTO request) {
        log.info("Received translation request for document: {}", request.getDocId());
        
        try {
            // Translate the document content using the existing service
            String translatedTitle = translationService.translateToSpanish(request.getDocName());
            
            // Send back the translation result
            translationProducer.sendTranslationResponse(request.getDocId(), translatedTitle);
            
            log.info("Translation completed and response sent for document: {}", request.getDocId());
        } catch (Exception e) {
            log.error("Error processing translation for document: {}", request.getDocId(), e);
        }
    }
}