package ru.itmo.blss.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.itmo.blss.data.dto.ReportDto;

import java.time.Duration;
import java.util.Collections;

import static ru.itmo.blss.report.config.KafkaConfig.TOPIC;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaService {
    private final Consumer<String, String> consumer;
    private final ObjectMapper objectMapper;
    private final ReportsService reportService;

    @EventListener
    public void receive(ContextRefreshedEvent event) throws JsonProcessingException {
        log.info("Start kafka listener");
        consumer.subscribe(Collections.singleton(TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                log.info("Receive {} with offset {}", record.value(), record.offset());
                final ReportDto reportDto = objectMapper.readValue(record.value(), ReportDto.class);
                reportService.saveNewReport(reportDto);
            }
        }
    }
}
