package com.example.translationservice.service;

import com.example.translationservice.dto.TranslationMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslationProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    @Value("${kafka.topic.translation-response:translation-responses}")
    private String translationResponseTopic;

    public void sendTranslationResponse(String docId, String translatedContent) {
        TranslationMessageDTO message = new TranslationMessageDTO(docId, translatedContent);
        
        log.info("Sending translation response for document: {}", docId);
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(translationResponseTopic, docId, message);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Translation response sent successfully for document: {}, offset: {}", 
                         docId, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send translation response for document: {}", docId, ex);
            }
        });
    }
}