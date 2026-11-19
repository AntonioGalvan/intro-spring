package com.example.introspring.dto;

public record LoginResponse(
        String token,
        UsuarioResponse user) {
}
