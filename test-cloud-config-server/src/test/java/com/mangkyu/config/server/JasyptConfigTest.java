package com.mangkyu.config.server;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

class JasyptConfigTest {

    @Disabled
    @Test
    void privateKey암호화() throws IOException {
        ClassPathResource resource = new ClassPathResource("privatekey.txt");
        String privateKey = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm(JasyptConfig.ALGORITHM);
        standardPBEStringEncryptor.setPassword(JasyptConfig.KEY);

        String enc = standardPBEStringEncryptor.encrypt(privateKey);
        System.out.printf("enc = ENC(%s)\n", enc);
        System.out.printf("dec = %s\n", standardPBEStringEncryptor.decrypt(enc));
    }

    @Test
    void string암호화() {
        String privateKey = "myKey";
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm(JasyptConfig.ALGORITHM);
        standardPBEStringEncryptor.setPassword(JasyptConfig.KEY);

        String enc = standardPBEStringEncryptor.encrypt(privateKey);
        System.out.printf("enc = ENC(%s)\n", enc);
        System.out.printf("dec = %s\n", standardPBEStringEncryptor.decrypt(enc));
    }

}