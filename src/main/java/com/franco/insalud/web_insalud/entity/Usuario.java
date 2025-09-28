package com.franco.insalud.web_insalud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "usuario")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String usuario;

    @NotBlank
    @Size(min = 6, max = 100)
    private String contrasena;

    @OneToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;


    public Usuario() {
    }

    public Usuario(Long id, String usuario, String contrasena, Persona persona) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}