package model.bo;

import java.util.ArrayList;


import model.dao.DespesaDAO;
import model.vo.DespesaVO;
import model.vo.UsuarioVO;


public class DespesaBO {

	// CADASTRAR DESPESA. 
	public void cadastrarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		despesaDAO.cadastrarDespesaDAO(despesaVO);
			
	}

	// CONSULTAR DESPESA (TODAS). 
	public ArrayList<DespesaVO> consultarDespesaBO() {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<DespesaVO> listaDepesaVO = despesaDAO.consultarTodosDespesasDAO();
		if (listaDepesaVO.isEmpty()) {
			System.out.println("\nA lista de despesas está vazia.");

		}
		return listaDepesaVO;
	}
	
	// CONSULTAR DESPESA (UM).
	public DespesaVO consultarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		DespesaVO despesa = despesaDAO.consultarTodosDespesasDAO(despesaVO);
		if(despesa == null) {
			System.out.println("\nDespesa não localizada.");
		}
		return despesa;
	}


	// ATUALIZAR DESPESA. 
	public void atualizarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaDAO.verificarRegistroDespesaPorId(despesaVO.getId())) {
			int resultado = despesaDAO.atualizarDespesaDAO(despesaVO);
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
	public void excluirDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaDAO.verificarRegistroDespesaPorId(despesaVO.getId())) {
			int resultado = despesaDAO.excluirDespesaDAO(despesaVO);
			if (resultado == 1) {
				System.out.println("\nDespesa excluida com sucesso.");
			} else {
				System.out.println("\nNão foi possível excluir a despesa.");
			}

		} else {
			System.out.println("\nDespesa não existe na base de dados.");
		}
	}

	public ArrayList<DespesaVO> consultarTodasAsDespesasPorUsuario(int idusuario) {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<DespesaVO> listaDepesaVO = despesaDAO.consultarTodasAsDespesasPorUsuario(idusuario);
		return listaDepesaVO;
	}
}
