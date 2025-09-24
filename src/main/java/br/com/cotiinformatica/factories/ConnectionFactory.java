package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	/*
	 * Método para realizar a conexão com o banco de dados
	 * do PostgreSQL e retornar esta conexão para as 
	 * classes de repositório do sistema.
	 */
	public Connection getConnection() {
		try {
			
			//variáveis para conexão com o banco de dados
			var host = "jdbc:postgresql://localhost:5432/bd_agendatarefas";
			var user = "postgres";
			var pass = "coti";
			
			//Abrindo e retornando uma conexão com o banco de dados
			return DriverManager.getConnection(host, user, pass);
		}
		catch(Exception e) {
			e.printStackTrace(); //imprimir um log de erro
			return null; //retornar vazio
		}
	}
}
