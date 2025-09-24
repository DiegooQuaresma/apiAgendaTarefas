package br.com.cotiinformatica.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.dtos.responses.TarefaCategoriaResponse;
import br.com.cotiinformatica.dtos.responses.TarefaFinalizadoResponse;
import br.com.cotiinformatica.dtos.responses.TarefaPrioridadeResponse;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.enums.Prioridade;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class TarefaRepository {

	private ConnectionFactory connectionFactory = new ConnectionFactory();

	/*
	 * Método para inserir uma tarefa no banco de dados
	 */
	public void insert(Tarefa tarefa) {

		try {

			// Abrir conexão com o banco de dados
			var connection = connectionFactory.getConnection();

			// escrever o comando SQL que será executado no banco de dados
			var sql = """
					insert into tarefa(id, nome, data, prioridade, finalizado, categoria_id)
					values (?,?,?,?,?,?)
					""";

			// executar o comando SQL no banco de dados passando os parâmetros
			var statement = connection.prepareStatement(sql);
			statement.setObject(1, tarefa.getId());
			statement.setString(2, tarefa.getNome());
			statement.setDate(3, java.sql.Date.valueOf(tarefa.getData()));
			statement.setString(4, tarefa.getPrioridade().toString());
			statement.setBoolean(5, tarefa.getFinalizado());
			statement.setObject(6, tarefa.getCategoria().getId());
			statement.execute();

			// fechando a conexão com o banco de dados
			connection.close();
		} catch (Exception e) {
			e.printStackTrace(); // log de erro no console do eclipse
		}
	}

	/*
	 * Método para atualizar uma tarefa no banco de dados
	 */
	public boolean update(Tarefa tarefa) {

		try {
			// Abrir a conexão com o banco de dados
			var connection = connectionFactory.getConnection();

			// Escrever o comando SQL
			var sql = """
						update tarefa set nome = ?, data = ?, prioridade = ?, finalizado = ?, categoria_id = ?
						where id = ?
					""";

			// Executando o comando SQL no banco de dados
			var statement = connection.prepareStatement(sql);
			statement.setString(1, tarefa.getNome());
			statement.setDate(2, java.sql.Date.valueOf(tarefa.getData()));
			statement.setString(3, tarefa.getPrioridade().toString());
			statement.setBoolean(4, tarefa.getFinalizado());
			statement.setObject(5, tarefa.getCategoria().getId());
			statement.setObject(6, tarefa.getId());
			var rowsAffected = statement.executeUpdate();

			// fechar a conexão do banco de dados
			connection.close();

			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace(); // log de erro no console do eclipse
			return false;
		}
	}

	/*
	 * Método para excluir uma tarefa no banco de dados
	 */
	public boolean delete(UUID id) {

		try {
			// Abrir conexão com o banco de dados
			var connection = connectionFactory.getConnection();

			// Escrever o comando SQL
			var sql = """
						delete from tarefa
						where id = ?
					""";

			// Executando o comando SQL no banco de dados
			var statement = connection.prepareStatement(sql);
			statement.setObject(1, id);
			var rowsAffected = statement.executeUpdate();

			// fechando a conexão com o banco de dados
			connection.close();

			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace(); // log de erro no console do eclipse
			return false;
		}
	}

	/*
	 * Método para consultar todas as tarefas no banco de dados
	 */
	public List<Tarefa> findAll() {
		try {

			// Abrir conexão com o banco de dados
			var connection = connectionFactory.getConnection();

			// Escrever o comando SQL
			var sql = """
											select
					t.id as idtarefa, t.nome as nometarefa, t.data, t.prioridade, t.finalizado,
					c.id as idcategoria, c.nome as nomecategoria
					from tarefa t
					inner join categoria c
					on c.id = t.categoria_id
					order by data desc;
										""";

			// Executar o comando sql no banco de dados
			var statement = connection.prepareStatement(sql);
			var result = statement.executeQuery();

			// Criando uma lista de tarefas vazia
			var lista = new ArrayList<Tarefa>();

			// Ler cada registro (linha) obtido do banco de dados
			while (result.next()) {

				// Criando um objeto da classe de entidade e ler os campos do banco
				var tarefa = new Tarefa();
				tarefa.setCategoria(new Categoria());// instanciando a categoria da tarefa

				tarefa.setId(UUID.fromString(result.getString("idtarefa")));
				tarefa.setNome(result.getString("nometarefa"));
				tarefa.setData(LocalDate.parse(result.getString("data")));
				tarefa.setPrioridade(Prioridade.valueOf(result.getString("prioridade")));
				tarefa.setFinalizado(result.getBoolean("finalizado"));
				tarefa.getCategoria().setId(UUID.fromString(result.getString("idcategoria")));
				tarefa.getCategoria().setNome(result.getString("nomecategoria"));

				lista.add(tarefa); // Adicionar a tarefa na lista
			}

			// fechando a conexão com o banco de dados
			connection.close();

			// retornar a lista
			return lista;
		} catch (Exception e) {
			e.printStackTrace(); // log de erro no console do eclipse
			return null;
		}
	}

	public Tarefa findById(UUID id) {
		try {
			// Abrindo conexão com o banco de dados
			var connection = connectionFactory.getConnection();

			var sql = """
						select
						t.id as idtarefa, t.nome as nometarefa, t.data, t.prioridade, t.finalizado,
						c.id as idcategoria, c.nome as nomecategoria
						from tarefa t
						inner join categoria c
						on c.id = t.categoria_id
					where t.id = ?

						""";
			// Executar a consulta no banco de dados
			var statement = connection.prepareStatement(sql);
			statement.setObject(1, id); // passando o valor do Id
			var result = statement.executeQuery();

			// Criando um objeto da classe Tarefa com valor inicial null(vazio)
			Tarefa tarefa = null;

			// Verificar se algum registro foi encontrado no banco
			if (result.next()) {

				tarefa = new Tarefa();
				tarefa.setCategoria(new Categoria());// instanciando categoria

				tarefa.setId(UUID.fromString(result.getString("idtarefa")));
				tarefa.setNome(result.getString("nometarefa"));
				tarefa.setData(LocalDate.parse(result.getString("data")));
				tarefa.setPrioridade(Prioridade.valueOf(result.getString("prioridade")));
				tarefa.setFinalizado(result.getBoolean("finalizado"));
				tarefa.getCategoria().setId(UUID.fromString(result.getString("idcategoria")));
				tarefa.getCategoria().setNome(result.getString("nomecategoria"));

			}

			// Fechando o banco de dados
			connection.close();

			// Retornando os dados da tarefa
			return tarefa;

		}

		catch (Exception e) {
			e.printStackTrace(); // log de erro no console do eclipse
			return null;
		}
	}

	// Método para consultar a quantidade de tarefas finalizadas e nao finalizadas
	// na banco de dados

	public List<TarefaFinalizadoResponse> groupByFinalizado() {
		try {
			var connection = connectionFactory.getConnection();
			var sql = """
						SELECT
					 	CASE
						 	WHEN finalizado = true THEN ' Tarefas finalizadas'
							ELSE 'Tarefas em aberto'
						END AS status,
					  	COUNT(*) as quantidade
					FROM
						tarefa
					GROUP BY
						finalizado;
										""";
			var statement = connection.prepareStatement(sql);
			var result = statement.executeQuery();

			var lista = new ArrayList<TarefaFinalizadoResponse>();
			while (result.next()) {
				var response = new TarefaFinalizadoResponse();
				response.setStatus(result.getString("status"));
				response.setQuantidade(result.getInt("quantidade"));
				lista.add(response);
			}
			connection.close();
			return lista;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	// Método para consultar q quantidade de tarefas por prioridade na banco de
	// dados
	public List<TarefaPrioridadeResponse> groupByPrioridade() {
		try {
			var connection = connectionFactory.getConnection();
			var sql = """
						SELECT
						prioridade,
							COUNT(*) AS quantidade
					FROM
						tarefa
					GROUP BY
						prioridade;
															""";
			var statement = connection.prepareStatement(sql);
			var result = statement.executeQuery();

			var lista = new ArrayList<TarefaPrioridadeResponse>();
			while (result.next()) {
				var response = new TarefaPrioridadeResponse();
				response.setPrioridade(result.getString("prioridade"));
				response.setQuantidade(result.getInt("quantidade"));
				lista.add(response);
			}
			connection.close();
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<TarefaCategoriaResponse> groupByCategoria() {
		try {
			var connection = connectionFactory.getConnection();
			var sql = """
									SELECT
					  	c.nome as categoria,
						  COUNT(*) as quantidade
					FROM tarefa t
					INNER JOIN categoria c
					ON c.id = t.categoria_id
					GROUP BY
						c.nome;
																				""";
			var statement = connection.prepareStatement(sql);
			var result = statement.executeQuery();

			var lista = new ArrayList<TarefaCategoriaResponse>();
			while (result.next()) {
				var response = new TarefaCategoriaResponse();
				response.setCategoria(result.getString("categoria"));
				response.setQuantidade(result.getInt("quantidade"));
				lista.add(response);
			}
			connection.close();
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
