package com.project.colabeduc.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.colabeduc.backend.entities.Descricao;

public interface DescricaoRepository extends JpaRepository<Descricao, Long> {
    
    public List<Descricao> getDescricoes();

}
