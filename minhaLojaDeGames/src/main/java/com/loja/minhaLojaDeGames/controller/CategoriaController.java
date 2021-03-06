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

import com.loja.minhaLojaDeGames.model.Categoria;
import com.loja.minhaLojaDeGames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	private ResponseEntity<List<Categoria>> findAllCategoria(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Categoria> findByIDCategoria(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).
				orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	private ResponseEntity<List<Categoria>> findByDescricaoCategoria(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity <Categoria> postCategoria (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).
				body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity <Categoria> putCategoria (@RequestBody Categoria categoria){
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id){
		repository.deleteById(id);
	}
}