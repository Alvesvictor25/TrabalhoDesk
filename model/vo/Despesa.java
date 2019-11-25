package model.vo;

import java.time.LocalDate;

public class Despesa extends LancamentoVO {

	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private String categoria;

	public Despesa(LocalDate dataVencimento, LocalDate dataPagamento, String categoria) {
		super();
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.categoria = categoria;

	}

	public Despesa() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Despesa(String descricaoDespesa, Double valorDespesa, LocalDate pagamentoDespesa,
			LocalDate vencimentoDespesa, String categoriaDespesa) {
		super();
		this.dataVencimento = vencimentoDespesa;
		this.dataPagamento = pagamentoDespesa;
		this.categoria = categoriaDespesa;
	}

	public Despesa(int id, String descricaoDespesa, Double valorDespesa, LocalDate pagamentoDespesa,
			LocalDate vencimentoDespesa, String categoriaDespesa) {
		super(id, id, descricaoDespesa, valorDespesa);
		this.dataVencimento = vencimentoDespesa;
		this.dataPagamento = pagamentoDespesa;
		this.categoria = categoriaDespesa;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}


	@Override
	public String toString() {
		return "Despesa [dataVencimento=" + dataVencimento + ", dataPagamento=" + dataPagamento + ", categoria="
				+ categoria + "]";
	}

}
