package com.jakubchyla.englishsentences.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "sentences")
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text_en")
    private String textEn;

    @Column(name = "text_pl")
    private String textPl;

    @Temporal(TemporalType.TIMESTAMP)
    final private Date timestamp = new Date(System.currentTimeMillis());

    @OneToMany(targetEntity = Favorite.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sentence_id", referencedColumnName = "id")
    private List<Favorite> Favorite;


}


