package controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.data.category.DefaultCategoryDataset;

import model.bo.DespesaBO;
import model.bo.ReceitaBO;
import model.seletor.DespesaSeletor;
import model.seletor.ReceitaSeletor;
import model.util.PlanilhaReceitaModel;
import model.vo.ContaBanco;
import model.vo.Receita;

public class ControladoraReceita {

	public boolean cadastrarReceitaController(Receita receita) {
		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.cadastrarReceitaBO(receita);
	}

	public ArrayList<Receita> consultarTodasReceitasController() {
		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.consultarReceitasBO();
	}

	public Receita consultarReceitaController(Receita receita) {
		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.consultarReceitaBO(receita);
	}

	public void atualizarReceitaController(Receita receita) {
		ReceitaBO receitaBO = new ReceitaBO();
		receitaBO.atualizarReceitaBO(receita);
	}

	public void excluirReceitaController(Receita receita) {
		ReceitaBO receitaBO = new ReceitaBO();
		receitaBO.excluirReceitaBO(receita);
	}

	public ArrayList<Receita> consultarReceitas(ReceitaSeletor seletor) {
		ReceitaBO receitaBO = new ReceitaBO();
		return null;

	}

	public String validarCamposCadastrarReceita(int idUsuarioReceita, String descricaoReceita, String nomeBancoReceita,
			String categoriaReceita, Double valorReceita, LocalDate dataReceita) {
		String mensagemValidacao = "";

		boolean resultadoDescricao = validarDescricao(descricaoReceita);
		boolean resultadoCategoria = validarCategoria(categoriaReceita);

		if (!resultadoDescricao) {
			mensagemValidacao += "Descrição inválida.\n";
		}
		if (!resultadoCategoria) {
			mensagemValidacao += "Categoria inválida.\n";
		}
		return mensagemValidacao;
	}

	public boolean validarCategoria(String categoriaReceita) {
		String regex = "[\\w]{3,10}";
		boolean resultadoVerificacao = validarCamposRegex(categoriaReceita, regex);
		return resultadoVerificacao;
	}

	public boolean validarDescricao(String descricaoReceita) {
		String regex = "[\\w\\s]{3,10}";
		boolean resultadoVerificacao = validarCamposRegex(descricaoReceita, regex);
		return resultadoVerificacao;
	}

	public boolean validarValorReceita(String valorReceita) {
		String regex = "\\d{1,10}\\.\\d{1,2}";
		boolean resultadoVerificacao = validarCamposRegex(valorReceita, regex);
		return resultadoVerificacao;
	}

	private static boolean validarCamposRegex(String teste, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(teste);
		boolean resultadoVerificacao = matcher.matches();

		return resultadoVerificacao;

	}

	public List<Receita> consultarTodasReceitasPorIdUsuario(int idUsuario) {
		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.consultarTodasReceitasPorIdUsuario(idUsuario);
	}

	public DefaultCategoryDataset criarGraficoReceita(int idUsuario) {
		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.criarGraficoReceita(idUsuario);
	}

	public List<Receita> consultarReceitasComFiltro(ContaBanco nomeBancoReceita, String descricaoReceita,
			Date consultaDateInicio, Date consultaDateFim) {

		ReceitaSeletor seletor = criarSeletor(nomeBancoReceita, descricaoReceita, consultaDateInicio, consultaDateFim);

		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.consultarReceitasComFiltro(seletor);
	}

	public ReceitaSeletor criarSeletor(ContaBanco nomeBancoReceita, String descricaoReceita, Date consultaDateInicio,
			Date consultaDateFim) {
		ReceitaSeletor seletor = new ReceitaSeletor();

		if (nomeBancoReceita != null) {
			seletor.setContaBancoUsuario(nomeBancoReceita);
		}

		if (descricaoReceita != null) {
			seletor.setDescricaoReceita(descricaoReceita);
		}
		if (consultaDateInicio != null) {
			seletor.setConsultaDataInicio(consultaDateInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
		if (consultaDateFim != null) {
			seletor.setConsultaDataFim(consultaDateFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}

		return seletor;
	}

	public List<String> verificarDescricoesReceitasDoUsuario(int idUsuario) {
		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.verificarDescricoesReceitasDoUsuario(idUsuario);
	}

	public String gerarPlanilhaReceita(List<Receita> consultarComFiltro, String caminhoEscolhido) {
		PlanilhaReceitaModel novaPlanilha = new PlanilhaReceitaModel();
		return novaPlanilha.gerarPlanilha(consultarComFiltro, caminhoEscolhido);
	}

	public String validarReceita(Double valorDespesa) {
		String mensagemValidacao = "";
		return mensagemValidacao;
	}

}
