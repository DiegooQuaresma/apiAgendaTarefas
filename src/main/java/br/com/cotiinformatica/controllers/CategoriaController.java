package br.com.cotiinformatica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.repositories.CategoriaRepository;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

	// Atributo
	private CategoriaRepository categoriaRepository = new CategoriaRepository();

	/*
	 * Método para consultar as categorias
	 */
	@GetMapping
	public ResponseEntity<?> getAll() {
		try {

			// Executando a consulta de categorias e obter a lista
			var lista = categoriaRepository.findAll();
			// HTTP 200 - OK
			return ResponseEntity.status(200).body(lista);

		} catch (Exception e) {
			// HTTP 500 - Internal Server Error
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
}
