package com.jakubchyla.englishsentences.sentenceService;

import com.jakubchyla.englishsentences.model.Favorite;
import com.jakubchyla.englishsentences.sentenceController.dto.AddToFavByUserDto;
import com.jakubchyla.englishsentences.sentenceRepository.FavoriteRepository;
import com.jakubchyla.englishsentences.user.User;
import com.jakubchyla.englishsentences.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;

    public Favorite addToFavByUserDto(AddToFavByUserDto addToFavByUserDto) {
        Favorite fav = new Favorite();
        Optional<User> user = userRepository.findByEmail(addToFavByUserDto.email());

        fav.setUserId(user.get().getId().longValue());
        fav.setSentenceId(addToFavByUserDto.sentenceId());

        return favoriteRepository.save(fav);
    }
}
