package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import model.bo.UsuarioBO;
import model.vo.Usuario;

public class ControladoraUsuario {

	public void cadastrarUsuarioController(Usuario usuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		usuarioBO.cadastrarUsuarioBO(usuario);
	}

	// CONSULTAR USUARIO (TODOS).
	public ArrayList<Usuario> consultarTodosUsuariosController() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarUsuariosBO();
	}

	// CONSULTAR USUARIO (UM).
	public Usuario consultarUsuariosController(Usuario usuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarUsuarioBO(usuario);
	}

	public ArrayList<String> consultarTodosOsCpfs() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTodosOsCpfs();
	}

	public ArrayList<String> consultarTodosOsNomes() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTodosOsNomes();
	}

	// ATUALIZAR USUARIO.
	public void atualizarUsuarioController(Usuario usuario) {
		UsuarioBO usuarioBo = new UsuarioBO();
		usuarioBo.atualizarUsuarioBO(usuario);
	}

	// EXCLUIR USUARIO
	public void excluirUsuarioController(Usuario usuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		usuarioBO.excluirUsuarioBO(usuario);
	}

	public String validarCamposCadastro(String username, String novaSenha, String confirmarSenha, String usuarioNome,
			String usuarioCpf, String usuarioTelefone, String usuarioEmail) {
		String mensagemValidacao = "";

		boolean resultadoUsername = validarUsername(username);
		boolean resultadoUsernameExistente = verificarRegistroPorUsername(username);
		boolean resultadoSenha = verificadorDeSenha(novaSenha);
		boolean resultadoConfirmarSenha = verificadorDeSenha(confirmarSenha);
		boolean resultadoNome = validarNomeUsuario(usuarioNome);
		boolean resultadoCpf = validarNumerosCPF(usuarioCpf);
		boolean resultadoTelefone = validarTelefone(usuarioTelefone);
		boolean resultadoEmail = validarEmail(usuarioEmail);
		boolean resultaEmailExistente = verificarRegistroPorEmail(usuarioEmail);
		boolean resultadoCpfExistente = validarSeCpfExiste(usuarioCpf);

		if (!resultadoUsername) {
			mensagemValidacao += "Username inválido.\n";
		}
		if (resultadoUsernameExistente) {
			mensagemValidacao += "Username já cadastrado.\n";
		}
		if (!resultadoSenha || !resultadoConfirmarSenha) {
			mensagemValidacao += "Senha inválida.\n";
		}
		if (!novaSenha.equals(confirmarSenha)) {
			mensagemValidacao += "Senhas não coincidem.\n";
		}
		if (!resultadoNome) {
			mensagemValidacao += "Nome do usuário inválido.\n";
		}
		if (!resultadoCpf) {
			mensagemValidacao += "CPF inválido.\n";
		}
		if (resultadoCpfExistente) {
			mensagemValidacao += "CPF já cadastrado.\n";
		}
		if (!resultadoTelefone) {
			mensagemValidacao += "Telefone inválido.\n";
		}

		if (!resultadoEmail) {
			mensagemValidacao += "Email inválido.\n";
		}
		if(resultaEmailExistente) {
			mensagemValidacao += "Email já cadastrado.\n";
		}
		return mensagemValidacao;
	}

	public boolean verificarRegistroPorUsername(String username) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.verificarRegistroPorUsername(username);
	}

	private boolean validarSeCpfExiste(String usuarioCpf) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.verificarRegistroPorCpf(usuarioCpf);
	}

	public boolean validarUsername(String username) {
		String regex = "[a-zA-Z0-9_\\.]{5,15}$";
		if (username != null) {
			boolean verificarUsername = validarCamposRegex(regex, username);
			return verificarUsername;
		}
		return false;
	}

	public boolean validarNomeUsuario(String usuarioNome) {
		String regex = "[\\w]{3,10}\\s[\\w]{3,10}$\\s?";
		boolean verificarNomeUsuario = validarCamposRegex(regex, usuarioNome);
		return verificarNomeUsuario;
	}

	public boolean validarCpf(String usuarioCpf) {
		String regex = "\\d{3}.\\d{3}.\\d{3}-\\d{2}";
		boolean verificarCpf = validarCamposRegex(regex, usuarioCpf);
		return verificarCpf;
	}

	public boolean validarTelefone(String usuarioTelefone) {
		String regex = "(\\(\\d{2}\\)\\s?)?\\d{5}-\\d{4}";
		boolean verificarTelefone = validarCamposRegex(regex, usuarioTelefone);
		return verificarTelefone;
	}

	public String recuperarSenha(String emailEnviado) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.recuperarSenha(emailEnviado);
	}

	public String verificarEmail(String emailVerificar) {
		String mensagemValidacao = "";
		boolean verificarEmail = validarEmail(emailVerificar);
		if (!verificarEmail) {
			mensagemValidacao += "Email inválido.";
		}

		return mensagemValidacao;
	}

	public String verificarRecoveryKey(String username, Integer recoveryKey) {
		UsuarioBO usuarioBO = new UsuarioBO();
		String mensagemValidacao = "";

		if (recoveryKey == null || recoveryKey < 3) {
			mensagemValidacao += "Código inválido.";
		} else {
			boolean verificarRecoveryKey = usuarioBO.consultarRecoveryKey(username, recoveryKey);
			if (!verificarRecoveryKey) {
				mensagemValidacao = "Código recoverykey inválido.";
			}
		}

		return mensagemValidacao;

	}

	public String consultarRecoveryKey(String recoveryKey) {
		String mensagemValidacao = "";
		String regex = "(^|$)[a-z]+(^|$)[0-9]";

		boolean verificarEmail = validarCamposRegex(regex, recoveryKey);
		if (!verificarEmail) {
			recoveryKey += "RecoveryKey inválido.";
		}

		return mensagemValidacao;

	}

	public String validarSenha(String novaSenha, String confirmarSenha) {
		String mensagemValidacao = "";
		boolean senha = verificadorDeSenha(novaSenha);
		boolean confirmar = verificadorDeSenha(confirmarSenha);

		if (!senha) {
			mensagemValidacao += "Senha precisa ter mais do que 6 caracteres\n" + "Letras maiúsculas e minúsculas\n"
					+ "Números\n" + "Caracteres especiais.\n";
		}
		if (!confirmar) {
			mensagemValidacao += "Confirmar senha precisa ter mais do que 6 caracteres\n"
					+ "Letras maiúsculas e minúsculas\n" + "Números\n" + "Caracteres especiais.\n";
		}
		if (!novaSenha.equals(confirmarSenha)) {
			mensagemValidacao += "Senhas não coincidem";
		}
		return mensagemValidacao;
	}

	public boolean validarEmail(String emailVerificar) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		boolean verificarEmail = validarCamposRegex(regex, emailVerificar);
		return verificarEmail;
	}

	public boolean verificadorDeSenha(String senha) {
		if (senha.length() < 6)
			return false;

		boolean achouNumero = false;
		boolean achouMaiuscula = false;
		boolean achouMinuscula = false;
		boolean achouSimbolo = false;
		for (char c : senha.toCharArray()) {
			if (c >= '0' && c <= '9') {
				achouNumero = true;
			} else if (c >= 'A' && c <= 'Z') {
				achouMaiuscula = true;
			} else if (c >= 'a' && c <= 'z') {
				achouMinuscula = true;
			} else {
				achouSimbolo = true;
			}
		}
		return achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo;
	}

	private static boolean validarCamposRegex(String regex, String stringTeste) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(stringTeste);
		boolean resultadoVerificacao = matcher.matches();

		return resultadoVerificacao;
	}

	public String validarLogin(String username, String senha) {
		String mensagemValidacao = "";
		boolean resultadoUsername = validarUsername(username);
		boolean resultadoSenha = verificadorDeSenha(senha);

		if (!resultadoUsername) {
			mensagemValidacao += "Username inválido.\n";
		}
		if (!resultadoSenha) {
			mensagemValidacao += "Senha inválida.\n";
		}

		return mensagemValidacao;
	}

	public Usuario efetuarLogin(String username, String senha) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.efetuarLogin(username, senha);
	}

	public Usuario obterUsuarioParaTrocarSenha(String usernameUsuario, int recoverykey) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.obterUsuarioParaTrocarSenha(usernameUsuario, recoverykey);
	}

	public boolean validarNumerosCPF(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
		if (erros.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verificarRegistroPorCpf(String cpf) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.verificarRegistroPorCpf(cpf);
	}

	public boolean verificarRegistroPorEmail(String email) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.verificarRegistroPorEmail(email);
	}
}
