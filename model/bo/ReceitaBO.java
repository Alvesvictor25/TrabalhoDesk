package model.bo;

import java.util.ArrayList;
import model.dao.ReceitaDAO;
import model.vo.ReceitaVO;


public class ReceitaBO {

	// CADASTRAR RECEITA.
	public void cadastrarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if (receitaDAO.verificarRegistroPorId(receitaVO.getId())) {
		System.out.println("\nReceita já está cadastrado.");

		} else {
			int resultado = receitaDAO.cadastrarReceitaDAO(receitaVO);
			if (resultado == 1) {
				System.out.println("\nReceita cadastrada com sucesso.");

			} else {
				System.out.println("\nNão foi possivel cadastrar a receita.");
			}
		}
	}

	// CONSULTAR RECEITA (TODAS).
	public ArrayList<ReceitaVO> consultarReceitasBO() {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ArrayList<ReceitaVO> listaReceitasVO = receitaDAO.consultarTodasReceitasDAO();
		if (listaReceitasVO.isEmpty()) {
			System.out.println("\nA lista de receita está vazia.");
		}
		return listaReceitasVO;
	}

	// CADASTRAR RECEITA (UM).
	public ReceitaVO consultarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO= new ReceitaDAO();
		ReceitaVO receita = receitaDAO.consultarTodasReceitasDAO(receitaVO);
		if (receita == null) {
			System.out.println("\nReceita não localizada.");
		}
		return receita;
	}

	// ATUALIZAR RECEITA.
	public void atualizarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if (receitaDAO.verificarRegistroPorId(receitaVO.getId())) {
			int resultado = receitaDAO.atualizarReceitaDAO(receitaVO);
			if (resultado == 1) {
				System.out.println("\nReceita atualizado com sucessa.");
			} else {
				System.out.println("\nNão foi possível atualizar a receita.");
			}

		} else {
			System.out.println("\nReceita não existe na base de dados.");
		}
	}

	// EXCLUIR RECEITA. 
	public void excluirReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if (receitaDAO.verificarRegistroPorId(receitaVO.getId())) {
			int resultado = receitaDAO.excluirReceitaDAO(receitaVO);
			if (resultado == 1) {
				System.out.println("\nReceita excluida com sucesso.");
			} else {
				System.out.println("\nNão foi possível excluir a receita.");
			}

		} else {
			System.out.println("\nReceita não existe na base de dados.");
		}
	}
}
