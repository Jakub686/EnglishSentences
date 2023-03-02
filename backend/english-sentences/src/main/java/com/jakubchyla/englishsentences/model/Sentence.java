package com.jakubchyla.englishsentences.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text_en")
    private String textEn;

//    @Column(name = "text_pl")
//    private String textPl;

    @Temporal(TemporalType.TIMESTAMP)
    final private Date timestamp = new Date(System.currentTimeMillis());

    @OneToMany(targetEntity = TranslationToPl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "translation_to_pl", referencedColumnName = "id")
    private List<TranslationToPl> translationToPl;
}
