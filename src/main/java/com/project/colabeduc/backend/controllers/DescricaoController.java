package com.project.colabeduc.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.colabeduc.backend.entities.Descricao;
import com.project.colabeduc.backend.services.DescricaoService;

@RestController
@RequestMapping("/api/descricoes")
public class DescricaoController {
    
    @Autowired
    private DescricaoService descricaoService;

    @GetMapping("/{idDescricao}")
    public Descricao getDescricao(@PathVariable Long idDescricao) {
        return descricaoService.getDescricao(idDescricao);
    }

    @GetMapping("/")
    public List<Descricao> getDescricoes() {
        List<Descricao> descricoes = descricaoService.getDescricoes();
        return descricoes;
    }

    @PostMapping
    public void salvar(@RequestBody Descricao descricao) {
        descricaoService.salvar(descricao);
    }

    @PutMapping
    public void atualizar(@RequestBody Descricao descricao) {
        descricaoService.salvar(descricao);
    }

    @DeleteMapping("/{idDescricao}")
    public void remover(@PathVariable Long idDescricao) {
        descricaoService.remover(idDescricao);
    }
    
    
}
