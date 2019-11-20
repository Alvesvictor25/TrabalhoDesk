package view2;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class JPanelNovoUsuarioOp2 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public JPanelNovoUsuarioOp2() {
		setLayout(null);
		
		JButton btnPular = new JButton("Pular");
		btnPular.setBounds(565, 15, 95, 20);
		add(btnPular);
		
		textField = new JTextField();
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
		lblNewLabel_1.setBounds(0, 0, 670, 447);
		add(lblNewLabel_1);

	}
}
