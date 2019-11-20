package model.vo;

public abstract class  LancamentoVO {

	private int id;
	private int idusuario;
	private String descricao;
	private double valor;
	
	public LancamentoVO(int id, int idusuario, String descricao, double valor) {
		super();
		this.id = id;
		this.idusuario = idusuario;
		this.descricao = descricao;
		this.valor = valor;
	}

	public LancamentoVO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
	
}
