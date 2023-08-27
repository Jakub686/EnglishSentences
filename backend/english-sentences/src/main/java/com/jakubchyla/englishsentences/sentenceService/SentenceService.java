package com.jakubchyla.englishsentences.sentenceService;

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

    public Sentence getById(Long id) {
        return sentenceRepository.findById(id).orElse(null);
    }

    public RandomDTO findSentenceRandom() {
        Sentence sentence = getRandomSentence();

        RandomDTO randomDTO = new RandomDTO(sentence.getId(), sentence.getTextEn(), sentence.getTextPl(), false);
        return randomDTO;
    }

    public RandomDTO findSentenceRandomForEmail(String email, boolean fav) {
        Sentence sen = getRandomSentence(email, fav);
        boolean isFavorite = isSentenceFav(email, sen);
        RandomDTO rndDTO = new RandomDTO(sen.getId(), sen.getTextEn(), sen.getTextPl(), isFavorite);

        return rndDTO;
    }


    private boolean isSentenceFav(String email, Sentence sen) {
        if (sen.getFavorite() == null || sen.getFavorite().isEmpty())
            return false;

        User user = userRepository.findByEmail(email).get();
        return sen.getFavorite().stream().anyMatch(favorite -> favorite.getUserId() == user.getId());
    }


    public List<RandomDTO> findSentenceListForEmail(String email) {
        List<RandomDTO> randomDTOList = new ArrayList<>();
        List<Sentence> sentencesList = sentenceRepository.findAll();
        Optional<User> userOptional = userRepository.findByEmail(email);

        sentencesList.forEach(sen -> {
            RandomDTO randomDTO =
                    new RandomDTO(sen.getId(), sen.getTextEn(), sen.getTextPl(), findStatusFavSentence(sen, userOptional.get().getId())
            );
            randomDTOList.add(randomDTO);
        });
        return randomDTOList;
    }

    private Sentence getRandomSentence() {
        List<Long> ids = sentenceRepository.findAllIds();

        int index = new Random().nextInt(ids.size());
        Long id = ids.get(index);
        Sentence sentence = sentenceRepository.findById(id).orElse(null);
        return sentence;
    }

    private Sentence getRandomSentence(String email, boolean fav) {
        Long userId = getUserId(email);
        List<Long> ids;
        if (fav) {
            ids = sentenceRepository.findAllFavSentencesIdsByUserId(userId);
        } else {
            ids = sentenceRepository.findAllIds();
        }

        int index = new Random().nextInt(ids.size());
        Long id = ids.get(index);
        Sentence sentence = sentenceRepository.findById(id).orElse(null);
        return sentence;
    }

    Long getUserId(String email) {
        return userRepository.findByEmail(email).get().getId();
    }

    private boolean findStatusFavSentence(Sentence sentence, Long userId) {
        return favoriteRepository.findByUserIdAndSentenceId(userId, sentence.getId())
                .stream()
                .findFirst()
                .isPresent();
    }

    public List<Sentence> findByText(String text) {
        return sentenceRepository.findByText(text);
    }

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
