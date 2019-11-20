package view2;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControladoraDespesa;
import controller.ControladoraReceita;
import model.vo.DespesaVO;
import model.vo.ReceitaVO;
import model.vo.UsuarioVO;

public class JPreceita extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jpDespesas;
	private JTable jTable;
	private JPanel jpReceita;
	private ArrayList<ReceitaVO> listaReceita;
	private ArrayList<DespesaVO> listaDespesa;
	private String[] colunasTabelaDespesa = { "Categoria", "Descrição", "Vencimento", "Pagamento", "Valor" };
	private String[] colunasTabelaReceita = { "Descricao", "Descrição", "Vencimento", "Pagamento", "Valor" };
	private JTabbedPane jtbReceita;
	private JPanel jpDespesa;
	private JTable jTableDespesa;
	private UsuarioVO usuarioJPreceita;

	/**
	 * Create the panel.
	 * 
	 * @param usuario
	 */
	public JPreceita(UsuarioVO usuarioLogin) {
		usuarioJPreceita = usuarioLogin;
		setLayout(new BorderLayout(0, 0));
		criaTela();
		atualizar();
		atualizarDespesa();
	}

	private void criaTela() {
		jpDespesas = new JPanel();
		jpDespesas.setLayout(null);
		jpDespesas.setBorder(BorderFactory.createTitledBorder("Ultimos lançamentos"));
		jpDespesas.setBounds(20, 60, 650, 200);
		add(jpDespesas);

		jtbReceita = new JTabbedPane();
		jtbReceita.setBounds(10, 38, 596, 146);
		jpDespesas.add(jtbReceita);

		jpReceita = new JPanel();
		jpReceita.setLayout(null);
		jpReceita.setBounds(20, 20, 250, 170);
		jtbReceita.add("Receita", jpReceita);

		jpDespesa = new JPanel();
		jpDespesa.setLayout(null);
		jpDespesa.setBounds(20, 20, 250, 170);
		jtbReceita.add("Despesa", jpDespesa);

		jTable = new JTable();
		jTable.setLayout(null);
		jTable.setBounds(10, 11, 540, 105);
		jpDespesa.add(jTable);

		jTableDespesa = new JTable();
		jTableDespesa.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		jTableDespesa.setLayout(null);
		jTableDespesa.setBounds(10, 11, 540, 105);
		jpReceita.add(jTableDespesa);

	}

	private void atualizarDespesa() {
		ControladoraDespesa despesa = new ControladoraDespesa();
		listaDespesa = despesa.consultarTodasDespesasController();

		limparTela();

		DefaultTableModel model = (DefaultTableModel) jTable.getModel();

		for (DespesaVO receit : listaDespesa) {
			String[] novaLinha = new String[5];
			novaLinha[0] = receit.getDescricao();
			novaLinha[1] = receit.getCategoria();
			novaLinha[2] = String.valueOf(receit.getValor());
			novaLinha[3] = String.valueOf(receit.getDataPagamento());
			novaLinha[4] = String.valueOf(receit.getDataVencimento());

			model.addRow(novaLinha);
		}

	}

	private void atualizar() {

		ControladoraReceita receita = new ControladoraReceita();
		listaReceita = receita.consultarTodasReceitasController();

		limparTelaReceita();

		DefaultTableModel model = (DefaultTableModel) jTable.getModel();

		for (ReceitaVO receit : listaReceita) {
			String[] novaLinha = new String[5];
			novaLinha[0] = receit.getDescricao();
			novaLinha[1] = receit.getDescricao();
			novaLinha[2] = receit.getDescricao();
			novaLinha[3] = receit.getDescricao();
			novaLinha[4] = receit.getDescricao();

			model.addRow(novaLinha);
		}

	}

	private void limparTelaReceita() {
		jTable.setModel(new DefaultTableModel(new Object[][] { colunasTabelaReceita }, colunasTabelaReceita));
		
	}

	private void limparTela() {
		jTable.setModel(new DefaultTableModel(new Object[][] { colunasTabelaReceita }, colunasTabelaReceita));

	} // TODO Auto-generated method stub
}
