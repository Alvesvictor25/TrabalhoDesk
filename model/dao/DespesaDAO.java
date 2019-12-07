package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.seletor.DespesaSeletor;
import model.vo.Despesa;

public class DespesaDAO {
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// VERIFICAR ID DESPESA.
	public boolean verificarRegistroDespesaPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT IDDESPESA FROM DESPESA WHERE IDDESPESA = " + id;

		try {

			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true;

			}
		} catch (SQLException error) {
			System.out.println("Erro ao executar a Query que verifica a existência de uma despesa por ID.");
			System.out.println("Erro: " + error.getMessage());
			error.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public boolean cadastrarDespesaDAO(Despesa despesa) {
		Connection conn = Banco.getConnection();
		String sql = "Insert into despesa( idusuario, descricao, valor, datavencimento, datapagamento, categoria)"
				+ "Values(?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		boolean resultado = false;

		try {
			stmt.setInt(1, despesa.setIdUsuario());
			stmt.setString(2, despesa.getDescricao());
			stmt.setDouble(3, despesa.getValor());
			stmt.setDate(4, java.sql.Date.valueOf(despesa.getDataVencimento()));
			stmt.setDate(5, java.sql.Date.valueOf(despesa.getDataPagamento()));
			stmt.setString(6, despesa.getCategoria());
			resultado = stmt.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que cadastra uma despesa.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return resultado;
	}

	public ArrayList<Despesa> consultarTodosDespesasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<Despesa> listaDespesas = new ArrayList<Despesa>();

		String query = "SELECT IDDESPESA,IDUSUARIO,DESCRICAO,VALOR,DATAVENCIMENTO,DATAPAGAMENTO,CATEGORIA FROM DESPESA";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Despesa despesa = criarResultSet(resultado);
				listaDespesas.add(despesa);

			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de consulta de todas as despesas por usuário.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaDespesas;
	}

	public Despesa consultarTodosDespesasDAO(Despesa despesa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		Despesa consultaDespesa = new Despesa();

		String query = "SELECT IDDESPESA,IDUSUARIO,DESCRICAO,VALOR,DATAVENCIMENTO,DATAPAGAMENTO,CATEGORIA FROM DESPESA WHERE IDDESPESA = "
				+ despesa.getId();

		try {
			resultado = stmt.executeQuery(query);

			while (resultado.next()) {
				consultaDespesa = criarResultSet(resultado);

			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de consulta de despesa por IDdespesa.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return consultaDespesa;
	}

	// ATUALIZAR DESPESA.
	public int atualizarDespesaDAO(Despesa despesa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "UPDATE DESPESA SET IDDESPESA =" + despesa.getId() + ", IDUSUARIO = "
				+ despesa.setIdUsuario() + ", descricao = '" + despesa.getDescricao() + "', valor = "
				+ despesa.getValor() + ", datavencimento = '" + despesa.getDataVencimento() + "', datapagamento ='"
				+ despesa.getDataPagamento() + "', categoria = '" + despesa.getCategoria() + "' WHERE IDDESPESA = "
				+ despesa.getId();

		try {
			resultado = stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica a atualização de uma despesa por IDdespesa.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	// EXCLUIR DESPESA.
	public int excluirDespesaDAO(Despesa despesa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM despesa WHERE iddespesa = " + despesa.getId();

		try {

			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica a exclusão de uma despesa por IDdespesa.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public ArrayList<Despesa> consultarTodasAsDespesasPorUsuario(int idUsuario) {
		Connection conn = Banco.getConnection();
		String sql = "SELECT * FROM DESPESA WHERE IDUSUARIO = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		ArrayList<Despesa> listaDespesas = new ArrayList<Despesa>();
		try {
			stmt.setInt(1, idUsuario);

			rs = stmt.executeQuery();

			while(rs.next()) {
				Despesa despesaUsuario = new Despesa();
				despesaUsuario = criarResultSet(rs);
				listaDespesas.add(despesaUsuario);
			}

		} catch(SQLException e) {
			System.out.println("Erro ao consultar despesas por IDusuário.");
			System.out.println("Erro: " + e.getMessage());
		} 
		return listaDespesas;
	}

	private Despesa criarResultSet(ResultSet resultado) {
		Despesa despesaResultSet = new Despesa();

		try {
			despesaResultSet.setId(resultado.getInt("IDDESPESA"));
			despesaResultSet.setIdUsuario(resultado.getInt("IDUSUARIO"));
			despesaResultSet.setDescricao(resultado.getString("DESCRICAO"));
			despesaResultSet.setValor(resultado.getDouble("VALOR"));
			despesaResultSet.setDataVencimento(LocalDate.parse(resultado.getString("DATAPAGAMENTO"), dateTime));
			despesaResultSet.setDataPagamento(LocalDate.parse(resultado.getString("DATAPAGAMENTO"), dateTime));
			despesaResultSet.setCategoria(resultado.getString("CATEGORIA"));

		} catch (SQLException e) {
			System.out.println("Erro ao criar despesa pelo resultSet.");
			System.out.println("Erro: " + e.getMessage());
		}
		return despesaResultSet;
	}

	public List<Despesa> consultarDespesaComFiltro(DespesaSeletor seletor) {
		String sql = "select * from despesa p";

		if(seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ArrayList<Despesa> listaDespesas = new ArrayList<Despesa>();

		try {
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Despesa p = criarResultSet(rs);
				listaDespesas.add(p);
			}
		} catch(SQLException e) {
			System.out.println("Erro na consulta de despesa com filtro");
			System.out.println("Erro: " + e.getMessage());
		}
		return listaDespesas;
	}

	private String criarFiltros(DespesaSeletor seletor, String sql) {
		sql += " Where ";
		boolean primeiro = true;
		
		if((seletor.getCategoriaDespesa() != null) && (seletor.getCategoriaDespesa().trim().length() > 0)) {
			if(!primeiro) {
				sql += " AND ";
			}
			sql += "p.categoria = '" + seletor.getCategoriaDespesa() + "'";
			primeiro = false;
		}

		if ((seletor.getDescricaoDespesa() != null) && (seletor.getDescricaoDespesa().trim().length() >0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.descricao = '" + seletor.getDescricaoDespesa() + "'";
			primeiro = false;
		}

		if ((seletor.getConsultaDataInicio() != null) && (seletor.getConsultaDataFim() != null)) {

			if (!primeiro) {
				sql += " AND ";
			}
			sql += " p.dataVencimento BETWEEN " + seletor.getConsultaDataInicio() + " AND " + seletor.getConsultaDataFim();
			primeiro = false;
		} else if (seletor.getConsultaDataInicio() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += " p.dataVencimento >= " + seletor.getConsultaDataInicio();
			primeiro = false;
		} else if (seletor.getConsultaDataFim() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.dataVencimento <= " + seletor.getConsultaDataFim();
			primeiro = false;
		}
		return sql;
	}
}
