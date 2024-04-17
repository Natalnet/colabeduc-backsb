package com.project.colabeduc.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.colabeduc.backend.entities.PasswordGenerator;
import com.project.colabeduc.backend.entities.PasswordResetToken;
import com.project.colabeduc.backend.entities.Usuario;
import com.project.colabeduc.backend.repositories.PasswordResetTokenRepository;
import com.project.colabeduc.backend.repositories.UsuarioRepository;
import com.project.colabeduc.exceptions.EmailNaoEncontradoException;
import com.project.colabeduc.exceptions.TokenInvalidoException;

import jakarta.mail.MessagingException;

import java.util.Date;
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
    private PasswordResetTokenRepository passwordResetTokenRepository;

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

    public void recuperarSenha(String email)  throws MessagingException{
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setEmail(email);
            resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); // 1 hora de expiração
            passwordResetTokenRepository.save(resetToken);
           
            // Envie o e-mail com o token
            emailService.sendPasswordResetEmail(email, token);
        } else {
            throw new EmailNaoEncontradoException("Email não encontrado");
        }
    }

    public void resetarSenha(String token, String novaSenha) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        if (resetToken != null && resetToken.getExpiryDate().after(new Date())) {
            // Verifica se o token ainda existe no banco de dados
            if (passwordResetTokenRepository.existsByToken(token)) {
                Usuario usuario = usuarioRepository.findByEmail(resetToken.getEmail());
                if (usuario != null) {
                    usuario.setPassword(PasswordGenerator.encodePassword(novaSenha));
                    usuarioRepository.save(usuario);
                    passwordResetTokenRepository.delete(resetToken);
                }
            } else {
                // Token não existe no banco de dados, portanto, a troca de senha não é permitida
                throw new TokenInvalidoException("Token inválido ou expirado");
            }
        } else {
            throw new TokenInvalidoException("Token inválido ou expirado");
        }
    }

}
