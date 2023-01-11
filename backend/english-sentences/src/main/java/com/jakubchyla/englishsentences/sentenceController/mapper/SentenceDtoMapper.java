package com.jakubchyla.englishsentences.sentenceController.mapper;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceController.dto.SimpleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SentenceDtoMapper {

    public static ResponseEntity<List<SimpleDto>> mapSentenceToSimpleDto(List<Sentence> allSentence) {
        return new ResponseEntity<>(allSentence.stream()
                .map(sentence -> new SimpleDto(sentence.getId(), sentence.getText()))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
