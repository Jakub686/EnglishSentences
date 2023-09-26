package com.jakubchyla.englishsentences;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@SpringBootTest
class SentenceServiceTest {
    @Autowired
    private SentenceService sentenceService;

    @MockBean
    private SentenceRepository sentenceRepository;


    @Test
    public void testFindByText() {
        Sentence sentence1 = new Sentence();
        sentence1.setId(1L);
        sentence1.setTextEn("Text1");
        sentence1.setTextPl("Text1");
        when(sentenceRepository.findByText("Text1")).thenReturn(Arrays.asList(sentence1));

        List<Sentence> result = sentenceService.findByText("Text1");
        assertEquals(1, result.size());
        assertEquals("Text1", result.get(0).getTextEn());
    }

}
