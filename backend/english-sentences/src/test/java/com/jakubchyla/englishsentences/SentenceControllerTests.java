package com.jakubchyla.englishsentences;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SentenceControllerTests {

    @LocalServerPort
    private int port;

//    private String baseUrl = "http://localhost:8080/api/v1/secured/";
private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private SentenceRepository repo;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1/secured/");
    }

    @Test
    public void testAddProduct() {
        Sentence sentence = new Sentence();
        sentence.setTextEn("hi");
        sentence.setTextPl("czesc");
        Sentence response = restTemplate.postForObject(baseUrl, sentence, Sentence.class);
        assertEquals("hi", response.getTextEn());
        assertEquals(3, repo.findAll().size());
    }
}
