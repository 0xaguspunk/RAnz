package vista;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Mostra informació dels creadors del programa
 * 
 * @author Ranz
 */
public class FinestraAbout extends JPanel {
	
	private JPanel jpBackground;
	private JLabel jlImage;
	
	/**
	 * Constructor de la classe
	 */
	public FinestraAbout() {
		
		jpBackground = new JPanel();
		jlImage = new JLabel();
		jlImage.setLayout(new BorderLayout());
		jpBackground.add(jlImage, BorderLayout.CENTER);
		ImageIcon image = new ImageIcon("images/about.png");
		jlImage.setIcon(image);
		
		add(jpBackground);		
	}
}