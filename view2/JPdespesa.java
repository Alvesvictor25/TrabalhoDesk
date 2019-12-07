package view2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.ControladoraBanco;
import controller.ControladoraDespesa;
import model.util.DespesaTableModel;
import model.vo.Despesa;
import model.vo.Usuario;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

public class JPdespesa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtCategoria;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private ArrayList<Despesa> todasDespesasDoUsuario;
	private JDateChooser dcDataPagamento;
	private JDateChooser dcDataVencimento;
	private Usuario usuarioDespesa;
	private JTable tabelaDespesa;
	private DespesaTableModel modelDespesa;
	private JComboBox cbCategoria;
	private JDateChooser dcConsultaInicio;
	private JDateChooser dcConsultaFim;
	private JComboBox cbDescricao;
	private JButton btnFiltroDeResultado;
	private JButton btnOcultarBotesFiltro;
	private ArrayList<String> categoriasDespesa = new ArrayList<String>();
	private ArrayList<String> descricoesDespesa = new ArrayList<String>();
	private JButton btnConsultaComFiltro;
	private List<Despesa> consultarComFiltro;
	private JScrollPane scrollPaneDespesa;
	private JLabel label;

	@SuppressWarnings("unchecked")
	public JPdespesa(Usuario usuarioLogin) {
		if (usuarioLogin != null) {
			usuarioDespesa = usuarioLogin;
		}
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

		dcConsultaFim = new JDateChooser();
		dcConsultaFim.setBounds(501, 304, 147, 23);
		add(dcConsultaFim);
		dcConsultaFim.setVisible(false);

		dcConsultaInicio = new JDateChooser();
		dcConsultaInicio.setBounds(319, 304, 139, 23);
		add(dcConsultaInicio);
		dcConsultaInicio.setVisible(false);

		verificarDescricoesDespesaDoUsuario(usuarioDespesa.getIdUsuario());
		cbDescricao = new JComboBox(descricoesDespesa.toArray());
		cbDescricao.setBounds(17, 303, 113, 21);
		add(cbDescricao);
		cbDescricao.setVisible(false);

		verificarCategoriasDespesaDoUsuario(usuarioDespesa.getIdUsuario());
		cbCategoria = new JComboBox(categoriasDespesa.toArray());
		cbCategoria.setBounds(178, 304, 113, 21);
		cbCategoria.setVisible(false);
		add(cbCategoria);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(113, 55, 178, 26);
		add(txtDescricao);
		txtDescricao.setColumns(10);

		txtValor = new JTextField();
		txtValor.setBounds(732, 55, 89, 26);
		add(txtValor);
		txtValor.setColumns(10);

		txtCategoria = new JTextField();
		txtCategoria.setBounds(417, 54, 231, 26);
		add(txtCategoria);
		txtCategoria.setColumns(10);

		dcDataPagamento = new JDateChooser("dd/MM/yyyy", "##/##/####", (char) 0);
		dcDataPagamento.setBounds(450, 87, 121, 26);
		add(dcDataPagamento);

		dcDataVencimento = new JDateChooser("dd/MM/yyyy", "##/##/####", (char) 0);
		dcDataVencimento.setBounds(155, 87, 121, 26);
		add(dcDataVencimento);

		modelDespesa = new DespesaTableModel(getListaDespesas());

		tabelaDespesa = new JTable(modelDespesa);
		tabelaDespesa.setBounds(17, 336, 876, 172);
		tabelaDespesa.setPreferredScrollableViewportSize(new Dimension(600, 300));

		scrollPaneDespesa = new JScrollPane(tabelaDespesa);
		scrollPaneDespesa.setSize(757, 191);
		scrollPaneDespesa.setLocation(17, 335);
		this.add(scrollPaneDespesa);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraDespesa controllerDespesa = new ControladoraDespesa();

				int idUsuario = usuarioDespesa.getIdUsuario();
				String descricaoDespesa = txtDescricao.getText();
				Double valorDespesa = Double.parseDouble(txtValor.getText());
				String categoriaDespesa = txtCategoria.getText();
				LocalDate pagamentoDespesa = dcDataPagamento.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				LocalDate vencimentoDespesa = dcDataVencimento.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();

				String mensagemValidacao = controllerDespesa.validarDespesa(descricaoDespesa, valorDespesa,
						pagamentoDespesa, vencimentoDespesa, categoriaDespesa);

				if (mensagemValidacao.isEmpty()) {
					Despesa despesa = new Despesa(idUsuario, descricaoDespesa, valorDespesa, pagamentoDespesa,
							vencimentoDespesa, categoriaDespesa);
					controllerDespesa.cadastrarDespesaController(despesa);
					modelDespesa.onAdd(despesa);

					limparTextFieldCadastro();
					JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso!!!");
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}

			}
		});
		btnSalvar.setBounds(305, 143, 181, 28);
		add(btnSalvar);

		JButton btnExcluirDespesa = new JButton("Excluir despesa");
		btnExcluirDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int despesaSelecionada = tabelaDespesa.getSelectedRow();

				Despesa despesa = modelDespesa.getValue(despesaSelecionada);
				ControladoraDespesa controllerDespesa = new ControladoraDespesa();
				if (despesa != null) {
					int deletarDespesa = JOptionPane.showConfirmDialog(null,
							"Deseja excluir despesa?" + JOptionPane.YES_NO_OPTION);
					if (deletarDespesa == JOptionPane.YES_NO_OPTION) {
						boolean resultadoDespesaExcluida = controllerDespesa.excluirDespesaController(despesa);
						if (resultadoDespesaExcluida) {
							modelDespesa.onRemove(despesaSelecionada);
						}
					}

				}
			}
		});
		btnExcluirDespesa.setBounds(17, 532, 139, 23);
		add(btnExcluirDespesa);

		JButton btnEditarDespesa = new JButton("Editar despesa");
		btnEditarDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog atualizar = new JDialog();
				atualizar.setVisible(true);
				atualizar.setBounds(50, 80, 200, 300);

				JTextField nome = new JTextField();
				nome.setBounds(50, 80, 15, 50);
				atualizar.getContentPane().add(nome);

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
		btnOcultarBotesFiltro.setBounds(17, 264, 151, 18);
		add(btnOcultarBotesFiltro);
		btnOcultarBotesFiltro.setVisible(false);

		JButton btnGraficoDespesa = new JButton("Detalhar despesas");
		btnGraficoDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraDespesa controller = new ControladoraDespesa();

				ArrayList<Despesa> todasDespesasDoUsuario = controller
						.consultarTodasAsDespesasPorUsuario(usuarioDespesa.getIdUsuario());

				DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

				for (Despesa c : todasDespesasDoUsuario) {
					dataSet.setValue(c.getValor(), c.getDescricao(), "");
				}
				JFreeChart freeChart = ChartFactory.createBarChart3D("Gráfico Despesas", "", "Valor", dataSet,
						PlotOrientation.VERTICAL, true, false, false);
				BarRenderer render = null;
				CategoryPlot plot = null;
				render = new BarRenderer();
				ChartFrame frame = new ChartFrame("Dr.Muquirana - Gráfico despesas", freeChart);
				frame.setVisible(true);
				frame.setBounds(60, 20, 800, 600);

			}
		});
		btnGraficoDespesa.setBounds(695, 263, 164, 20);
		add(btnGraficoDespesa);

		btnConsultaComFiltro = new JButton("Consulta com filtro");
		btnConsultaComFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraDespesa controller = new ControladoraDespesa();
				String categoriaDespesa = cbCategoria.getSelectedItem().toString();
				String descricaoDespesa = cbDescricao.getSelectedItem().toString();
				Date consultaDateInicio = dcConsultaInicio.getDate();
				Date consultaDateFim = dcConsultaFim.getDate();

				consultarComFiltro = controller.consultarDespesaComFiltro(categoriaDespesa, descricaoDespesa,
						consultaDateInicio, consultaDateFim);

				modelDespesa.onRemoveAll();
				modelDespesa.onAddAll(consultarComFiltro);

			}
		});
		btnConsultaComFiltro.setBounds(705, 294, 155, 20);
		add(btnConsultaComFiltro);

		JButton btnLimparConsulta = new JButton("limpar consulta");
		btnLimparConsulta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cbCategoria.setSelectedIndex(-1);
				cbDescricao.setSelectedIndex(-1);
				dcConsultaFim.setCalendar(null);
				dcConsultaInicio.setCalendar(null);
			}

		});
		btnLimparConsulta.setBounds(709, 232, 140, 20);
		add(btnLimparConsulta);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(JPdespesa.class.getResource("/icones/tenor.gif")));
		label.setBounds(0, 0, 439, 339);
		add(label);
	}

	protected void limparTextFieldCadastro() {
		txtCategoria.setText("");
		txtValor.setText("");
		txtDescricao.setText("");
		dcDataPagamento.setCalendar(null);
		dcDataVencimento.setCalendar(null);

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
		dcConsultaInicio.setVisible(statusBotoes);
		dcConsultaFim.setVisible(statusBotoes);
		cbDescricao.setVisible(statusBotoes);
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
