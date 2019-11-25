package view2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import model.vo.Usuario;

public class FrmTelaInicial extends JFrame {

	private JPanel contentPane;
	private static Usuario usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTelaInicial frame = new FrmTelaInicial(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param usuario2 
	 */
	public FrmTelaInicial(Usuario usuarioLogin) {
		usuario = usuarioLogin;
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
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		setTitle("Dr.Muquirana");

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane jTabbedPane1 = new JTabbedPane();
		jTabbedPane1.setForeground(Color.BLACK);
		jTabbedPane1.setBounds(100, 100, 900, 650);
		jTabbedPane1.setOpaque(true);
		contentPane.add(jTabbedPane1);

		JPprincipal jpInicial = new JPprincipal(usuario);
		jTabbedPane1.addTab("Principal", jpInicial);
		jpInicial.setLayout(null);
		jpInicial.setBounds(100, 100, 900, 650);

		JPreceita jpReceita = new JPreceita(usuario);
		jTabbedPane1.addTab("Receita", jpReceita);
		jpReceita.setLayout(null);
		jpReceita.setBounds(100, 100, 900, 650);

		JPdespesa painelDespesa = new JPdespesa(usuario);
		jTabbedPane1.addTab("Despesa", painelDespesa);
		painelDespesa.setLayout(null);
		painelDespesa.setBounds(100, 100, 900, 650);
		
	}

}
