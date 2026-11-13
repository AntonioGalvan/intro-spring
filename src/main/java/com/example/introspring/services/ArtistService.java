package com.example.introspring.services;

import com.example.introspring.dto.CreateArtistRequest;
import com.example.introspring.models.Artist;
import com.example.introspring.structures.LinkedList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ArtistService {
    private final LinkedList<Artist> artists = new LinkedList<>();

    public ArtistService() {
        artists.add(new Artist(1, "Queen", "Reino Unido"));
        artists.add(new Artist(2, "Tame Impala", "Australia"));
        artists.add(new Artist(3, "Michael Jackson", "Estados Unidos"));
    }

    public List<Artist> getAll() {
        return artists.toList();
    }

    public Artist getById(int id) {
        for (Artist artist : artists.toList()) {
            if (artist.id() == id) {
                return artist;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found");
    }

    public Artist create(CreateArtistRequest request) {
        Artist artist = new Artist(
                artists.size() + 1,
                request.name(),
                request.country()
        );

        artists.add(artist);
        return artist;
    }
}
