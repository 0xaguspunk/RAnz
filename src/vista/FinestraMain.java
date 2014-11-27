package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controlador.ListenerMain;

/**
 * Finestra principal, JFrame que engloba la resta de JPanels i classes.
 * També conté la barra de menú principal i és on afegim les diferents
 * cards
 * 
 * @author Ranz
 */
public class FinestraMain extends JFrame{

	private JPanel jpBackground;
	private JLabel jlImage;
	private CardLayout clCards;
	private JMenuBar jmbMain;
	private JMenu jmFile;
	private JMenu jmSession;
	private JMenu jmHelp;
	private JMenuItem jmiLoadSession;
	private JMenuItem jmiCloseSession;
	private JMenuItem jmiExit;
	private JMenuItem jmiAbout;
	private FinestraSecond finestraSecond;

	/**
	 * Constructor de la classe
	 */
	public FinestraMain(){
		
		jmbMain = new JMenuBar();
		jmFile = new JMenu("File");
		jmSession = new JMenu("Session");
		jmHelp = new JMenu("Help");
		jmiLoadSession = new JMenuItem("Load Session");
		jmiCloseSession = new JMenuItem("Close Session");
		jmiExit = new JMenuItem("Exit");
		jmiAbout = new JMenuItem("About us");
		
		jmbMain.add(jmFile);
		jmbMain.add(jmSession);
		jmbMain.add(jmHelp);
		jmFile.add(jmiExit);
		jmSession.add(jmiLoadSession);
		jmSession.add(jmiCloseSession);
		jmHelp.add(jmiAbout);
				
		setJMenuBar(jmbMain);
		
		jpBackground = new JPanel();
		jlImage = new JLabel();
		jlImage.setLayout(new BorderLayout());
		jpBackground.add(jlImage, BorderLayout.CENTER);
		ImageIcon image = new ImageIcon("images/RanzBackground.png");
		jlImage.setIcon(image);
		
		clCards = new CardLayout();
		getContentPane().setLayout(clCards);
		getContentPane().add(jpBackground, "Main");
		
		setIconImage(new ImageIcon("images/LogoRanz.png").getImage());
        setVisible(true);
        setSize(1250, 700);
        setResizable(false);
        setTitle("RAnz");

        clCards.show(getContentPane(), "Main");
        //When we press 'x' in the window remains opened
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
	}
		
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	FinestraMain finestraMain = new FinestraMain();
            	
            	ListenerMain controladorFinestraMain = new ListenerMain(finestraMain); 
            	
            	finestraMain.registreControladorMain(controladorFinestraMain);
            	            	            	
            }
        });
    }
	
	public void registreControladorMain(ListenerMain controladorMain) {
		jmiLoadSession.setActionCommand("LOAD_SESSION");
		jmiLoadSession.addActionListener(controladorMain);
		jmiCloseSession.setActionCommand("CLOSE_SESSION");
		jmiCloseSession.addActionListener(controladorMain);
		jmiAbout.setActionCommand("ABOUT_US");
		jmiAbout.addActionListener(controladorMain);
		jmiExit.setActionCommand("EXIT");
		jmiExit.addActionListener(controladorMain);
	}
	
	/**
	 * Mostra les diferents cards
	 * 
	 * @param id String amb el nom de la card
	 */
	public void showCard (String id){
		clCards.show(getContentPane(), id);
	}
}
