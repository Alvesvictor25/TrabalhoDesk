package view2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControladoraUsuario;
import model.vo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class FrmCadastrarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSenha;
	private JTextField txtUsuarioNome;
	private JTextField txtUsuarioCpf;
	private JTextField txtUsuarioTelefone;
	private JTextField txtUsuarioEmail;
	private JTextField txtUsuarioUsername;
	private JTextField txtConfirmarSenha;
	private Usuario usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCadastrarUsuario frame = new FrmCadastrarUsuario();
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
	public FrmCadastrarUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 729, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Dr.Muquirana - Cadastro");

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = txtUsuarioUsername.getText();
				String senha = txtSenha.getText();
				String confirmarSenha = txtConfirmarSenha.getText();
				String usuarioNome = txtUsuarioNome.getText();
				String usuarioCpf = txtUsuarioCpf.getText();
				String usuarioTelefone = txtUsuarioTelefone.getText();
				String usuarioEmail = txtUsuarioEmail.getText();

				ControladoraUsuario controllerUsuario = new ControladoraUsuario();
				String mensagemValidacao = controllerUsuario.validarCamposCadastro(username, senha, confirmarSenha,
						usuarioNome, usuarioCpf, usuarioTelefone, usuarioEmail);

				if (mensagemValidacao.isEmpty()) {
					usuario = new Usuario(usuarioNome, usuarioCpf, usuarioTelefone, username, senha, usuarioEmail);
					controllerUsuario.cadastrarUsuarioController(usuario);
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}
			}
		});
		btnCadastrar.setBounds(316, 480, 123, 30);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmLogin login = new FrmLogin();
				login.setVisible(true);
				fecharTelaCadastroUsuario();
			}
		});
		btnVoltar.setBounds(596, 11, 89, 23);
		contentPane.add(btnVoltar);

		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(276, 86, 238, 30);
		contentPane.add(txtSenha);

		txtUsuarioNome = new JTextField();
		txtUsuarioNome.setColumns(10);
		txtUsuarioNome.setBounds(276, 285, 238, 30);
		contentPane.add(txtUsuarioNome);

		txtUsuarioCpf = new JTextField();
		txtUsuarioCpf.setColumns(10);
		txtUsuarioCpf.setBounds(276, 326, 238, 30);
		contentPane.add(txtUsuarioCpf);

		txtUsuarioTelefone = new JTextField();
		txtUsuarioTelefone.setColumns(10);
		txtUsuarioTelefone.setBounds(276, 376, 238, 30);
		contentPane.add(txtUsuarioTelefone);

		txtUsuarioEmail = new JTextField();
		txtUsuarioEmail.setColumns(10);
		txtUsuarioEmail.setBounds(276, 417, 238, 30);
		contentPane.add(txtUsuarioEmail);

		txtUsuarioUsername = new JTextField();
		txtUsuarioUsername.setColumns(10);
		txtUsuarioUsername.setBounds(276, 45, 238, 30);
		contentPane.add(txtUsuarioUsername);

		txtConfirmarSenha = new JTextField();
		txtConfirmarSenha.setColumns(10);
		txtConfirmarSenha.setBounds(316, 140, 196, 30);
		contentPane.add(txtConfirmarSenha);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				FrmCadastrarUsuario.class.getResource("/icones/cadastro de usuário sem retangulos teste 1.png")));
		label.setBounds(0, 0, 720, 552);
		contentPane.add(label);
	}

	public void fecharTelaCadastroUsuario() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}