package com.redhat.strema.helper;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//TODO: add stereotype
public class FileHelper {
  // TODO: fix this
  @Value("${spring.file.location}")
  private String location;

  public void writeBytes(byte[] bytes) {
    int counter = 1;
    File file;
    while((file = new File(String.format("%s-%d",location, counter))).exists()) {
      counter ++;
    }
    try (OutputStream os = new FileOutputStream(file)) {
      os.write(bytes);
    } catch (IOException ioe) {
      System.err.println("Error on writing");
      ioe.printStackTrace();
    }
  }
}
