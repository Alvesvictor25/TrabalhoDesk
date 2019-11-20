package model.util;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.vo.DespesaVO;
 
public class DespesaTableModel extends AbstractTableModel{
 
	private static final long serialVersionUID = 1L;
	
    private final int CATEGORIA=0;
    private final int DESCRICAO=1;
    private final int VALOR=2;
    private final int DATAPAGAMENTO=3;
    private final int DATAVENCIMENTO=4;
 
    private final String colunas[]={"Categoria:","Descrição:","Valor:","Data Pagamento:","Data Vencimento:"};
    private final List<DespesaVO> dados;
 
    public DespesaTableModel(List<DespesaVO> dados) {
        this.dados=dados;
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
        DespesaVO listaDespesas =dados.get(rowIndex);
 
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
        DespesaVO despesa =dados.get(rowIndex);
 

    }
 
    //Métodos abaixo são para manipulação de dados
 
    /**
     * retorna o valor da linha indicada
     * @param rowIndex
     * @return
     */
    public DespesaVO getValue(int rowIndex){
        return dados.get(rowIndex);
    }
 
    /**
     * retorna o indice do objeto
     * @param empregado
     * @return
     */
    public int indexOf(DespesaVO empregado) {
        return dados.indexOf(empregado);
    }
 
    /**
     * add um empregado á lista
     * @param empregado
     */
    public void onAdd(DespesaVO empregado) {
        dados.add(empregado);
        fireTableRowsInserted(indexOf(empregado), indexOf(empregado));
    }
 
    /**
     * add uma lista de empregados
     * @param dadosIn
     */
    public void onAddAll(List<DespesaVO> dadosIn) {
        dados.addAll(dadosIn);
        fireTableDataChanged();
    }
 
    /**
     * remove um registro da lista, através do indice
     * @param rowIndex
     */
    public void onRemove(int rowIndex) {
        dados.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
 
    /**
     * remove um registro da lista, através do objeto
     * @param empregado
     */
    public void onRemove(DespesaVO empregado) {
        int indexBefore=indexOf(empregado);//pega o indice antes de apagar
        dados.remove(empregado);  
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