package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.bo.ReceitaBO;
import model.seletor.ReceitaSeletor;
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

	public String validarCamposCadastrarReceita(int idUsuarioReceita, String descricaoReceita, String categoriaReceita,
			Double valorReceita, LocalDate dataReceita) {
		String mensagemValidacao = "";
		return mensagemValidacao;
	}

}
