package model.vo;

import java.time.LocalDate;

public class Receita extends LancamentoVO {

	private LocalDate dataReceita;
	private ContaBanco contaBancoUsuario;
	private String categoria;

	public Receita(int id, int idusuario, int idContaBanco, String descricao, double valor, LocalDate dataReceita,
			String categoria, ContaBanco contaBancoUsuario) {
		super(id, idusuario, descricao, valor);
		this.contaBancoUsuario = contaBancoUsuario;
		this.dataReceita = dataReceita;
		this.categoria = categoria;
	}

	public Receita() {
		super();
	}

	public Receita(int idUsuarioReceita, int idContaBanco, String descricaoReceita, String categoriaReceita,
			LocalDate dataReceita, Double valorReceita, ContaBanco contaBancoUsuario) {
		super(idUsuarioReceita, idUsuarioReceita, descricaoReceita, valorReceita);
		this.contaBancoUsuario = contaBancoUsuario;
		this.dataReceita = dataReceita;
		this.categoria = categoriaReceita;

	}

	public LocalDate getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(LocalDate dataReceita) {
		this.dataReceita = dataReceita;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public ContaBanco getContaBancoUsuario() {
		return contaBancoUsuario;
	}

	public void setContaBancoUsuario(ContaBanco contaBancoUsuario) {
		this.contaBancoUsuario = contaBancoUsuario;
	}

}
