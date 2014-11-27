package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Manage_Info;
import controlador.ListenerSecond;
import controlador.ListenerThird;

/**
 * Tercera card que conté les classes de circuit, estadístiques,
 * plot i la taula amb la informació de la BBDD
 * 
 * @author Ranz
 */
public class FinestraThird extends JPanel {

	private FinestraStatistics finestraStatistics;
	private FinestraCircuit finestraCircuit;
	private FinestraPlot finestraPlot;
	private FinestraRaces finestraRaces;
	private ListenerSecond controladorSecond;
	private Manage_Info manageInfo;	
	
	/**
	 * Constructor de la classe
	 */
	public FinestraThird() {
				
	}
	
	public void setStatistics(FinestraStatistics finestraStatistics) {
		
		this.finestraStatistics = finestraStatistics;
		add(finestraStatistics);
		
	}
	
	public void setCircuit(FinestraCircuit finestraCircuit) {
		
		this.finestraCircuit = finestraCircuit;
		add(finestraCircuit);		
	}
	
	public void setPlot(FinestraPlot finestraPlot) {
		
		this.finestraPlot = finestraPlot;
		add(finestraPlot);		
	}
	
	public void setRaces(FinestraRaces finestraRaces) {
		
		this.finestraRaces = finestraRaces;
		add(finestraRaces);		
	}
	
	public void setPost(Manage_Info manageInfo) {
		this.manageInfo = manageInfo;
	}

	public void registreControladorThird(ListenerThird controladorThird) {

	}
}