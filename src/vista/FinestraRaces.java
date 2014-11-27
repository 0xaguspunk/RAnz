package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import model.ConnectorDB;
import model.ModelTaula;

/**
 * Classe que fa consultes a la BBDD i mostra la informació en 
 * la taula del JPanel corresponent
 * 
 * @author Ranz
 */
public class FinestraRaces extends JPanel {
	
	private JTable jtLaps;
	private ConnectorDB conn;
	
	/**
	 * Constructor de la classe
	 * 
	 * @param conn connexió amb la BBDD
	 */
	public FinestraRaces(ConnectorDB conn) {
		
		this.conn = conn;
		
		String columns[] = {"Session ID","Circuit","Min time","Avg time","Avg top speed","Avg average speed","Avg minimum speed"};
		
		Object[][] tableGrid;
		int aux = 0;
		int i=0;
		ResultSet consulta;
		
		consulta = conn.selectQuery("SELECT count(*) FROM races");
		try {
			while(consulta.next()) {
				aux = consulta.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		consulta = conn.selectQuery("SELECT * FROM races");
		
		tableGrid = new Object[aux][7];
		DecimalFormat df = new DecimalFormat("#.##");

		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
			while (consulta.next())
			{				
		 		tableGrid[i][0] = consulta.getObject("SessionID");
		 		tableGrid[i][1] = consulta.getString("Circuit");
		 		if((int)((consulta.getDouble("MinimumTime")-(int)(consulta.getDouble("MinimumTime")/60)*60))>9)
		 			tableGrid[i][2] = Integer.toString((int)(consulta.getDouble("MinimumTime")/60)) + ":" + Integer.toString((int)((consulta.getDouble("MinimumTime")-(int)(consulta.getDouble("MinimumTime")/60)*60)));
		 		else
		 			tableGrid[i][2] = Integer.toString((int)(consulta.getDouble("MinimumTime")/60)) + ":0" + Integer.toString((int)((consulta.getDouble("MinimumTime")-(int)(consulta.getDouble("MinimumTime")/60)*60)));
		 		if((int)((consulta.getDouble("AverageTime")-(int)(consulta.getDouble("AverageTime")/60)*60))>9)
		 			tableGrid[i][3] = Integer.toString((int)(consulta.getDouble("AverageTime")/60)) + ":" + Integer.toString((int)((consulta.getDouble("AverageTime")-(int)(consulta.getDouble("AverageTime")/60)*60)));
		 		else
		 			tableGrid[i][3] = Integer.toString((int)(consulta.getDouble("AverageTime")/60)) + ":0" + Integer.toString((int)((consulta.getDouble("AverageTime")-(int)(consulta.getDouble("AverageTime")/60)*60)));
		 		tableGrid[i][4] = df.format(consulta.getDouble("AverageTopSpeed"));
		 		tableGrid[i][5] = df.format(consulta.getDouble("AverageAverageSpeed"));
		 		tableGrid[i][6] = df.format(consulta.getDouble("AverageMinimumSpeed"));
		 					
		 		i++;
			}
		} catch (SQLException eDB) {
				System.out.println("Problema al recuperar les dades...");
		}
		
 		jtLaps = new JTable(new ModelTaula(tableGrid,columns));
 		
		add(jtLaps.getTableHeader(),BorderLayout.CENTER);
		add(jtLaps, BorderLayout.CENTER );
				
        TableColumn column = null;        
        column = jtLaps.getColumnModel().getColumn(0);
        column.setPreferredWidth(85);
        column = jtLaps.getColumnModel().getColumn(1);
        column.setPreferredWidth(74);
        column = jtLaps.getColumnModel().getColumn(2);
        column.setPreferredWidth(60);
        column = jtLaps.getColumnModel().getColumn(3);
        column.setPreferredWidth(60);
        column = jtLaps.getColumnModel().getColumn(4);
        column.setPreferredWidth(97);
        column = jtLaps.getColumnModel().getColumn(5);
        column.setPreferredWidth(114);
        column = jtLaps.getColumnModel().getColumn(6);
        column.setPreferredWidth(124);
	}
}