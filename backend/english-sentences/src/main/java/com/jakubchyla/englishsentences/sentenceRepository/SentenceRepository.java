package com.jakubchyla.englishsentences.sentenceRepository;

import com.jakubchyla.englishsentences.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {

    @Query("FROM Sentence WHERE textEn LIKE %?1%")
    List<Sentence> findByText(String textEn);

    @Query("SELECT id FROM Sentence ORDER BY id ASC")
    List<Long> findAllIds();

    @Query("SELECT s.id FROM Sentence s JOIN s.Favorite f WHERE f.userId = :userId")
    List<Long> findAllFavSentencesIdsByUserId(Long userId);



}
