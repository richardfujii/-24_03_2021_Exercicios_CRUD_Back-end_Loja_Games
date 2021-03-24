package com.loja.minhaLojaDeGames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.minhaLojaDeGames.model.Produto;
import com.loja.minhaLojaDeGames.repository.ProdutoRepository;



@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	private ResponseEntity<List<Produto>> findAllProduto(){
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	private ResponseEntity<Produto> findByIDProduto(@PathVariable long id){
		return repository.findById(id).map(res -> ResponseEntity.ok(res)).
				orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	private ResponseEntity<List<Produto>> findByDescricaoTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@GetMapping("/desenvolvedor/desenvolvedor/{desenvolvedor}")
	private ResponseEntity<List<Produto>> findAllByDesenvolvedor(@PathVariable String desenvolvedor){
		return ResponseEntity.ok(repository.findAllByDesenvolvedorContainingIgnoreCase(desenvolvedor));
	}
	
	@PostMapping
	private ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).
				body(repository.save(produto));
	}
	
	@PutMapping
	private ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
		return ResponseEntity.ok(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id)
	{
		repository.deleteById(id);
	}
	
}