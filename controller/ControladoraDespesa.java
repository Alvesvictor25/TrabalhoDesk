package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import org.jfree.data.category.DefaultCategoryDataset;

import model.bo.DespesaBO;
import model.bo.UsuarioBO;
import model.seletor.DespesaSeletor;
import model.vo.Despesa;

public class ControladoraDespesa {

	// CADASTRAR DESPESA.
	public void cadastrarDespesaController(Despesa despesa) {
		DespesaBO despesaBO = new DespesaBO();
		despesaBO.cadastrarDespesaBO(despesa);
	}

	// CONSULTAR DESPESA (TODOS).
	public ArrayList<Despesa> consultarTodasDespesasController() {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarDespesaBO();
	}

	// CONSULTAR DESPESA (UM).
	public Despesa consultarDespesaController(Despesa despesa) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarDespesaBO(despesa);
	}

	// ATUALIZAR DESPESA.
	public void atualizarDespesaController(Despesa despesa) {
		DespesaBO despesaBO = new DespesaBO();
		despesaBO.atualizarDespesaBO(despesa);
	}

	// EXCLUIR DESPESA.
	public boolean excluirDespesaController(Despesa despesa) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.excluirDespesaBO(despesa);
	}

	public ArrayList<Despesa> consultarDespesa(DespesaSeletor seletor) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Despesa> consultarTodasAsDespesasPorUsuario(int idusuario) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarTodasAsDespesasPorUsuario(idusuario);
	}

	public String validarDespesa(String descricaoDespesa, Double valorDespesa, LocalDate dataPagamentoDespesa,
			LocalDate dataVencimentoDespesa, String categoriaDespesa) {
		String msg = "";

		return msg;
	}

	private boolean validarCategoria(String categoriaDespesa) {

		return false;
	}


	private boolean validarValor(Double valorDespesa) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean validarDescricao(String descricaoDespesa) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean validarCamposRegex(String regex, String teste) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(teste);
		boolean resultadoVerificacao = matcher.matches();

		return resultadoVerificacao;
	}

	public ArrayList<String> verificarCategoriasDespesaDoUsuario(int idUsuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.verificarCategoriasDespesaDoUsuario(idUsuario);
	}

	public ArrayList<String> verificarDescricoesDespesaDoUsuario(int idUsuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.verificarDescricoesDespesaDoUsuario(idUsuario);
	}

	public DefaultCategoryDataset criarGraficoDespesa(int idUsuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.criarGraficoDespesa(idUsuario);
	}

	public List<Despesa> consultarDespesaComFiltro(String categoriaDespesa,
			String descricaoDespesa, Date consultaDateInicio, Date consultaDateFim) {
		
		DespesaSeletor seletor = criarSeletor(categoriaDespesa, descricaoDespesa, consultaDateInicio, consultaDateFim);
		
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarDespesaComFiltro(seletor);
	}

	public DespesaSeletor criarSeletor(String categoriaDespesa,
			String descricaoDespesa, Date consultaDateInicio, Date consultaDateFim) {
		DespesaSeletor seletor = new DespesaSeletor();

		if (categoriaDespesa != null) {
			seletor.setCategoriaDespesa(categoriaDespesa);
		}
		if (descricaoDespesa != null) {
			seletor.setDescricaoDespesa(descricaoDespesa);
		}
		if (consultaDateInicio != null) {
			seletor.setConsultaDataInicio(consultaDateInicio.toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
		}
		if (consultaDateFim != null) {
			seletor.setConsultaDataFim(consultaDateFim.toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
		}

		return seletor;
	}

	public String validarDespesa(Double txtGastoAlimentacao) {
		String mensagemValidacao = "";
		
		return mensagemValidacao;
	}

}
