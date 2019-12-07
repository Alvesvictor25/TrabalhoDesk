package view2;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import controller.ControladoraDespesa;
import model.vo.Despesa;
import model.vo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class JPanelNovoUsuarioOp2 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private Usuario usuario;

	/**
	 * Create the panel.
	 */
	public JPanelNovoUsuarioOp2() {
		setLayout(null);
		setBounds(0, 0, 668, 440);

		JButton btnPular = new JButton("Pular");
		btnPular.setBounds(563, 15, 89, 23);
		add(btnPular);

		JButton btnSalvarDespesasTelaInicial = new JButton("Salvar");
		btnSalvarDespesasTelaInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraDespesa controller = new ControladoraDespesa();
				Double valorDespesa = Double.parseDouble(textField.getText());
				String mensagemValidacao = controller.validarDespesa(valorDespesa);

				if (mensagemValidacao.isEmpty()) {
					Despesa novaDespesa = new Despesa();
					novaDespesa.setCategoria("Alimentação");
					novaDespesa.setDescricao("Mais recente");
					novaDespesa.setValor(valorDespesa);
					controller.cadastrarDespesaController(novaDespesa);
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
		lblPrimeirosPassos.setForeground(Color.WHITE);
		lblPrimeirosPassos.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblPrimeirosPassos.setBounds(25, 15, 128, 23);
		add(lblPrimeirosPassos);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JPanelNovoUsuarioOp2.class.getResource("/icones/segunda.png")));
		lblNewLabel_1.setBounds(0, 0, 670, 440);
		add(lblNewLabel_1);

	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}