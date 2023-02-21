package com.jakubchyla.englishsentences.sentenceRepository;

import com.jakubchyla.englishsentences.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {

    @Query("FROM Sentence WHERE text LIKE %?1%")
    List<Sentence> findByText(String text);

}
