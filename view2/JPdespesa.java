package view2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controller.ControladoraDespesa;
import model.util.DespesaTableModel;
import model.vo.Despesa;
import model.vo.Usuario;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class JPdespesa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtCategoria;
	private JTable table;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private String[] colunasTabelaDespesa = { "Descrição", "Valor", "Categoria", "Data de vencimento",
			"Data de pagamento" };
	private ArrayList<Despesa> todasDespesasDoUsuario;
	private JDateChooser jchooserPagamento;
	private JDateChooser jchooserVencimento;
	private Usuario usuarioDespesa;
	private JTable tabelaDespesa;
	private DespesaTableModel modelDespesa;
	private JComboBox cbTipoDeConta;
	private JComboBox cbCategoria;
	private JDateChooser dateChooser2;
	private JDateChooser dateChooser1;
	private JComboBox cbDescricao;
	private JButton btnFiltroDeResultado;
	private JButton btnOcultarBotesFiltro;
	private ArrayList<String> categoriasDespesa = new ArrayList<String>();
	private ArrayList<String> descricoesDespesa = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	public JPdespesa(Usuario usuarioLogin) {
		usuarioDespesa = usuarioLogin;
		setLayout(null);
		setBounds(100, 100, 859, 605);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(41, 55, 62, 20);
		add(lblDescricao);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(675, 55, 62, 20);
		add(lblValor);

		JLabel lblDataVencimento = new JLabel("Data Vencimento");
		lblDataVencimento.setBounds(41, 87, 104, 20);
		add(lblDataVencimento);

		JLabel lblDataPagamento = new JLabel("Data Pagamento");
		lblDataPagamento.setBounds(330, 87, 104, 20);
		add(lblDataPagamento);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(330, 54, 77, 22);
		add(lblCategoria);


		dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(742, 304, 107, 20);
		add(dateChooser1);

		dateChooser2 = new JDateChooser();
		dateChooser2.setBounds(606, 304, 107, 20);
		add(dateChooser2);
		dateChooser2.setVisible(false);
	
		verificarDescricoesDespesaDoUsuario(usuarioDespesa.getIdUsuario());
		cbDescricao = new JComboBox<Object>(descricoesDespesa.toArray());
		cbDescricao.setBounds(219, 304, 113, 21);
		cbDescricao.setSelectedIndex(-1);
		add(cbDescricao);
		cbDescricao.setVisible(false);

		verificarCategoriasDespesaDoUsuario(usuarioDespesa.getIdUsuario());
		cbCategoria = new JComboBox<Object>(categoriasDespesa.toArray());
		cbCategoria.setBounds(378, 304, 113, 21);
		cbCategoria.setSelectedIndex(-1);
		cbCategoria.setVisible(false);
		add(cbCategoria);

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

		jchooserPagamento = new JDateChooser("dd/MM/yyyy", "##/##/####", (char) 0);
		jchooserPagamento.setBounds(450, 87, 121, 23);
		add(jchooserPagamento);

		jchooserVencimento = new JDateChooser("dd/MM/yyyy", "##/##/####", (char) 0);
		jchooserVencimento.setBounds(155, 87, 121, 23);
		add(jchooserVencimento);

		modelDespesa = new DespesaTableModel(getListaDespesas());

		tabelaDespesa = new JTable(modelDespesa);
		tabelaDespesa.setBounds(17, 336, 876, 172);
		add(tabelaDespesa);

		JButton btnDetalhar = new JButton("Detalhar");
		btnDetalhar.setBounds(606, 532, 89, 23);
		add(btnDetalhar);

		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraDespesa controllerDespesa = new ControladoraDespesa();

				String descricaoDespesa = txtDescricao.getText();
				Double valorDespesa = Double.parseDouble(txtValor.getText());
				String categoriaDespesa = txtCategoria.getText();
				LocalDate pagamentoDespesa = jchooserPagamento.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				LocalDate vencimentoDespesa = jchooserVencimento.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();

				String mensagemValidacao = controllerDespesa.validarDespesa(descricaoDespesa, valorDespesa,
						pagamentoDespesa, vencimentoDespesa, categoriaDespesa);

				if (mensagemValidacao.isEmpty()) {
					int id = usuarioDespesa.getIdUsuario();
					Despesa despesa = new Despesa(id, descricaoDespesa, valorDespesa, pagamentoDespesa,
							vencimentoDespesa, categoriaDespesa);
					controllerDespesa.cadastrarDespesaController(despesa);
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}

				JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso!!!");

			}
		});
		btnSalvar.setBounds(330, 132, 181, 28);
		add(btnSalvar);

		JButton btnExcluirDespesa = new JButton("Excluir despesa");
		btnExcluirDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int despesaSelecionada = tabelaDespesa.getSelectedRow();

				Despesa despesa = todasDespesasDoUsuario.get(despesaSelecionada - 1);
				ControladoraDespesa controllerDespesa = new ControladoraDespesa();
				if (despesa != null) {
					JOptionPane.showConfirmDialog(null, "Deseja excluir despesa?" + JOptionPane.OK_CANCEL_OPTION);

				}
			}
		});
		btnExcluirDespesa.setBounds(17, 532, 139, 23);
		add(btnExcluirDespesa);

		JButton btnEditarDespesa = new JButton("Editar despesa");
		btnEditarDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descricaoDespesa = txtDescricao.getText();
				Double valorDespesa = Double.parseDouble(txtValor.getText());
				String categoriaDespesa;
			}
		});
		btnEditarDespesa.setBounds(207, 533, 139, 20);
		add(btnEditarDespesa);

		btnFiltroDeResultado = new JButton("Filtro de resultado");
		btnFiltroDeResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean statusBotoes = true;
				habilitarDesabitarBotoesFiltroDePesquisa(statusBotoes);

			}
		});
		btnFiltroDeResultado.setBounds(17, 303, 151, 18);
		add(btnFiltroDeResultado);

		btnOcultarBotesFiltro = new JButton("Ocultar botões filtro");
		btnOcultarBotesFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean statusBotoes = false;
				habilitarDesabitarBotoesFiltroDePesquisa(statusBotoes);
			}
		});
		btnOcultarBotesFiltro.setBounds(17, 251, 151, 18);
		add(btnOcultarBotesFiltro);
		btnOcultarBotesFiltro.setVisible(false);
	}

	private void verificarDescricoesDespesaDoUsuario(int idUsuario) {
		ControladoraDespesa controllerDespesa = new ControladoraDespesa();
		descricoesDespesa = controllerDespesa.verificarDescricoesDespesaDoUsuario(idUsuario);
		
	}

	private void verificarCategoriasDespesaDoUsuario(int idUsuario) {
		ControladoraDespesa controllerDespesa = new ControladoraDespesa();
		categoriasDespesa = controllerDespesa.verificarCategoriasDespesaDoUsuario(idUsuario);
		
	}

	protected void habilitarDesabitarBotoesFiltroDePesquisa(boolean statusBotoes) {
		cbCategoria.setVisible(statusBotoes);
		dateChooser2.setVisible(statusBotoes);
		dateChooser1.setVisible(statusBotoes);
		cbDescricao.setVisible(statusBotoes);
		cbTipoDeConta.setVisible(statusBotoes);
		btnFiltroDeResultado.setVisible(!statusBotoes);
		btnOcultarBotesFiltro.setVisible(statusBotoes);

	}

	private List<Despesa> getListaDespesas() {
		ControladoraDespesa controllerDespesa = new ControladoraDespesa();
		ArrayList<Despesa> listaDespesas = new ArrayList<Despesa>();
		listaDespesas = controllerDespesa.consultarTodasAsDespesasPorUsuario(usuarioDespesa.getIdUsuario());

		return listaDespesas;
	}
}
