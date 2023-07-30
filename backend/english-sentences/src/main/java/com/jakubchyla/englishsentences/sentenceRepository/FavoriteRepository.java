package com.jakubchyla.englishsentences.sentenceRepository;

import com.jakubchyla.englishsentences.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>  {

    //    @Query("FROM Sentence WHERE textEn LIKE %?1%")
    @Query("FROM Favorite WHERE sentenceId LIKE %?1%")
    List<Favorite> findBySentenceId(Long sentenceId);


}
