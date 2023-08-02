package com.jakubchyla.englishsentences.sentenceService;

import com.jakubchyla.englishsentences.model.Favorite;
import com.jakubchyla.englishsentences.model.Sentence;
import com.jakubchyla.englishsentences.sentenceController.dto.RandomDTO;
import com.jakubchyla.englishsentences.sentenceRepository.FavoriteRepository;
import com.jakubchyla.englishsentences.sentenceRepository.SentenceRepository;
import com.jakubchyla.englishsentences.user.User;
import com.jakubchyla.englishsentences.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class SentenceService {

    @Autowired
    private SentenceRepository sentenceRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;

    public Sentence saveSentence(Sentence sentence) {
        return sentenceRepository.save(sentence);
    }

    public List<Sentence> findAllSentence() {
        return sentenceRepository.findAll();
    }

    public Long findHighestId() {
        sentenceRepository.findAll();
        List<Sentence> sentences = sentenceRepository.findAll();
        Long highestId = sentences.stream()
                .map(Sentence::getId) // map to extract the ids
                .max(Long::compare) // find the maximum id
                .orElse(null);

        return highestId;
    }

    public Sentence getById(Long id) {
        return sentenceRepository.findById(id).orElse(null);
    }

    public RandomDTO findSentenceRandomForEmail(String email) {
        Sentence sentence = getRandomSentence();

        Optional<User> userOptional = userRepository.findByEmail(email);
        RandomDTO randomDTO = new RandomDTO(sentence.getId(), sentence.getTextEn(), sentence.getTextPl(), findFavSentence(sentence, userOptional.get().getId()));
        return randomDTO;
    }

    private Sentence getRandomSentence() {
        Random random = new Random();
        Long id;
        Sentence sentence;
        do {
            id = random.nextLong(findHighestId() + 1);// used findHighestId to not hardcoded seed in random
            sentence = sentenceRepository.findById(id).orElse(null);
        }
        while (sentence == null);
        return sentence;
    }

    private boolean findFavSentence(Sentence sentence, Long userId) {

        try {
            List<Favorite> favList = favoriteRepository.findByUserIdAndSentenceId(userId, sentence.getId());
            if (favList.size() > 0) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public List<Sentence> findByText(String text) {
        return sentenceRepository.findByText(text);
    }

//    public Sentence dtoGetById(Long id) {
//        return sentenceRepository.findById(id).orElse(null);
//    }

    public void deleteSentence(Long id) {
        boolean exists = sentenceRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Sentence is not exists");
        }
        sentenceRepository.deleteById(id);
    }

    public Sentence updateSentence(Sentence sentence) {
        Sentence existingSentence = sentenceRepository.findById(sentence.getId()).orElse(null);
        existingSentence.setTextEn(sentence.getTextEn());
        return sentenceRepository.save(existingSentence);
    }

    public String testMethod(String input) {
        return input + "hello world";
    }

}
