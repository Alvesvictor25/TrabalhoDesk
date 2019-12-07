package view2;

import javax.swing.JPanel;

import model.util.ReceitaTableModel;
import model.vo.ContaBanco;
import model.vo.Despesa;
import model.vo.Receita;
import model.vo.Usuario;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import controller.ControladoraBanco;
import controller.ControladoraDespesa;
import controller.ControladoraReceita;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;

public class JPreceita extends JPanel {

	private static final long serialVersionUID = 1L;
	private Usuario usuarioJPreceita;
	private JTextField txtValor;
	private JTextField txtCategoria;
	private JTextField txtDescricao;
	private JComboBox cbDescricao;
	private JDateChooser dcConsultaInicio;
	private JComboBox cbTipoDeConta;
	private JDateChooser dcDataReceita;
	private List<ContaBanco> contaBancoAtiva;
	private JComboBox<ContaBanco> cbContaBancoUsuario;
	private JScrollPane scrollPaneReceita;
	private ReceitaTableModel modelReceita;
	private JTable tabelaReceita;
	private JDateChooser dcConsultaFim;
	private List<Receita> consultarComFiltro;
	private List<String> descricaoReceitaPreencherCb;
	private JButton btnFiltroDeResultado;
	private JButton btnOcultarBotoesFiltro;
	private JButton btnLimparConsulta;
	private JButton btnGerarPlanilhaXls;

