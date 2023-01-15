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

    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    final private Date timestamp = new Date(System.currentTimeMillis());

    @OneToMany(targetEntity = UrlLink.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="sentence_id",referencedColumnName = "id")
    private List<UrlLink> urllinks;
}
