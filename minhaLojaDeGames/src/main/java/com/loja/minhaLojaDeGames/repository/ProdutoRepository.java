package com.loja.minhaLojaDeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.minhaLojaDeGames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	public List<Produto> findAllByTituloContainingIgnoreCase(String titulo);
	public List<Produto> findAllByDesenvolvedorContainingIgnoreCase(String desenvolvedor);
}
