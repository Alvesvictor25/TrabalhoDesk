package view2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.ControladoraBanco;
import controller.ControladoraDespesa;
import controller.ControladoraReceita;
import model.util.ContasBancoTableModel;
import model.util.DespesaTableModel;
import model.util.ReceitaTableModel;
import model.vo.ContaBanco;
import model.vo.Despesa;
import model.vo.Receita;
import model.vo.Usuario;

public class JPprincipal extends JPanel {
	private static final long serialVersionUID = 1L;
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private Usuario usuarioJPprincipal;
	private ArrayList<ContaBanco> listaDeContas;
	private JTextField txtNumeroConta;
	private JTextField txtAgencia;
	private JTextField txtSaldoAtual;
	private JLabel lblNmeroDaConta;
	private Double saldoDasContas;
	private Double saldoDespesas;
	private String[] nomeDeBancos = { "Caixa", "Banco do Brasil", "Itaú", "Bradesco", "Santander", "HSBC", "Nubank" };
	private JComboBox cbNomeBancos;
	private JTable tblReceitaMenuPrincipal;
	private JTable tblDespesaMenuPrincipal;
	private JLabel lbSaldoDespesas;
	private JLabel lbSaldoContas;
	private ContasBancoTableModel modelBanco;
	private JTable tabelaConAtivas;
	private JTable tabelaConInat;
	private JScrollPane spContasInativas;
	private List<ContaBanco> contasBancoInativas;
	private List<ContaBanco> contasBancoAtivas;
	private ContasBancoTableModel modelContasInativas;
	private ContasBancoTableModel modelContasAtivas;

	private JScrollPane spContasAtivas;

