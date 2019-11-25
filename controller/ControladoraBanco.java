package controller;

import java.util.ArrayList;

import model.bo.ContaBancoBO;
import model.vo.ContaBanco;

public class ControladoraBanco {

	public void cadastrarContaBanco(ContaBanco conta) {
		ContaBancoBO bancoBO = new ContaBancoBO();
		bancoBO.cadastrarContaBanco(conta);

	}

	public ArrayList<ContaBanco> consultarContasPorUsuario(int idUsuario) {
		ContaBancoBO bancoBO = new ContaBancoBO();
		return bancoBO.consultarContasPorUsuario(idUsuario);
	}

	public String validarCamposContaBancoUsuario(String nomeConta, int numeroConta, int agenciaConta,
			double saldoConta) {
		String msg = "";
		return msg;
	}
}
