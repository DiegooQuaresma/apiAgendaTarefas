package br.com.cotiinformatica.controllers;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.requests.TarefaPostRequest;
import br.com.cotiinformatica.dtos.requests.TarefaPutRequest;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.enums.Prioridade;
import br.com.cotiinformatica.repositories.TarefaRepository;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefaController {

	// Atributo
	private TarefaRepository tarefaRepository = new TarefaRepository();

	/*
	 * Método para cadastrar uma tarefa na API.
	 */
	@PostMapping
	public ResponseEntity<?> post(@RequestBody TarefaPostRequest request) {

		// Capturar os dados da requisição
		var tarefa = new Tarefa();

		tarefa.setId(UUID.randomUUID()); // gerando um ID
		tarefa.setNome(request.getNomeTarefa()); // capturando o nome da tarefa
		tarefa.setData(LocalDate.parse(request.getDataTarefa())); // capturando a data da tarefa
		tarefa.setPrioridade(Prioridade.valueOf(request.getPrioridadeTarefa())); // capturando a prioridade
		tarefa.setFinalizado(false); // definindo o campo finalizado como 'false'

		tarefa.setCategoria(new Categoria()); // instanciando a categoria da tarefa
		tarefa.getCategoria().setId(UUID.fromString(request.getIdCategoria())); // capturando o id da categoria

		// Salvar a tarefa no banco de dados
		tarefaRepository.insert(tarefa);

		return ResponseEntity.ok("Tarefa cadastrada com sucesso!");
	}

	/*
	 * Método para atualizar uma tarefa na API.
	 */
	@PutMapping
	public ResponseEntity<?> put(@RequestBody TarefaPutRequest request) {

		// Capturar os dados da requisição
		var tarefa = new Tarefa();

		tarefa.setId(UUID.fromString(request.getIdTarefa())); // capturando o id da tarefa
		tarefa.setNome(request.getNomeTarefa()); // capturando o nome da tarefa
		tarefa.setData(LocalDate.parse(request.getDataTarefa())); // capturando a data da tarefa
		tarefa.setPrioridade(Prioridade.valueOf(request.getPrioridadeTarefa())); // capturando a prioridade
		tarefa.setFinalizado(false); // definindo o campo finalizado como 'false'

		tarefa.setCategoria(new Categoria()); // instanciando a categoria da tarefa
		tarefa.getCategoria().setId(UUID.fromString(request.getIdCategoria())); // capturando o id da categoria

		// Atualizar a tarefa no banco de dados
		tarefaRepository.update(tarefa);

		return ResponseEntity.ok("Tarefa atualizada com sucesso!");
	}

	/*
	 * Método para excluir uma tarefa na API
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable UUID id) {

		// Excluindo a tarefa no banco de dados
		if (tarefaRepository.delete(id)) {
			return ResponseEntity.ok("Tarefa excluída com sucesso!");
		} else {
			// HTTP 404 (não encontrado)
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Método para consultar as tarefas
	 */
	@GetMapping
	public ResponseEntity<?> getAll() {

		// Consultar todas as tarefas no banco de dados
		var tarefas = tarefaRepository.findAll();

		// Retornar os dados obtidos do banco
		return ResponseEntity.ok(tarefas);
	}

	/*
	 * Método para consultar 1 tarefa através do ID
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {

		// Consultar 1 tarefa no banco de dados através do ID
		var tarefa = tarefaRepository.findById(id);

		// Verificar se alguma tarefa foi encontrada
		if (tarefa != null) {
			// Retornar os dados da tarefa
			return ResponseEntity.ok(tarefa);
		} else {
			// HTTP 404 (não encontrado)
			return ResponseEntity.notFound().build();
		}
	}
}
