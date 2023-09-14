package com.jakubchyla.englishsentences;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SentenceService sentenceService;

    @Test
    public void testSaveSentence() throws Exception {
        // Create a sample Sentence object for testing
        Sentence sampleSentence = new Sentence();
        sampleSentence.setTextEn("Sample English Text");
        sampleSentence.setTextPl("Sample Polish Text");

        // Define the expected response when sentenceService.saveSentence is called
        Mockito.when(sentenceService.saveSentence(Mockito.any(Sentence.class))).thenReturn(sampleSentence);

        // Perform a POST request to the /api/v1/demo-controller/ endpoint
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/secured/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sampleSentence)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.textEn").value("Sample English Text"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.textPl").value("Sample Polish Text"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSayHelloEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/demo-controller")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello from secured endpoint"));
    }
}