package com.example.emotion_chat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("ncp")
public class NcpConfig {
  private String clientId ;
  private String clientSecret;
  private String apiURL;
}
