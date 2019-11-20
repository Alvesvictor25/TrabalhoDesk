package view2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrmNovoUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmNovoUsuario frame = new FrmNovoUsuario();
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
	public FrmNovoUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnVoltar = new JButton("Pular");
		btnVoltar.setBounds(563, 15, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane = new JPanelNovoUsuarioOp2();
				setContentPane(contentPane);
				revalidate();
				
			}
		});
		btnContinuar.setBounds(271, 372, 128, 35);
		contentPane.add(btnContinuar);
		
		textField = new JTextField("R$");
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
		});
		textField.setBounds(271, 279, 128, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTxt = new JLabel("Esse valor pode ser aproximado, você poderá modificá-lo depois.");
		lblTxt.setBounds(181, 338, 395, 23);
		contentPane.add(lblTxt);
		
		JLabel lblTxt_1 = new JLabel("PRIMEIROS PASSOS");
		lblTxt_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTxt_1.setBounds(10, 11, 149, 29);
		contentPane.add(lblTxt_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmNovoUsuario.class.getResource("/icones/primeira.png")));
		label.setBounds(0, 0, 669, 444);
		contentPane.add(label);
	}
}
