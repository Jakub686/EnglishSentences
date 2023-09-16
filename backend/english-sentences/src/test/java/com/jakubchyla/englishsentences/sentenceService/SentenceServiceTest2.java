package com.jakubchyla.englishsentences.sentenceService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
public class SentenceServiceTest2 {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    SentenceService sentenceService;

    @MockBean
    private SentenceRepository sentenceRepository;

    @BeforeEach
    void setup() {
        Sentence sentence = new Sentence();
        sentence.setId(1L);
        sentence.setTextPl("abc");
        sentence.setTextEn("ABC");
        Mockito.when(sentenceRepository.findById(1L)).thenReturn(Optional.of(sentence));
    }


    @Test
    public void testSaveSentence() throws Exception {
        // Create a sample Sentence object for testing
        Sentence sentence = new Sentence();
        sentence.setId(1L);
        sentence.setTextPl("abc");
        sentence.setTextEn("ABC");

        // Mock the behavior of the sentenceService.saveSentence method
        Mockito.when(sentenceService.saveSentence(Mockito.any(Sentence.class))).thenReturn(sentence);

        // Convert the Sentence object to JSON
        String sentenceJson = objectMapper.writeValueAsString(sentence);

        // Perform the POST request to save the Sentence
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sentenceJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.textPl").value("abc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.textEn").value("ABC"));
    }

    @Test
    public void testGetSentenceById() {
        String textPl = "abc";
        Sentence sentence = sentenceService.getById(1L);
        assertEquals(textPl, sentence.getTextPl());
    }
}
