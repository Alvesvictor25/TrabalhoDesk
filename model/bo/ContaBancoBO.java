package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ContaBancoDAO;
import model.vo.ContaBanco;

public class ContaBancoBO {

	public void cadastrarContaBanco(ContaBanco conta) {
		ContaBancoDAO contaDAO = new ContaBancoDAO();
		contaDAO.cadastrarContaBanco(conta);
	}

	public ArrayList<ContaBanco> consultarContasPorUsuario(int idUsuario) {
		ContaBancoDAO contaDAO = new ContaBancoDAO();
		return contaDAO.consultarContasPorUsuario(idUsuario);
	}

	public ArrayList<String> consultarNomeDosBancos(int idUsuario) {
		ContaBancoDAO contaDAO = new ContaBancoDAO();
		return contaDAO.consultarNomeDosBancos(idUsuario);
	}

	public boolean atualizarContasBanco(ContaBanco contas) {
		ContaBancoDAO contaDAO = new ContaBancoDAO();
		return contaDAO.atualizarContasBanco(contas);
	}

	public List<ContaBanco> consultarStatusContaBanco(int idUsuario, boolean statusConta) {
		ContaBancoDAO contaDAO = new ContaBancoDAO();
		return contaDAO.consultarStatusContaBanco(idUsuario, statusConta);
	}

}