	/**
	 * Create the panel.
	 * 
	 * @param usuario
	 */
	public JPreceita(Usuario usuarioLogin, List<ContaBanco> contasBancoAtivas) {
		usuarioJPreceita = usuarioLogin;
		contaBancoAtiva = contasBancoAtivas;
		setBounds(100, 100, 857, 606);
		setLayout(null);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescricao.setBounds(88, 35, 117, 21);
		add(lblDescricao);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValor.setBounds(88, 161, 92, 22);
		add(lblValor);

		JLabel lblDataReceita = new JLabel("Data da receita:");
		lblDataReceita.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDataReceita.setBounds(88, 99, 147, 23);
		add(lblDataReceita);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategoria.setBounds(88, 67, 117, 22);
		add(lblCategoria);

		verificarDescricoesReceitasDoUsuario(usuarioJPreceita.getIdUsuario());

		cbDescricao = new JComboBox(descricaoReceitaPreencherCb.toArray());
		cbDescricao.setBounds(218, 281, 144, 20);
		add(cbDescricao);
		cbDescricao.setVisible(false);

		dcConsultaFim = new JDateChooser();
		dcConsultaFim.setBounds(595, 281, 139, 20);
		add(dcConsultaFim);
		dcConsultaFim.setVisible(false);

		dcConsultaInicio = new JDateChooser();
		dcConsultaInicio.setBounds(407, 281, 127, 19);
		add(dcConsultaInicio);
		dcConsultaInicio.setVisible(false);

		cbTipoDeConta = new JComboBox(contaBancoAtiva.toArray());
		cbTipoDeConta.setBounds(37, 280, 151, 22);
		cbTipoDeConta.setSelectedIndex(-1);
		add(cbTipoDeConta);
		cbTipoDeConta.setVisible(false);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(240, 35, 228, 29);
		add(txtDescricao);
		txtDescricao.setColumns(10);

		txtValor = new JTextField();
		txtValor.setBounds(246, 161, 212, 20);
		add(txtValor);
		txtValor.setColumns(10);

		txtCategoria = new JTextField();
		txtCategoria.setBounds(240, 67, 218, 21);
		add(txtCategoria);
		txtCategoria.setColumns(10);

		dcDataReceita = new JDateChooser("dd/MM/yyyy", "##/##/####", (char) 0);
		dcDataReceita.setBounds(245, 99, 213, 20);
		add(dcDataReceita);

		JButton button_1 = new JButton("Excluir despesa");
		button_1.setBounds(37, 514, 139, 23);
		add(button_1);

		JButton button_3 = new JButton("Salvar");
		button_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ControladoraReceita controllerReceita = new ControladoraReceita();

				int idUsuarioReceita = usuarioJPreceita.getIdUsuario();
				String nomeBancoReceita = (String) cbContaBancoUsuario.getSelectedItem();
				String descricaoReceita = txtDescricao.getText();
				String categoriaReceita = txtCategoria.getText();
				Double valorReceita = Double.parseDouble(txtValor.getText());
				LocalDate dataReceita = dcDataReceita.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				ContaBanco contaBancoUsuario = (ContaBanco) cbContaBancoUsuario.getSelectedItem();

				int idContaSelecionada = contaBancoUsuario.getIdConta();
				String mensagemValidacao = controllerReceita.validarCamposCadastrarReceita(idUsuarioReceita,
						nomeBancoReceita, descricaoReceita, categoriaReceita, valorReceita, dataReceita);

				if (mensagemValidacao.isEmpty()) {
					Receita receitaDoUsuario = new Receita(idUsuarioReceita, idContaSelecionada, descricaoReceita,
							categoriaReceita, dataReceita, valorReceita, contaBancoUsuario);
					boolean resultadoCadastro = controllerReceita.cadastrarReceitaController(receitaDoUsuario);
					if (resultadoCadastro) {
						JOptionPane.showMessageDialog(null, "Receita cadastrada com sucesso!!");
						modelReceita.onAdd(receitaDoUsuario);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar receita.");
					}

				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao);
				}

			}
		});
		button_3.setBounds(514, 35, 144, 21);
		add(button_3);

		btnFiltroDeResultado = new JButton("Filtrar consulta");
		btnFiltroDeResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean statusBotoes = true;
				habilitarDesabitarBotoesFiltroDePesquisa(statusBotoes);

			}
		});
		btnFiltroDeResultado.setBounds(25, 251, 151, 18);
		add(btnFiltroDeResultado);

		btnOcultarBotoesFiltro = new JButton("Ocultar");
		btnOcultarBotoesFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean statusBotoes = false;
				habilitarDesabitarBotoesFiltroDePesquisa(statusBotoes);

			}
		});
		btnOcultarBotoesFiltro.setBounds(579, 186, 159, 29);
		btnOcultarBotoesFiltro.setVisible(false);
		add(btnOcultarBotoesFiltro);

		modelReceita = new ReceitaTableModel(getListaReceitas());

		tabelaReceita = new JTable(modelReceita);
		tabelaReceita.setBounds(17, 336, 876, 172);
		tabelaReceita.setPreferredScrollableViewportSize(new Dimension(600, 300));

		scrollPaneReceita = new JScrollPane(tabelaReceita);
		scrollPaneReceita.setSize(757, 191);
		scrollPaneReceita.setLocation(29, 312);
		this.add(scrollPaneReceita);

		JLabel lblConta = new JLabel("Conta:");
		lblConta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConta.setBounds(88, 133, 117, 21);
		add(lblConta);

		cbContaBancoUsuario = new JComboBox(contasBancoAtivas.toArray());
		cbContaBancoUsuario.setBounds(240, 130, 218, 20);
		add(cbContaBancoUsuario);

		JButton btnGraficoReceita = new JButton("Gráfico receita");
		btnGraficoReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraReceita controller = new ControladoraReceita();

				List<Receita> todasReceitasDoUsuario = controller
						.consultarTodasReceitasPorIdUsuario(usuarioJPreceita.getIdUsuario());

				DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

				for (Receita c : todasReceitasDoUsuario) {
					dataSet.setValue(c.getValor(), c.getDescricao(), "");
				}
				JFreeChart freeChart = ChartFactory.createBarChart3D("Gráfico Receita", "", "Valor", dataSet,
						PlotOrientation.VERTICAL, true, false, false);
				BarRenderer render = null;
				CategoryPlot plot = null;
				render = new BarRenderer();
				ChartFrame frame = new ChartFrame("Dr.Muquirana - Gráfico Receita", freeChart);
				frame.setVisible(true);
				frame.setBounds(60, 20, 800, 600);

			}
		});
		btnGraficoReceita.setBounds(571, 84, 159, 22);
		add(btnGraficoReceita);

		JButton btnConsultaComFiltro = new JButton("Consulta com filtro");
		btnConsultaComFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraReceita controller = new ControladoraReceita();
				ContaBanco nomeBancoReceita = (ContaBanco) cbTipoDeConta.getSelectedItem();
				String descricaoReceita = cbDescricao.getSelectedItem().toString();
				Date consultaDateInicio = dcConsultaInicio.getDate();
				Date consultaDateFim = dcConsultaFim.getDate();

				consultarComFiltro = controller.consultarReceitasComFiltro(nomeBancoReceita, descricaoReceita,
						consultaDateInicio, consultaDateFim);

				modelReceita.onRemoveAll();
				modelReceita.onAddAll(consultarComFiltro);

			}
		});
		btnConsultaComFiltro.setBounds(310, 250, 127, 20);
		add(btnConsultaComFiltro);

		btnLimparConsulta = new JButton("limpar consulta");
		btnLimparConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbTipoDeConta.setSelectedIndex(-1);
				cbDescricao.setSelectedIndex(-1);
				dcConsultaFim.setCalendar(null);
				dcConsultaInicio.setCalendar(null);			}
		});
		btnLimparConsulta.setBounds(571, 226, 144, 23);
		add(btnLimparConsulta);
		
		btnGerarPlanilhaXls = new JButton("Gerar planilha xls");
		btnGerarPlanilhaXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Salvar arquivo como...");
				
				int resultado = fileChooser.showSaveDialog(null);
				if(resultado == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = fileChooser.getSelectedFile().getAbsolutePath();
					 
					ControladoraReceita controller = new ControladoraReceita();
					String mensagem = controller.gerarPlanilhaReceita(consultarComFiltro, caminhoEscolhido);
					
					JOptionPane.showMessageDialog(null, mensagem);
					
				}
			}
		});
		btnGerarPlanilhaXls.setBounds(574, 117, 156, 22);
		add(btnGerarPlanilhaXls);
		btnLimparConsulta.setVisible(false);

	}

	private List<ContaBanco> consultarContasBancoUsuario(int idUsuario) {
		ControladoraBanco controller = new ControladoraBanco();
		return controller.consultarStatusContaBanco(idUsuario, true);
	}

	private void verificarDescricoesReceitasDoUsuario(int idUsuario) {
		ControladoraReceita controller = new ControladoraReceita();
		descricaoReceitaPreencherCb = controller.verificarDescricoesReceitasDoUsuario(idUsuario);

	}

	private List<Receita> getListaReceitas() {
		ControladoraReceita controller = new ControladoraReceita();
		List<Receita> listaReceitas = new ArrayList<Receita>();
		listaReceitas = controller.consultarTodasReceitasPorIdUsuario(usuarioJPreceita.getIdUsuario());

		return listaReceitas;
	}

	protected void habilitarDesabitarBotoesFiltroDePesquisa(boolean statusBotoes) {
		dcConsultaInicio.setVisible(statusBotoes);
		dcConsultaFim.setVisible(statusBotoes);
		cbDescricao.setVisible(statusBotoes);
		cbTipoDeConta.setVisible(statusBotoes);
		btnFiltroDeResultado.setVisible(!statusBotoes);
		btnOcultarBotoesFiltro.setVisible(statusBotoes);
		btnLimparConsulta.setVisible(statusBotoes);

	}
}
