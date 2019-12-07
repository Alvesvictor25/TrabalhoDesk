package model.util;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.vo.Despesa;

public class DespesaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private final int CATEGORIA = 0;
	private final int DESCRICAO = 1;
	private final int VALOR = 2;
	private final int DATAPAGAMENTO = 3;
	private final int DATAVENCIMENTO = 4;

	private final String colunas[] = { "Categoria", "Descrição", "Valor", "Data Pagamento", "Data Vencimento" };
	private final List<Despesa> dados;

	public DespesaTableModel(List<Despesa> dados) {
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
		case CATEGORIA:
			return String.class;
		case DESCRICAO:
			return String.class;
		case VALOR:
			return double.class;
		case DATAPAGAMENTO:
			return LocalDate.class;
		case DATAVENCIMENTO:
			return LocalDate.class;
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Despesa listaDespesas = dados.get(rowIndex);
		switch (columnIndex) {
		case CATEGORIA:
			return listaDespesas.getCategoria();
		case DESCRICAO:
			return listaDespesas.getDescricao();
		case VALOR:
			return listaDespesas.getValor();
		case DATAPAGAMENTO:
			return listaDespesas.getDataVencimento();
		case DATAVENCIMENTO:
			return listaDespesas.getDataPagamento();

		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Despesa despesa = dados.get(rowIndex);
		switch (columnIndex) {
		case 0:
			despesa.setCategoria(String.valueOf(aValue));
		case 1:
			despesa.setDescricao(String.valueOf(aValue));
		case 3:
			despesa.setValor(Double.parseDouble((String) aValue));
		case 4:
			despesa.setDataPagamento(LocalDate.parse(String.valueOf(aValue)));
		case 5:
			despesa.setDataVencimento(LocalDate.parse(String.valueOf(aValue)));
		}
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
		fireTableCellUpdated(rowIndex, 4);
		
	}

	public Despesa getValue(int rowIndex) {
		return dados.get(rowIndex);
	}

	public int indexOf(Despesa despesa) {
		return dados.indexOf(despesa);
	}

	public void onAdd(Despesa despesa) {
		dados.add(despesa);
		fireTableRowsInserted(indexOf(despesa), indexOf(despesa));
	}

	public void onAddAll(List<Despesa> dadosIn) {
		dados.addAll(dadosIn);
		fireTableDataChanged();
	}

	public void onRemove(int rowIndex) {
		dados.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void onRemove(Despesa despesa) {
		int indexBefore = indexOf(despesa);
		dados.remove(despesa);
		fireTableRowsDeleted(indexBefore, indexBefore);
	}

	public void onRemoveAll() {
		dados.clear();
		fireTableDataChanged();
	}

}