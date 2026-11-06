package com.example.introspring.dto;

public record CreateSongRequest(
        String title,
        String artist,
        int durationSeconds
) {
}
