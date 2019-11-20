package view2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controller.ControladoraDespesa;
import model.util.DespesaTableModel;
import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.ScrollPane;

public class JPdespesa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtCategoria;
	private JTable table;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private String[] colunasTabelaDespesa = { "Descrição", "Valor", "Categoria", "Data de vencimento",
			"Data de pagamento" };
	private ArrayList<DespesaVO> todasDespesasDoUsuario;
	private JDateChooser jchooserPagamento;
	private JDateChooser jchooserVencimento;
	private UsuarioVO usuarioDespesa;
	private JTable tabelaDespesa;
	private DespesaTableModel modelDespesa;

	public JPdespesa(UsuarioVO usuarioLogin) {
		usuarioDespesa = usuarioLogin;
		setLayout(null);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(34, 45, 104, 20);
		add(lblDescricao);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(34, 76, 101, 20);
		add(lblValor);

		JLabel lblDataVencimento = new JLabel("Data Vencimento");
		lblDataVencimento.setBounds(34, 107, 111, 20);
		add(lblDataVencimento);

		JLabel lblDataPagamento = new JLabel("Data Pagamento");
		lblDataPagamento.setBounds(34, 138, 111, 20);
		add(lblDataPagamento);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(34, 169, 96, 20);
		add(lblCategoria);

		JLabel lblUltimosLanamentos = new JLabel("Ultimos lançamentos");
		lblUltimosLanamentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblUltimosLanamentos.setBounds(197, 321, 250, 23);
		add(lblUltimosLanamentos);

		JLabel lblDespesasPorCategoria = new JLabel("Despesas por categoria");
		lblDespesasPorCategoria.setBounds(385, 25, 139, 20);
		add(lblDespesasPorCategoria);

		JLabel lblGerarRelatrio = new JLabel("Gerar relatório");
		lblGerarRelatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerarRelatrio.setBounds(534, 24, 127, 23);
		add(lblGerarRelatrio);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(104, 45, 125, 23);
		add(txtDescricao);
		txtDescricao.setColumns(10);

		txtValor = new JTextField();
		txtValor.setBounds(104, 76, 125, 23);
		add(txtValor);
		txtValor.setColumns(10);

		txtCategoria = new JTextField();
		txtCategoria.setBounds(104, 169, 125, 23);
		add(txtCategoria);
		txtCategoria.setColumns(10);

		jchooserPagamento = new JDateChooser("dd/MM/yyyy", "##/##/####", (char) 0);
		jchooserPagamento.setBounds(144, 138, 121, 23);
		add(jchooserPagamento);

		jchooserVencimento = new JDateChooser("dd/MM/yyyy", "##/##/####", (char) 0);
		jchooserVencimento.setBounds(144, 107, 121, 23);
		add(jchooserVencimento);

		modelDespesa = new DespesaTableModel(getListaDespesas());
		
		
		tabelaDespesa = new JTable(modelDespesa);
		tabelaDespesa.setBounds(34, 355, 584, 120);
		tabelaDespesa.setAutoResizeMode(WIDTH);
		add(tabelaDespesa);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(593, 355, 25, 120);
		add(scrollPane);
		
		JButton btnDetalhar = new JButton("Detalhar");
		btnDetalhar.setBounds(665, 397, 89, 23);
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
					DespesaVO despesa = new DespesaVO(id, descricaoDespesa, valorDespesa, pagamentoDespesa,
							vencimentoDespesa, categoriaDespesa);
					controllerDespesa.cadastrarDespesaController(despesa);
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}

				JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso!!!");

			}
		});
		btnSalvar.setBounds(88, 213, 89, 23);
		add(btnSalvar);

		JButton btnExcluirDespesa = new JButton("Excluir despesa");
		btnExcluirDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int despesaSelecionada = tabelaDespesa.getSelectedRow();

				DespesaVO despesa = todasDespesasDoUsuario.get(despesaSelecionada - 1);
				ControladoraDespesa controllerDespesa = new ControladoraDespesa();
				if (despesa != null) {
					JOptionPane.showConfirmDialog(null, "Deseja excluir despesa?" + JOptionPane.OK_CANCEL_OPTION);

				}
			}
		});
		btnExcluirDespesa.setBounds(665, 332, 139, 23);
		add(btnExcluirDespesa);

		JButton btnEditarDespesa = new JButton("Editar despesa");
		btnEditarDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descricaoDespesa = txtDescricao.getText();
				Double valorDespesa = Double.parseDouble(txtValor.getText());
				String categoriaDespesa;
			}
		});
		btnEditarDespesa.setBounds(665, 366, 139, 20);
		add(btnEditarDespesa);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(665, 75, 125, 23);
		add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(665, 106, 125, 23);
		add(comboBox_1);

	}

	private List<DespesaVO> getListaDespesas() {
		ControladoraDespesa controllerDespesa = new ControladoraDespesa();
		ArrayList<DespesaVO> listaDespesas = new ArrayList<DespesaVO>();
		listaDespesas = controllerDespesa.consultarTodasDespesasController();

		return listaDespesas;
	}
}
