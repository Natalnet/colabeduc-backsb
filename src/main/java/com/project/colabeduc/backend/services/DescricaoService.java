package com.project.colabeduc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.colabeduc.backend.entities.Descricao;
import com.project.colabeduc.backend.repositories.DescricaoRepository;

@Service
public class DescricaoService {

    @Autowired
    private DescricaoRepository descricaoRepository;

    public Descricao getDescricao(Long idDescricao) {
        Optional<Descricao> descricao = descricaoRepository.findById(idDescricao);

        return descricao.get();
    }

    public Descricao salvar(Descricao descricao) {
        return descricaoRepository.save(descricao);
    }

    public Descricao atualizar(Descricao descricao) {
        return descricaoRepository.save(descricao);
    }

    public void remover(Long idDescricao) {
        descricaoRepository.deleteById(idDescricao);
    }

    public List<Descricao> getDescricoes() {
        return descricaoRepository.findAll();
    }
}
