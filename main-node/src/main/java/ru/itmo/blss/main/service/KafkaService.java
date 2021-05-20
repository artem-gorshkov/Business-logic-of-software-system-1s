package ru.itmo.blss.main.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static ru.itmo.blss.main.config.KafkaConfig.TOPIC;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaService {
    private final Producer<String, String> producer;
    private final ObjectMapper objectMapper;

    public void send(Object obj) throws JsonProcessingException {
        final String msg = objectMapper.writeValueAsString(obj);
        try {
            final ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, msg);
            RecordMetadata metadata = producer.send(record).get();
            log.info("sent record {} with offset {}", record.value(), metadata.offset());
        } catch (InterruptedException|ExecutionException e) {
            log.error(e.getMessage(), e);
        }
    }
}
