package com.example.mindspace.openai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RecommendationAPI {

    @Value("${openapi.auth-key}")
    private String openApiKey;

    public Void getTherapistsRecommendation() {
        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders(Map.of("a", "a"))
        return null;
    }
}
