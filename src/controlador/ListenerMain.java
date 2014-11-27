package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;

import model.ConnectorDB;
import model.Manage_Info;
import vista.FinestraAbout;
import vista.FinestraCircuit;
import vista.FinestraMain;
import vista.FinestraSecond;

/**
 * Listener encarregat de controlar els events de la finestra principal
 * i la primera Card
 * 
 * @author Ranz
 */
public class ListenerMain implements ActionListener {

	private FinestraMain finestraPrincipal;
	private FinestraSecond finestraSecond;
	private FinestraCircuit finestraCircuit;
	private FinestraAbout finestraAbout;
	private ListenerSecond controladorSecond;
	private Manage_Info manageInfo;
    private JFileChooser fc;
    private File savedFile;
    private ConnectorDB conn;
    
    /**
     * Constructor de la classe
     * 
     * @param finestraPrincipal classe del JFrame principal
     */
	public ListenerMain(FinestraMain finestraPrincipal) {
		this.finestraPrincipal = finestraPrincipal;
		//Enviem a la nova instància ConectorDB usuari, password, BBDD i port per iniciar els paràmetres de connexió
		conn = new ConnectorDB("root", "Ranz", "ranz", 3306);
		//Ens connectem a la BBDD
		conn.connect();
	}
	
	/**
	 * S'encarrega de controlar on clica l'usuari
	 * 
	 * @param e Event controlat pel l'ActionListener
	 */
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getActionCommand().equals("LOAD_SESSION")) {
    		manageInfo = new Manage_Info();
    		fc = new JFileChooser();
    		fc.setCurrentDirectory(new File("GPSFiles"));
            int returnVal = fc.showSaveDialog(finestraPrincipal);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	//File is saved in 
                savedFile = fc.getSelectedFile();
                //Print file name into pathbox (when selected)
                manageInfo.saveFile(savedFile.getAbsolutePath());
            	
            	finestraSecond = new FinestraSecond();
            	finestraCircuit = new FinestraCircuit(manageInfo.getArrayPOST(), 0);
            	finestraPrincipal.getContentPane().add(finestraSecond, "Second");    	
            	finestraSecond.setCircuit(finestraCircuit);            	
            	// CREEM EL CONTROLADOR DEL SEGON PANELL DEL CARD
            	// LI PASSEM ELS PUNTS DEL GPS I ESTABLIM LA RELACIO AMB EL SEGON PANELL
            	// ESTABLIM LA RELACIO CONTROLADOR_SEGON_PANELL_CARD------>SEGON_PANELL_CARD
            	controladorSecond = new ListenerSecond(manageInfo, finestraSecond, finestraCircuit, finestraPrincipal, conn);
            	// ESTABLIM LA RELACIO SEGON_PANELL_CARD----->CONTROLADOR_SEGON_PANELL_CARD
            	finestraSecond.registreControladorSecond(controladorSecond);
            	finestraCircuit.registreControladorCircuit(controladorSecond);          	
            	finestraPrincipal.showCard("Second");            	
            }    		
        }
    	
    	if (e.getActionCommand().equals("CLOSE_SESSION")) {
    		finestraPrincipal.showCard("Main");
        }
    	
    	if (e.getActionCommand().equals("ABOUT_US")) {
    		finestraAbout = new FinestraAbout();
    		finestraPrincipal.getContentPane().add(finestraAbout, "About");
    		finestraPrincipal.showCard("About");    		
        }
    	
    	if (e.getActionCommand().equals("EXIT")) {
    		
    		conn.deleteQuery("DELETE FROM races");   		
			//Ens desconectem de la BBDD una vegada no la necessitem
			conn.disconnect();
			
			System.exit(0);
        }    	
    }
}