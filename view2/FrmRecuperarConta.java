package view2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControladoraUsuario;
import model.vo.Usuario;
import javax.swing.ImageIcon;

public class FrmRecuperarConta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtRecoveryKey;
	private JTextField txtNovaSenha;
	private JTextField txtConfirmarNovaSenha;
	private JLabel lblRecoveryKey;
	private JButton btnVerificarCdigo;
	private JButton btnAtualizar;
	private JLabel lblSenha;
	private JLabel lblEmail;
	private JButton btnEnviarEmail;
	private Usuario usuario;
	private int count = 0;
	private JTextField txtLoginRecoverykey;
	private JButton btnReenviarEmail;
	private String reenviarEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRecuperarConta frame = new FrmRecuperarConta();
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
	public FrmRecuperarConta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Recuperar senha");

		txtEmail = new JTextField();
		txtEmail.setBounds(175, 340, 187, 23);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtRecoveryKey = new JTextField();
		txtRecoveryKey.setBounds(175, 355, 187, 23);
		txtRecoveryKey.setVisible(false);
		contentPane.add(txtRecoveryKey);
		txtRecoveryKey.setColumns(10);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ControladoraUsuario controllerUsuario = new ControladoraUsuario();

				String mensagemValidacao = controllerUsuario.validarSenha(txtNovaSenha.getText(),
						txtConfirmarNovaSenha.getText());
				if (mensagemValidacao.isEmpty()) {
					usuario.setSenha(txtNovaSenha.getText());
					controllerUsuario.atualizarUsuarioController(usuario);
					JOptionPane.showMessageDialog(null, "Senha alterada!");
				
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}
			}
		});
		btnAtualizar.setBounds(203, 390, 126, 23);
		btnAtualizar.setVisible(false);
		contentPane.add(btnAtualizar);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(243, 292, 48, 14);
		contentPane.add(lblEmail);

		lblRecoveryKey = new JLabel("Recovery Key");
		lblRecoveryKey.setBounds(222, 291, 96, 17);
		lblRecoveryKey.setVisible(false);
		contentPane.add(lblRecoveryKey);

		txtNovaSenha = new JTextField("Nova senha");
		txtNovaSenha.setBounds(175, 320, 187, 23);
		txtNovaSenha.setVisible(false);
		contentPane.add(txtNovaSenha);
		txtNovaSenha.setColumns(10);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(243, 294, 48, 14);
		lblSenha.setVisible(false);
		contentPane.add(lblSenha);

		txtConfirmarNovaSenha = new JTextField("Confirmar senha");
		txtConfirmarNovaSenha.setBounds(175, 355, 191, 23);
		txtConfirmarNovaSenha.setVisible(false);
		contentPane.add(txtConfirmarNovaSenha);
		txtConfirmarNovaSenha.setColumns(10);

		btnEnviarEmail = new JButton("Enviar Email");
		btnEnviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ControladoraUsuario controllerUsuario = new ControladoraUsuario();
				String mensagemValidacao = controllerUsuario.verificarEmail(txtEmail.getText());

				if (mensagemValidacao.isEmpty()) {
					controllerUsuario.recuperarSenha(txtEmail.getText());
					habilitarDesabilitarBotaoRecoveryKey(true);
					desabilitarBotoesEnviarEmail(false);

				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}

			}
		});
		btnEnviarEmail.setBounds(203, 390, 126, 23);
		contentPane.add(btnEnviarEmail);

		btnVerificarCdigo = new JButton("Verificar código");

		btnVerificarCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagemValidacao = "";
				ControladoraUsuario controllerUsuario = new ControladoraUsuario();

				mensagemValidacao = controllerUsuario.verificarRecoveryKey(txtLoginRecoverykey.getText(),
						Integer.parseInt(txtRecoveryKey.getText()));
				usuario = controllerUsuario.obterUsuarioParaTrocarSenha(txtLoginRecoverykey.getText(),
						Integer.parseInt(txtRecoveryKey.getText()));

				if (mensagemValidacao.isEmpty()) {
					habilitarBotoesNovaSenha(true);
					habilitarDesabilitarBotaoRecoveryKey(false);
					controllerUsuario.consultarRecoveryKey(txtRecoveryKey.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Tentativa: " + count + " - " + mensagemValidacao);
				}
			}
		});
		btnVerificarCdigo.setBounds(203, 391, 126, 20);
		btnVerificarCdigo.setVisible(false);
		contentPane.add(btnVerificarCdigo);

		 btnReenviarEmail = new JButton("Reenviar Email");
		btnReenviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReenviarEmail.setBounds(481, 33, 146, 23);
		contentPane.add(btnReenviarEmail);
		btnReenviarEmail.setVisible(false);

		txtLoginRecoverykey = new JTextField("Login");
		txtLoginRecoverykey.setBounds(175, 320, 191, 23);
		contentPane.add(txtLoginRecoverykey);
		txtLoginRecoverykey.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmRecuperarConta.class.getResource("/icones/RK.jpg")));
		label.setBounds(0, 0, 801, 522);
		contentPane.add(label);
		txtLoginRecoverykey.setVisible(false);

	}

	protected void habilitarBotoesNovaSenha(boolean habilitar) {
		lblSenha.setVisible(habilitar);
		txtNovaSenha.setVisible(habilitar);
		txtConfirmarNovaSenha.setVisible(habilitar);
		btnAtualizar.setVisible(habilitar);

	}

	protected void desabilitarBotoesEnviarEmail(boolean desabilitar) {
		lblEmail.setVisible(desabilitar);
		txtEmail.setVisible(desabilitar);
		btnEnviarEmail.setVisible(desabilitar);

	}

	protected void habilitarDesabilitarBotaoRecoveryKey(boolean ficarVisivel) {
		lblRecoveryKey.setVisible(ficarVisivel);
		txtRecoveryKey.setVisible(ficarVisivel);
		txtLoginRecoverykey.setVisible(ficarVisivel);
		btnVerificarCdigo.setVisible(ficarVisivel);
	}
}
