package model.bo;

import java.util.ArrayList;


import model.dao.DespesaDAO;
import model.vo.Despesa;
import model.vo.Usuario;


public class DespesaBO {

	// CADASTRAR DESPESA. 
	public void cadastrarDespesaBO(Despesa despesa) {
		DespesaDAO despesaDAO = new DespesaDAO();
		despesaDAO.cadastrarDespesaDAO(despesa);
			
	}

	// CONSULTAR DESPESA (TODAS). 
	public ArrayList<Despesa> consultarDespesaBO() {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<Despesa> listaDepesaVO = despesaDAO.consultarTodosDespesasDAO();
		if (listaDepesaVO.isEmpty()) {
			System.out.println("\nA lista de despesas está vazia.");

		}
		return listaDepesaVO;
	}
	
	// CONSULTAR DESPESA (UM).
	public Despesa consultarDespesaBO(Despesa despesa) {
		DespesaDAO despesaDAO = new DespesaDAO();
		Despesa despesaConsultar = despesaDAO.consultarTodosDespesasDAO(despesa);
		if(despesa == null) {
			System.out.println("\nDespesa não localizada.");
		}
		return despesaConsultar;
	}


	// ATUALIZAR DESPESA. 
	public void atualizarDespesaBO(Despesa despesa) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaDAO.verificarRegistroDespesaPorId(despesa.getId())) {
			int resultado = despesaDAO.atualizarDespesaDAO(despesa);
			if (resultado == 1) {
				System.out.println("\nDespesa atualizada com sucesso.");
			} else {
				System.out.println("\nNão foi possível atualizar a despesa.");
			}

		} else {
			System.out.println("\nDespesa não existe na base de dados.");
		}

	}

	// EXCLUIR DESPESA. 
	public void excluirDespesaBO(Despesa despesa) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaDAO.verificarRegistroDespesaPorId(despesa.getId())) {
			int resultado = despesaDAO.excluirDespesaDAO(despesa);
			if (resultado == 1) {
				System.out.println("\nDespesa excluida com sucesso.");
			} else {
				System.out.println("\nNão foi possível excluir a despesa.");
			}

		} else {
			System.out.println("\nDespesa não existe na base de dados.");
		}
	}

	public ArrayList<Despesa> consultarTodasAsDespesasPorUsuario(int idusuario) {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<Despesa> listaDepesaVO = despesaDAO.consultarTodasAsDespesasPorUsuario(idusuario);
		return listaDepesaVO;
	}
}
