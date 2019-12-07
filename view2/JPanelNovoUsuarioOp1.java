package view2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ControladoraDespesa;
import model.vo.Usuario;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelNovoUsuarioOp1 extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtGastoAlimentacao;
	private Usuario usuario;

	public JPanelNovoUsuarioOp1() {
		setLayout(null);
		setBounds(0, 0, 668, 440);

		try {
			MaskFormatter mascaraDinheiros = new MaskFormatter("###.###.##");

			txtGastoAlimentacao = new JFormattedTextField(mascaraDinheiros);
			txtGastoAlimentacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtGastoAlimentacao.setHorizontalAlignment(txtGastoAlimentacao.RIGHT);
			txtGastoAlimentacao.setBounds(269, 277, 142, 33);
			add(txtGastoAlimentacao);
			txtGastoAlimentacao.setColumns(10);
		} catch (Exception e) {
			System.out.println("Erro na mascara de dinheiro.");
			System.out.println("Erro: " + e.getMessage());
		}

		JButton btnSalvarDespesasTelaInicial = new JButton("Salvar");
		btnSalvarDespesasTelaInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraDespesa controller = new ControladoraDespesa();
			//	String mensagemValidacao = controller.validarDespesa(txtGastoAlimentacao);

			}
		});
		btnSalvarDespesasTelaInicial.setForeground(Color.BLACK);
		btnSalvarDespesasTelaInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalvarDespesasTelaInicial.setBounds(269, 375, 142, 23);
		add(btnSalvarDespesasTelaInicial);

		JLabel lbInformarPossibilidadeDeAlterar = new JLabel(
				"Esse valor pode ser aproximado, você poderá modificá-lo depois.");
		lbInformarPossibilidadeDeAlterar.setBounds(183, 338, 391, 23);
		add(lbInformarPossibilidadeDeAlterar);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JPanelNovoUsuarioOp1.class.getResource("/icones/primeira.png")));
		label.setBounds(0, 0, 670, 440);
		add(label);

	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
