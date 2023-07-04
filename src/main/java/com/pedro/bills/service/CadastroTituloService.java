package com.pedro.bills.service;

import com.pedro.bills.model.StatusTitulo;
import com.pedro.bills.model.Titulo;
import com.pedro.bills.repository.Titulos;
import com.pedro.bills.repository.filter.TituloFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroTituloService {

    @Autowired
    private Titulos titulos;

    public void salvar(Titulo titulo){
        try{
            titulos.save(titulo);
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Invalid format");
        }
    }

    public void excluir(Long id){
        titulos.deleteById(id);
    }

    public String receber(Long id) {
        Titulo titulo = titulos.getReferenceById(id);
        titulo.setStatus(StatusTitulo.RECEBIDO);
        titulos.save(titulo);

        return StatusTitulo.RECEBIDO.getDescricao();
    }

    public List<Titulo> filtrar(TituloFilter filtro){
        String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();
        return titulos.findByDescricaoContaining(descricao);
    }
}
