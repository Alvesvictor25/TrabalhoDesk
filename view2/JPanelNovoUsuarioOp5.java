package view2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelNovoUsuarioOp5 extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	public JPanelNovoUsuarioOp5() {
		setLayout(null);
		setBounds(0, 0, 672, 442);

		JButton btnPular = new JButton("Pular");
		btnPular.setBounds(565, 15, 95, 20);
		add(btnPular);

		JButton btnSalvarDespesasTelaInicial = new JButton("Salvar");
		btnSalvarDespesasTelaInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel lbInformarPossibilidadeDeAlterar = new JLabel("Esse valor pode ser aproximado, você poderá modificá-lo depois.");
		lbInformarPossibilidadeDeAlterar.setBounds(183, 338, 391, 23);
		add(lbInformarPossibilidadeDeAlterar);

		JLabel lblPrimeirosPassos = new JLabel("PRIMEIROS PASSOS");
		lblPrimeirosPassos.setBounds(25, 15, 152, 23);
		add(lblPrimeirosPassos);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JPanelNovoUsuarioOp5.class.getResource("/icones/quinta.png")));
		lblNewLabel_1.setBounds(0, 0, 670, 440);
		add(lblNewLabel_1);
	}

}
