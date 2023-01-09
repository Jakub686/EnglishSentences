package com.jakubchyla.englishsentences.sentenceService;

import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
