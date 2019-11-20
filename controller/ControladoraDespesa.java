package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.bo.DespesaBO;
import model.seletor.DespesaSeletor;
import model.vo.DespesaVO;

public class ControladoraDespesa {

	// CADASTRAR DESPESA. 
	public void cadastrarDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		despesaBO.cadastrarDespesaBO(despesaVO);
	}

	// CONSULTAR DESPESA (TODOS).
	public ArrayList<DespesaVO> consultarTodasDespesasController() {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarDespesaBO();
	}

	// CONSULTAR DESPESA (UM). 
	public DespesaVO consultarDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarDespesaBO(despesaVO);
	}

	// ATUALIZAR DESPESA. 
	public void atualizarDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		despesaBO.atualizarDespesaBO(despesaVO);
	}

	// EXCLUIR DESPESA.
	public void excluirDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		despesaBO.excluirDespesaBO(despesaVO);
	}

	public ArrayList<DespesaVO> consultarDespesa(DespesaSeletor seletor) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<DespesaVO> consultarTodasAsDespesasPorUsuario(int idusuario) {
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

	private boolean validarData(String dataPagamentoDespesa) {
		String regex = "(0[1-9]|[12][0-9]|3[01])[-  /.](0[1-9]|[0-9]|1[012])[-  /.]((19|20)\\d\\d)";
		String data = dataPagamentoDespesa;
		boolean verificarData = validarCamposRegex(regex, dataPagamentoDespesa);
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

	
}
