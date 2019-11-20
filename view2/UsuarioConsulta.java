package view2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControladoraDespesa;
import controller.ControladoraReceita;
import controller.ControladoraUsuario;
import model.seletor.DespesaSeletor;
import model.seletor.ReceitaSeletor;
import model.seletor.Seletor;
import model.vo.DespesaVO;
import model.vo.ReceitaVO;
import model.vo.UsuarioVO;

public class UsuarioConsulta extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TIPO_LANCAMENTO_RECEITA = "Receita";
	private static final String TIPO_LANCAMENTO_DESPESA = "Despesa";
	private static final String TIPO_LANCAMENTO_SELECIONAR = "-- Selecionar Lançamento --";

	private JTable tableReceita;
	ArrayList<UsuarioVO> usuarios;
	private JComboBox cbNome;
	private JComboBox cbTiposLancamento;
	private String[] tiposLancamento = { TIPO_LANCAMENTO_SELECIONAR, TIPO_LANCAMENTO_RECEITA, TIPO_LANCAMENTO_DESPESA };
	private JTable tableDespesa;
	private String[] colunasReceitas = { "ID Receita", "Nome", "Descrição", "Data receita", "Valor" };
	private String[] colunasDespesas = { "ID Despesa", "Nome", "Descrição", "Data vencimento", "Data pagamento", "Valor" };

	/**
	 * Create the panel.
	 */
	public UsuarioConsulta() {
		setLayout(null);

		tableReceita = new JTable();
		tableReceita.setVisible(false);
		tableReceita.setBounds(10, 102, 570, 179);
		add(tableReceita);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(75, 311, 89, 23);
		add(btnExcluir);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(432, 294, 89, 23);
		add(btnEditar);

		consultarNome();
		cbNome = new JComboBox(usuarios.toArray());
		cbNome.setBounds(54, 38, 211, 23);
		cbNome.setSelectedIndex(0);
		add(cbNome);

		cbTiposLancamento = new JComboBox(tiposLancamento);
		cbTiposLancamento.setBounds(398, 38, 212, 23);
		cbTiposLancamento.setSelectedIndex(0);
		add(cbTiposLancamento);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ControladoraDespesa controllerDespesa = new ControladoraDespesa();
				ControladoraReceita controllerReceita = new ControladoraReceita();

				if(tableDespesa.isVisible()) {
					tableDespesa.setVisible(false);
				} 
				if(tableReceita.isVisible()) {
					tableReceita.setVisible(false);
				}
				
				UsuarioVO nomeSelecionado = (UsuarioVO) cbNome.getSelectedItem();
				String lancamentoSelecionado = (String) cbTiposLancamento.getSelectedItem();
				if (lancamentoSelecionado.equals(TIPO_LANCAMENTO_RECEITA)) {
					tableReceita.setVisible(true);
					ReceitaSeletor seletor = new ReceitaSeletor();
					seletor.setLimite(10);
					seletor.setUsuario(nomeSelecionado);
					ArrayList<ReceitaVO> receitas = controllerReceita.consultarReceitas(seletor);
					atualizarTabelaReceita(receitas);
				} else if (lancamentoSelecionado.equals(TIPO_LANCAMENTO_DESPESA)) {
					tableDespesa.setVisible(true);
					DespesaSeletor seletor = new DespesaSeletor();
					seletor.setLimite(10);
					seletor.setUsuario(nomeSelecionado);
					ArrayList<DespesaVO> despesas = controllerDespesa.consultarDespesa(seletor);
					atualizarTabelaDespesa(despesas);

				}

			}
		});
		btnConsultar.setBounds(278, 68, 89, 23);
		add(btnConsultar);

		tableDespesa = new JTable();
		tableDespesa.setBounds(10, 102, 570, 179);
		tableDespesa.setVisible(false);
		add(tableDespesa);

	}

	protected void atualizarTabelaDespesa(ArrayList<DespesaVO> despesas) {

		limparTelaDespesa();

	}

	private void limparTelaDespesa() {
		tableDespesa.setModel(new DefaultTableModel(new Object[][] { colunasDespesas, }, colunasDespesas));
		
	}

	protected void atualizarTabelaReceita(ArrayList<ReceitaVO> receitas) {
		// TODO Auto-generated method stub

	}

	private void consultarNome() {
		ControladoraUsuario controller = new ControladoraUsuario();

		UsuarioVO usuarioVazio = new UsuarioVO();
		usuarioVazio.setNome("-- Selecionar Nome --");

		usuarios = controller.consultarTodosUsuariosController();
		usuarios.add(0, usuarioVazio);

	}

	private void selecionarCategoria() {

//		tipoDeCategorias.add(ReceitaVO);

	}
}
