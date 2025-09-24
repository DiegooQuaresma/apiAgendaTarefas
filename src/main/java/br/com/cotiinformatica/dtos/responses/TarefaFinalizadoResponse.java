package br.com.cotiinformatica.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TarefaFinalizadoResponse {

	private String status;
	private Integer quantidade;

}
