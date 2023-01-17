package com.jakubchyla.englishsentences.sentenceController.dto;

import com.jakubchyla.englishsentences.model.UrlLink;

import java.util.List;

public record UpdateUrlLinkDto(List<UrlLink> urlLink) {
}
