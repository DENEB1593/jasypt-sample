package com.example.jasypt.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jasypt.encryptor")
public class JasyptConfigure {

  private String password;

  public JasyptConfigure() { }

  public JasyptConfigure(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
