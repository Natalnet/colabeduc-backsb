package com.project.colabeduc.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.colabeduc.backend.entities.Usuario;
import com.project.colabeduc.backend.repositories.UsuarioRepository;
import com.project.colabeduc.exceptions.EmailNaoEncontradoException;

import jakarta.mail.MessagingException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UsuarioService {
    
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z]).{6,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    public Usuario getUsuario(Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        return usuario.get();
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) throws Exception {
        String senhaNormal = usuario.getPassword();
        String senhaCriptografada = bCryptPasswordEncoder.encode(senhaNormal);
        usuario.setPassword(senhaCriptografada);

        Usuario usr = usuarioRepository.findByUsername(usuario.getUsername());
        if(usr != null) {
            throw new RuntimeException("Esse login já existe");
        }

        Matcher matcher = pattern.matcher(senhaNormal);
        if (!matcher.matches()){
            throw new RuntimeException("Senha não atende aos requisitos minimos");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void remover(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public Usuario getUsuarioPorLogin(String login) {
        return usuarioRepository.findByUsername(login);
    }

    public Usuario recuperarSenha(String username) throws MessagingException{
        Optional<Usuario> usuario = this.usuarioRepository.acharUsername(username);

        if (usuario.isPresent()) {
            Usuario user = usuario.get();
            String token = UUID.randomUUID().toString();
            LocalDateTime token_expiration = LocalDateTime.now().plusHours(24);
            user.setToken(token);
            user.setReset_token_expiration(token_expiration);
            this.emailService.sendPasswordResetEmail(user.getEmail(), token, user);
            
            return this.usuarioRepository.save(user);
        } 
        
        throw new EmailNaoEncontradoException("Email não encontrado");
    }

    public Usuario resetarSenha(String token, String newPassword) {
        Optional<Usuario> userToken = usuarioRepository.findByToken(token);
        
        if (userToken.isPresent()) {
            Usuario usuario = userToken.get();

            if(usuario.getToken().equals(token) && usuario.getReset_token_expiration().isAfter(LocalDateTime.now())){
                String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);
                usuario.setPassword(encryptedPassword);

                return this.usuarioRepository.save(usuario);
            }

            throw new RuntimeException("Erro na validação do token");
        }

        throw new RuntimeException("Token não encontado");  
    }

}
