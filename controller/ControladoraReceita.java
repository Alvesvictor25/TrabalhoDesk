package controller;

import java.util.ArrayList;

import model.bo.ReceitaBO;
import model.seletor.ReceitaSeletor;
import model.vo.ReceitaVO;

public class ControladoraReceita {

	public void cadastrarReceitaController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();
		receitaBO.cadastrarReceitaBO(receitaVO);
	}

	public ArrayList<ReceitaVO> consultarTodasReceitasController() {
		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.consultarReceitasBO();
	}
 
	public ReceitaVO consultarReceitaController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();
		return receitaBO.consultarReceitaBO(receitaVO);
	}

	public void atualizarReceitaController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();
		receitaBO.atualizarReceitaBO(receitaVO);
	}

	public void excluirReceitaController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();
		receitaBO.excluirReceitaBO(receitaVO);
	}

	public ArrayList<ReceitaVO> consultarReceitas(ReceitaSeletor seletor) {
		ReceitaBO receitaBO = new ReceitaBO();
		return null;
		
	}

}
