package br.com.cotiinformatica.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TarefaPostRequest {

	private String nomeTarefa; //nome da tarefa
	private String dataTarefa; //data da tarefa
	private String prioridadeTarefa; //prioridade da tarefa
	private String idCategoria; //categoria da tarefa
}
