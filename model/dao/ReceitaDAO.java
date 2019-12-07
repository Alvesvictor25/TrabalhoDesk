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

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import model.seletor.DespesaSeletor;
import model.seletor.ReceitaSeletor;
import model.vo.ContaBanco;
import model.vo.Despesa;
import model.vo.Receita;

public class ReceitaDAO {
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public boolean verificarRegistroPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT IDRECEITA FROM RECEITA WHERE IDRECEITA = " + id;

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
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			stmt.setInt(1, receita.getId());
			stmt.setString(2, receita.getDescricao());
			stmt.setDouble(3, receita.getValor());
			stmt.setDate(4, java.sql.Date.valueOf(receita.getDataReceita()));
			stmt.setString(5, receita.getCategoria());
			resultado = stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				receita.setId(idGerado);
			}

			ContaBancoDAO contaDAO = new ContaBancoDAO();
			ContaBanco contaUsuario = receita.getContaBancoUsuario();
			receita.setContaBancoUsuario(contaUsuario);
			
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
				+ ", descricao = '" + receita.getDescricao() + "', valor =" + receita.getValor() + ", datareceita = '"
				+ receita.getDataReceita() + "' WHERE IDRECEITA = " + receita.getId();

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

	public List<Receita> consultarTodasReceitasPorIdUsuario(int idUsuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		String query = "SELECT * FROM RECEITA WHERE idusuario = " + idUsuario;
		List<Receita> listaReceitas = new ArrayList<Receita>();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Receita receita = criarReceitaResultSet(rs);
				listaReceitas.add(receita);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de CONSULTA de receita por Id.");
			System.out.println("Erro: " + e.getMessage());
		}

		return listaReceitas;
	}

	private Receita criarReceitaResultSet(ResultSet rs) {
		Receita receita = new Receita();
		try {
			receita.setId(rs.getInt("IDRECEITA"));
			receita.setIdUsuario(rs.getInt("IDUSUARIO"));
			ContaBancoDAO bancoDAO = new ContaBancoDAO();
			
			int idConta = rs.getInt("IDCONTA");
			
			ContaBanco contaBancoUsuario = bancoDAO.consultarContaBancoPorId(idConta);
			receita.setContaBancoUsuario(contaBancoUsuario);
			
			receita.setDescricao(rs.getString("DESCRICAO"));
			receita.setValor(rs.getDouble("VALOR"));
			receita.setDataReceita(LocalDate.parse(rs.getString("DATARECEITA"), dateTime));
		} catch (SQLException e) {
			System.out.println("Erro ao criar despesa pelo resultSet.");
			System.out.println("Erro: " + e.getMessage());
		}
		return receita;
	}

	public DefaultCategoryDataset criarGraficoReceita(int idUsuario) {
		Connection conn = Banco.getConnection();
		DefaultCategoryDataset dataSet = null;
		String sql = "Select datareceita, valor from receita where idusuario = " + idUsuario;
		try {
			dataSet = new JDBCCategoryDataset(conn, sql);
		} catch (SQLException e) {
			System.out.println("Erro ao criar gráfico receita");
			System.out.println("Erro: " + e.getMessage());
		}
		return dataSet;
	}

	public List<Receita> consultarReceitasComFiltro(ReceitaSeletor seletor) {
		String sql = "select * from receita p ";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ArrayList<Receita> listaReceitas = new ArrayList<Receita>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Receita p = criarReceitaResultSet(rs);
				listaReceitas.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Erro na consulta de receita com filtro");
			System.out.println("Erro: " + e.getMessage());
		}
		return listaReceitas;
	}

	private String criarFiltros(ReceitaSeletor seletor, String sql) {
		sql += " Where ";
		boolean primeiro = true;

		if (seletor.getContaBancoUsuario() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.IDCONTA = " + seletor.getContaBancoUsuario().getIdConta();
			primeiro = false;
		}

		if ((seletor.getDescricaoReceita() != null) && (seletor.getDescricaoReceita().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.descricao = '" + seletor.getDescricaoReceita() + "'";
			primeiro = false;
		}

		if ((seletor.getConsultaDataInicio() != null) && (seletor.getConsultaDataFim() != null)) {

			if (!primeiro) {
				sql += " AND ";
			}
			sql += " p.dataVencimento BETWEEN " + seletor.getConsultaDataInicio() + " AND "
					+ seletor.getConsultaDataFim();
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

	public List<String> verificarDescricoesReceitasDoUsuario(int idUsuario) {
		Connection conn = Banco.getConnection();
		String sql = "select distinct(descricao) from receita where idusuario = ?";
		ResultSet rs = null;
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		List<String> descricoesReceitaDoUsuario = new ArrayList<String>();
		try {
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String descricao = rs.getString("DESCRICAO");
				descricoesReceitaDoUsuario.add(descricao);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar descrição por usuário.");
			System.out.println("Erro: " + e.getMessage());
		}
		return descricoesReceitaDoUsuario;
	}
}
