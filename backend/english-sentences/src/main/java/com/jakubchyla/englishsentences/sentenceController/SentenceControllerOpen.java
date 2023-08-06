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

    @PostMapping("/")
    public ResponseEntity<Sentence> saveSentence(@RequestBody Sentence sentence) {
        return new ResponseEntity<>(sentenceService.saveSentence(sentence), HttpStatus.CREATED);
    }

    @GetMapping("/sentences")
    public ResponseEntity<List<Sentence>> getSentence() {
        List<Sentence> sentence = sentenceService.findAllSentence();
        return new ResponseEntity<>(sentence, HttpStatus.OK);
    }

//    @GetMapping("/randomForUser")
//    public ResponseEntity<RandomDTO> getSentenceRandomForUser(String email) {
//        RandomDTO randomDTO = sentenceService.findSentenceRandomForEmail(email);
//        return new ResponseEntity<>(randomDTO, HttpStatus.OK);
//    }

    @GetMapping("/random")
    public ResponseEntity<RandomDTO> getSentenceRandom() {
        RandomDTO randomDTO = sentenceService.findSentenceRandom();
        return new ResponseEntity<>(randomDTO, HttpStatus.OK);
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

    @PatchMapping("/add-fav")
    public ResponseEntity<AddToFav> addToFavByUser(@RequestBody AddToFav favDto) {
        //TODO refactor
        AddToFav fav = new AddToFav(favDto.email(), favDto.sentenceId(), favoriteService.addToFav(favDto));
        return new ResponseEntity<>(fav, HttpStatus.OK);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<UpdateDto> updateSentence(@PathVariable Long id, @RequestBody UpdateDto updateDto) {
//        Sentence sentence = new Sentence();
//        sentence.setId(id);
//        sentence.setTextEn(updateDto.textEn());
//        sentenceService.updateSentence(sentence);
//        return new ResponseEntity<>(updateDto, HttpStatus.OK);
//    }


//    @PutMapping("/textpl/{id}")
//    public ResponseEntity<UpdateUrlLinkDto> updateSentenceUrlLink(@PathVariable Long id, @RequestBody UpdateUrlLinkDto updateUrlLinkDto) {
//       // String loginInfo = login;
//        //System.out.println(loginInfo);
//        Sentence sentence = new Sentence();
//        sentence.setId(id);
//        sentence.setTranslationToPl(updateUrlLinkDto.textPl());
//        sentenceService.updateSentence(sentence);
//        return new ResponseEntity<>(updateUrlLinkDto, HttpStatus.OK);
//    }

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
