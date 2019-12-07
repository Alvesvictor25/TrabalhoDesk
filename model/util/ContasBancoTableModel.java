package model.util;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.vo.ContaBanco;

/**
 * classe Table Model "modelo de tabela" p/entidade Empregado
 * 
 * @author Roberto Silva
 */
public class ContasBancoTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int NOMEDOBANCO = 0;
	private final int SALDODACONTA = 1;
	private final int NUMERODACONTA = 2;
	private final int STATUSDACONTA = 3;

	private final String colunas[] = { "Nome do Banco", "Saldo disponivel", "Número da conta", "Status" };
	private final List<ContaBanco> dados;

	public ContasBancoTableModel(List<ContaBanco> dados) {
		this.dados = dados;
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case NOMEDOBANCO:
			return String.class;
		case SALDODACONTA:
			return double.class;
		case NUMERODACONTA:
			return int.class;
		case STATUSDACONTA:
			return Boolean.class;
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ContaBanco conta = dados.get(rowIndex);

		switch (columnIndex) {
		case NOMEDOBANCO:
			return conta.getNomeDoBanco();
		case SALDODACONTA:
			return conta.getSaldoDaConta();
		case NUMERODACONTA:
			return conta.getNumeroConta();
		case STATUSDACONTA:
			return conta.isStatusDaConta();
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == STATUSDACONTA)
			return true;

		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ContaBanco conta = dados.get(rowIndex);

		if (columnIndex == STATUSDACONTA) {
			conta.setStatusDaConta((Boolean) aValue);
		}
	}

	public ContaBanco getValue(int rowIndex) {
		return dados.get(rowIndex);
	}

	public int indexOf(ContaBanco conta) {
		return dados.indexOf(conta);
	}

	public void onAdd(ContaBanco conta) {
		dados.add(conta);
		fireTableRowsInserted(indexOf(conta), indexOf(conta));
	}

	public void onAddAll(List<ContaBanco> dadosIn) {
		dados.addAll(dadosIn);
		fireTableDataChanged();
	}

	public void onRemove(int rowIndex) {
		dados.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void onRemove(ContaBanco conta) {
		int indexBefore = indexOf(conta);
		dados.remove(conta);
		fireTableRowsDeleted(indexBefore, indexBefore);
	}

	public void onRemoveAll() {
		dados.clear();
		fireTableDataChanged();
	}

}