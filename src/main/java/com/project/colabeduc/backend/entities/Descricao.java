package com.project.colabeduc.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@NamedQuery(name = "Descricao.getDescricoes", query ="SELECT d FROM Descricao d")
public class Descricao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer version;

    @Column
    private String titulo;

    @Column
    private String resumo;

    @Column
    private String descricao;


    

}
