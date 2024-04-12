package com.project.colabeduc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.colabeduc.backend.entities.Usuario;
import com.project.colabeduc.backend.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuario(Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        return usuario.get();
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void remover(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }


}
