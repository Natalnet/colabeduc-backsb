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

@Service
public class UsuarioService {
    
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z]).{6,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    

    public Usuario getUsuario(Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        return usuario.get();
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) throws Exception {
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


    public void remover(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public Usuario getUsuarioPorLogin(String login) {
        return usuarioRepository.findByUsername(login);
    }


}
