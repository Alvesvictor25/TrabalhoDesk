package view2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.ControladoraBanco;
import controller.ControladoraDespesa;
import model.util.ContaBancoTableModel;
import model.vo.ContaBanco;
import model.vo.Despesa;
import model.vo.Receita;
import model.vo.Usuario;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class JPprincipal extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable tbContasBancarias;
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private JTable table_1;
	private Usuario usuarioJPprincipal;
	private ArrayList<ContaBanco> listaDeContas;
	private String[] colunasTabela = { "Nome", "Saldo", "Numero" };
	private JTextField txtNomeBanco;
	private JTextField txtNumeroConta;
	private JTextField txtAgencia;
	private JTextField txtSaldoAtual;
	private JLabel lblNmeroDaConta;
	private Double valorTotalDasContas;

	/**
	 * Create the panel.
	 * 
	 * @param usuario
	 */
	public JPprincipal(Usuario usuarioLogin) {
		usuarioJPprincipal = usuarioLogin;
		setLayout(null);

		JLabel lblTextosAqui = new JLabel("Textos aqui ");
		lblTextosAqui.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextosAqui.setBounds(396, 349, 191, 30);
		add(lblTextosAqui);

		JLabel lblEconomia = new JLabel("Economia ");
		lblEconomia.setHorizontalAlignment(SwingConstants.CENTER);
		lblEconomia.setBounds(410, 285, 155, 19);
		add(lblEconomia);

		JLabel lblSaldoEmContas = new JLabel("Saldo em contas");
		lblSaldoEmContas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldoEmContas.setBounds(22, 57, 145, 30);
		add(lblSaldoEmContas);

		JLabel lblReceitas = new JLabel("Receitas");
		lblReceitas.setBounds(10, 98, 83, 14);
		add(lblReceitas);

		JLabel lblDespesas = new JLabel("Despesas");
		lblDespesas.setBounds(101, 98, 76, 14);
		add(lblDespesas);

		JLabel lblR = new JLabel("R$ 2000.00");
		lblR.setBounds(10, 123, 83, 19);
		add(lblR);

		JLabel label = new JLabel("R$ 2000.00");
		label.setBounds(101, 123, 83, 19);
		add(label);

		JLabel lblBalanoMensal = new JLabel("Balanço mensal");
		lblBalanoMensal.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalanoMensal.setBounds(10, 194, 174, 24);
		add(lblBalanoMensal);

		JLabel lblReceitas_1 = new JLabel("Receitas");
		lblReceitas_1.setBounds(103, 239, 48, 14);
		add(lblReceitas_1);

		JLabel label_1 = new JLabel("R$ 2000.00");
		label_1.setBounds(161, 237, 83, 19);
		add(label_1);

		JLabel label_2 = new JLabel("Receitas");
		label_2.setBounds(101, 272, 83, 14);
		add(label_2);

		JLabel label_3 = new JLabel("R$ 2000.00");
		label_3.setBounds(161, 270, 83, 19);
		add(label_3);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(58, 228, 14, 76);
		lblNewLabel.setOpaque(true);
		add(lblNewLabel);

		JLabel label_4 = new JLabel("");
		label_4.setOpaque(true);
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.GREEN);
		label_4.setBounds(33, 255, 14, 49);
		add(label_4);

		JLabel lblMinhasContas = new JLabel("Minhas contas");
		lblMinhasContas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinhasContas.setBounds(437, 11, 145, 19);
		add(lblMinhasContas);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(572, 13, 48, 14);
		add(lblTotal);

		JLabel label_5 = new JLabel("R$ 2000.00");
		label_5.setBounds(615, 11, 83, 19);
		add(label_5);

		JButton btnOcultar = new JButton("Ocultar");
		btnOcultar.setBounds(708, 367, 89, 23);
		add(btnOcultar);

		JButton btnAdicionarNovaConta = new JButton("Adicionar nova conta");
		btnAdicionarNovaConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nomeConta = txtNomeBanco.getText();
				int numeroConta = Integer.parseInt(txtNumeroConta.getText());
				int agenciaConta = Integer.parseInt(txtAgencia.getText());
				double saldoConta = Double.parseDouble(txtSaldoAtual.getText());
				
				ControladoraBanco controller = new ControladoraBanco();
				String mensagemValidacaoConta = controller.validarCamposContaBancoUsuario(nomeConta, numeroConta, agenciaConta, saldoConta); 
				
				if(mensagemValidacaoConta.isEmpty()) {
					ContaBanco novaContaBanco = new ContaBanco(usuarioJPprincipal.getIdUsuario(), nomeConta, numeroConta, agenciaConta, saldoConta);
					controller.cadastrarContaBanco(novaContaBanco);
				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacaoConta);
				}
				
				atualizarTableContas(usuarioJPprincipal.getIdUsuario());
			}
		});
		btnAdicionarNovaConta.setBounds(651, 249, 186, 19);
		add(btnAdicionarNovaConta);

		JButton btnDetalhar_1 = new JButton("Detalhar");
		btnDetalhar_1.setBounds(116, 297, 89, 23);
		add(btnDetalhar_1);

		tbContasBancarias = new JTable();
		tbContasBancarias.setBounds(278, 38, 309, 100);
		add(tbContasBancarias);


		JButton btnDetalhar = new JButton("Detalhar");
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDetalhar.setBounds(431, 315, 89, 23);
		add(btnDetalhar);

		JTabbedPane tabelaAbas = new JTabbedPane();
		tabelaAbas.setBounds(60, 390, 505, 100);
		add(tabelaAbas);

		JPanel painel = new JPanel();
		painel.setBounds(0, 0, 100, 50);
		tabelaAbas.add("Receita", painel);

		table_1 = new JTable();
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column" }));
		table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_1.setFillsViewportHeight(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setColumnSelectionAllowed(true);
		painel.add(table_1);

		JPanel jpReceita = new JPanel();
		jpReceita.setLayout(null);
		jpReceita.setBounds(60, 380, 90, 90);
		tabelaAbas.add("Receita", jpReceita);
		
		txtNomeBanco = new JTextField();
		txtNomeBanco.setBounds(708, 35, 96, 20);
		add(txtNomeBanco);
		txtNomeBanco.setColumns(10);
		
		txtNumeroConta = new JTextField();
		txtNumeroConta.setColumns(10);
		txtNumeroConta.setBounds(708, 62, 96, 20);
		add(txtNumeroConta);
		
		txtAgencia = new JTextField();
		txtAgencia.setColumns(10);
		txtAgencia.setBounds(708, 95, 96, 20);
		add(txtAgencia);
		
		txtSaldoAtual = new JTextField();
		txtSaldoAtual.setColumns(10);
		txtSaldoAtual.setBounds(708, 122, 96, 20);
		add(txtSaldoAtual);
		
		JLabel lbNomeBanco = new JLabel("Nome do banco");
		lbNomeBanco.setBounds(602, 38, 96, 19);
		add(lbNomeBanco);
		
		JLabel lblAgencia = new JLabel("Agência");
		lblAgencia.setBounds(607, 97, 63, 17);
		add(lblAgencia);
		
		lblNmeroDaConta = new JLabel("Número da conta");
		lblNmeroDaConta.setBounds(597, 63, 107, 19);
		add(lblNmeroDaConta);
		
		JLabel lblSaldoAtual_1 = new JLabel("Saldo atual");
		lblSaldoAtual_1.setBounds(602, 122, 96, 21);
		add(lblSaldoAtual_1);
	}

	protected void atualizarTableContas(int idUsuario) {

		ControladoraBanco controller = new ControladoraBanco();
		listaDeContas = controller.consultarContasPorUsuario(usuarioJPprincipal.getIdUsuario());
		limparTela();
		DefaultTableModel model = (DefaultTableModel) tbContasBancarias.getModel();

		for (ContaBanco conta : listaDeContas) {
			String[] novaLinha = new String[3];
			novaLinha[0] = conta.getNomeDoBanco();
			novaLinha[1] = String.valueOf(conta.getSaldoDaConta());
			novaLinha[2] = String.valueOf(conta.getNumeroConta());
			valorTotalDasContas += conta.getSaldoDaConta(); 
			model.addRow(novaLinha);
		}

	}

	private void limparTela() {
		tbContasBancarias.setModel(new DefaultTableModel(new Object[][] { colunasTabela }, colunasTabela));

	}
}
