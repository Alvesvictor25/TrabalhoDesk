package model.vo;

public class UsuarioVO {

	private int idUsuario;
	private String nome;
	private String cpf;
	private String telefone;
	private String login;
	private String senha;
	private String email;
	private int codigoRecoveryKey;

	public UsuarioVO() {
		super();
	}

	public UsuarioVO(int idUsuario, String nome, String cpf, String telefone, String login, String senha, String email,
			int codigoRecoveryKey) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.codigoRecoveryKey = codigoRecoveryKey;
	}

	public UsuarioVO(String nome, String cpf, String telefone, String login, String senha, String email,
			int codigoRecoveryKey) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.codigoRecoveryKey = codigoRecoveryKey;
	}

	public UsuarioVO(String nome, String cpf, String telefone, String login, String senha, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCodigoRecoveryKey() {
		return codigoRecoveryKey;
	}

	public void setCodigoRecoveryKey(int codigoRecoveryKey) {
		this.codigoRecoveryKey = codigoRecoveryKey;
	}

	public void imprimir() {
		System.out.printf("%-10s  %-20s  %-15s  %-15s %-15s \n", this.getIdUsuario(), this.getNome(), this.getCpf(),
				this.getTelefone(), this.getLogin());
	}

	@Override
	public String toString() {
		return nome;
	}
}
