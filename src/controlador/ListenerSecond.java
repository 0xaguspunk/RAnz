package controlador;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ConnectorDB;
import model.Manage_Info;
import model.POST_InfoGPS;
import model.Race;
import vista.FinestraCircuit;
import vista.FinestraMain;
import vista.FinestraPlot;
import vista.FinestraRaces;
import vista.FinestraSecond;
import vista.FinestraStatistics;
import vista.FinestraThird;

/**
 * Listener encarregat de controlar els events de la segona Card
 * que conté la finestra del circuit
 * 
 * @author Ranz
 */
public class ListenerSecond implements MouseListener {
	
	private ArrayList<POST_InfoGPS> post;
	private FinestraMain finestraPrincipal;
	private Manage_Info manageInfo;
	private FinestraSecond finestraSecond;
	private FinestraCircuit finestraCircuit;
	private FinestraThird finestraThird;
	private FinestraStatistics finestraStatistics;
	private FinestraPlot finestraPlot;
	private ListenerThird controladorThird;
	private FinestraRaces finestraRaces;
	private boolean pressed;
	private ConnectorDB conn;
	
	/**
	 * Constructor de la classe
	 * 
	 * @param manageInfo tota la informació de voltes i estadístiques
	 * @param finestraSecond JPanel de la segona Card
	 * @param finestraCircuit JPanel on es dibuixa el circuit
	 * @param finestraPrincipal classe del JFrame principal
	 * @param conn conexió a la Bases de dades
	 */
	public ListenerSecond(Manage_Info manageInfo, FinestraSecond finestraSecond, FinestraCircuit finestraCircuit, FinestraMain finestraPrincipal, ConnectorDB conn) {
		this.manageInfo = manageInfo;
		this.finestraSecond = finestraSecond;
		this.finestraCircuit = finestraCircuit;
		this.finestraPrincipal = finestraPrincipal;
		pressed = false;
		this.conn = conn;
	}	

	/**
	 * Controla l'event sencer de premer i deixar anar el botó del mouse
	 * 
	 * @param e Event controlat pel MouseListener
	 */
	public void mouseClicked(MouseEvent e) {
					
	}

	/**
	 * Controla l'event del mouse quan se centra en algun element
	 * 
	 * @param e Event controlat pel MouseListener
	 */
	public void mouseEntered(MouseEvent e) {
		
	}

	/**
	 * Controla l'event del mouse quan se surt d'algun element
	 * 
	 * @param e Event controlat pel MouseListener
	 */
	public void mouseExited(MouseEvent e) {
		
	}

	/**
	 * Controla l'event del mouse quan es prem el botó
	 * 
	 * @param e Event controlat pel MouseListener
	 */
	public void mousePressed(MouseEvent e) {
		
		if (pressed == false){
			int x = e.getX();
			int y = e.getY();
			post = manageInfo.getArrayPOST();
			manageInfo.Convert_POST(500,300);
			int iNear=0;
			int minDis = 1000;
			int aux=0;
			int i=0;

			for(i=0;i<350;i++)
			{
				aux = (int)(((post.get(i).getCoordY()-y)*(post.get(i).getCoordY()-y)) + ((post.get(i).getCoordX()-x)*(post.get(i).getCoordX()-x)));
				if(minDis >= Math.sqrt(aux)){
					iNear = i;
					minDis = (int)Math.sqrt(aux);			
				}
			}
					
	    	finestraThird = new FinestraThird();
	    	finestraThird.setLayout(new GridLayout(2,2));
	    	manageInfo.Convert_Race(iNear);
	    	finestraStatistics = new FinestraStatistics(manageInfo, iNear);
	    	finestraCircuit = new FinestraCircuit(manageInfo.getArrayPOST(), iNear);
	    	finestraCircuit.setFirstPoints(-1, manageInfo);
	    	finestraCircuit.setScaled(true);
	    	finestraPlot = new FinestraPlot(manageInfo);
	    	
	    	finestraPrincipal.getContentPane().add(finestraThird, "Third");
	    	finestraThird.setCircuit(finestraCircuit);
	    	finestraThird.setStatistics(finestraStatistics);	    		    	
	    	finestraThird.setPlot(finestraPlot);
	    		    	
	    	controladorThird = new ListenerThird(finestraThird, finestraStatistics, manageInfo, iNear, finestraCircuit, finestraPlot);
	    	finestraThird.registreControladorThird(controladorThird);
	    	finestraStatistics.registreControladorStatistics(controladorThird);
	    	finestraPrincipal.showCard("Third");
	    	
	    	Race race = new Race();
	    	race = manageInfo.getSeason().getRaces().get(0);
	    	
			ResultSet consulta;
			//Inserim un registre a la BBDD
			conn.insertQuery("INSERT INTO races (SessionID,Circuit,MinimumTime,AverageTime, AverageTopSpeed, AverageAverageSpeed, AverageMinimumSpeed) VALUES ('"+race.getID()+"','"+race.getCircuitName()+"','"+race.getTminRace()+"','"+race.getAverageTimeRaceSec()+"','"+race.getAverageMaxSpeed()+"','"+race.getAverageSpeedRace()+"','"+race.getAverageMinSpeed()+"')");

			finestraRaces = new FinestraRaces(conn);
			finestraThird.setRaces(finestraRaces);
		}
		pressed = true;
	}

	/**
	 * Controla l'event del mouse quan es deixa de premer el botó
	 * 
	 * @param e Event controlat pel MouseListener
	 */
	public void mouseReleased(MouseEvent e) {
		
	}
}