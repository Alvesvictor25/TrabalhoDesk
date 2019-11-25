package model.bo;

import java.util.ArrayList;

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

}
