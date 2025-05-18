package com.example.quizeeApp.service;


import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service()
public class AuthValidatorService {
	private final RestTemplate restTemplate;
    private final String authValidateUrl = "http://localhost:9090/auth/validate";

    public AuthValidatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                authValidateUrl, HttpMethod.GET, request, Map.class);
            System.out.println("Response from authApp: " + response.getBody());
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            System.out.println("Error response: " + e.getResponseBodyAsString());
            throw new RuntimeException("Auth failed: " + e.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Auth call failed");
        }
    }
}
