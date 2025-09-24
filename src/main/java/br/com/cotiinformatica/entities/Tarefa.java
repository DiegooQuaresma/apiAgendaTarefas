package br.com.cotiinformatica.entities;

import java.time.LocalDate;
import java.util.UUID;

import br.com.cotiinformatica.enums.Prioridade;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Tarefa {

	private UUID id;
	private String nome;
	private LocalDate data;
	private Prioridade prioridade;
	private Boolean finalizado;
	private Categoria categoria;
}
