package com.redhat.strema.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.strema.helper.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class YoutubeConsumer {
  private final Logger logger = LoggerFactory.getLogger(YoutubeConsumer.class);

  @Autowired
  private final ObjectMapper mapper = new ObjectMapper();

  @KafkaListener(topics = "youtube", groupId = "group_id")
  public void consume(byte[] bytes) throws IOException {
    logger.info("****** -> Consumed video!");
    FileHelper fileHelper = new FileHelper();
    fileHelper.writeBytes(bytes);
  }
}
