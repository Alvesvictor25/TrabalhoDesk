package view2;

import javax.swing.JPanel;

import model.vo.Receita;
import model.vo.Usuario;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import controller.ControladoraReceita;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;

public class JPreceita extends JPanel {

	private static final long serialVersionUID = 1L;
	private Usuario usuarioJPreceita;
	private JTextField txtValor;
	private JTable table;
	private JTextField txtCategoria;
	private JTextField txtDescricao;
	private JButton btnFiltrarResultado;
	private JButton btnOcultar;
	private JComboBox cbDescricao;
	private JDateChooser dateChooser1;
	private JDateChooser dateChooser2;
	private JComboBox cbCategoria;
	private JComboBox cbTipoDeConta;
	private JDateChooser jchooserVencimento;

	/**
	 * Create the panel.
	 * 
	 * @param usuario
	 */
	public JPreceita(Usuario usuarioLogin) {
		usuarioJPreceita = usuarioLogin;
		setBounds(100, 100, 900, 650);
		setLayout(null);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(41, 55, 62, 20);
		add(lblDescricao);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(675, 55, 62, 20);
		add(lblValor);

		JLabel lblDataVencimento = new JLabel("Data Vencimento");
		lblDataVencimento.setBounds(41, 87, 104, 20);
		add(lblDataVencimento);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(330, 54, 77, 22);
		add(lblCategoria);

		cbDescricao = new JComboBox();
		cbDescricao.setBounds(219, 304, 113, 21);
		add(cbDescricao);
		cbDescricao.setVisible(false);

		dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(742, 304, 107, 20);
		add(dateChooser1);

		dateChooser2 = new JDateChooser();
		dateChooser2.setBounds(606, 304, 107, 20);
		add(dateChooser2);
		dateChooser2.setVisible(false);

		cbCategoria = new JComboBox();
		cbCategoria.setBounds(378, 304, 113, 21);
		add(cbCategoria);
		cbCategoria.setVisible(false);

		cbTipoDeConta = new JComboBox();
		cbTipoDeConta.setBounds(41, 303, 127, 23);
		add(cbTipoDeConta);
		cbTipoDeConta.setVisible(false);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(113, 55, 181, 21);
		add(txtDescricao);
		txtDescricao.setColumns(10);

		txtValor = new JTextField();
		txtValor.setBounds(732, 55, 89, 20);
		add(txtValor);
		txtValor.setColumns(10);

		txtCategoria = new JTextField();
		txtCategoria.setBounds(417, 54, 231, 22);
		add(txtCategoria);
		txtCategoria.setColumns(10);

		jchooserVencimento = new JDateChooser("dd/MM/yyyy", "##/##/####", (char) 0);
		jchooserVencimento.setBounds(155, 87, 121, 23);
		add(jchooserVencimento);

		JButton button_1 = new JButton("Excluir despesa");
		button_1.setBounds(225, 457, 139, 23);
		add(button_1);

		JButton button_2 = new JButton("Detalhar");
		button_2.setBounds(413, 457, 89, 23);
		add(button_2);

		JButton button_3 = new JButton("Salvar");
		button_3.addActionListener(new ActionListener() {
			private JDateChooser dateChooserReceita;

			public void actionPerformed(ActionEvent e) {

				int idUsuarioReceita = usuarioJPreceita.getIdUsuario();
				String descricaoReceita = txtDescricao.getText();
				String categoriaReceita = txtCategoria.getText();
				Double valorReceita = Double.parseDouble(txtValor.getText());
				LocalDate dataReceita = dateChooserReceita.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();

				ControladoraReceita controllerReceita = new ControladoraReceita();
				String mensagemValidacao = controllerReceita.validarCamposCadastrarReceita(idUsuarioReceita,
						descricaoReceita, categoriaReceita, valorReceita, dataReceita);

				if (mensagemValidacao.isEmpty()) {
					Receita receitaDoUsuario = new Receita(idUsuarioReceita, descricaoReceita, categoriaReceita,
							valorReceita, dataReceita);
					boolean resultadoCadastro = controllerReceita.cadastrarReceitaController(receitaDoUsuario);
					if (resultadoCadastro) {
						JOptionPane.showMessageDialog(null, "Receita cadastrada com sucesso!!");
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar receita.");
					}

				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}

			}
		});
		button_3.setBounds(37, 141, 144, 21);
		add(button_3);

		btnFiltrarResultado = new JButton("Filtrar consulta");
		btnFiltrarResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean statusBotoes = true;
				habilitarDesabilitarBotoesFiltro(statusBotoes);

			}
		});
		btnFiltrarResultado.setBounds(17, 303, 151, 18);
		add(btnFiltrarResultado);

		btnOcultar = new JButton("Ocultar");
		btnOcultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean statusBotoes = false;
				habilitarDesabilitarBotoesFiltro(statusBotoes);

			}
		});
		btnOcultar.setBounds(17, 251, 151, 18);
		btnOcultar.setVisible(false);
		add(btnOcultar);

	}

	protected void habilitarDesabilitarBotoesFiltro(boolean statusBotoes) {
		cbCategoria.setVisible(statusBotoes);
		cbDescricao.setVisible(statusBotoes);
		cbTipoDeConta.setVisible(statusBotoes);
		dateChooser1.setVisible(statusBotoes);
		dateChooser2.setVisible(statusBotoes);
		btnFiltrarResultado.setVisible(!statusBotoes);
		btnOcultar.setVisible(statusBotoes);

	}
}
