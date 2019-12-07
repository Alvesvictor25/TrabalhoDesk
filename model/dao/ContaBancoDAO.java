package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ContaBanco;

public class ContaBancoDAO {

	public void cadastrarContaBanco(ContaBanco conta) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO CONTABANCO (IDUSUARIO, NOMEDOBANCO, AGENCIA, NUMEROCONTA, SALDODACONTA, STATUSCONTA) VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		try {
			stmt.setInt(1, conta.getIdUsuario());
			stmt.setString(2, conta.getNomeDoBanco());
			stmt.setInt(3, conta.getAgencia());
			stmt.setInt(4, conta.getNumeroConta());
			stmt.setDouble(5, conta.getSaldoDaConta());
			stmt.setBoolean(6, conta.isStatusDaConta());

			stmt.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar conta do banco.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public ArrayList<ContaBanco> consultarContasPorUsuario(int idUsuario) {
		Connection conn = Banco.getConnection();
		ResultSet rs = null;
		String sql = "SELECT * FROM dbcontrolegastos.contabanco where idusuario = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ArrayList<ContaBanco> listaDeContas = new ArrayList<ContaBanco>();
		try {
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ContaBanco conta = criarContaBancoResultSet(rs);
				listaDeContas.add(conta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar contas por usuário.");
			System.out.println("Erro: " + e.getMessage());
		}
		return listaDeContas;
	}

	public ArrayList<String> consultarNomeDosBancos(int idUsuario) {
		Connection conn = Banco.getConnection();
		String sql = "select distinct(nomedobanco) from contabanco where idusuario = ?";
		ResultSet rs = null;
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ArrayList<String> nomeDosBancos = new ArrayList<String>();
		try {
			stmt.setInt(1, idUsuario);

			rs = stmt.executeQuery();

			while (rs.next()) {
				nomeDosBancos.add(rs.getString("NOMEDOBANCO"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar nome dos bancos.");
			System.out.println("Erro: " + e.getMessage());
		}
		return nomeDosBancos;
	}

	public boolean atualizarContasBanco(ContaBanco contas) {
		Connection conn = Banco.getConnection();
		String sql = "UPDATE `dbcontrolegastos`.`contabanco` SET `STATUSCONTA` = ? WHERE IDCONTA = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		int linhasAfetadas = 0;
		try {
			stmt.setBoolean(1, contas.isStatusDaConta());
			stmt.setInt(2, contas.getIdConta());
			linhasAfetadas = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar contas banco.");
			System.out.println("Erro: " + e.getMessage());
		}
		return linhasAfetadas > 0;
	}

	public ContaBanco consultarContaBancoPorId(int idConta) {
		Connection conn = Banco.getConnection();
		ResultSet rs = null;
		String sql = "Select * from contabanco where idconta = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ContaBanco consultaConta = null;
		try {

			stmt.setInt(1, idConta);
			rs = stmt.executeQuery();

			while (rs.next()) {
				consultaConta = criarContaBancoResultSet(rs);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar conta banco por ID.");
			System.out.println("Erro: " + e.getMessage());
		}
		return consultaConta;
	}

	private ContaBanco criarContaBancoResultSet(ResultSet rs) {
		ContaBanco contaResultSet = new ContaBanco();

		try {
			contaResultSet.setIdConta(rs.getInt("IDCONTA"));
			contaResultSet.setIdUsuario(rs.getInt("IDUSUARIO"));
			contaResultSet.setAgencia(rs.getInt("AGENCIA"));
			contaResultSet.setNumeroConta(rs.getInt("NUMEROCONTA"));
			contaResultSet.setSaldoDaConta(rs.getDouble("SALDODACONTA"));
			contaResultSet.setNomeDoBanco(rs.getString("NOMEDOBANCO"));
			contaResultSet.setStatusDaConta(rs.getBoolean("STATUSCONTA"));
		} catch (SQLException e) {
			System.out.println("Erro ao criar conta pelo resultSet");
			System.out.println("Erro: " + e.getMessage());
		}
		return contaResultSet;
	}

	public List<ContaBanco> consultarStatusContaBanco(int idUsuario, boolean statusConta) {
		Connection conn = Banco.getConnection();
		String sql = "select * from contabanco where idusuario = ? and statusconta = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		List<ContaBanco> listaDeContasBanco = new ArrayList<ContaBanco>();
		ResultSet rs = null;

		try {
			stmt.setInt(1, idUsuario);
			stmt.setBoolean(2, statusConta);

			rs = stmt.executeQuery();

			while (rs.next()) {
				ContaBanco resultadoConsulta = criarContaBancoResultSet(rs);
				listaDeContasBanco.add(resultadoConsulta);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar status contas do usuário.");
			System.out.println("Erro: " + e.getMessage());
		}
		return listaDeContasBanco;
	}

}
