package com.project.colabeduc.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.colabeduc.backend.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> getUsuarios();

    Usuario findByUsername(String username);

    Optional<Usuario> findByToken(String token);

    @Query(value = "select u from Usuario u where u.username = :username")
    Optional<Usuario> acharUsername(String username);

    Optional<Usuario> findByEmail(String email);

}