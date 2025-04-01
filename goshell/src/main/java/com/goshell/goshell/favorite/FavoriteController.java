package com.goshell.goshell.favorite;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController (FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    //Add Favorite
    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestBody Favorite favorite){
        String result = favoriteService.addFavorite(favorite);

        if (result.contains("Favorite already exists!")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return result.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing field")
                : ResponseEntity.created(URI.create("/favorites/" + result)).body("Added to favorites successfully!");
    }

    //Get user's favorites
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserFavorite(@PathVariable String userId){
        List<Favorite> result = favoriteService.getFavoriteByUserId(userId);

        return result.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid userId")
                : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //Delete favorite
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable String favoriteId){
        String result = favoriteService.deleteFavorite(favoriteId);

        return result.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid favoriteId")
                : ResponseEntity.status(HttpStatus.OK).body("Delete favorite successfully");
    }
}
