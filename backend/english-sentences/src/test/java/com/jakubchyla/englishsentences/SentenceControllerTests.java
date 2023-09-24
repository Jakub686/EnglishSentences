package com.jakubchyla.englishsentences;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceController.dto.RandomDTO;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1/secured");
    }

    @Test
    public void testAddSentence() {
        Sentence sentence = new Sentence();
        sentence.setTextEn("hi");
        sentence.setTextPl("czesc");

        ResponseEntity<Sentence> responseEntity = restTemplate.exchange(
                baseUrl + "/", HttpMethod.POST, new HttpEntity<>(sentence), Sentence.class
        );

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("hi", responseEntity.getBody().getTextEn());
        assertEquals(1, repo.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('1', 'hello', 'czesc')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testGetSentencesById() {
        ParameterizedTypeReference<Sentence> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<Sentence> responseEntity = restTemplate.exchange(
                baseUrl + "/{id}", HttpMethod.GET, null, responseType, 1);

        Sentence sentence = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("hello", sentence.getTextEn());

    }

    @Test
    @Sql(statements = "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('1', 'testEn', 'testPl')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteSentence() {
        assertEquals(1, repo.findAll().size());

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                baseUrl + "/{id}", HttpMethod.DELETE, null, Void.class, 1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, repo.findAll().size());
    }

    @Test
    @Sql(statements = {
            "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('1', 'testEn', 'testPl')",
            "INSERT INTO FAVORITE (id, sentence_id, user_id) VALUES ('1', '1', '1')",
            "INSERT INTO _USER (id, firstname, lastname, email, role) VALUES ('1', 'jakub', 'chyla', 'jakub@gmail.com', 'USER')",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testGetSentenceForLogged() {
        ResponseEntity<RandomDTO> responseEntity = restTemplate.exchange(
                baseUrl + "/random-for-user?email=jakub@gmail.com&fav=true", HttpMethod.GET, null, RandomDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertAll(
                () -> assertEquals("testEn", responseEntity.getBody().textEn()),
                () -> assertNotNull(responseEntity.getBody().favorite()),
                () -> assertTrue(responseEntity.getBody().favorite())
        );
    }

    @Test
    @Sql(statements = {
            "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('1', 'testEnglish', 'testPolish')",
            "INSERT INTO FAVORITE (id, sentence_id, user_id) VALUES ('1', '1', '1')",
            "INSERT INTO _USER (id, firstname, lastname, email, role) VALUES ('1', 'jakub', 'chyla', 'jakub@gmail.com', 'USER')",
            "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('2', 'testEnglish2', 'testPolish2')",
            "INSERT INTO FAVORITE (id, sentence_id, user_id) VALUES ('2', '2', '1')",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testGetSentencesForLogged() {

        ParameterizedTypeReference<List<RandomDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<RandomDTO>> responseEntity = restTemplate.exchange(
                baseUrl + "/sentences-logged?email=jakub@gmail.com&fav=true", HttpMethod.GET, null, responseType);

        List<RandomDTO> sentenceList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(sentenceList.isEmpty());
        assertEquals("testEnglish", sentenceList.get(0).textEn());
        assertEquals(2, sentenceList.size());

    }

    @Test
    @Sql(statements = {
            "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('1', 'We went to school', 'Poszlismy do szkoly')",
            "INSERT INTO FAVORITE (id, sentence_id, user_id) VALUES ('1', '1', '1')",
            "INSERT INTO _USER (id, firstname, lastname, email, role) VALUES ('1', 'jakub', 'chyla', 'jakub@gmail.com', 'USER')",
            "INSERT INTO SENTENCES (id, text_en, text_pl) VALUES ('2', 'I like pancakes', 'Lubie placki')",
            "INSERT INTO FAVORITE (id, sentence_id, user_id) VALUES ('2', '2', '1')",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testGetSearch() {

        ParameterizedTypeReference<List<Sentence>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<Sentence>> responseEntity = restTemplate.exchange(
                baseUrl + "/search?textEn=pancakes", HttpMethod.GET, null, responseType);

        List<Sentence> sentenceList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(sentenceList.isEmpty());
        assertEquals("I like pancakes", sentenceList.get(0).getTextEn());
        assertEquals(1, sentenceList.size());
    }

}
