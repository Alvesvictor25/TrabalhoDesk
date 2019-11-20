package view2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.ControladoraDespesa;
import model.vo.DespesaVO;
import model.vo.ReceitaVO;
import model.vo.UsuarioVO;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;

public class JPprincipal extends JPanel {
	private JTable table;
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String[] colunas = { "Categoria", "Descrição", "Data Receita", "ID", "Valor" };
	private JTable table_1;
	private UsuarioVO usuarioJPprincipal;

	/**
	 * Create the panel.
	 * @param usuario 
	 */
	public JPprincipal(UsuarioVO usuarioLogin) {
		usuarioJPprincipal = usuarioLogin;
		setLayout(null);

		JLabel lblSaldoEmContas = new JLabel("Saldo em contas");
		lblSaldoEmContas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldoEmContas.setBounds(10, 11, 145, 30);
		add(lblSaldoEmContas);

		JLabel lblReceitas = new JLabel("Receitas");
		lblReceitas.setBounds(10, 52, 83, 14);
		add(lblReceitas);

		JLabel lblDespesas = new JLabel("Despesas");
		lblDespesas.setBounds(103, 52, 76, 14);
		add(lblDespesas);

		JLabel lblR = new JLabel("R$ 2000.00");
		lblR.setBounds(10, 77, 83, 19);
		add(lblR);

		JLabel label = new JLabel("R$ 2000.00");
		label.setBounds(96, 77, 83, 19);
		add(label);

		JLabel lblBalanoMensal = new JLabel("Balanço mensal");
		lblBalanoMensal.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalanoMensal.setBounds(10, 131, 174, 24);
		add(lblBalanoMensal);

		JLabel lblReceitas_1 = new JLabel("Receitas");
		lblReceitas_1.setBounds(103, 223, 48, 14);
		add(lblReceitas_1);

		JLabel label_1 = new JLabel("R$ 2000.00");
		label_1.setBounds(161, 221, 83, 19);
		add(label_1);

		JLabel label_2 = new JLabel("Receitas");
		label_2.setBounds(103, 248, 83, 14);
		add(label_2);

		JLabel label_3 = new JLabel("R$ 2000.00");
		label_3.setBounds(171, 251, 83, 19);
		add(label_3);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(64, 203, 14, 76);
		lblNewLabel.setOpaque(true);
		add(lblNewLabel);

		JLabel label_4 = new JLabel("");
		label_4.setOpaque(true);
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.GREEN);
		label_4.setBounds(34, 223, 14, 49);
		add(label_4);

		JLabel lblMinhasContas = new JLabel("Minhas contas");
		lblMinhasContas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinhasContas.setBounds(335, 38, 145, 19);
		add(lblMinhasContas);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(350, 163, 48, 14);
		add(lblTotal);

		JLabel label_5 = new JLabel("R$ 2000.00");
		label_5.setBounds(437, 159, 83, 19);
		add(label_5);

		table = new JTable();
		table.setBounds(286, 79, 279, 76);
		add(table);

		JLabel lblEconomia = new JLabel("Economia ");
		lblEconomia.setHorizontalAlignment(SwingConstants.CENTER);
		lblEconomia.setBounds(410, 237, 155, 19);
		add(lblEconomia);

		JButton btnDetalhar = new JButton("Detalhar");
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDetalhar.setBounds(437, 281, 89, 23);
		add(btnDetalhar);

		JLabel lblTextosAqui = new JLabel("Textos aqui ");
		lblTextosAqui.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextosAqui.setBounds(396, 320, 191, 30);
		add(lblTextosAqui);

		JButton btnOcultar = new JButton("Ocultar");
		btnOcultar.setBounds(708, 367, 89, 23);
		add(btnOcultar);

		JButton btnAdicionarNovaConta = new JButton("Adicionar nova conta");
		btnAdicionarNovaConta.setBounds(636, 176, 186, 19);
		add(btnAdicionarNovaConta);

		JLabel lblDescricao = new JLabel("Descricao");
		lblDescricao.setBounds(602, 223, 48, 14);
		add(lblDescricao);

		JLabel lblSaldoAtual = new JLabel("Saldo atual");
		lblSaldoAtual.setBounds(602, 260, 83, 19);
		add(lblSaldoAtual);

		JLabel lblTipoDeConta = new JLabel("Tipo de conta");
		lblTipoDeConta.setBounds(602, 290, 83, 17);
		add(lblTipoDeConta);

		JButton btnDetalhar_1 = new JButton("Detalhar");
		btnDetalhar_1.setBounds(96, 281, 89, 23);
		add(btnDetalhar_1);

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
	}

}
