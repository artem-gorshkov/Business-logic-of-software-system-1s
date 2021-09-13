package ru.itmo.blss.report.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.blss.report.property.KafkaProperty;

import java.util.Properties;

@Configuration
@AllArgsConstructor
public class KafkaConfig {
    public static final String TOPIC = "report-events";
    private final KafkaProperty kafkaProperty;

    @Bean
    public Consumer<String, String> kafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaProperty.getServer());
        props.setProperty("group.id", kafkaProperty.getGroupId());
        props.setProperty("enable.auto.commit", "true");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(props);
    }
}
