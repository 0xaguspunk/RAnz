package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import model.Lap;
import model.Manage_Info;
import model.ModelTaula;
import model.Race;
import controlador.ListenerThird;

/**
 * Classe que mostra totes les estadístiques de les voltes en una taula.
 * Tant individualment com totes alhora
 * 
 * @author Ranz
 */
public class FinestraStatistics extends JPanel{
	
	private JTable jtLaps;
	private Race race; 
	private ArrayList<Lap> laps;
	private Color color;
	
	/**
	 * Constructor de la classe
	 * 
	 * @param manageInfo tota la informació de voltes i estadístiques
	 * @param meta punt que hem marcat com a meta
	 */
	public FinestraStatistics(Manage_Info manageInfo,int meta){	
		
		//Those are column titles
		String columns[] = {"Lap","Time","Top speed (Km/h)","Avg speed (Km/h)","Min speed(Km/h)","Length (m)", "Color"};
		
		//Table will be [aux][7]
		Object[][] tableGrid;
		int aux = 0;
		int i;	
		//We verify how many laps we have. aux = laps + 2 (average and total). 
		//Funció per mirar el size de laps
		
		race = manageInfo.getSeason().getRaces().get(0);
		laps = race.getLaps();
		aux = laps.size();
						
		//We ask for memory for the table
		tableGrid = new Object[aux+2][7];
		color = new Color(0,0,0);
		
		DecimalFormat df = new DecimalFormat("#.##");
				
	 	for(i=0;i<aux;i++){
	 		tableGrid[i][0] = Integer.toString(i+1);
	 		if(laps.get(i).getSecLapTime()>9)
	 			tableGrid[i][1] = (Integer.toString(laps.get(i).getMinLapTime()) + ":" + Integer.toString(laps.get(i).getSecLapTime()));
	 		else 
	 			tableGrid[i][1] = Integer.toString(laps.get(i).getMinLapTime()) + ":0" + Integer.toString(laps.get(i).getSecLapTime());
	 		tableGrid[i][2] = df.format(laps.get(i).getMaxLapSpeed());
	 		tableGrid[i][3] = df.format(laps.get(i).getAverageLapSpeed());
	 		tableGrid[i][4] = df.format(laps.get(i).getMinLapSpeed());
	 		tableGrid[i][5] = Integer.toString((int)(laps.get(i).getDistance()*1000));
	 		if(i==0) color = new Color(0,0,255);
	 		if(i==1) color = new Color(0,255,255);
	 		if(i==2) color = new Color(128,128,128);
	 		if(i==3) color = new Color(0,255,0);
	 		if(i==4) color = new Color(192,192,192);
	 		if(i==5) color = new Color(255,0,127);
	 		if(i==6) color = new Color(255,128,0);
	 		if(i==7) color = new Color(255,153,153);
	 		if(i==8) color = new Color(255,0,0);
	 		if(i==9) color = new Color(255,255,0);
	 		if(i==10) color = new Color(255,255,255);
	 		if(i==11) color = new Color(255,128,0);
	 		if(i==12) color = new Color(0,255,255);
	 		if(i==13) color = new Color(255,0,0);
	 		if(i==14) color = new Color(0,0,255);
	 		if(i==15) color = new Color(0,255,255);
	 		if(i==16) color = new Color(128,128,128);
	 		if(i==17) color = new Color(0,255,0);
	 		if(i==18) color = new Color(192,192,192);
	 		if(i==19) color = new Color(255,0,127);
	 		if(i==20) color = new Color(255,128,0);
	 		if(i==21) color = new Color(255,153,153);
	 		if(i==22) color = new Color(255,0,0);
	 		if(i==23) color = new Color(255,255,0);
	 		if(i==24) color = new Color(255,255,255);
	 		if(i==25) color = new Color(255,128,0);
	 		if(i==26) color = new Color(0,255,255);
	 		if(i==27) color = new Color(255,0,0);
	 		tableGrid[i][6] = color;
	 	}
	 	
 		tableGrid[aux][0] = "AVERAGE";
 		if(race.getAverageTimeRaceSec()>9)
 			tableGrid[aux][1] = Integer.toString(race.getAverageTimeRaceMin()) + ":" + Integer.toString(race.getAverageTimeRaceSec()-(int)(race.getAverageTimeRaceSec()/60)*60);
 		else 
 			tableGrid[aux][1] = Integer.toString(race.getAverageTimeRaceMin()) + ":0" + Integer.toString(race.getAverageTimeRaceSec()-(int)(race.getAverageTimeRaceSec()/60)*60);
 		tableGrid[aux][2] = df.format(race.getAverageMaxSpeed());
 		tableGrid[aux][3] = df.format(race.getAverageSpeedRace());
 		tableGrid[aux][4] = df.format(race.getAverageMinSpeed());
 		tableGrid[aux][5] = df.format((int)(race.getAverageLength()*1000));
 		tableGrid[aux][6] = Color.WHITE;
 		
 		tableGrid[aux+1][0] = "TOTAL";
 		if(race.getTotalTimeSec()>9)
 			tableGrid[aux+1][1] = Integer.toString(race.getTotalTimeMin()) + ":" + Integer.toString(race.getTotalTimeSec());
 		else 
 			tableGrid[aux+1][1] = Integer.toString(race.getTotalTimeMin()) + ":0" + Integer.toString(race.getTotalTimeSec());
 		tableGrid[aux+1][2] = "---";
 		tableGrid[aux+1][3] = "---";
 		tableGrid[aux+1][4] = "---";
 		tableGrid[aux+1][5] = Double.toString((int)(race.getTotalLength()*1000));
 		tableGrid[aux+1][6] = Color.WHITE;
		
 		jtLaps = new JTable(new ModelTaula(tableGrid, columns));
 		jtLaps.setDefaultRenderer(Color.class, new ColorRenderer(false)); 		
 		
		add(jtLaps.getTableHeader(),BorderLayout.CENTER);
		add(jtLaps, BorderLayout.CENTER );
        
        TableColumn column = null;        
        column = jtLaps.getColumnModel().getColumn(0);
        column.setPreferredWidth(65);
        column = jtLaps.getColumnModel().getColumn(1);
        column.setPreferredWidth(50);
        column = jtLaps.getColumnModel().getColumn(2);
        column.setPreferredWidth(115);
        column = jtLaps.getColumnModel().getColumn(3);
        column.setPreferredWidth(115);
        column = jtLaps.getColumnModel().getColumn(4);
        column.setPreferredWidth(115);
        column = jtLaps.getColumnModel().getColumn(5);
        column.setPreferredWidth(88);
        column = jtLaps.getColumnModel().getColumn(6);
        column.setPreferredWidth(60);       
   	
	}
		
	public JTable getTable () {
		return jtLaps;
	}
	
    /** 
     * This is needed in order to control the buttons actions with the controller. 
     */
	public void registreControladorStatistics(ListenerThird controladorThird) {
		jtLaps.addMouseListener(controladorThird);
	}
}