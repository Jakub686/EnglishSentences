package com.jakubchyla.englishsentences.sentenceController.dto;

import com.jakubchyla.englishsentences.model.TranslationToPl;

import java.util.List;

public record UpdateUrlLinkDto(List<TranslationToPl> textPl) {
}
