package com.jakubchyla.englishsentences.sentenceService;

import com.jakubchyla.englishsentences.model.Favorite;
import com.jakubchyla.englishsentences.sentenceController.dto.AddToFav;
import com.jakubchyla.englishsentences.sentenceRepository.FavoriteRepository;
import com.jakubchyla.englishsentences.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FavoriteService {

    @Autowired
    private FavoriteRepository favRepo;
    @Autowired
    private UserRepository userRepo;

    public boolean addToFav(AddToFav dto) {


        switch (isSentenceIsUserFav(dto)) {
            // remove from fav
            case 1: {
                List<Favorite> favList = favRepo
                        .findByUserIdAndSentenceId(userRepo.findByEmail(dto.email()).get().getId(), dto.sentenceId());
                for (Favorite favorite : favList) {
                    favRepo.deleteById(favorite.getId());
                }
                return false;
            }

            //add to fav
            case 0: {
                Favorite fav = new Favorite();
                fav.setSentenceId(dto.sentenceId());
                fav.setUserId(userRepo.findByEmail(dto.email()).get().getId());
                favRepo.save(fav);
                return true;
            }
            default: return false;
        }
    }

    private int isSentenceIsUserFav(AddToFav dto) {
        List<Favorite> userFavs = favRepo.findByUserIdAndSentenceId(userRepo.findByEmail(dto.email()).get().getId(), dto.sentenceId());
        if (userFavs.size() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
