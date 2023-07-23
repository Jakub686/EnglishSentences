package com.jakubchyla.englishsentences.sentenceRepository;

import com.jakubchyla.englishsentences.model.SentencePl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlLinkRepository extends JpaRepository<SentencePl, Long> {
}
