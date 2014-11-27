package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.POST_InfoGPS;
import controlador.ListenerSecond;

/**
 * Segona card que contindrà la classe del circuit
 * 
 * @author Ranz
 */
public class FinestraSecond extends JPanel{
	
	private FinestraCircuit finestraCircuit;
	
	/**
	 * Constructor de la classe
	 */
	public FinestraSecond() {
		
		setLayout(new BorderLayout());	
	}
	
	public void setCircuit(FinestraCircuit finestraCircuit) {
		this.finestraCircuit = finestraCircuit;		
		add(finestraCircuit);
		
	}

	public void registreControladorSecond(ListenerSecond controladorSecond) {
		finestraCircuit.addMouseListener(controladorSecond);
	}
}