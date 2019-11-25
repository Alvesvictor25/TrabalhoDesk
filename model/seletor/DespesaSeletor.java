package model.seletor;

import model.vo.Usuario;

public class DespesaSeletor {


	// Atributos que servirão de filtros
	private Usuario nomeUsuario;

	// Atributos para possível paginação dos resultados (intervalo)
	private int limite;
	private int pagina;

	public DespesaSeletor() {
		// Default: traz os resultados sem limite e sem página
		this.limite = 0;
		this.pagina = -1;
	}

	public DespesaSeletor(Usuario nomeSelecionado) {
	}

	/**
	 * Verifica se este seletor tem algum campo preenchido
	 *
	 * @return verdadeiro se existe algum campo de filtro preenchido
	 */
	public boolean temFiltro() {
		boolean temFiltro = false;

		if ((this.nomeUsuario != null) && (this.nomeUsuario.getIdUsuario() > 0)) {
			temFiltro = true;
		}

		return temFiltro;
	}

	/**
	 * Verifica se os campos de paginacao estao preenchidos
	 *
	 * @return verdadeiro se os campos limite e pagina estao preenchidos
	 */
	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}

	/**
	 * Calcula deslocamento (offset) a partir da pagina e do limite
	 *
	 * @return offset
	 */
	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	// Getters e setters
	public Usuario getUsuario() {
		return nomeUsuario;
	}

	public void setUsuario(Usuario usuario) {
		this.nomeUsuario = usuario;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
}


