package view2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import model.vo.Usuario;

import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrmNovoUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static final int JPANEL_NUMERO_1 = 1;
	protected static final int JPANEL_NUMERO_2 = 2;
	protected static final int JPANEL_NUMERO_3 = 3;
	protected static final int JPANEL_NUMERO_4 = 4;
	protected static final int JPANEL_NUMERO_5 = 5;

	private JPanel contentPane;
	private CardLayout cd;
	private JPanel pPrincipal;
	private JPanelNovoUsuarioOp1 pOp1;
	private JPanelNovoUsuarioOp2 pOp2;
	private JPanel pOp3;
	private JPanel pOp5;
	private JPanel pOp4;
	private JButton btnNewButton_2;
	private int jpanelAtual = 1;
	private Usuario novoUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmNovoUsuario frame = new FrmNovoUsuario(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param usuario 
	 */
	public FrmNovoUsuario(Usuario usuario) {
		novoUsuario = usuario;
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 683, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pPrincipal = new JPanel();
		pPrincipal.setBounds(0, 0, 672, 442);
		contentPane.add(pPrincipal);
		pPrincipal.setLayout(new CardLayout(0, 0));

		cd = (CardLayout) pPrincipal.getLayout();
		cd.show(pPrincipal, "p2");

		pOp1 = new JPanelNovoUsuarioOp1();
		pOp1.setUsuario(usuario);
		pPrincipal.add(pOp1, "painel_1");

		pOp2 = new JPanelNovoUsuarioOp2();
		pOp2.setUsuario(usuario);
		pPrincipal.add(pOp2, "painel_2");

//		pOp3 = new JPanelNovoUsuarioOp3(novoUsuario);
//		pPrincipal.add(pOp3, "painel_3");
//
//		pOp4 = new JPanelNovoUsuarioOp4(novoUsuario);
//		pPrincipal.add(pOp4, "painel_4");
//
//		pOp5 = new JPanelNovoUsuarioOp5(novoUsuario);
//		pPrincipal.add(pOp5, "painel_5");

		btnNewButton_2 = new JButton("Avançar >");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanelAtual++;

				switch (jpanelAtual) {
				case JPANEL_NUMERO_1:
					cd.show(pPrincipal, "painel_1");
					repaint();
					revalidate();
					break;
				case JPANEL_NUMERO_2:
					cd.show(pPrincipal, "painel_2");
					repaint();
					revalidate();
					break;
				case JPANEL_NUMERO_3:
					cd.show(pPrincipal, "painel_3");
					repaint();
					revalidate();
					break;
				case JPANEL_NUMERO_4:
					cd.show(pPrincipal, "painel_4");
					repaint();
					revalidate();
					break;
				case JPANEL_NUMERO_5:
					cd.show(pPrincipal, "painel_5");
					repaint();
					revalidate();
					break;
				}
			}
		});
		btnNewButton_2.setBounds(0, 444, 672, 34);
		contentPane.add(btnNewButton_2);
	}

}
