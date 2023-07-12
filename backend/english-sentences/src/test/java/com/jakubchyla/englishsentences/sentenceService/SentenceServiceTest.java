package com.jakubchyla.englishsentences.sentenceService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SentenceServiceTest {

    @Test
    public void testTestMethod() {
        // Arrange
        SentenceService sentenceService = new SentenceService();
        String input = "example ";

        // Act
        String result = sentenceService.testMethod(input);

        // Assert
        Assertions.assertEquals("example hello world", result);
    }
}