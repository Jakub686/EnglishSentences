package com.jakubchyla.englishsentences.sentenceService;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class SentenceServiceTest {

    @Mock
    private SentenceRepository sentenceRepository;

    @InjectMocks
    private SentenceService sentenceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenExistingId_whenGetById_thenReturnSentence() {
        // Given
        Long sentenceId = 1L;
        Sentence expectedSentence = new Sentence();
        expectedSentence.setId(sentenceId);
        given(sentenceRepository.findById(sentenceId)).willReturn(Optional.of(expectedSentence));

        // When
        Sentence result = sentenceService.getById(sentenceId);

        // Then
        assertNotNull(result);
        assertEquals(sentenceId, result.getId());
    }

    @Test
    public void givenNonExistentId_whenGetById_thenReturnNull() {
        // Given
        Long sentenceId = 999L;
        given(sentenceRepository.findById(sentenceId)).willReturn(Optional.empty());

        // When
        Sentence result = sentenceService.getById(sentenceId);

        // Then
        assertNull(result);
    }

    @Test
    public void givenNullId_whenGetById_thenReturnNull() {
        // Given
        Long sentenceId = null;

        // When
        Sentence result = sentenceService.getById(sentenceId);

        // Then
        assertNull(result);
    }

    @Test
    public void givenExceptionThrown_whenGetById_thenThrowException() {
        // Given
        Long sentenceId = 1L;
        given(sentenceRepository.findById(sentenceId)).willThrow(new RuntimeException("Database error"));

        // When and Then
        assertThrows(RuntimeException.class, () -> sentenceService.getById(sentenceId));
    }
}
