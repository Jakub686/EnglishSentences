package com.jakubchyla.englishsentences.sentenceController;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceController.dto.SimpleDto;
import com.jakubchyla.englishsentences.sentenceController.dto.UpdateDto;
import com.jakubchyla.englishsentences.sentenceController.dto.UpdateUrlLinkDto;
import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.jakubchyla.englishsentences.sentenceController.mapper.SentencesDtoMapper.mapSentencesToSimpleDto;

@CrossOrigin(origins = "*")
@RestController
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;

    @PostMapping("/")
    public ResponseEntity<Sentence> saveSentence(@RequestBody Sentence sentence) {
        return new ResponseEntity<>(sentenceService.saveSentence(sentence), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Sentence>> getSentence() {
        List<Sentence> sentence = sentenceService.findAllSentence();
        return new ResponseEntity<>(sentence, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<Sentence> getSentenceRandom() {
        Sentence sentence = sentenceService.findSentenceRandom();
        return new ResponseEntity<>(sentence, HttpStatus.OK);
    }

    //TODO serach ma zwraca simple
    @GetMapping("/search")
    public ResponseEntity<List<Sentence>> findByText(@RequestParam String textEn) {
        if (sentenceService.findByText(textEn) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(sentenceService.findByText(textEn), HttpStatus.OK);
        }
    }

    @GetMapping("/simple")
    public ResponseEntity<List<SimpleDto>> simpleGetSentence() {
        return mapSentencesToSimpleDto(sentenceService.findAllSentence());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sentence> getById(@PathVariable Long id) {
        if (sentenceService.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(sentenceService.getById(id), HttpStatus.OK);
        }
    }

    @GetMapping("/simple/{id}")
    public ResponseEntity<SimpleDto> simpleGetById(@PathVariable Long id) {
        if (sentenceService.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            Sentence sentence = sentenceService.getById(id);
            SimpleDto simpleDto = new SimpleDto(sentence.getId(), sentence.getTextEn());
            return new ResponseEntity<>(simpleDto, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateDto> updateSentence(@PathVariable Long id, @RequestBody UpdateDto updateDto) {
        Sentence sentence = new Sentence();
        sentence.setId(id);
        sentence.setTextEn(updateDto.textEn());
        sentenceService.updateSentence(sentence);
        return new ResponseEntity<>(updateDto, HttpStatus.OK);
    }

    @PutMapping("/textpl/{id}")
    public ResponseEntity<UpdateUrlLinkDto> updateSentenceUrlLink(@PathVariable Long id, @RequestBody UpdateUrlLinkDto updateUrlLinkDto) {
        Sentence sentence = new Sentence();
        sentence.setId(id);
        sentence.setTranslationToPl(updateUrlLinkDto.textPl());
        sentenceService.updateSentence(sentence);
        return new ResponseEntity<>(updateUrlLinkDto, HttpStatus.OK);
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
}