	/**
	 * Create the panel.
	 * 
	 * @param usuario
	 */
	@SuppressWarnings("unchecked")
	public JPprincipal(Usuario usuarioLogin, List<ContaBanco> contasBancoAtivas,  List<ContaBanco> contasBancoInativas) {
		usuarioJPprincipal = usuarioLogin;
		this.contasBancoAtivas = contasBancoAtivas;
		this.contasBancoInativas = contasBancoInativas;
		setLayout(null);

		Arrays.sort(nomeDeBancos);
		saldoDespesas = calcularValorDeTodasAsDespesas(usuarioJPprincipal.getIdUsuario());
		saldoDasContas = calcularValorTotalDasReceitas(usuarioJPprincipal.getIdUsuario());

		JLabel lblSaldoEmContas = new JLabel("Saldo em contas");
		lblSaldoEmContas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldoEmContas.setBounds(576, 209, 145, 30);
		add(lblSaldoEmContas);

		JLabel lblReceitas = new JLabel("Receitas");
		lblReceitas.setBounds(545, 250, 63, 14);
		add(lblReceitas);

		JLabel lblDespesas = new JLabel("Despesas");
		lblDespesas.setBounds(684, 250, 76, 14);
		add(lblDespesas);

		lbSaldoContas = new JLabel("R$ " + saldoDasContas);
		lbSaldoContas.setBounds(684, 272, 83, 19);
		add(lbSaldoContas);

		lbSaldoDespesas = new JLabel("R$ " + saldoDespesas);
		lbSaldoDespesas.setBounds(545, 272, 63, 19);
		add(lbSaldoDespesas);

		JLabel lblMinhasContas = new JLabel("Minhas contas");
		lblMinhasContas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinhasContas.setBounds(128, 23, 145, 19);
		add(lblMinhasContas);

		txtNumeroConta = new JTextField();
		txtNumeroConta.setColumns(10);
		txtNumeroConta.setBounds(608, 99, 96, 20);
		add(txtNumeroConta);

		txtAgencia = new JTextField();
		txtAgencia.setColumns(10);
		txtAgencia.setBounds(608, 130, 96, 20);
		add(txtAgencia);

		txtSaldoAtual = new JTextField();
		txtSaldoAtual.setColumns(10);
		txtSaldoAtual.setBounds(608, 161, 96, 20);
		add(txtSaldoAtual);

		JButton btnAdicionarNovaConta = new JButton("Adicionar nova conta");
		btnAdicionarNovaConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeConta = (String) cbNomeBancos.getSelectedItem();
				int numeroConta = Integer.parseInt(txtNumeroConta.getText());
				int agenciaConta = Integer.parseInt(txtAgencia.getText());
				double saldoConta = Double.parseDouble(txtSaldoAtual.getText());

				ControladoraBanco controller = new ControladoraBanco();
				String mensagemValidacaoConta = controller.validarCamposContaBancoUsuario(nomeConta, numeroConta,
						agenciaConta, saldoConta);

				if (mensagemValidacaoConta.isEmpty()) {
					ContaBanco novaContaBanco = new ContaBanco(usuarioJPprincipal.getIdUsuario(), nomeConta,
							numeroConta, agenciaConta, saldoConta, true);
					modelContasAtivas.onAdd(novaContaBanco);

					atualizarBalancoReceitaDespesaEcorLabels(novaContaBanco.getSaldoDaConta());
					limparTxtFieldCadastroContas();
					JOptionPane.showMessageDialog(null, "Conta cadastrada!");

					controller.cadastrarContaBanco(novaContaBanco);
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacaoConta);
				}

			}
		});
		btnAdicionarNovaConta.setBounds(645, 11, 186, 19);
		add(btnAdicionarNovaConta);

		JButton btnGraficoDespesa = new JButton("Gráfico despesa");
		btnGraficoDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraDespesa controller = new ControladoraDespesa();
				DefaultCategoryDataset dataSet = controller.criarGraficoDespesa(usuarioJPprincipal.getIdUsuario());

				JFreeChart chart = ChartFactory.createLineChart3D("Despesas", "Data pagamento", "Valor", dataSet,
						PlotOrientation.VERTICAL, false, true, false);
				BarRenderer render = null;
				CategoryPlot plot = null;
				render = new BarRenderer();
				ChartFrame frame = new ChartFrame("Dr.Muquirana - Gráfico despesas", chart);
				frame.setVisible(true);
				frame.setBounds(60, 20, 800, 600);
			}
		});
		btnGraficoDespesa.setBounds(655, 41, 132, 19);
		add(btnGraficoDespesa);

		modelBanco = new ContasBancoTableModel(getList());

		JTabbedPane tabelaAbas = new JTabbedPane();
		tabelaAbas.setBounds(15, 320, 789, 228);
		add(tabelaAbas);

		JPanel jpReceita = new JPanel();
		jpReceita.setLayout(null);
		jpReceita.setBounds(60, 380, 90, 90);
		tabelaAbas.add("Receita", jpReceita);

		ReceitaTableModel modelReceita = new ReceitaTableModel(getListaReceitas());

		tblReceitaMenuPrincipal = new JTable(modelReceita);
		tblReceitaMenuPrincipal.setBounds(17, 336, 876, 172);
		tblReceitaMenuPrincipal.setPreferredScrollableViewportSize(new Dimension(500, 300));

		JScrollPane scrollReceita = new JScrollPane(tblReceitaMenuPrincipal);
		scrollReceita.setBounds(10, 11, 764, 178);
		jpReceita.add(scrollReceita);

		JPanel jpDespesa = new JPanel();
		jpDespesa.setLayout(null);
		jpDespesa.setBounds(60, 380, 90, 90);
		tabelaAbas.add("Despesa", jpDespesa);

		DespesaTableModel modelDespesa = new DespesaTableModel(getListaDespesas());

		tblDespesaMenuPrincipal = new JTable(modelDespesa);
		tblDespesaMenuPrincipal.setBounds(17, 336, 876, 172);

		tblDespesaMenuPrincipal.setPreferredScrollableViewportSize(new Dimension(500, 300));
		JScrollPane scrollDespesa = new JScrollPane(tblDespesaMenuPrincipal);
		scrollDespesa.setBounds(10, 11, 764, 178);
		jpDespesa.add(scrollDespesa);

		JLabel lbNomeBanco = new JLabel("Nome do banco");
		lbNomeBanco.setBounds(508, 69, 96, 19);
		add(lbNomeBanco);

		JLabel lblAgencia = new JLabel("Agência");
		lblAgencia.setBounds(508, 128, 63, 17);
		add(lblAgencia);

		lblNmeroDaConta = new JLabel("Número da conta");
		lblNmeroDaConta.setBounds(508, 99, 107, 19);
		add(lblNmeroDaConta);

		JLabel lblSaldoAtual_1 = new JLabel("Saldo atual");
		lblSaldoAtual_1.setBounds(508, 158, 96, 21);
		add(lblSaldoAtual_1);

		cbNomeBancos = new JComboBox(nomeDeBancos);
		cbNomeBancos.setBounds(598, 69, 107, 19);
		add(cbNomeBancos);

		JButton btnAlterarContaBanco = new JButton("Alterar");
		btnAlterarContaBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlterarContaBanco.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAlterarContaBanco.setBounds(128, 190, 76, 15);
		add(btnAlterarContaBanco);

		JButton btnDesativarContaBanco = new JButton("Salvar Alterações");
		btnDesativarContaBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContaBanco contaSelecionada = new ContaBanco();
				boolean statusAtual = false;
				int linhaSelecionadaAtivas = tabelaConAtivas.getSelectedRow();
				if (linhaSelecionadaAtivas > 0) {
					contaSelecionada = modelContasAtivas.getValue(linhaSelecionadaAtivas);
					statusAtual = contaSelecionada.isStatusDaConta();
				} else {
					int linhaSelecionadaInativas = tabelaConInat.getSelectedRow();
					contaSelecionada = modelContasInativas.getValue(linhaSelecionadaInativas);
					statusAtual = contaSelecionada.isStatusDaConta();

				}
				ControladoraBanco controller = new ControladoraBanco();
				String msg = alterarStatusContaBanco(contaSelecionada, statusAtual);
				setContasBancoAtivas(controller.consultarContasPorUsuario(usuarioJPprincipal.getIdUsuario()));
				setContasBancoInativas(controller.consultarContasPorUsuario(usuarioJPprincipal.getIdUsuario()));
				JOptionPane.showMessageDialog(null, msg);
			}
		});
		btnDesativarContaBanco.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDesativarContaBanco.setBounds(30, 190, 76, 15);
		add(btnDesativarContaBanco);

		JButton btnGraficoReceita = new JButton("Gráfico receita");
		btnGraficoReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraReceita controller = new ControladoraReceita();

				DefaultCategoryDataset dataSet = controller.criarGraficoReceita(usuarioJPprincipal.getIdUsuario());

				JFreeChart chart = ChartFactory.createLineChart3D("Receitas", "Data receita", "Valor", dataSet,
						PlotOrientation.VERTICAL, false, true, false);
				BarRenderer render = null;
				CategoryPlot plot = null;
				render = new BarRenderer();
				ChartFrame frame = new ChartFrame("Dr.Muquirana - Gráfico despesas", chart);
				frame.setVisible(true);
				frame.setBounds(60, 20, 800, 600);
			}
		});
		btnGraficoReceita.setBounds(468, 11, 132, 19);
		add(btnGraficoReceita);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(30, 53, 447, 126);
		add(tabbedPane);

		JPanel jpContasAtivas = new JPanel();
		jpContasAtivas.setLayout(null);
		tabbedPane.add("Contas Ativas", jpContasAtivas);

		modelContasAtivas = new ContasBancoTableModel(contasBancoAtivas);
		tabelaConAtivas = new JTable(modelContasAtivas);
		tabelaConAtivas.setLocation(0, 0);
		tabelaConAtivas.setSize(416, 106);

		spContasAtivas = new JScrollPane(tabelaConAtivas);
		spContasAtivas.setBounds(0, 0, 442, 98);
		jpContasAtivas.add(spContasAtivas);

		JPanel jpContasInativas = new JPanel();
		tabbedPane.add("Contas Inativas", jpContasInativas);
		jpContasInativas.setLayout(null);

		modelContasInativas = new ContasBancoTableModel(contasBancoInativas);
		tabelaConInat = new JTable(modelContasInativas);
		tabelaConInat.setLocation(0, 0);
		tabelaConInat.setSize(416, 106);
		spContasInativas = new JScrollPane(tabelaConInat);
		spContasInativas.setBounds(0, 0, 442, 98);
		jpContasInativas.add(spContasInativas);

		definirCorLabelDespesa(saldoDespesas, saldoDasContas);

	}

	protected String alterarStatusContaBanco(ContaBanco contaSelecionada, boolean statusAtual) {
		ControladoraBanco controller = new ControladoraBanco();
		String resultadoStatusAlterado = controller.atualizarContasBanco(contaSelecionada);
		
		if (statusAtual) {
			modelContasAtivas.onAdd(contaSelecionada);
			modelContasInativas.onRemove(contaSelecionada);
		} else {
			modelContasAtivas.onRemove(contaSelecionada);
			modelContasInativas.onAdd(contaSelecionada);
		}
		return resultadoStatusAlterado;
	}

	protected void limparTxtFieldCadastroContas() {
		txtNumeroConta.setText("");
		txtAgencia.setText("");
		txtSaldoAtual.setText("");

	}

	protected void atualizarBalancoReceitaDespesaEcorLabels(Double receitaAtualizada) {
		saldoDasContas += receitaAtualizada;
		definirCorLabelDespesa(saldoDespesas, saldoDasContas);
		lbSaldoContas.setText("R$ " + saldoDasContas);

	}

	public void atualizarLabelDespesa(Double despesaAtualizada) {
		saldoDespesas += despesaAtualizada;
		definirCorLabelDespesa(saldoDespesas, saldoDasContas);
		lbSaldoDespesas.setText("R$ " + saldoDespesas);
	}

	private void definirCorLabelDespesa(Double saldoDespesas, Double saldoDasContas) {

		if (saldoDespesas > saldoDasContas) {
			lbSaldoDespesas.setForeground(Color.RED);
			lbSaldoContas.setForeground(Color.ORANGE);
		}

		if (saldoDasContas > saldoDespesas) {
			lbSaldoDespesas.setForeground(Color.GREEN);
			lbSaldoContas.setForeground(Color.GREEN);
		}

	}

	private Double calcularValorDeTodasAsDespesas(int idUsuario) {
		ControladoraDespesa controller = new ControladoraDespesa();
		ArrayList<Despesa> despesas = new ArrayList<Despesa>();
		despesas = controller.consultarTodasAsDespesasPorUsuario(idUsuario);

		Double valorTotalDespesas = 0.0;
		for (Despesa c : despesas) {
			valorTotalDespesas += c.getValor();
		}
		return valorTotalDespesas;

	}

	private Double calcularValorTotalDasReceitas(int idUsuario) {
		ControladoraBanco controller = new ControladoraBanco();
		ArrayList<ContaBanco> contas = new ArrayList<ContaBanco>();
		contas = controller.consultarContasPorUsuario(idUsuario);
		Double valor = 0.0;

		for (ContaBanco c : contas) {
			valor += c.getSaldoDaConta();
		}
		return valor;
	}

	private List<ContaBanco> getList() {
		ControladoraBanco controller = new ControladoraBanco();
		listaDeContas = controller.consultarContasPorUsuario(usuarioJPprincipal.getIdUsuario());
		return listaDeContas;
	}

	private List<Despesa> getListaDespesas() {
		ControladoraDespesa controllerDespesa = new ControladoraDespesa();
		ArrayList<Despesa> listaDespesas = new ArrayList<Despesa>();
		listaDespesas = controllerDespesa.consultarTodasAsDespesasPorUsuario(usuarioJPprincipal.getIdUsuario());

		return listaDespesas;
	}

	private List<Receita> getListaReceitas() {
		ControladoraReceita controllerReceita = new ControladoraReceita();
		List<Receita> listaDespesas = new ArrayList<Receita>();
		listaDespesas = controllerReceita.consultarTodasReceitasPorIdUsuario(usuarioJPprincipal.getIdUsuario());

		return listaDespesas;
	}

	public List<ContaBanco> getContasBancoInativas() {
		return contasBancoInativas;
	}

	public void setContasBancoInativas(List<ContaBanco> contasBancoInativas) {
		this.contasBancoInativas = contasBancoInativas;
	}

	public List<ContaBanco> getContasBancoAtivas() {
		return contasBancoAtivas;
	}

	public void setContasBancoAtivas(List<ContaBanco> contasBancoAtivas) {
		this.contasBancoAtivas = contasBancoAtivas;
	}

	public void setTabelaConInat(JTable tabelaConInat) {
		this.tabelaConInat = tabelaConInat;
	}
}
