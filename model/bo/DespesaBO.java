package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.DespesaDAO;
import model.seletor.DespesaSeletor;
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
		if (despesa == null) {
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
	public boolean excluirDespesaBO(Despesa despesa) {
		DespesaDAO despesaDAO = new DespesaDAO();
		int resultado = 0;
		if (despesaDAO.verificarRegistroDespesaPorId(despesa.getId())) {
			resultado = despesaDAO.excluirDespesaDAO(despesa);
			if (resultado == 1) {
				System.out.println("\nDespesa excluida com sucesso.");
			} else {
				System.out.println("\nNão foi possível excluir a despesa.");
			}

		} else {
			System.out.println("\nDespesa não existe na base de dados.");
		}
		return resultado > 0;
	}

	public ArrayList<Despesa> consultarTodasAsDespesasPorUsuario(int idusuario) {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<Despesa> listaDepesaVO = despesaDAO.consultarTodasAsDespesasPorUsuario(idusuario);
		return listaDepesaVO;
	}

	public List<Despesa> consultarDespesaComFiltro(DespesaSeletor seletor) {
		DespesaDAO despesaDAO = new DespesaDAO();
		return despesaDAO.consultarDespesaComFiltro(seletor);
	}
}
