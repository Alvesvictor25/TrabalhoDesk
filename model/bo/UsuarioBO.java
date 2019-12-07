package model.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.data.category.DefaultCategoryDataset;

import controller.ControladoraUsuario;
import model.dao.UsuarioDAO;
import model.seletor.DespesaSeletor;
import model.vo.Despesa;
import model.vo.Usuario;

public class UsuarioBO {

	// CADASTRAR USUARIO.
	public void cadastrarUsuarioBO(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarRegistroPorCpf(usuario.getCpf())) {
			System.out.println("\nUsuário já está cadastrado.");
		} else {
			int resultado = usuarioDAO.cadastrarUsuarioDAO(usuario);
			if (resultado == 1) {
				System.out.println("\nUsuário cadastrado com sucesso.");

			} else {
				System.out.println("\nNão foi possivel cadastrar o usuário.");
			}
		}
	}

	// CONSULTAR USUARIO.
	public ArrayList<Usuario> consultarUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<Usuario> listaUsuariosVO = usuarioDAO.consultarTodosUsuariosDAO();
		if (listaUsuariosVO.isEmpty()) {
			System.out.println("\nA lista de Usuários está vazia.");
		}
		return listaUsuariosVO;
	}

	public Usuario consultarUsuarioBO(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario2 = usuarioDAO.consultarTodosUsuariosDAO(usuario);
		if (usuario == null) {
			System.out.println("\nUsuário não localizado.");
		}
		return usuario2;
	}

	// ATUALIZAR USUARIO.
	public void atualizarUsuarioBO(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarRegistroPorIdUsuario(usuario.getIdUsuario())) {
			boolean resultado = usuarioDAO.atualizarUsuarioDAO(usuario);
			if (resultado) {
				System.out.println("\nUsuário atualizado com sucesso.");
			} else {
				System.out.println("\nNão foi possível atualizar o usuário.");
			}
		} else {
			System.out.println("\nUsuário não existe na base de dados.");
		}
	}

	// EXCLUIR USUARIO.
	public void excluirUsuarioBO(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarRegistroPorIdUsuario(usuario.getIdUsuario())) {
			int resultado = usuarioDAO.excluirUsuarioDAO(usuario);
			if (resultado == 1) {
				System.out.println("\nUsuário excluido com sucesso.");
			} else {
				System.out.println("\nNão foi possível excluir o usuário.");
			}
		} else {
			System.out.println("\nUsuário não existe na base de dados.");
		}
	}

	public ArrayList<String> consultarTodosOsCpfs() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTodosOsCpfs();
	}

	public ArrayList<String> consultarTodosOsNomes() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTodosOsNomes();
	}

	public String recuperarSenha(String emailUsuarioDestinatario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.obterUsuarioPorEmail(emailUsuarioDestinatario);
		String msg = "";

		if (usuario != null) {
			int recoveryKey = gerarRecoveryKey(usuario);
			String mensagem = "Acesse o Dr Muquirana e informe o seguinte código: " + recoveryKey;
			String remetente = "victor.alves@aluno.sc.senac.br";
			EmailService enviarEmail = new EmailService(remetente, emailUsuarioDestinatario, mensagem);
			enviarEmail.sendEmail();
			msg = "Email enviado.";
		} else {
			msg = "Email não existe.";
		}
		return msg;
	}

	private int gerarRecoveryKey(Usuario usuario) {
		Random geradorDeRecoveryKey = new Random();
		int numeroGerado = geradorDeRecoveryKey.nextInt(10000);

		usuario.setCodigoRecoveryKey(numeroGerado);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.atualizarUsuarioDAO(usuario);

		return numeroGerado;
	}

	public boolean consultarRecoveryKey(String username, Integer recoveryKey) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		boolean consultar = usuarioDAO.consultarRecoveyKeyUsuario(username, recoveryKey);
		return consultar;

	}

	public Usuario efetuarLogin(String username, String senha) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.efetuarLogin(username, senha);
	}

	public Usuario obterUsuarioParaTrocarSenha(String usernameUsuario, int recoverykey) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.obterUsuarioParaTrocarSenha(usernameUsuario, recoverykey);
	}

	public ArrayList<String> verificarCategoriasDespesaDoUsuario(int idUsuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.verificarCategoriasDespesaDoUsuario(idUsuario);
	}

	public ArrayList<String> verificarDescricoesDespesaDoUsuario(int idUsuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.verificarDescricoesDespesaDoUsuario(idUsuario);
	}

	public DefaultCategoryDataset criarGraficoDespesa(int idUsuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.criarGraficoDespesa(idUsuario);
	}

	public boolean verificarRegistroPorCpf(String cpf) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.verificarRegistroPorCpf(cpf);
	}

	public boolean verificarRegistroPorUsername(String username) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.verificarRegistroPorUsername(username);
	}

	public boolean verificarRegistroPorEmail(String email) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.verificarRegistroPorEmail(email);
	}

}
