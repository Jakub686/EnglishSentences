package com.jakubchyla.englishsentences.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SentencePl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String textPl;

}

