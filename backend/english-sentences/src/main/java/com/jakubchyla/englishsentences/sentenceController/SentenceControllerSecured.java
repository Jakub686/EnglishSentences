package com.jakubchyla.englishsentences.sentenceController;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceController.dto.AddToFav;
import com.jakubchyla.englishsentences.sentenceController.dto.RandomDTO;
import com.jakubchyla.englishsentences.sentenceService.FavoriteService;
import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/secured")
@RestController
public class SentenceControllerSecured {

    @Autowired
    private SentenceService sentenceService;

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/")
    public ResponseEntity<Sentence> saveSentence(@RequestBody Sentence sentence) {
        Sentence savedSentence = sentenceService.saveSentence(sentence);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSentence);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sentence> getById(@PathVariable Long id) {
        Sentence sentence = sentenceService.getById(id);
        if (sentence == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(sentence);
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

    @GetMapping("/random-for-user")
    public ResponseEntity<RandomDTO> getSentenceRandomForUser(String email, boolean fav) {
        RandomDTO randomDTO = sentenceService.findSentenceRandomForEmail(email, fav);
        if (randomDTO.id() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(randomDTO);
    }

    @GetMapping("/sentences-logged")
    public ResponseEntity<List<RandomDTO>> getSentence(String email) {
        List<RandomDTO> sentenceList = sentenceService.findSentenceListForEmail(email);

        if (sentenceList.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(sentenceList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Sentence>> findByText(String textEn) {
        List<Sentence> sentences = sentenceService.findByText(textEn);

        if (sentences.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(sentences);
    }

    @PatchMapping("/add-fav")
    public ResponseEntity<Boolean> addToFavByUser(@RequestBody AddToFav favDto) {
        return new ResponseEntity<>(favoriteService.addToFav(favDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sentence> updateSentenceUrlLink(@RequestBody Sentence sentence) {
        Sentence updated = sentenceService.updateSentence(sentence);

        return ResponseEntity.ok(updated);
    }

}
