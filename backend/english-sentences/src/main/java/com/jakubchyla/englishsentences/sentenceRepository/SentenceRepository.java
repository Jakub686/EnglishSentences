package com.jakubchyla.englishsentences.sentenceRepository;

import com.jakubchyla.englishsentences.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {
}
