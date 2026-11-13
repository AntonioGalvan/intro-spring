package com.example.introspring.controllers;

import com.example.introspring.dto.CreateArtistRequest;
import com.example.introspring.models.Artist;
import com.example.introspring.services.ArtistService;
import com.example.introspring.structures.LinkedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<Artist> getAll() {
        return artistService.getAll();
    }

    @GetMapping("/{id}")
    public Artist getById(@PathVariable int id) {
        return artistService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Artist> create(@RequestBody CreateArtistRequest request) {
        Artist artist = artistService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(artist);
    }
}
