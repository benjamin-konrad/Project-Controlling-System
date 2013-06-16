package edu.hm.cs.swe2.pcs.cvsgen;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class TableModelData extends DefaultTableModel{
	private static final long serialVersionUID = 1L;

	private Class[] columnTypes;

	private boolean[] columnEditables;

	private List<TableRowEntry> entries;
	
	private TableRowEntry selectedRowEntry;
	

	public TableRowEntry getSelectedRowEntry() {
		return selectedRowEntry;
	}

	public List<TableRowEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<TableRowEntry> entries) {
		this.entries = entries;
	}

	public TableModelData(Object[][] arg0, Object[] arg1, Class[] columnTypes, boolean[] columnEditables, List<TableRowEntry> entries) {
		super(arg0, arg1);
		this.columnTypes = columnTypes;
		this.columnEditables = columnEditables;
		this.entries = entries;
	}
	
	public Class getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}
	
	public boolean isCellEditable(int row, int column) {
		return columnEditables[column];
	}

	@Override
	public Object getValueAt(int x, int y) {
		TableRowEntry entry = entries.get(x);
		Object value = entry.toArray()[y];
		if(y == 8)
			value = ((Boolean)value)? "ja":"nein";
		return value;
		
	}
	
	public void addEntry(TableRowEntry entry){
		entries.add(entry);
		this.fireTableDataChanged();
	}
	
	public TableRowEntry getEntry(int index){
		return entries.get(index);
	}

	@Override
	public int getRowCount() {
		if(entries == null)
			return 0;
		else
			return entries.size();
	}

	@Override
	public int getColumnCount() {
		return 11;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
		selectedRowEntry = entries.get(row);
	}
	
	
	
	
}