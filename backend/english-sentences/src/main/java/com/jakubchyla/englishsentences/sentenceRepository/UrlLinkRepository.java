package com.jakubchyla.englishsentences.sentenceRepository;

import com.jakubchyla.englishsentences.model.TranslationToPl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlLinkRepository extends JpaRepository<TranslationToPl, Long> {
}
