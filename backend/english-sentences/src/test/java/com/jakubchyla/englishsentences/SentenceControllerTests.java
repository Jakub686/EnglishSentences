package com.jakubchyla.englishsentences;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SentenceControllerTests {
//    SPRING_PROFILES_ACTIVE=test
    @LocalServerPort
    private int port;

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
    public void testAddSentence() {
        Sentence sentence = new Sentence();
        sentence.setTextEn("hi");
        sentence.setTextPl("czesc");
        Sentence response = restTemplate.postForObject(baseUrl, sentence, Sentence.class);
        assertEquals("hi", response.getTextEn());
        assertEquals(1, repo.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('1', 'testEn', 'testPl')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testGetSentences() {
        List<Sentence> products = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(1, products.size());
        assertEquals(1, repo.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('1', 'testEn', 'testPl')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteSentence(){
        int recordCount=repo.findAll().size();
        assertEquals(1, recordCount);
        restTemplate.delete(baseUrl+"/{id}", 1);
        assertEquals(0, repo.findAll().size());

    }
}
