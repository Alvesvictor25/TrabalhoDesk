package model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.UsuarioVO;

public class UsuarioDAO {

	// VERIFICAR ID USUARIO.
	public boolean verificarRegistroPorCpf(String cpf) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT cpf FROM usuario WHERE cpf = '" + cpf + "'";

		try {

			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true;

			}
		} catch (SQLException error) {
			System.out.println("Erro ao executar a Query que verifica a existência de um usuário por cpf.");
			System.out.println("Erro: " + error.getMessage());
			error.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	// CADASTRO USUARIO.
	public int cadastrarUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		String sql = "insert into usuario(nome,cpf,telefone,login,senha, email)VALUES (?,?,?,?,?,?)";
		int resultado = 0;

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuarioVO.getNome());
			stmt.setString(2, usuarioVO.getCpf());
			stmt.setString(3, usuarioVO.getTelefone());
			stmt.setString(4, usuarioVO.getLogin());
			stmt.setString(5, usuarioVO.getSenha());
			stmt.setString(6, usuarioVO.getEmail());
			resultado = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeConnection(conn);
		}

		return resultado;
	}

	public boolean verificarRegistroPorIdUsuario(int idUsuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT idusuario FROM usuario WHERE idusuario = " + idUsuario;

		try {

			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true;

			}
		} catch (SQLException error) {
			System.out.println("Erro ao executar a Query que verifica a existência de um usuário por Id.");
			System.out.println("Erro: " + error.getMessage());
			error.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;

	}

	// CONSULTAR USUARIO (TODOS)
	public ArrayList<UsuarioVO> consultarTodosUsuariosDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<UsuarioVO> usuariosVO = new ArrayList<UsuarioVO>();

		String query = "SELECT idusuario, nome, cpf, telefone, login, senha FROM usuario";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setIdUsuario(resultado.getInt(1));
				usuarioVO.setNome(resultado.getString(2));
				usuarioVO.setCpf(resultado.getString(3));
				usuarioVO.setTelefone(resultado.getString(4));
				usuarioVO.setLogin(resultado.getString(5));
				usuarioVO.setSenha(resultado.getString(6));

				usuariosVO.add(usuarioVO);

			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de consulta de todos os usuários por IdUsuario.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}
		return usuariosVO;
	}

	// CONSULTAR USUARIO ( UM)
	public UsuarioVO consultarTodosUsuariosDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		UsuarioVO usuario = new UsuarioVO();

		String query = "SELECT IDUSUARIO,NOME,CPF,TELEFONE,LOGIN,SENHA FROM USUARIO WHERE IDUSUARIO = "
				+ usuarioVO.getIdUsuario();

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				usuario.setIdUsuario(resultado.getInt(1));
				usuario.setNome(resultado.getString(2));
				usuario.setCpf(resultado.getString(3));
				usuario.setTelefone(resultado.getString(4));
				usuario.setLogin(resultado.getString(5));
				usuario.setSenha(resultado.getString(6));

			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de consulta de usuário por IdUsuario.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}
		return usuario;

	}

	public String consultarSenha(String email) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String senha = "";

		String query = "SELECT senha FROM usuario where EMAIL = '" + email + "'";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				senha = resultado.getString("SENHA");

			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de consulta de senha por EmailService.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}
		return senha;

	}

	// ATUALIZAR USUARIO.
	public boolean atualizarUsuarioDAO(UsuarioVO usuario) {
		Connection conn = Banco.getConnection();
		int rs = 0;
		boolean linhasAfetadasUpdate = false;
		String sql = "UPDATE usuario SET nome = '" + usuario.getNome() + "',cpf = '" + usuario.getCpf() + "', telefone = '" 
				+ usuario.getTelefone() + "', login = '" + usuario.getLogin() + "', senha = '" + usuario.getSenha() + "', email = '"
				+ usuario.getEmail() + "', recoverykey = " + usuario.getCodigoRecoveryKey() + " WHERE idusuario = " + usuario.getIdUsuario();
		try {
			Statement stmt = conn.createStatement();

		//	stmt.setString(1, usuario.getNome());
		//	stmt.setString(2, usuario.getCpf());
		//	stmt.setString(3, usuario.getTelefone());
		//	stmt.setString(4, usuario.getLogin());
		//	stmt.setString(5, usuario.getSenha());
		//	stmt.setString(6, usuario.getEmail());
		//	stmt.setInt(7, usuario.getCodigoRecoveryKey());
		//	stmt.setInt(8, usuario.getIdUsuario());

			rs = stmt.executeUpdate(sql);
			linhasAfetadasUpdate = rs > 0;

			
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica a atualização de um usuário por IdUsuario.");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeConnection(conn);
		}

		return linhasAfetadasUpdate;
	}

	public int excluirUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM usuario WHERE idusuario = " + usuarioVO.getIdUsuario();

		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica a exclusão de um usuário por IdUsuario.");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return resultado;
	}

	public ArrayList<String> consultarTodosOsCpfs() {
		String sql = "select cpf from usuario";
		Connection conn = Banco.getConnection();
		ResultSet resultSet = null;
		ArrayList<String> cpfUsuarios = new ArrayList<String>();

		try {
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				cpfUsuarios.add(resultSet.getString("cpf"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar cpf dos usuários.");
			System.out.println("Erro: " + e.getMessage());
		}
		return cpfUsuarios;
	}

	public ArrayList<String> consultarTodosOsNomes() {
		String sql = "select distinct(nome) from usuario";
		Connection conn = Banco.getConnection();
		ResultSet resultSet = null;
		ArrayList<String> nomesUsuarios = new ArrayList<String>();

		try {
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				nomesUsuarios.add(resultSet.getString("nome"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar nome dos usuários.");
			System.out.println("Erro: " + e.getLocalizedMessage());
		}

		return nomesUsuarios;
	}

	public boolean verificarEmail(String emailEnviado) {
		Connection conn = Banco.getConnection();
		String sql = "select count(email) from usuario where email = ? ";
		boolean verificarSeEmailExiste = false;
		ResultSet rs = null;

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailEnviado);

			rs = stmt.executeQuery();
			verificarSeEmailExiste = rs.next();

		} catch (SQLException e) {
			System.out.println("Erro ao recuperar senha: " + e.getMessage());
		} finally {

		}

		return verificarSeEmailExiste;

	}

	public UsuarioVO obterUsuarioPorEmail(String emailEnviado) {
		Connection conn = Banco.getConnection();
		ResultSet rs = null;
		String sql = "Select * from usuario where email = ?";
		UsuarioVO usuario = new UsuarioVO();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailEnviado);
			rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = criarUsuarioResultSet(rs);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao obter email usuário. Erro: " + e.getMessage());
		}
		return usuario;
	}

	private UsuarioVO criarUsuarioResultSet(ResultSet rs) {
		UsuarioVO usuario = new UsuarioVO();
		try {
			usuario.setNome(rs.getString("NOME"));
			usuario.setCpf(rs.getString("CPF"));
			usuario.setTelefone(rs.getString("TELEFONE"));
			usuario.setLogin(rs.getString("LOGIN"));
			usuario.setSenha(rs.getString("SENHA"));
			usuario.setEmail(rs.getString("EMAIL"));
			usuario.setCodigoRecoveryKey(rs.getInt("RECOVERYKEY"));
			usuario.setIdUsuario(rs.getInt("IDUSUARIO"));

		} catch (SQLException e) {
			System.out.println("Erro ao criar usuário resultset. Erro: " + e.getMessage());
		}
		return usuario;
	}

	public boolean consultarRecoveyKeyUsuario(String username, Integer recoveryKey) {
		Connection conn = Banco.getConnection();
		String sql = "SELECT * FROM usuario where RECOVERYKEY = ? and LOGIN = ?";
		ResultSet rs = null;
		boolean resultadoBuscaRK = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, recoveryKey);
			stmt.setString(2, username);

			rs = stmt.executeQuery();
			resultadoBuscaRK = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao consultar rk usuario: " + e.getMessage());
		}
		return resultadoBuscaRK;
	}

	public boolean inserirRecoveryKey(int codigoRecoveryKey) {
		Connection conn = Banco.getConnection();

		return (Boolean) null;

	}

	public UsuarioVO efetuarLogin(String username, String senha) {
		Connection conn = Banco.getConnection();
		ResultSet rs = null;
		String sql = "select * from usuario where login = ? and senha = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		UsuarioVO usuario = new UsuarioVO();

		try {
			stmt.setString(1, username);
			stmt.setString(2, senha);

			rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = criarUsuarioResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao efetuar login usuário.");
			System.out.println("Error: " + e.getMessage());
		}
		return usuario;
	}

	public UsuarioVO obterUsuarioParaTrocarSenha(String usernameUsuario, int recoverykey) {
		Connection conn = Banco.getConnection();
		ResultSet rs = null;
		String sql = "select * from usuario where LOGIN = ? and RECOVERYKEY = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		UsuarioVO usuario = new UsuarioVO();

		try {
			stmt.setString(1, usernameUsuario);
			stmt.setInt(2, recoverykey);

			rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = criarUsuarioResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter usuário para recovery key.");
			System.out.println("Error: " + e.getMessage());
		}
		return usuario;
	}

}