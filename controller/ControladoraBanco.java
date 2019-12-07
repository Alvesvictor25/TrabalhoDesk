package controller;

import java.util.ArrayList;
import java.util.List;

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

	public ArrayList<String> consultarNomeDosBancos(int idUsuario) {
		ContaBancoBO bancoBO = new ContaBancoBO();
		return bancoBO.consultarNomeDosBancos(idUsuario);
	}

	public String atualizarContasBanco(ContaBanco contas) {
		String msg = "";
		ContaBancoBO bancoBO = new ContaBancoBO();
		boolean resultado = bancoBO.atualizarContasBanco(contas);
			if(resultado) {
				msg += "Status conta banco atualizado.";
			} else {
				msg += "Erro ao atualizar status conta banco.";
			}
		
		return msg;
		
	}

	public List<ContaBanco> consultarStatusContaBanco(int idUsuario, boolean statusConta) {
		ContaBancoBO bancoBO = new ContaBancoBO();
		return bancoBO.consultarStatusContaBanco(idUsuario, statusConta);
	}
}
