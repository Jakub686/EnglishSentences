package com.jakubchyla.englishsentences.sentenceRepository;

import com.jakubchyla.englishsentences.model.UrlLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlLinkRepository extends JpaRepository<UrlLink, Long> {
}
