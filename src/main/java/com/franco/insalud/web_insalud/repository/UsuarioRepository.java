package com.franco.insalud.web_insalud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franco.insalud.web_insalud.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

Optional<Usuario> findByUsuario(String usuario);
    
	
}
