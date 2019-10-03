package com.redhat.strema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class YoutubeProducer {

  private static final String TOPIC = "youtube";
  private final Logger logger = LoggerFactory.getLogger(YoutubeProducer.class);

  @Autowired
  private KafkaTemplate<String, byte[]> kafkaTemplate;

  public void sendBytes(String url) {
    logger.info(String.format("***** -> Fetching from -> %s", url));
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (InputStream is = new URL(url).openStream()) {
      byte[] byteChunk = new byte[4098];
      int n;
      while ((n = is.read(byteChunk)) > 0) {
        baos.write(byteChunk, 0, n);
      }
    } catch (IOException ioe) {
      System.err.println("Error on reading");
      ioe.printStackTrace();
    }
    if(baos.size() > 0) {
      this.kafkaTemplate.send(TOPIC, baos.toByteArray());
    }
  }
}
