package com.project.colabeduc.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.colabeduc.backend.entities.Usuario;
import com.project.colabeduc.backend.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{idUsuario}")
    public Usuario getDescricao(@PathVariable Long idUsuario) {
        return usuarioService.getUsuario(idUsuario);
    }

    @GetMapping("/")
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return usuarios;
    }

    @PostMapping
    public void salvar(@RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
    }

    @PutMapping
    public void atualizar(@RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
    }

    @DeleteMapping("/{idUsuario}")
    public void remover(@PathVariable Long idUsuario) {
        usuarioService.remover(idUsuario);
    }
}
