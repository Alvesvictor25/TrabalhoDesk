package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.Receita;

public class ReceitaDAO {
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public boolean verificarRegistroPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT IDRECEITA FROM RECEITA WHERE IDRECEITA = " + id ;

		try {

			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true;

			}
		} catch (SQLException error) {
			System.out.println("Erro ao executar a Query que verifica a existência de uma receita por ID.");
			System.out.println("Erro: " + error.getMessage());
			error.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public boolean cadastrarReceitaDAO(Receita receita) {
		Connection conn = Banco.getConnection();
		int resultado = 0;
		String sql = "INSERT INTO receita (IDUSUARIO, descricao, valor, datareceita, categoria) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);


		try {
			stmt.setInt(1,  receita.getId());
			stmt.setString(2, receita.getDescricao());
			stmt.setDouble(3, receita.getValor());
			stmt.setDate(4, java.sql.Date.valueOf(receita.getDataReceita()));
			stmt.setString(5, receita.getCategoria());
			resultado = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica a existência de uma receita por ID.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado > 0;
	}

	public ArrayList<Receita> consultarTodasReceitasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<Receita> receitasVO = new ArrayList<Receita>();

		String query = "SELECT IDRECEITA,IDUSUARIO,DESCRICAO,VALOR,DATARECEITA FROM RECEITA";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Receita receita = new Receita();
				receita.setId(resultado.getInt(1));
				receita.setIdUsuario(resultado.getInt(2));
				receita.setDescricao(resultado.getString(3));
				receita.setValor(resultado.getDouble(4));
				receita.setDataReceita(LocalDate.parse(resultado.getString(5), dateTime));
			
				receitasVO.add(receita);

			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de consulta de todas as receitas por Id.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return receitasVO;
	}

	// CONSULTAR RECEITA (UM). 
	public Receita consultarTodasReceitasDAO(Receita receita) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		Receita receitaUsuario = new Receita();

		String query = "SELECT IDRECEITA, IDUSUARIO,DESCRICAO,VALOR,DATARECEITA FROM RECEITA WHERE IDRECEITA = "
				+ receita.getId();

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				receitaUsuario.setId(resultado.getInt(1));
				receitaUsuario.setIdUsuario(resultado.getInt(2));
				receitaUsuario.setDescricao(resultado.getString(3));
				receitaUsuario.setValor(resultado.getDouble(4));
				receitaUsuario.setDataReceita(LocalDate.parse(resultado.getString(5), dateTime));
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de CONSULTA de receita por Id.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}
		return receitaUsuario;

	}

	public int atualizarReceitaDAO(Receita receita) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "UPDATE RECEITA SET IDRECEITA =" + receita.getId() + ", idusuario = " + receita.setIdUsuario()
		+ ", descricao = '" + receita.getDescricao() + "', valor ="
		+ receita.getValor() + ", datareceita = '" + receita.getDataReceita()
		+  "' WHERE IDRECEITA = " + receita.getId();
		
		try {
			resultado = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que ATUALIZAR uma receita por Id.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return resultado;
	}

	public int excluirReceitaDAO(Receita receita) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM receita WHERE idreceita = " + receita.getId();

		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica a exclusão de uma receita por id.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}
}
