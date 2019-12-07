package model.seletor;

import java.time.LocalDate;

import model.vo.ContaBanco;

public class ReceitaSeletor {
	private String descricaoReceita;
	private ContaBanco contaBancoUsuario;
	private LocalDate consultaDataInicio;
	private LocalDate consultaDataFim;

	public ReceitaSeletor() {
	}

	public boolean temFiltro() {
		boolean temFiltro = false;

		if ((this.descricaoReceita != null) && (this.descricaoReceita.trim().length() > 0)) {
			temFiltro = true;
		}

		if ((this.contaBancoUsuario != null)) {
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

	public String getDescricaoReceita() {
		return descricaoReceita;
	}

	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
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

	public ContaBanco getContaBancoUsuario() {
		return contaBancoUsuario;
	}

	public void setContaBancoUsuario(ContaBanco contaBancoUsuario) {
		this.contaBancoUsuario = contaBancoUsuario;
	}

}
