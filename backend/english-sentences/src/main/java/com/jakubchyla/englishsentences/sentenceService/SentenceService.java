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

import java.util.ArrayList;
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

    public RandomDTO findSentenceRandom() {
        Sentence sentence = getRandomSentence();

        RandomDTO randomDTO = new RandomDTO(sentence.getId(), sentence.getTextEn(), sentence.getTextPl(), false);
        return randomDTO;
    }

    public RandomDTO findSentenceRandomForEmail(String email, boolean fav) {
        //TODO: optional change into like sentence = sentenceRepository.findById(id).orElse(null);
        RandomDTO rndDTO;
        Sentence sentence = getRandomSentence();
        Optional<Sentence> sentenceOptional = Optional.ofNullable(sentence);
        boolean sentenceStatus;
        if (!fav) {
            if (sentenceOptional.get().getFavorite().isEmpty()) {
                sentenceStatus = false;
                rndDTO = new RandomDTO(sentence.getId(), sentence.getTextEn(), sentence.getTextPl(), sentenceStatus);
            } else {
                sentenceStatus = true;
                rndDTO = new RandomDTO(sentence.getId(), sentence.getTextEn(), sentence.getTextPl(), sentenceStatus);
            }
        } else {
            do {
                sentence = getRandomSentence();
                sentenceOptional = Optional.ofNullable(sentence);
                if (!sentenceOptional.get().getFavorite().isEmpty()) {
                    sentenceStatus = true;
                    rndDTO = new RandomDTO(sentence.getId(), sentence.getTextEn(), sentence.getTextPl(), sentenceStatus);
                } else {
                    sentenceStatus = false;
                    rndDTO = new RandomDTO(sentence.getId(), sentence.getTextEn(), sentence.getTextPl(), sentenceStatus);
                }
            } while (fav != sentenceStatus);
        }
        return rndDTO;
    }

    public List<RandomDTO> findSentenceListForEmail(String email) {
        List<RandomDTO> randomDTOList = new ArrayList<>();
        List<Sentence> sentencesList = sentenceRepository.findAll();
        Optional<User> userOptional = userRepository.findByEmail(email);

        for (Sentence sentence : sentencesList) {
            RandomDTO randomDTO = new RandomDTO(sentence.getId(), sentence.getTextEn(), sentence.getTextPl(), findStatusFavSentence(sentence, userOptional.get().getId()));
            randomDTOList.add(randomDTO);
        }

        return randomDTOList;
    }

    private Sentence getRandomSentence() {
        List<Long> ids = sentenceRepository.findAllIds();

        Random random = new Random();
        int index = random.nextInt(ids.size());
        Long id = ids.get(index);
        Sentence sentence = sentenceRepository.findById(id).orElse(null);
        return sentence;
    }

    private boolean findStatusFavSentence(Sentence sentence, Long userId) {

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
        return sentenceRepository.save(sentence);
    }

    public String testMethod(String input) {
        return input + "hello world";
    }

}
