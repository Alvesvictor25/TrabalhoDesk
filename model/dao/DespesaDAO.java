package model.dao;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.vo.DespesaVO;

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

	// CADASTRAR DESPESA. 
	public int cadastrarDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "INSERT INTO despesa (IDUSUARIO, descricao, valor, datavencimento, datapagamento, categoria) VALUES ("
				+ despesaVO.getIdusuario() + ",'" + despesaVO.getDescricao() + "'," + despesaVO.getValor() + "," + "'"
				+ despesaVO.getDataVencimento() + "','" + despesaVO.getDataPagamento() + "', '"
				+ despesaVO.getCategoria() + "')";

		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que cadastra uma despesa por ID.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return resultado;
	}

	private DespesaVO criarResultSet(ResultSet resultado) {
		DespesaVO despesaVO = new DespesaVO();
		
		try {
			despesaVO.setCategoria(resultado.getString("CATEGORIA"));
			despesaVO.setDescricao(resultado.getString("DESCRICAO"));
			despesaVO.setDataVencimento(LocalDate.parse(resultado.getString("DATAPAGAMENTO"), dateTime));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// CONSULTAR DESPESA (TODAS).
	public ArrayList<DespesaVO> consultarTodosDespesasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<DespesaVO> despesasVO = new ArrayList<DespesaVO>();

		String query = "SELECT IDDESPESA,IDUSUARIO,DESCRICAO,VALOR,DATAVENCIMENTO,DATAPAGAMENTO,CATEGORIA FROM DESPESA";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				DespesaVO despesaVO = new DespesaVO();
				despesaVO.setId(resultado.getInt(1));
				despesaVO.setIdusuario(resultado.getInt(2));
				despesaVO.setDescricao(resultado.getString(3));
				despesaVO.setValor(resultado.getDouble(4));
				despesaVO.setDataVencimento(LocalDate.parse(resultado.getString(5), dateTime));
				despesaVO.setDataPagamento(LocalDate.parse(resultado.getString(6), dateTime));
				despesaVO.setCategoria(resultado.getString(7));
				despesasVO.add(despesaVO);

			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de consulta de todas as despesas por IDusuario.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return despesasVO;
	}

	// CONSULTAR DEPESA (UM). 
	public DespesaVO consultarTodosDespesasDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		DespesaVO despesa = new DespesaVO();

		String query = "SELECT IDDESPESA,IDUSUARIO,DESCRICAO,VALOR,DATAVENCIMENTO,DATAPAGAMENTO,CATEGORIA FROM DESPESA WHERE IDDESPESA = " 
				+ despesaVO.getId();

		try {
			resultado = stmt.executeQuery(query);
		
			while (resultado.next()) {
				despesa.setId(resultado.getInt(1));
				despesa.setIdusuario(resultado.getInt(2));
				despesa.setDescricao(resultado.getString(3));
				despesa.setValor(resultado.getDouble(4));
				despesa.setDataVencimento(LocalDate.parse(resultado.getString(5), dateTime));
				despesa.setDataPagamento(LocalDate.parse(resultado.getString(6), dateTime));
				despesa.setDataPagamento(LocalDate.parse(resultado.getString(6), dateTime));
				despesa.setCategoria(resultado.getString(7));

			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de consulta de despesa por IDdespesa.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return despesa;
	}

	// ATUALIZAR DESPESA. 
	public int atualizarDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "UPDATE DESPESA SET IDDESPESA =" + despesaVO.getId() + ", IDUSUARIO = " + despesaVO.getIdusuario() + ", descricao = '"
				+ despesaVO.getDescricao() + "', valor = " + despesaVO.getValor() + ", datavencimento = '"
				+ despesaVO.getDataVencimento() + "', datapagamento ='" + despesaVO.getDataPagamento()
				+ "', categoria = '" + despesaVO.getCategoria() + "' WHERE IDDESPESA = " + despesaVO.getId();

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
	public int excluirDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM despesa WHERE iddespesa = " + despesaVO.getId();

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

	public ArrayList<DespesaVO> consultarTodasAsDespesasPorUsuario(int idusuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
