package model.vo;

public class ContaBanco {

	private Integer idUsuario;
	private String nomeDoBanco;
	private Integer idConta;
	private Integer agencia;
	private Integer numeroConta;
	private Double saldoDaConta;
	private boolean statusDaConta;

	public ContaBanco(Integer idUsuario, String nomeDoBanco, Integer idConta, Integer agencia, Integer numeroConta,
			Double saldoDaConta, boolean statusDaConta) {
		super();
		this.idUsuario = idUsuario;
		this.nomeDoBanco = nomeDoBanco;
		this.idConta = idConta;
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.saldoDaConta = saldoDaConta;
		this.statusDaConta = statusDaConta;
	}

	public ContaBanco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContaBanco(int idUsuario, String nomeConta, int numeroConta, int agenciaConta, double saldoConta,
			boolean statusDaConta) {
		this.idUsuario = idUsuario;
		this.nomeDoBanco = nomeConta;
		this.agencia = agenciaConta;
		this.numeroConta = numeroConta;
		this.saldoDaConta = saldoConta;
		this.statusDaConta = statusDaConta;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Double getSaldoDaConta() {
		return saldoDaConta;
	}

	public void setSaldoDaConta(Double saldoDaConta) {
		this.saldoDaConta = saldoDaConta;
	}

	public String getNomeDoBanco() {
		return nomeDoBanco;
	}

	public void setNomeDoBanco(String nomeDoBanco) {
		this.nomeDoBanco = nomeDoBanco;
	}

	public boolean isStatusDaConta() {
		return statusDaConta;
	}

	public void setStatusDaConta(boolean statusDaConta) {
		this.statusDaConta = statusDaConta;
	}

	@Override
	public String toString() {
		return "Banco: " + nomeDoBanco;
	}

}
