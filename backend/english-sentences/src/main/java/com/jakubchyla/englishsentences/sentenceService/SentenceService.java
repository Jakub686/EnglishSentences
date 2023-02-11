package com.jakubchyla.englishsentences.sentenceService;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class SentenceService {

    @Autowired
    private SentenceRepository sentenceRepository;

    public Sentence saveSentence(Sentence sentence) {
        return sentenceRepository.save(sentence);
    }

    public List<Sentence> findAllSentence() {
        return sentenceRepository.findAll();
    }

    public Sentence getById(Long id) {
        return sentenceRepository.findById(id).orElse(null);
    }

    public Sentence findSentenceRandom() {
        Random random = new Random();
        Long id;
        Sentence result = null;
        do {
            id = random.nextLong(50);
            result = sentenceRepository.findById(id).orElse(null);
        }
        while (result == null);

        return result;

        /*

public class Main {
  public static void main(String[] args) {
    Random random = new Random();
    Set<Integer> generated = new HashSet<>();
    int randomInt;

    while (generated.size() < 1000) {
      randomInt = random.nextInt(10000);
      if (!generated.contains(randomInt)) {
        generated.add(randomInt);
        System.out.println("Random int: " + randomInt);
      }
    }
  }
}
         */


    }

    public Sentence dtogetById(Long id) {
        return sentenceRepository.findById(id).orElse(null);
    }

    public void deleteSentence(Long id) {
        boolean exists = sentenceRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Sentence is not exists");
        }
        sentenceRepository.deleteById(id);
    }

    public Sentence updateSentence(Sentence sentence) {
        Sentence existingSentence = sentenceRepository.findById(sentence.getId()).orElse(null);
        existingSentence.setText(sentence.getText());

        return sentenceRepository.save(existingSentence);
    }


}
