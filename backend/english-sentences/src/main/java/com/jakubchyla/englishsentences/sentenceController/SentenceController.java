package com.jakubchyla.englishsentences.sentenceController;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.dsig.SignatureProperties;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;

    @PostMapping("/")
    public ResponseEntity<Sentence> saveSentence( @RequestBody Sentence article) {
        Sentence newSentence = sentenceService.saveSentence(article);
        return new ResponseEntity<>(newSentence, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Sentence>> getSentence() {
        List<Sentence> sentence = sentenceService.findAllSentence();
        return new ResponseEntity<>(sentence, HttpStatus.OK);
    }

    @GetMapping("/simple")
    public ResponseEntity<List<SimpleDto>> simpleGetSentence() {
        return new ResponseEntity<>(sentenceService.findAllSentence().stream()
                .map(sentence -> new SimpleDto(sentence.getId(), sentence.getText()))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sentence> getById(@PathVariable Long id) {
        if (sentenceService.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(sentenceService.getById(id), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteArticle(@PathVariable("id") Long id) {
        if (sentenceService.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            sentenceService.deleteSentence(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Sentence> updateSentence(@RequestBody Sentence article) {
        return new ResponseEntity<>(sentenceService.updateSentence(article), HttpStatus.OK);
    }



}
