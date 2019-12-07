package view2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.ControladoraUsuario;
import model.vo.Usuario;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private JPasswordField passwordField;
	protected String login;
	protected char[] password;
	private Usuario usuario;
	private JLabel lblErroLoginUsuario;

	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmLogin() {
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
	
		setDefaultCloseOperation(FrmLogin.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 936, 607);
		getContentPane().setLayout(null);

		txtLogin = new JTextField("Username");
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtLogin.setText("alvesvictor");
				lblErroLoginUsuario.setVisible(false);
			}
		});

		txtLogin.setBounds(140, 153, 175, 29);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JButton btnCadastrar = new JButton("Criar conta");
		btnCadastrar.setFont(new Font("Tahoma", Font.ITALIC, 10));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCadastrarUsuario usuario = new FrmCadastrarUsuario();
				usuario.setVisible(true);
			}
		});
		btnCadastrar.setBounds(167, 334, 100, 23);
		getContentPane().add(btnCadastrar);

		JButton btnEsqueceuSenha = new JButton("Esqueceu senha?");
		btnEsqueceuSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRecuperarConta senha = new FrmRecuperarConta();
				senha.setVisible(true);
			}
		});
		btnEsqueceuSenha.setFont(new Font("Tahoma", Font.ITALIC, 10));
		btnEsqueceuSenha.setBounds(153, 368, 123, 23);
		getContentPane().add(btnEsqueceuSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				ControladoraUsuario controllerUsuario = new ControladoraUsuario();
				String mensagemValidacao = controllerUsuario.validarLogin(txtLogin.getText(), passwordField.getText());

				if (mensagemValidacao.isEmpty()) {
					usuario = controllerUsuario.efetuarLogin(txtLogin.getText(), passwordField.getText());
					if(usuario == null) {
						lblErroLoginUsuario.setVisible(true);
					} else {
						passarUsuarioParaTelaInicial(usuario);
					}
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
					lblErroLoginUsuario.setVisible(true);
				}
			}
		});
		btnEntrar.setBounds(126, 259, 182, 29);
		getContentPane().add(btnEntrar);

		passwordField = new JPasswordField("@Mem2020");
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setText("@Mem2020");
			}
		});
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(140, 204, 175, 29);
		getContentPane().add(passwordField);

		lblErroLoginUsuario = new JLabel("username ou senha inválido");
		lblErroLoginUsuario.setForeground(Color.RED);
		lblErroLoginUsuario.setBounds(140, 234, 175, 14);
		getContentPane().add(lblErroLoginUsuario);
		lblErroLoginUsuario.setVisible(false);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/icones/4.png")));
		lblNewLabel.setBounds(0, 0, 927, 566);
		getContentPane().add(lblNewLabel);

	}

	protected void passarUsuarioParaTelaInicial(Usuario usuario) {
		FrmTelaInicial telaInicial = new FrmTelaInicial(usuario);
		FrmNovoUsuario novoUsuario = new FrmNovoUsuario(usuario);
		telaInicial.setVisible(true);
		
	}
}
