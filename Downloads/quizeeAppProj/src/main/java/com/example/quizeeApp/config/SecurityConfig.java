package com.example.quizeeApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class SecurityConfig {

//    @Value("${auth.jwt.publicKey}")
//    private String publicKeyPEM;
//
//    @Bean
//    public PublicKey publicKey() throws Exception {
//        // Clean the public key by removing the header and footer
//        String publicKeyPEMClean = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
//                                               .replace("-----END PUBLIC KEY-----", "")
//                                               .replaceAll("\\s+", "");
//
//        // Decode the Base64-encoded public key
//        byte[] encoded = Base64.getDecoder().decode(publicKeyPEMClean);
//
//        // Generate the PublicKey using the decoded bytes
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePublic(keySpec);
//    }
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
