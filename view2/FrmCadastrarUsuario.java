package view2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControladoraUsuario;
import model.vo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

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
	private JFormattedTextField formattedTextFieldTelefone;
	private Container formattedTextFieldCpf;
	private JLabel lblErroNoUsername;
	private JLabel lblErroValidarSenha;
	private JLabel lblErroConfirmarSenha;
	private JLabel lblErroSenhasDiferentes;
	private JLabel lblErroNomeInvalido;
	private JLabel lblErroCpfInvalido;
	private JLabel lblErroVerificarTelefone;
	private JLabel lblErroVerificarEmail;
	private JLabel lblErroCpfJaCadastrado;
	private JLabel lblErroUsuarioCadastrado;
	private JLabel lblEmailJCadastrado;

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
		setDefaultCloseOperation(FrmCadastrarUsuario.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 726);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Dr.Muquirana - Cadastro");

		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			MaskFormatter mascaraTelefone = new MaskFormatter("(##)#####-####");
			formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
			formattedTextFieldCpf.setFont(new Font("Tahoma", Font.ITALIC, 12));
			formattedTextFieldCpf.setForeground(Color.LIGHT_GRAY);
			((JTextField) formattedTextFieldCpf).setColumns(14);
			formattedTextFieldCpf.setBounds(302, 386, 238, 30);
			contentPane.add(formattedTextFieldCpf);

			formattedTextFieldTelefone = new JFormattedTextField(mascaraTelefone);
			formattedTextFieldTelefone.setFont(new Font("Tahoma", Font.ITALIC, 12));
			formattedTextFieldTelefone.setForeground(Color.LIGHT_GRAY);
			formattedTextFieldTelefone.setColumns(10);
			formattedTextFieldTelefone.setBounds(302, 460, 238, 30);
			contentPane.add(formattedTextFieldTelefone);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsuarioTelefone = formattedTextFieldTelefone;
				txtUsuarioCpf = (JTextField) formattedTextFieldCpf;
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
					JOptionPane.showMessageDialog(null, "Usuário cadastrado!!!");
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}
			}
		});
		btnCadastrar.setBounds(302, 620, 198, 30);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmLogin login = new FrmLogin();
				login.setVisible(true);
				fecharTelaCadastroUsuario();
			}
		});
		btnVoltar.setBounds(620, 11, 127, 30);
		contentPane.add(btnVoltar);

		txtSenha = new JTextField("Letras maiúsculas, minúsculas, números");
		txtSenha.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtSenha.setForeground(Color.LIGHT_GRAY);
		txtSenha.setColumns(10);
		txtSenha.setBounds(302, 149, 238, 30);
		contentPane.add(txtSenha);

		txtUsuarioNome = new JTextField("Nome completo");
		txtUsuarioNome.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtUsuarioNome.setForeground(Color.LIGHT_GRAY);
		txtUsuarioNome.setColumns(10);
		txtUsuarioNome.setBounds(302, 316, 238, 30);
		contentPane.add(txtUsuarioNome);

		txtUsuarioEmail = new JTextField("example_-09@hotmail.com");
		txtUsuarioEmail.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtUsuarioEmail.setForeground(Color.LIGHT_GRAY);
		txtUsuarioEmail.setColumns(10);
		txtUsuarioEmail.setBounds(302, 525, 238, 30);
		contentPane.add(txtUsuarioEmail);

		txtUsuarioUsername = new JTextField("Username");
		txtUsuarioUsername.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtUsuarioUsername.setForeground(Color.LIGHT_GRAY);
		txtUsuarioUsername.setColumns(10);
		txtUsuarioUsername.setBounds(302, 86, 238, 30);
		contentPane.add(txtUsuarioUsername);

		txtConfirmarSenha = new JTextField("Letras maiúsculas, minúsculas, números");
		txtConfirmarSenha.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtConfirmarSenha.setForeground(Color.LIGHT_GRAY);
		txtConfirmarSenha.setColumns(10);
		txtConfirmarSenha.setBounds(336, 212, 236, 30);
		contentPane.add(txtConfirmarSenha);

		// LABEL MSG DE ERROS

		lblErroNoUsername = new JLabel("username inválido *");
		lblErroNoUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroNoUsername.setForeground(Color.RED);
		lblErroNoUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroNoUsername.setBounds(302, 116, 238, 22);
		lblErroNoUsername.setVisible(false);
		contentPane.add(lblErroNoUsername);

		lblErroValidarSenha = new JLabel("senha inválida *");
		lblErroValidarSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroValidarSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroValidarSenha.setForeground(Color.RED);
		lblErroValidarSenha.setBounds(302, 179, 232, 22);
		lblErroValidarSenha.setVisible(false);
		contentPane.add(lblErroValidarSenha);

		lblErroConfirmarSenha = new JLabel("senha inválida *");
		lblErroConfirmarSenha.setForeground(Color.RED);
		lblErroConfirmarSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroConfirmarSenha.setBounds(336, 243, 114, 22);
		lblErroConfirmarSenha.setVisible(false);
		contentPane.add(lblErroConfirmarSenha);

		lblErroSenhasDiferentes = new JLabel("senhas não coindicem*");
		lblErroSenhasDiferentes.setForeground(Color.RED);
		lblErroSenhasDiferentes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroSenhasDiferentes.setBounds(460, 243, 139, 22);
		lblErroSenhasDiferentes.setVisible(false);
		contentPane.add(lblErroSenhasDiferentes);

		lblErroNomeInvalido = new JLabel("nome inválido *");
		lblErroNomeInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroNomeInvalido.setForeground(Color.RED);
		lblErroNomeInvalido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroNomeInvalido.setBounds(302, 347, 232, 22);
		lblErroNomeInvalido.setVisible(false);
		contentPane.add(lblErroNomeInvalido);

		lblErroCpfInvalido = new JLabel("cpf inválido *");
		lblErroCpfInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroCpfInvalido.setForeground(Color.RED);
		lblErroCpfInvalido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroCpfInvalido.setBounds(302, 416, 238, 22);
		lblErroCpfInvalido.setVisible(false);
		contentPane.add(lblErroCpfInvalido);

		lblErroVerificarTelefone = new JLabel("telefone inválido *");
		lblErroVerificarTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroVerificarTelefone.setForeground(Color.RED);
		lblErroVerificarTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroVerificarTelefone.setBounds(302, 493, 232, 22);
		lblErroVerificarTelefone.setVisible(false);
		contentPane.add(lblErroVerificarTelefone);

		lblErroVerificarEmail = new JLabel("e-mail inválido*");
		lblErroVerificarEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroVerificarEmail.setForeground(Color.RED);
		lblErroVerificarEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroVerificarEmail.setBounds(304, 556, 236, 22);
		lblErroVerificarEmail.setVisible(false);
		contentPane.add(lblErroVerificarEmail);

		lblErroCpfJaCadastrado = new JLabel("cpf já cadastrado");
		lblErroCpfJaCadastrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroCpfJaCadastrado.setForeground(Color.RED);
		lblErroCpfJaCadastrado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroCpfJaCadastrado.setBounds(302, 416, 238, 22);
		lblErroCpfJaCadastrado.setVisible(false);
		contentPane.add(lblErroCpfJaCadastrado);

		lblErroUsuarioCadastrado = new JLabel("username já cadastrado *");
		lblErroUsuarioCadastrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroUsuarioCadastrado.setForeground(Color.RED);
		lblErroUsuarioCadastrado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErroUsuarioCadastrado.setBounds(302, 116, 238, 22);
		lblErroUsuarioCadastrado.setVisible(false);
		contentPane.add(lblErroUsuarioCadastrado);

		// FOCUS TXTFIELD

		txtUsuarioUsername.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				ControladoraUsuario controller = new ControladoraUsuario();
				boolean resultado = controller.validarUsername(txtUsuarioUsername.getText());
				boolean resultadoRegistroPorLogin = controller
						.verificarRegistroPorUsername(txtUsuarioUsername.getText());

				if (resultadoRegistroPorLogin) {
					lblErroUsuarioCadastrado.setVisible(true);
				}
				if (!resultado) {
					lblErroNoUsername.setVisible(true);
				}
			}

			public void focusGained(FocusEvent e) {
				lblErroUsuarioCadastrado.setVisible(false);
				lblErroNoUsername.setVisible(false);
				txtUsuarioUsername.setText("");
				txtUsuarioUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtUsuarioUsername.setForeground(Color.BLACK);
			}
		});

		txtSenha.addFocusListener(new FocusListener() {
			ControladoraUsuario controller = new ControladoraUsuario();

			public void focusLost(FocusEvent e) {
				boolean resultado = controller.verificadorDeSenha(txtSenha.getText());
				if (!resultado) {
					lblErroValidarSenha.setVisible(true);
				}
			}

			public void focusGained(FocusEvent e) {
				lblErroValidarSenha.setVisible(false);
				txtSenha.setText("");
				txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtSenha.setForeground(Color.BLACK);

			}
		});

		txtConfirmarSenha.addFocusListener(new FocusListener() {
			ControladoraUsuario controller = new ControladoraUsuario();

			public void focusLost(FocusEvent e) {
				boolean resultado = controller.verificadorDeSenha(txtConfirmarSenha.getText());
				String mensagemValidacaoSenhas = controller.validarSenha(txtSenha.getText(),
						txtConfirmarSenha.getText());

				if (!resultado) {
					lblErroConfirmarSenha.setVisible(true);
				}
				if (!mensagemValidacaoSenhas.isEmpty()) {
					lblErroSenhasDiferentes.setVisible(true);
				}
			}

			public void focusGained(FocusEvent e) {
				lblErroConfirmarSenha.setVisible(false);
				lblErroSenhasDiferentes.setVisible(false);
				txtConfirmarSenha.setText("");
				txtConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtConfirmarSenha.setForeground(Color.BLACK);

			}
		});
		txtUsuarioCpf = (JTextField) formattedTextFieldCpf;
		formattedTextFieldCpf.addFocusListener(new FocusListener() {
			ControladoraUsuario controller = new ControladoraUsuario();

			public void focusLost(FocusEvent e) {
				boolean resultado = controller.verificarRegistroPorCpf(txtUsuarioCpf.getText());
				String cpf = txtUsuarioCpf.getText();
				cpf.replaceAll(".", "");
				cpf.replaceAll("-", "");
				boolean verificarCPF = controller.validarNumerosCPF(cpf);
				if (resultado) {
					lblErroCpfJaCadastrado.setVisible(true);
				}

				if (!verificarCPF) {
					lblErroCpfInvalido.setVisible(true);
				}
			}

			public void focusGained(FocusEvent e) {
				lblErroCpfInvalido.setVisible(false);
				lblErroCpfJaCadastrado.setVisible(false);
				formattedTextFieldCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
				formattedTextFieldCpf.setForeground(Color.BLACK);
			}
		});

		txtUsuarioNome.addFocusListener(new FocusListener() {
			ControladoraUsuario controller = new ControladoraUsuario();

			public void focusLost(FocusEvent e) {
				boolean resultado = controller.validarNomeUsuario(txtUsuarioNome.getText());
				if (!resultado) {
					lblErroNomeInvalido.setVisible(true);
				}

			}

			public void focusGained(FocusEvent e) {
				lblErroNomeInvalido.setVisible(false);
				txtUsuarioNome.setText("");
				txtUsuarioNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtUsuarioNome.setForeground(Color.BLACK);

			}
		});
		txtUsuarioTelefone = formattedTextFieldTelefone;

		lblEmailJCadastrado = new JLabel("e-mail já cadastrado *");
		lblEmailJCadastrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailJCadastrado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmailJCadastrado.setForeground(Color.RED);
		lblEmailJCadastrado.setBounds(302, 556, 256, 22);
		lblEmailJCadastrado.setVisible(false);
		contentPane.add(lblEmailJCadastrado);

		JLabel label = new JLabel("");
		label.setIcon(
				new ImageIcon(FrmCadastrarUsuario.class.getResource("/icones/tela cadastro de usuário nova.png")));
		label.setBounds(0, 0, 752, 688);
		contentPane.add(label);

		formattedTextFieldTelefone.addFocusListener(new FocusListener() {
			ControladoraUsuario controller = new ControladoraUsuario();

			public void focusLost(FocusEvent e) {
				boolean resultado = controller.validarTelefone(txtUsuarioTelefone.getText());
				if (!resultado) {
					lblErroVerificarTelefone.setVisible(true);
				}
			}

			public void focusGained(FocusEvent e) {
				lblErroVerificarTelefone.setVisible(false);
				formattedTextFieldTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
				formattedTextFieldTelefone.setForeground(Color.BLACK);

			}
		});

		txtUsuarioEmail.addFocusListener(new FocusListener() {
			ControladoraUsuario controller = new ControladoraUsuario();

			public void focusLost(FocusEvent e) {
				boolean resultadoEmailExistente = controller.verificarRegistroPorEmail(txtUsuarioEmail.getText());
				boolean resultado = controller.validarEmail(txtUsuarioEmail.getText());
				if (!resultado) {
					lblErroVerificarEmail.setVisible(true);
				}
				if (resultadoEmailExistente) {
					lblEmailJCadastrado.setVisible(true);
				}
			}

			public void focusGained(FocusEvent e) {
				lblErroVerificarEmail.setVisible(false);
				lblEmailJCadastrado.setVisible(false);
				txtUsuarioEmail.setText("");
				txtUsuarioEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtUsuarioEmail.setForeground(Color.BLACK);
			}
		});
	}

	public void fecharTelaCadastroUsuario() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}