package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.ContaBanco;

public class ContaBancoDAO {

	public void cadastrarContaBanco(ContaBanco conta) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO CONTABANCO (IDUSUARIO, NOMEDOBANCO, AGENCIA, NUMEROCONTA, SALDODACONTA) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		try {
			stmt.setInt(1, conta.getIdUsuario());
			stmt.setString(2, conta.getNomeDoBanco());
			stmt.setInt(3, conta.getAgencia());
			stmt.setInt(4, conta.getNumeroConta());
			stmt.setDouble(5, conta.getSaldoDaConta());

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

	private ContaBanco criarContaBancoResultSet(ResultSet rs) {
		ContaBanco contaResultSet = new ContaBanco();

		try {
			contaResultSet.setIdConta(rs.getInt("IDCONTA"));
			contaResultSet.setIdUsuario(rs.getInt("IDUSUARIO"));
			contaResultSet.setAgencia(rs.getInt("AGENCIA"));
			contaResultSet.setNumeroConta(rs.getInt("NUMEROCONTA"));
			contaResultSet.setSaldoDaConta(rs.getDouble("SALDODACONTA"));
			contaResultSet.setNomeDoBanco(rs.getString("NOMEDOBANCO"));
		} catch (SQLException e) {
			System.out.println("Erro ao criar conta pelo resultSet");
			System.out.println("Erro: " + e.getMessage());
		}
		return contaResultSet;
	}
}
