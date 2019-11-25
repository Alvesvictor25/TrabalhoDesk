package model.vo;

import java.time.LocalDate;

public class Receita extends LancamentoVO {

	private LocalDate dataReceita;
	private String categoria;

	public Receita(int id, int idusuario, String descricao, double valor, LocalDate dataReceita, String categoria) {
		super(id, idusuario, descricao, valor);
		this.dataReceita = dataReceita;
		this.categoria = categoria;
	}

	public Receita() {
		super();
	}

	public Receita(int idUsuarioReceita, String descricaoReceita, String categoriaReceita, Double valorReceita,
			LocalDate dataReceita2) {
		super( idUsuarioReceita, idUsuarioReceita, descricaoReceita, valorReceita);
		this.dataReceita = dataReceita2;
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

	public void imprimir() {
		System.out.printf("\n %-15s %-15s %-18s %-14s %-13s \n",
				this.getId(),
				this.setIdUsuario(),
				this.getDescricao(),
				this.getValor(),
				this.getDataReceita());
		

	}

}
