package br.com.cotiinformatica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.repositories.TarefaRepository;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

	private TarefaRepository tarefaRepository = new TarefaRepository();

	@GetMapping("tarefas-finalizado")
	public ResponseEntity<?> getTarefaFinalizado() {
		var lista = tarefaRepository.groupByFinalizado();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("tarefas-prioridade")
	public ResponseEntity<?> getTarefaPrioridade() {
		var lista = tarefaRepository.groupByPrioridade();
		return ResponseEntity.ok(lista);

	}

	@GetMapping("tarefas-categoria")
	public ResponseEntity<?> getTarefaCategoria() {
		var lista = tarefaRepository.groupByCategoria();
		return ResponseEntity.ok(lista);
	}
}
