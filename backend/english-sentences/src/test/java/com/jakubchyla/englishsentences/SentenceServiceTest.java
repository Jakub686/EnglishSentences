package com.jakubchyla.englishsentences;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceController.dto.RandomDTO;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import com.jakubchyla.englishsentences.user.User;
import com.jakubchyla.englishsentences.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
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

    @MockBean
    private UserRepository userRepository;

    @Test
    public void test_findSentenceListForEmail_whenValid_returnsRandomDTOList() {

        //given
        Sentence sentence1 = new Sentence();
        sentence1.setId(1L);
        sentence1.setTextEn("Text1");
        sentence1.setTextPl("Text1");

        Sentence sentence2 = new Sentence();
        sentence2.setId(2L);
        sentence2.setTextEn("Text2");
        sentence2.setTextPl("Text2");
        List<Sentence> sentences = new ArrayList<>();
        sentences.add(sentence1);
        sentences.add(sentence2);

        User user = new User();
        user.setId(1L);
        user.setFirstname("Jakub");
        user.setLastname("Chyla");
        user.setEmail("jakub@gmail.com");

        //when
        when(sentenceRepository.findAll()).thenReturn(sentences);
        when(userRepository.findByEmail("jakub@gmail.com")).thenReturn(Optional.of(user));
        List<RandomDTO> result = sentenceService.findSentenceListForEmail("jakub@gmail.com");

        //then
        assertEquals("Text2", result.get(1).textEn());
        assertEquals(2, result.size());

    }

    @Test
    public void test_SentenceService_FindByText_WithValidText_ReturnsListWithOneSentence() {

        //given
        Sentence sentence = new Sentence();
        sentence.setId(1L);
        sentence.setTextEn("Text1");
        sentence.setTextPl("Text1");

        //when
        when(sentenceRepository.findByText("Text1")).thenReturn(Arrays.asList(sentence));

        //then
        List<Sentence> result = sentenceService.findByText("Text1");
        assertEquals(1, result.size());
        assertEquals("Text1", result.get(0).getTextEn());
    }

    @Test
    public void test_SentenceService_GetRandomSentence_whenValidIdsAreProvided_returnsRandomSentence() {

        //given
        Long optionalLong1 = 1L;
        Long optionalLong2 = 2L;
        List<Long> optionalLongList = Arrays.asList(optionalLong1, optionalLong2);
        Optional<List<Long>> optionalIds = Optional.of(optionalLongList);

        Sentence sentence = new Sentence();
        sentence.setId(1L);
        sentence.setTextEn("Text1");
        sentence.setTextPl("Text1");

        //when
        when(sentenceRepository.findAllIds()).thenReturn(optionalIds);
        when(sentenceRepository.findById(1L)).thenReturn(Optional.of(sentence));
        Sentence result = sentenceService.getRandomSentence();

        //then
        assertEquals("Text1", result.getTextEn());
    }

}
