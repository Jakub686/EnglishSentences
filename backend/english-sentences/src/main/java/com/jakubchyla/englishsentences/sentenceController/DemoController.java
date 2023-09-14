package com.jakubchyla.englishsentences.sentenceController;

import com.jakubchyla.englishsentences.sentenceService.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @Autowired
    private SentenceService sentenceService;

//    @PostMapping("/")
//    public ResponseEntity<Sentence> saveSentence(@RequestBody Sentence sentence) {
//        Sentence savedSentence = sentenceService.saveSentence(sentence);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedSentence);
//    }

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}

