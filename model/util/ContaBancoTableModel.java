package model.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.vo.ContaBanco;

public class ContaBancoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private final int NOMEDOBANCO = 0;
	private final int AGENCIA = 1;
	private final int NUMEROCONTA = 2;
	private final int SALDODACONTA = 3;

	private final String colunas[] = { "Nome do Banco", "Saldo", "Agência", "Número da conta" };
	private List<ContaBanco> dados;

	public ContaBancoTableModel(List<ContaBanco> dados) {
		this.dados = dados;
	}

	public int getRowCount() {
		return dados.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case NOMEDOBANCO:
			return String.class;
		case SALDODACONTA:
			return double.class;
		case AGENCIA:
			return int.class;
		case NUMEROCONTA:
			return int.class;
		default:
			throw new IndexOutOfBoundsException("Coluna inválida!!!");

		}
	}

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
		case AGENCIA:
			return conta.getAgencia();
		case NUMEROCONTA:
			return conta.getNomeDoBanco();
		default:
			throw new IndexOutOfBoundsException("Coluna inválida!!!");

		}
	}
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ContaBanco conta =dados.get(rowIndex);
 

    
    }
    public void onAddAll(List<ContaBanco> dadosIn) {
        dados.addAll(dadosIn);
        fireTableDataChanged();
    }

}
