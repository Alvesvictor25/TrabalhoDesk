package model.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.ReceitaVO;



public class ReceitaDAO {
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// VERIFICAR ID RECEITA.
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

	// CADASTRO RECEITA.
	public int cadastrarReceitaDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		

		String query = "INSERT INTO receita (IDUSUARIO, descricao, valor, datareceita) VALUES (" 
				+ receitaVO.getIdusuario() + ", '"
				+ receitaVO.getDescricao() + "', " 
				+ receitaVO.getValor() + ", '" 
				+ receitaVO.getDataReceita() + "')";

		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica a existência de uma receita por ID.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	// CONSULTAR RECEITA (TODAS).
	public ArrayList<ReceitaVO> consultarTodasReceitasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<ReceitaVO> receitasVO = new ArrayList<ReceitaVO>();

		String query = "SELECT IDRECEITA,IDUSUARIO,DESCRICAO,VALOR,DATARECEITA FROM RECEITA";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				ReceitaVO receitaVO = new ReceitaVO();
				receitaVO.setId(resultado.getInt(1));
				receitaVO.setIdusuario(resultado.getInt(2));
				receitaVO.setDescricao(resultado.getString(3));
				receitaVO.setValor(resultado.getDouble(4));
				receitaVO.setDataReceita(LocalDate.parse(resultado.getString(5), dateTime));
			
				receitasVO.add(receitaVO);

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
	public ReceitaVO consultarTodasReceitasDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ReceitaVO receita = new ReceitaVO();

		String query = "SELECT IDRECEITA, IDUSUARIO,DESCRICAO,VALOR,DATARECEITA FROM RECEITA WHERE IDRECEITA = "
				+ receitaVO.getId();

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				receita.setId(resultado.getInt(1));
				receita.setIdusuario(resultado.getInt(2));
				receita.setDescricao(resultado.getString(3));
				receita.setValor(resultado.getDouble(4));
				receita.setDataReceita(LocalDate.parse(resultado.getString(5), dateTime));
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de CONSULTA de receita por Id.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}
		return receita;

	}

	// ATUALIZAR RECEITA.
	public int atualizarReceitaDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "UPDATE RECEITA SET IDRECEITA =" + receitaVO.getId() + ", idusuario = " + receitaVO.getIdusuario()
		+ ", descricao = '" + receitaVO.getDescricao() + "', valor ="
		+ receitaVO.getValor() + ", datareceita = '" + receitaVO.getDataReceita()
		+  "' WHERE IDRECEITA = " + receitaVO.getId();
		
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

	// EXCLUIR RECEITA. 
	public int excluirReceitaDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM receita WHERE idreceita = " + receitaVO.getId();

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
