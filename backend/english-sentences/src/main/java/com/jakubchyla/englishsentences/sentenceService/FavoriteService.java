package com.jakubchyla.englishsentences.sentenceService;

import com.jakubchyla.englishsentences.model.Favorite;
import com.jakubchyla.englishsentences.sentenceController.dto.AddToFav;
import com.jakubchyla.englishsentences.sentenceRepository.FavoriteRepository;
import com.jakubchyla.englishsentences.user.User;
import com.jakubchyla.englishsentences.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean addToFav(AddToFav dto) {
        Favorite fav = new Favorite();
        Optional<User> user = userRepository.findByEmail(dto.email());

        fav.setSentenceId(dto.sentenceId());
        fav.setUserId(user.get().getId());
        if(dto.favorite() == false){
            favoriteRepository.save(fav);
        }
        if(dto.favorite() == true){
            List<Favorite> favList = favoriteRepository.findByUserIdAndSentenceId(user.get().getId(), dto.sentenceId());
            for(Favorite favorite : favList){
                favoriteRepository.deleteById(favorite.getId());
            }
        }
        return !dto.favorite();
    }
}
