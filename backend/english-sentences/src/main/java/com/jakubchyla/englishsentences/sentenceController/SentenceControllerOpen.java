package com.jakubchyla.englishsentences.sentenceController;

import com.jakubchyla.englishsentences.sentenceController.dto.RandomDTO;
import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/open")
@RestController
public class SentenceControllerOpen {

    @Autowired
    private SentenceService sentenceService;

    @GetMapping("/random")
    public ResponseEntity<RandomDTO> getSentenceRandom() {
        RandomDTO randomDTO = sentenceService.findSentenceRandom();
        return new ResponseEntity<>(randomDTO, HttpStatus.OK);
    }

}
