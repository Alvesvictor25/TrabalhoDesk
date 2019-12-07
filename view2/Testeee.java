package view2;

import java.util.ArrayList;
import java.util.List;

import controller.ControladoraBanco;
import model.vo.ContaBanco;

public class Testeee {

	public static void main(String[] args) {

		
		ContaBanco c1 = new ContaBanco();
		c1.setIdConta(4);
		c1.setIdUsuario(2);
		c1.setAgencia(000);
		c1.setNumeroConta(000);
		c1.setNomeDoBanco("Nao sei ");
		c1.setSaldoDaConta(2000.0);
		c1.setStatusDaConta(true);
		
		ControladoraBanco controller = new ControladoraBanco();
		
		//controller.cadastrarContaBanco(c1);
		
		
	}

}
