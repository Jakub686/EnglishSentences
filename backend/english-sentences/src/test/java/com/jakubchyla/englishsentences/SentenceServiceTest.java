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
import java.util.Optional;

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

        //given
        Sentence sentence1 = new Sentence();
        sentence1.setId(1L);
        sentence1.setTextEn("Text1");
        sentence1.setTextPl("Text1");

        //when
//        when(sentenceRepository.findByText("Text1")).thenReturn(Arrays.asList(sentence1));

        //then
        List<Sentence> result = sentenceService.findByText("Text1");
        assertEquals(1, result.size());
        assertEquals("Text1", result.get(0).getTextEn());
    }

    @Test
    public void testGetRandomSentence() {
        Long optionalLong1 = 1L;
        Long optionalLong2 = 2L;
        List<Long> optionalLongList = Arrays.asList(optionalLong1, optionalLong2);
        Optional<List<Long>> optionalIds = Optional.of(optionalLongList);

        Sentence sentence = new Sentence();
        sentence.setId(1L);
        sentence.setTextEn("Text1");
        sentence.setTextPl("Text1");

        when(sentenceRepository.findAllIds()).thenReturn(optionalIds);
        when(sentenceRepository.findById(1L)).thenReturn(Optional.of(sentence));

        Sentence result = sentenceService.getRandomSentence();
        System.out.println(result);

        assertEquals("Text1", result.getTextEn());
    }

}
