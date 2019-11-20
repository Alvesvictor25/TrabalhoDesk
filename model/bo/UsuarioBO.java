package model.bo;

import java.util.ArrayList;
import java.util.Random;

import controller.ControladoraUsuario;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	// CADASTRAR USUARIO.
	public void cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarRegistroPorCpf(usuarioVO.getCpf())) {
			System.out.println("\nUsuário já está cadastrado.");
		} else {
			int resultado = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
			if (resultado == 1) {
				System.out.println("\nUsuário cadastrado com sucesso.");

			} else {
				System.out.println("\nNão foi possivel cadastrar o usuário.");
			}
		}
	}

	// CONSULTAR USUARIO.
	public ArrayList<UsuarioVO> consultarUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioVO> listaUsuariosVO = usuarioDAO.consultarTodosUsuariosDAO();
		if (listaUsuariosVO.isEmpty()) {
			System.out.println("\nA lista de Usuários está vazia.");
		}
		return listaUsuariosVO;
	}

	public UsuarioVO consultarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioVO usuario = usuarioDAO.consultarTodosUsuariosDAO(usuarioVO);
		if (usuario == null) {
			System.out.println("\nUsuário não localizado.");
		}
		return usuario;
	}

	// ATUALIZAR USUARIO.
	public void atualizarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarRegistroPorIdUsuario(usuarioVO.getIdUsuario())) {
			boolean resultado = usuarioDAO.atualizarUsuarioDAO(usuarioVO);
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
	public void excluirUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarRegistroPorIdUsuario(usuarioVO.getIdUsuario())) {
			int resultado = usuarioDAO.excluirUsuarioDAO(usuarioVO);
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
		UsuarioVO usuario = usuarioDAO.obterUsuarioPorEmail(emailUsuarioDestinatario);
		String msg = "";

		if (usuario != null) {
			int recoveryKey = gerarRecoveryKey(usuario);
			String mensagem = "Acesse o Dr Muquirana e informe o seguinte código: " + recoveryKey;
			String remetente = "alvesvictor-@hotmail.com";
			EmailService enviarEmail = new EmailService(remetente, emailUsuarioDestinatario, mensagem);
			enviarEmail.sendEmail();
			msg = "Email enviado.";
		} else {
			msg = "Email não existe.";
		}
		return msg;
	}

	private int gerarRecoveryKey(UsuarioVO usuario) {
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

	public UsuarioVO efetuarLogin(String username, String senha) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.efetuarLogin(username, senha);
	}

	public UsuarioVO obterUsuarioParaTrocarSenha(String usernameUsuario, int recoverykey) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.obterUsuarioParaTrocarSenha(usernameUsuario, recoverykey);
	}

}
