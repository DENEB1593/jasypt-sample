package com.example.jasypt;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JasyptPasswordGeneratorTest {

  @Autowired
  private ApplicationContext context;

  private static StandardPBEStringEncryptor encryptor;

  @BeforeAll
  static void setUp() {
    encryptor = encryptor();
  }

  private static StandardPBEStringEncryptor encryptor() {
    String key = "encoder1!";
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setAlgorithm("PBEWithMD5AndDES");
    encryptor.setPassword(key);
    return encryptor;
  }

  @Test
  public void generateJasyptPassword() {
    String password = "somepassword";
    String encryptedPassword = encryptor.encrypt(password);//qBAXuHH2wLf9rjtpXmW82LB9YFaAq32B
    assertEquals(password, encryptor.decrypt(encryptedPassword));
  }

  @Test
  public void checkAppProps() {
    Environment environment = context.getBean(Environment.class);
    assertEquals("encoder1!", environment.getProperty("jasypt.encryptor.password"));
  }

  @Test
  public void generateDBConfig() {
    String username = "user";
    String password = "user1234";

    String encUsername = encryptor.encrypt(username);
    String encPassword = encryptor.encrypt(password);

    System.out.println("encUsername: " + encUsername);
    System.out.println("encPassword: " + encPassword);

    assertEquals(username, encryptor.decrypt(encUsername));
    assertEquals(password, encryptor.decrypt(encPassword));
  }


}
