package com.project.colabeduc.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.colabeduc.backend.dtos.request.RecuperarSenhaRequestDTO;
import com.project.colabeduc.backend.dtos.request.ResetarSenhaRequestDTO;
import com.project.colabeduc.backend.entities.Usuario;
import com.project.colabeduc.backend.services.UsuarioService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/{idUsuario}")
    @Secured(value = { "ROLE_ADMIN", "ROLE_GESTOR", })
    public Usuario getDescricao(@PathVariable Long idUsuario) {
        return usuarioService.getUsuario(idUsuario);
    }

    @GetMapping("/")
    @Secured(value = { "ROLE_ADMIN", "ROLE_GESTOR", })
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return usuarios;
    }

    @PostMapping
    @Secured(value = { "ROLE_ADMIN", "ROLE_GESTOR", })
    public void salvar(@RequestBody Usuario usuario) throws Exception {
        usuarioService.salvar(usuario);
    }

    @PutMapping
    @Secured(value = { "ROLE_ADMIN", "ROLE_GESTOR", })
    public void atualizar(@RequestBody Usuario usuario) throws Exception  {
        usuarioService.salvar(usuario);
    }

    @DeleteMapping("/{idUsuario}")
    @Secured(value = { "ROLE_ADMIN", "ROLE_GESTOR", })
    public void remover(@PathVariable Long idUsuario) {
        usuarioService.remover(idUsuario);
    }
    
    @PostMapping("/recuperar-senha")
    public ResponseEntity<Usuario> recuperarSenha(@RequestBody RecuperarSenhaRequestDTO request) throws MessagingException{
        Usuario usuario = this.usuarioService.recuperarSenha(request.username());

        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/resetar-senha")
    public ResponseEntity<Usuario> resetarSenha(@RequestParam("token") String token, @RequestBody ResetarSenhaRequestDTO request) {
        Usuario usuario = this.usuarioService.resetarSenha(token, request.newPassword());

        return ResponseEntity.ok().body(usuario);
    }

}
