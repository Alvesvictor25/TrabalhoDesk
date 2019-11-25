package model.bo;

import java.util.ArrayList;
import model.dao.ReceitaDAO;
import model.vo.Receita;


public class ReceitaBO {

	public boolean cadastrarReceitaBO(Receita receita) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		return receitaDAO.cadastrarReceitaDAO(receita);
	}

	public ArrayList<Receita> consultarReceitasBO() {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ArrayList<Receita> listaReceitasVO = receitaDAO.consultarTodasReceitasDAO();
		if (listaReceitasVO.isEmpty()) {
			System.out.println("\nA lista de receita está vazia.");
		}
		return listaReceitasVO;
	}

	public Receita consultarReceitaBO(Receita receita) {
		ReceitaDAO receitaDAO= new ReceitaDAO();
		Receita receitaUsuario = receitaDAO.consultarTodasReceitasDAO(receita);
		if (receitaUsuario == null) {
			System.out.println("\nReceita não localizada.");
		}
		return receitaUsuario;
	}

	public void atualizarReceitaBO(Receita receita) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if (receitaDAO.verificarRegistroPorId(receita.getId())) {
			int resultado = receitaDAO.atualizarReceitaDAO(receita);
			if (resultado == 1) {
				System.out.println("\nReceita atualizado com sucessa.");
			} else {
				System.out.println("\nNão foi possível atualizar a receita.");
			}

		} else {
			System.out.println("\nReceita não existe na base de dados.");
		}
	}

	public void excluirReceitaBO(Receita receita) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if (receitaDAO.verificarRegistroPorId(receita.getId())) {
			int resultado = receitaDAO.excluirReceitaDAO(receita);
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
