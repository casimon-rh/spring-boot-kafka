package com.redhat.strema.controller;

import com.redhat.strema.service.YoutubeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
  //  private final Producer producer;
  private final YoutubeProducer producer;

  @Autowired
  KafkaController(YoutubeProducer producer) {
    this.producer = producer;
  }


//  @PostMapping("/publish")
//  public void sendMessageTKafkaTopic(@RequestParam("message") String message) {
//    this.producer.sendMessage((message));
//  }

  @PostMapping("/fetch")
  public void fetchFromYoutube(@RequestParam("url") String url) {
    this.producer.sendBytes(url);
  }
}
