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
    @Column(name = "translation_id")
    private Long idTranslation;
    private String textPl;
}
