package view2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class TelaOpcoes extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOpcoes frame = new TelaOpcoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaOpcoes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnContinuar = new JButton("");
		btnContinuar.setIcon(new ImageIcon(TelaOpcoes.class.getResource("/icon/right-arrow (1).png")));
		btnContinuar.setBounds(438, 142, 59, 61);
		contentPane.add(btnContinuar);
		
		JLabel lblEsse = new JLabel("Esse valor pode ser aproximado, você poderá modificá-lo depois.");
		lblEsse.setBounds(71, 237, 387, 25);
		contentPane.add(lblEsse);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaOpcoes.class.getResource("/icon/icons8-hambúrguer-100 (1).png")));
		lblNewLabel.setBounds(199, 95, 88, 61);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantoFoiSeu = new JLabel("Quanto foi seu último gasto com alimentação?");
		lblQuantoFoiSeu.setBounds(114, 161, 278, 30);
		contentPane.add(lblQuantoFoiSeu);
		
		JLabel label = new JLabel("1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(52, 46, 18, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(144, 46, 18, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(248, 46, 18, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(346, 46, 18, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(446, 46, 18, 14);
		contentPane.add(label_4);
		
		JButton btnPular = new JButton("Pular");
		btnPular.setBounds(416, 11, 89, 23);
		contentPane.add(btnPular);
		
		JLabel lblPrimeirosPassos = new JLabel("Primeiros Passos");
		lblPrimeirosPassos.setBounds(10, 10, 116, 25);
		contentPane.add(lblPrimeirosPassos);
		
		textField = new JTextField();
		textField.setBounds(204, 185, 83, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaOpcoes.class.getResource("/icon/Primeiro.png")));
		lblNewLabel_1.setBounds(0, 0, 513, 335);
		contentPane.add(lblNewLabel_1);
	}
}
