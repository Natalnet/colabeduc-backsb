package com.project.colabeduc.backend.entities;

import java.util.Date;

import com.project.colabeduc.backend.enums.Papel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@NamedQuery(name = "Usuario.getUsuarios", query ="SELECT u FROM Usuario u")
@NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username=:username")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private static final long serialVersionUID = 1;

    @Column(nullable = false)
    private String username;

    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String profile_image_url;

    @Column
    private Boolean enabled = true;

    @Column
    private Boolean accountExpired;
    
    @Column
    private Boolean accountLocked;

    @Column
    private Boolean passwordExpired;

    @Column
    private Date date_created;

    @Column
    private Date last_updated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Papel papel;

}
