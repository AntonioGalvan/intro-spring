package com.example.introspring.controllers;

import com.example.introspring.dto.CreateSongRequest;
import com.example.introspring.models.Song;
import com.example.introspring.services.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    //Inyección de dependencias
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getAll() {
        return songService.getAll();
    }

    @GetMapping("/{id}")
    public Song getById(@PathVariable int id) {
        return songService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Song> create(@RequestBody CreateSongRequest request) {
        Song song = songService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(song);
    }

}
