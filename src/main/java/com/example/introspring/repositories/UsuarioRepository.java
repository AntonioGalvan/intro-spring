package com.example.introspring.repositories;

import com.example.introspring.models.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate jdbc;

    public UsuarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final RowMapper<Usuario> MAPPER = (rs, fila) -> new Usuario(
            rs.getInt("id_usuario"),
            rs.getString("nombre_usuario"),
            rs.getString("correo"),
            rs.getString("contrasena_hash"),
            rs.getString("estado"),
            rs.getString("nombre_rol"));

    private static final String SELECT = """
            SELECT u.id_usuario, u.nombre_usuario, u.correo, u.contrasena_hash, u.estado, r.nombre_rol
            FROM usuarios u
            JOIN roles r ON r.id_rol = u.id_rol
            """;

    public Optional<Usuario> buscarPorId(int id) {
        return jdbc.query(SELECT + " WHERE u.id_usuario = ?", MAPPER, id).stream().findFirst();
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return jdbc.query(SELECT + " WHERE LOWER(u.correo) = LOWER(?)", MAPPER, correo).stream().findFirst();
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE LOWER(correo) = LOWER(?)";
        return jdbc.queryForObject(sql, Integer.class, correo) > 0;
    }

    public boolean existeRol(String rol) {
        String sql = "SELECT COUNT(*) FROM roles WHERE nombre_rol = ? AND estado = 'activo'";
        return jdbc.queryForObject(sql, Integer.class, rol) > 0;
    }

    public Usuario crear(String nombre, String correo, String contrasenaHash, String rol) {
        String sql = """
                INSERT INTO usuarios (nombre_usuario, correo, contrasena_hash, id_rol)
                VALUES (?, ?, ?, (SELECT id_rol FROM roles WHERE nombre_rol = ?))
                RETURNING id_usuario
                """;
        Integer id = jdbc.queryForObject(sql, Integer.class, nombre, correo, contrasenaHash, rol);
        return buscarPorId(id).orElseThrow();
    }
}
