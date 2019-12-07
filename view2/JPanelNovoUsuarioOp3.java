package view2;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControladoraBanco;
import controller.ControladoraReceita;
import model.vo.ContaBanco;
import model.vo.Despesa;
import model.vo.Receita;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class JPanelNovoUsuarioOp3 extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton btnSalvarDespesasTelaInicial;

	/**
	 * Create the panel.
	 */
	public JPanelNovoUsuarioOp3() {
		setLayout(null);
		setBounds(0, 0, 668, 440);

		JButton btnPular = new JButton("Pular");
		btnPular.setBounds(563, 15, 89, 23);
		add(btnPular);

		btnSalvarDespesasTelaInicial = new JButton("Salvar");
		btnSalvarDespesasTelaInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraReceita controller = new ControladoraReceita();
				ControladoraBanco controllerConta = new ControladoraBanco();
//				TODO descomentar
//				ContaBanco banco = controllerConta.consultarContaBancoPorIdUsuario();
				Double valorDespesa = Double.parseDouble(textField.getText());
				String mensagemValidacao = controller.validarReceita(valorDespesa);

				if (mensagemValidacao.isEmpty()) {
					Receita novaReceita = new Receita();
					novaReceita.setCategoria("Salário");
					novaReceita.setDescricao("Ultimo salário");
					novaReceita.setValor(valorDespesa);
					controller.cadastrarReceitaController(novaReceita);
					btnSalvarDespesasTelaInicial.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}
			}
		});
		btnSalvarDespesasTelaInicial.setForeground(Color.BLACK);
		btnSalvarDespesasTelaInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalvarDespesasTelaInicial.setBounds(269, 375, 142, 23);
		add(btnSalvarDespesasTelaInicial);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(269, 277, 142, 33);
		add(textField);
		textField.setColumns(10);

		JLabel lbInformarPossibilidadeDeAlterar = new JLabel(
				"Esse valor pode ser aproximado, você poderá modificá-lo depois.");
		lbInformarPossibilidadeDeAlterar.setBounds(183, 338, 391, 23);
		add(lbInformarPossibilidadeDeAlterar);

		JLabel lblPrimeirosPassos = new JLabel("PRIMEIROS PASSOS");
		lblPrimeirosPassos.setBounds(25, 15, 152, 23);
		add(lblPrimeirosPassos);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JPanelNovoUsuarioOp3.class.getResource("/icones/terceira.png")));
		lblNewLabel_1.setBounds(0, 0, 670, 440);
		add(lblNewLabel_1);

	}
}
