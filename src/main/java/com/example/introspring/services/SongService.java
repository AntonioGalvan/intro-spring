package com.example.introspring.services;

import com.example.introspring.dto.CreateSongRequest;
import com.example.introspring.models.Song;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    private final List<Song> songs = new ArrayList<>(List.of(
            new Song(1, "Bohemian Rhapsody", "Queen", 350),
            new Song(2, "The less I know the better", "tame impala", 280),
            new Song(3, "Human Nature", "Michael Jackson", 280)
    ));

    public List<Song> getAll() {
        return songs;
    }

    public Song getById(int id) {
        for (Song song : songs) {
            if (song.id() == id) {
                return song;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found");
    }

    public Song create(CreateSongRequest request) {
        Song song = new Song(
                songs.size() + 1,
                request.title(),
                request.artist(),
                request.durationSeconds()
        );

        songs.add(song);
        return song;
    }
}
