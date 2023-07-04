package com.pedro.bills.repository;

import com.pedro.bills.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Titulos extends JpaRepository<Titulo, Long> {

    List<Titulo> findByDescricaoContaining(String descricao);



}
