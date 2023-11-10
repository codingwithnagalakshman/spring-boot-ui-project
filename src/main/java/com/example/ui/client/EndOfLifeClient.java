package com.example.ui.client;

import com.example.ui.domain.Software;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Service
public class EndOfLifeClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${serviceUrl}")
    private String serviceUrl;

    public String[] getAllProducts() {
        StringJoiner stringJoiner = new StringJoiner("/","", "");
        stringJoiner
                .add(serviceUrl)
                .add("all-products");
        return restTemplate.getForEntity(stringJoiner.toString(), String[].class).getBody();
    }

    public Software getSingleCycleDetails(String cycle, String product) {
        StringJoiner stringJoiner = new StringJoiner("/","", "");
        stringJoiner
                .add(serviceUrl)
                .add("single-cycle-details")
                .add(product)
                .add(cycle);
        return restTemplate.getForEntity(stringJoiner.toString(), Software.class).getBody();
    }

    public List<Software> getAllDetails(String product) {
        StringJoiner stringJoiner = new StringJoiner("/","", "");
        stringJoiner
                .add(serviceUrl)
                .add("get-all-details")
                .add(product);
        ResponseEntity<Software[]> forEntity = restTemplate.getForEntity(stringJoiner.toString(), Software[].class);
        return Arrays.asList(Objects.requireNonNull(forEntity.getBody()));
    }

}
