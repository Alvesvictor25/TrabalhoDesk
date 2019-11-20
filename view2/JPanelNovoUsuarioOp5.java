package view2;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelNovoUsuarioOp5 extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelNovoUsuarioOp5() {
		setLayout(null);

		JButton btnPular = new JButton("Pular");
		btnPular.setBounds(565, 15, 95, 20);
		add(btnPular);

		JTextField textField = new JTextField();
		textField.setBounds(297, 287, 96, 20);
		add(textField);
		textField.setColumns(10);

		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(291, 372, 114, 32);
		add(btnContinuar);

		JLabel lblNewLabel = new JLabel("Esse valor pode ser aproximado, você poderá modificá-lo depois.");
		lblNewLabel.setBounds(183, 338, 391, 23);
		add(lblNewLabel);

		JLabel lblPrimeirosPassos = new JLabel("PRIMEIROS PASSOS");
		lblPrimeirosPassos.setBounds(25, 15, 152, 23);
		add(lblPrimeirosPassos);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JPanelNovoUsuarioOp2.class.getResource("/icones/segunda.png")));
		lblNewLabel_1.setBounds(100, 100, 678, 474);
		add(lblNewLabel_1);
	}

}
