package com.jakubchyla.englishsentences.sentenceController.dto;

import com.jakubchyla.englishsentences.model.SentencePl;

import java.util.List;

public record UpdateUrlLinkDto(List<SentencePl> textPl) {
}
