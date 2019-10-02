package com.redhat.strema;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.Properties;

@Configuration
public class KafkaStreamsConfig {
  @Bean
  public KafkaStreams kafkaStreams(KafkaProperties kafkaProperties, @Value("${spring.application.name}") String appname){
    final Properties props = new Properties();

    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, appname);
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
    props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, JsonNode.class);

    final KafkaStreams kafkaStreams = new KafkaStreams(kafkaStreamTopology(), props);
    kafkaStreams.start();

    return kafkaStreams;
  }
  @Bean
  public Topology kafkaStreamTopology() {
    final StreamsBuilder streamsBuilder = new StreamsBuilder();
    streamsBuilder.stream("test");
    return streamsBuilder.build();
  }
}
