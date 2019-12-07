package model.seletor;

import java.time.LocalDate;


public class DespesaSeletor {

	private String descricaoDespesa;
	private String categoriaDespesa;
	private String nomeDoBanco;
	private LocalDate consultaDataInicio;
	private LocalDate consultaDataFim;

	public DespesaSeletor() {
		super();
	}

	public boolean temFiltro() {
		boolean temFiltro = false;

		if ((this.categoriaDespesa != null) && (this.categoriaDespesa.trim().length() > 0)) {
			temFiltro = true;
		}

		if ((this.descricaoDespesa != null) && (this.descricaoDespesa.trim().length() > 0)) {
			temFiltro = true;
		}

		if ((this.nomeDoBanco != null) && (this.nomeDoBanco.trim().length() > 0)) {
			temFiltro = true;
		}

		if (this.consultaDataFim != null) {
			temFiltro = true;
		}

		if (this.consultaDataInicio != null) {
			temFiltro = true;
		}
		return temFiltro;
	}

	public String getNomeDoBanco() {
		return nomeDoBanco;
	}

	public void setNomeDoBanco(String nomeDoBanco) {
		this.nomeDoBanco = nomeDoBanco;
	}

	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	public String getCategoriaDespesa() {
		return categoriaDespesa;
	}

	public void setCategoriaDespesa(String categoriaDespesa) {
		this.categoriaDespesa = categoriaDespesa;
	}

	public LocalDate getConsultaDataInicio() {
		return consultaDataInicio;
	}

	public void setConsultaDataInicio(LocalDate consultaDataInicio) {
		this.consultaDataInicio = consultaDataInicio;
	}

	public LocalDate getConsultaDataFim() {
		return consultaDataFim;
	}

	public void setConsultaDataFim(LocalDate consultaDataFim) {
		this.consultaDataFim = consultaDataFim;
	}
}