package model.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.vo.ContaBanco;
import model.vo.Despesa;
import model.vo.Receita;

public class ReceitaTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final int CATEGORIA = 0;
	private final int DESCRICAO = 1;
	private final int VALOR = 2;
	private final int DATARECEITA = 3;
	private final int CONTABANCO = 4;
	private List<Receita> dados;

	private final String colunas[] = { "Categoria", "Descrição", "Valor", "Data receita", "Conta" };

	public ReceitaTableModel(List<Receita> dados) {
		this.dados = dados;
	}

	public int getRowCount() {
		return dados.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
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
		case DATARECEITA:
			return LocalDate.class;
		case CONTABANCO:
			return String.class;
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Receita listaReceitas = dados.get(rowIndex);

		switch (columnIndex) {
		case CATEGORIA:
			return listaReceitas.getCategoria();
		case DESCRICAO:
			return listaReceitas.getDescricao();
		case VALOR:
			return listaReceitas.getValor();
		case DATARECEITA:
			return listaReceitas.getDataReceita();
		// case CONTABANCO:
		// return listaReceitas.get
		}
		return null;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Receita receita = dados.get(rowIndex);
		switch (columnIndex) {
		case 0:
			receita.setCategoria(String.valueOf(aValue));
		case 1:
			receita.setDescricao(String.valueOf(aValue));
		case 3:
			receita.setValor(Double.parseDouble((String) aValue));
		case 4:
			receita.setDataReceita(LocalDate.parse(String.valueOf(aValue)));
		//case 5:
		//	receita.setIdContaBanco(Integer.parseInt((String) aValue));
		}
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
		fireTableCellUpdated(rowIndex, 4);
		
	}
	// Métodos abaixo são para manipulação de dados

	/**
	 * retorna o valor da linha indicada
	 * 
	 * @param rowIndex
	 * @return
	 */
	public Receita getValue(int rowIndex) {
		return dados.get(rowIndex);
	}

	/**
	 * retorna o indice do objeto
	 * 
	 * @param receita
	 * @return
	 */
	public int indexOf(Receita receita) {
		return dados.indexOf(receita);
	}

	/**
	 * add um empregado á lista
	 * 
	 * @param empregado
	 */
	public void onAdd(Receita receita) {
		dados.add(receita);
		fireTableRowsInserted(indexOf(receita), indexOf(receita));
	}

	/**
	 * add uma lista de empregados
	 * 
	 * @param dadosIn
	 */
	public void onAddAll(List<Receita> dadosIn) {
		dados.addAll(dadosIn);
		fireTableDataChanged();
	}

	/**
	 * remove um registro da lista, através do indice
	 * 
	 * @param rowIndex
	 */
	public void onRemove(int rowIndex) {
		dados.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/**
	 * remove um registro da lista, através do objeto
	 * 
	 * @param empregado
	 */
	public void onRemove(Receita receita) {
		int indexBefore = indexOf(receita);// pega o indice antes de apagar
		dados.remove(receita);
		fireTableRowsDeleted(indexBefore, indexBefore);
	}

	/**
	 * remove todos registros da lista
	 */
	public void onRemoveAll() {
		dados.clear();
		fireTableDataChanged();
	}

}
