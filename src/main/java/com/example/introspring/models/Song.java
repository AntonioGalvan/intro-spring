package com.example.introspring.models;

public record Song(
        int id,
        String title,
        String artist,
        int durationSeconds
) {
}
