package com.jakubchyla.englishsentences.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TranslationToPl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "sentence_id")
    private Long sentenceId;

    private String textPl;


//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//
//    private Long id;
//    @Column(name = "sentence_id")
//    private Long sentenceId;
//    @Column(name = "user_id")
//    private Long userId;
}

