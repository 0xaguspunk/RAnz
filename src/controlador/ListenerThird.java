package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTable;

import model.Manage_Info;
import model.POST_InfoGPS;

import vista.FinestraCircuit;
import vista.FinestraPlot;
import vista.FinestraStatistics;
import vista.FinestraThird;

/**
 * Listener encarregat de controlar els events de la tercera Card
 * la que conté 4 JPanels amb el circuit, estadístiques, plot i 
 * informació de les sessions(BBDD)
 * 
 * @author Ranz
 */
public class ListenerThird implements MouseListener{
	
	private ArrayList<POST_InfoGPS> post;
	private FinestraThird finestraThird;
	private FinestraStatistics finestraStatistics;
	private FinestraCircuit finestraCircuit;
	private FinestraPlot finestraPlot;
	private Manage_Info manageInfo;
	private JTable jtLaps;
	private int iNear;
	private boolean ctrl;
	private int row, auxRow,iWasHere;
	private boolean []lapsToPaint;
	
	/**
	 * Constructor de la classe
	 * 
	 * @param finestraThird JPanel de la tercera Card
	 * @param finestraStatistics Jpanel amb la taula d'estadístiques
	 * @param manageInfo tota la informació de voltes i estadístiques
	 * @param iNear informació del punt on cliquem
	 * @param finestraCircuit JPanel on es dibuixa el circuit
	 * @param finestraPlot JPanel on es dibuixa el plot
	 */
	public ListenerThird(FinestraThird finestraThird, FinestraStatistics finestraStatistics, Manage_Info manageInfo, int iNear, FinestraCircuit finestraCircuit, FinestraPlot finestraPlot) {
		this.finestraThird = finestraThird;
		this.finestraStatistics = finestraStatistics;
		this.manageInfo = manageInfo;
		this.finestraCircuit = finestraCircuit;
		this.finestraPlot = finestraPlot;
		jtLaps = new JTable();
		this.iNear = iNear;
		this.lapsToPaint = new boolean[manageInfo.getSeason().getRaces().get(0).getLaps().size()+2];
		for(int i=0;i<manageInfo.getSeason().getRaces().get(0).getLaps().size()+2;i++) 
			this.lapsToPaint[i]=false;
		this.iWasHere = 0;
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

		ctrl = e.isControlDown();
		jtLaps = finestraStatistics.getTable();
	    row = jtLaps.rowAtPoint(e.getPoint());
	    int col = jtLaps.columnAtPoint(e.getPoint());
	    
		post = manageInfo.getArrayPOST();
		manageInfo.Convert_POST(500,300);
		
		manageInfo.Convert_Race(iNear);
		
		if (ctrl == false && iWasHere != 0) {
			for(int i=0;i<manageInfo.getSeason().getRaces().get(0).getLaps().size()+2;i++) 
				this.lapsToPaint[i]=false;
			finestraCircuit.repaint();
			finestraPlot.repaint();
			iWasHere=0;
		}
		
		if (ctrl == false) {
			if(row != manageInfo.getSeason().getRaces().get(0).getLaps().size()){
				lapsToPaint[row]=true;
				finestraPlot.setLap(row,lapsToPaint);
				finestraCircuit.setPointsPaint(row, manageInfo,lapsToPaint);
				auxRow = row;	
			}
		}
		
	
		if (ctrl == true) {	
			if(iWasHere==0) lapsToPaint[auxRow] = true;
			if(lapsToPaint[row] == true) lapsToPaint[row] = false;
			else lapsToPaint[row] = true;
			finestraPlot.setLap(row,lapsToPaint);
			finestraCircuit.setPointsPaint(row, manageInfo,lapsToPaint);
			iWasHere++;
		}
		
	}

	/**
	 * Controla l'event del mouse quan es deixa de premer el botó
	 * 
	 * @param e Event controlat pel MouseListener
	 */
	public void mouseReleased(MouseEvent e) {
		
		ctrl = e.isControlDown();
		
		if (ctrl == false && iWasHere == 0){
			for(int i=0;i<manageInfo.getSeason().getRaces().get(0).getLaps().size()+2;i++) 
				this.lapsToPaint[i]=false;
		}
		
	}
	
	/**
	 * Indica les voltes que s'hauran de pintar
	 * 
	 * @param lapsToPaint array de booleans
	 */
	public void setlapsToPaint(boolean[] lapsToPaint){
		this.lapsToPaint = lapsToPaint;
		
	}
}