package model;

import java.awt.Color;

import javax.swing.table.AbstractTableModel;

/**
 * Crea i controla les taules que creem
 * 
 * @author Ranz
 */
public class ModelTaula extends AbstractTableModel {

	private String columns[] = {"Lap","Time","Top speed (Km/h)","Avg speed (Km/h)","Min speed(Km/h)","Length (m)", "Color"};
	private Object[][] dades;

	/**
	 * Constructor de la classe
	 * 
	 * @param tableGrid Matriu de la taula a crear
	 * @param columns Títol de les columnes de la taula
	 */
	public ModelTaula(Object[][] tableGrid, String columns[]) {
		this.dades = tableGrid;
		this.columns = columns;
	}

	public String getColumnName(int col) {
		return columns[col];
	}

	public int getColumnCount() {
		return columns.length;
	}

	public int getRowCount() {
		return dades.length;
	}

	public Object getValueAt(int f, int c) {
		return dades[f][c];
	}

	public Class getColumnClass(int columnIndex) {
		if (columnIndex<6) {
			return String.class;
		}
		return Color.class;
	}

}