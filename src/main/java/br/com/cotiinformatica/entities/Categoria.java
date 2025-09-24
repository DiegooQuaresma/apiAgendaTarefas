package br.com.cotiinformatica.entities;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Categoria {

	private UUID id;
	private String nome;
}
