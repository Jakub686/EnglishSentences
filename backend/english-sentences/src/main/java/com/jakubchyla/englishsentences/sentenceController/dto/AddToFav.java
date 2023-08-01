package com.jakubchyla.englishsentences.sentenceController.dto;

public record AddToFav(String email, Long sentenceId, boolean favorite) {

}
