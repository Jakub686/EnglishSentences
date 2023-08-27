package com.jakubchyla.englishsentences.sentenceController;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceController.dto.AddToFav;
import com.jakubchyla.englishsentences.sentenceController.dto.RandomDTO;
import com.jakubchyla.englishsentences.sentenceController.dto.SimpleDto;
import com.jakubchyla.englishsentences.sentenceService.FavoriteService;
import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.jakubchyla.englishsentences.sentenceController.mapper.SentencesDtoMapper.mapSentencesToSimpleDto;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/open")
@RestController
public class SentenceControllerOpen {

    @Autowired
    private SentenceService sentenceService;
    @Autowired
    private FavoriteService favoriteService;


    @GetMapping("/random")
    public ResponseEntity<RandomDTO> getSentenceRandom() {
        RandomDTO randomDTO = sentenceService.findSentenceRandom();
        return new ResponseEntity<>(randomDTO, HttpStatus.OK);
    }

    @GetMapping("/simple")
    public ResponseEntity<List<SimpleDto>> simpleGetSentence() {
        return mapSentencesToSimpleDto(sentenceService.findAllSentence());
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

    @PatchMapping("/add-fav")
    public ResponseEntity<Boolean> addToFavByUser(@RequestBody AddToFav favDto) {
        return new ResponseEntity<>(favoriteService.addToFav(favDto), HttpStatus.OK);
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
