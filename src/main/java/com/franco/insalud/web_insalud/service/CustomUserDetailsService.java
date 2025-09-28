package com.franco.insalud.web_insalud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.franco.insalud.web_insalud.entity.Usuario;
import com.franco.insalud.web_insalud.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        
      
        if (usuario.getPersona().getPaciente() != null) {
            authorities.add(new SimpleGrantedAuthority(usuario.getPersona().getPaciente().getRol()));
        }
        
        if (usuario.getPersona().getEmpleado() != null) {
            authorities.add(new SimpleGrantedAuthority(usuario.getPersona().getEmpleado().getRol()));
        }
        
        return new User(usuario.getUsuario(), usuario.getContrasena(), authorities);
    }
}