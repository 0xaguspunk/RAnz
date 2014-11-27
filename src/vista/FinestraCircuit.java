package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;

import javax.swing.DefaultFocusManager;
import javax.swing.JPanel;

import model.Lap;
import model.Manage_Info;
import model.POST_InfoGPS;
import controlador.ListenerSecond;

/**
 * Dibuixa el circuit amb totes els seus punts i espera que li seleccionem
 * la meta per dividir els punts en tantes voltes com hi hagi
 * 
 * @author Ranz
 */
public class FinestraCircuit extends JPanel{
	
	private ArrayList<POST_InfoGPS> post;
	private int ball;
	private int lap = -1;
	private ArrayList<Lap> laps;
	private boolean scaled = false;
	private boolean []lapsToPaint;
		
	/**
	 * Constructor de la classe
	 * 
	 * @param post Informació amb els punts que ha de pintar
	 * @param ball Punt on hem seleccionat la meta
	 */
	public FinestraCircuit(ArrayList<POST_InfoGPS> post, int ball) {
		
		this.post = post;
		this.ball = ball;
		setBackground(Color.black);
		this.laps = new ArrayList<Lap>();
	}
	
	public void setBall(int i){
		this.ball = i; 
	}
	
	public void setScaled(boolean aux){
		this.scaled = aux;
	}
	
	public void setPointsPaint(int lap, Manage_Info manageInfo, boolean []lapsToPaint){
		this.lap = lap;
		this.laps = manageInfo.getSeason().getRaces().get(0).getLaps();
		this.lapsToPaint = new boolean[manageInfo.getSeason().getRaces().get(0).getLaps().size()+2];
		for(int i=0;i<manageInfo.getSeason().getRaces().get(0).getLaps().size()+2;i++) 
			this.lapsToPaint[i]=false;
		this.lapsToPaint = lapsToPaint;
		
		this.repaint();
	}
		
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.yellow);
		if (lap == -1 || lap == (laps.size()+1)) {
			if(scaled==false){
				if(post!=null){
					for(int i=0; i<(post.size()-1); i++){
						g.drawLine((int)post.get(i).getCoordX(), (int)post.get(i).getCoordY(), (int)post.get(i+1).getCoordX(), (int)post.get(i+1).getCoordY());	
					}
					
					g.setColor(Color.blue);
					g.fillOval((int)post.get(ball).getCoordX()-6, (int)post.get(ball).getCoordY()-6,13, 13); 
				}
			}
			else{
				if (laps.size() > 1) {					
					for(int k=0;k<laps.size();k++){
						Color(g,k+1);
						for(int h=0;h<laps.get(k).getPoints().size()-1;h++){
							g.drawLine((int)laps.get(k).getPoints().get(h).getCoordX(), (int)laps.get(k).getPoints().get(h).getCoordY(), (int)laps.get(k).getPoints().get(h+1).getCoordX(), (int)laps.get(k).getPoints().get(h+1).getCoordY());
	
						}
						if(k<laps.size()-1) g.drawLine((int)laps.get(k).getPoints().get(laps.get(k).getPoints().size()-1).getCoordX(), (int)laps.get(k).getPoints().get(laps.get(k).getPoints().size()-1).getCoordY(), (int)laps.get(k+1).getPoints().get(0).getCoordX(), (int)laps.get(k+1).getPoints().get(0).getCoordY());
						g.setColor(Color.blue);
						g.fillOval((int)post.get(ball).getCoordX()-6, (int)post.get(ball).getCoordY()-6,13, 13); 
					}
				}
				else {
					Color(g,1);
					for(int h=0;h<laps.get(0).getPoints().size()-1;h++){
						g.drawLine((int)laps.get(0).getPoints().get(h).getCoordX(), (int)laps.get(0).getPoints().get(h).getCoordY(), (int)laps.get(0).getPoints().get(h+1).getCoordX(), (int)laps.get(0).getPoints().get(h+1).getCoordY());

					}
					g.drawLine((int)laps.get(0).getPoints().get(laps.get(0).getPoints().size()-1).getCoordX(), (int)laps.get(0).getPoints().get(laps.get(0).getPoints().size()-1).getCoordY(), (int)laps.get(0).getPoints().get(0).getCoordX(), (int)laps.get(0).getPoints().get(0).getCoordY());
					g.setColor(Color.blue);
					g.fillOval((int)post.get(ball).getCoordX()-6, (int)post.get(ball).getCoordY()-6,13, 13); 
				}
			}
		
		}else {
			
			for(int t=0;t<laps.size();t++)
			{		
				if(lapsToPaint[t]==true){

					Color(g,t+1);
					for(int j=0;j<laps.get(t).getPoints().size()-1;j++){
						g.drawLine((int)laps.get(t).getPoints().get(j).getCoordX(), (int)laps.get(t).getPoints().get(j).getCoordY(), (int)laps.get(t).getPoints().get(j+1).getCoordX(), (int)laps.get(t).getPoints().get(j+1).getCoordY());
					}
					if(t<laps.size()-1) g.drawLine((int)laps.get(t).getPoints().get(laps.get(t).getPoints().size()-1).getCoordX(), (int)laps.get(t).getPoints().get(laps.get(t).getPoints().size()-1).getCoordY(), (int)laps.get(t+1).getPoints().get(0).getCoordX(), (int)laps.get(t+1).getPoints().get(0).getCoordY());
					g.setColor(Color.blue);
					g.fillOval((int)post.get(ball).getCoordX()-6, (int)post.get(ball).getCoordY()-6,13, 13); 
				}
			}
		}	
	}
	
	public void Color(Graphics g,int n) {

		if(n==1) g.setColor(Color.blue);
		if(n==2) g.setColor(Color.cyan);
		if(n==3) g.setColor(Color.darkGray);
		if(n==4) g.setColor(Color.green);
		if(n==5) g.setColor(Color.lightGray);
		if(n==6) g.setColor(Color.magenta);
		if(n==7) g.setColor(Color.orange);
		if(n==8) g.setColor(Color.pink);
		if(n==9) g.setColor(Color.red);
		if(n==10) g.setColor(Color.YELLOW);
		if(n==11) g.setColor(Color.white);
		if(n==12) g.setColor(Color.orange);
		if(n==13) g.setColor(Color.CYAN);
		if(n==14) g.setColor(Color.red);
		if(n==15) g.setColor(Color.blue);
		if(n==16) g.setColor(Color.cyan);
		if(n==17) g.setColor(Color.darkGray);
		if(n==18) g.setColor(Color.green);
		if(n==19) g.setColor(Color.lightGray);
		if(n==20) g.setColor(Color.magenta);
		if(n==21) g.setColor(Color.orange);
		if(n==22) g.setColor(Color.pink);
		if(n==23) g.setColor(Color.red);
		if(n==24) g.setColor(Color.YELLOW);
		if(n==25) g.setColor(Color.white);
		if(n==26) g.setColor(Color.orange);
		if(n==27) g.setColor(Color.CYAN);
		if(n==28) g.setColor(Color.red);

	}
	
	public void registreControladorCircuit(ListenerSecond controladorSecond) {
		addMouseListener(controladorSecond);
	}

	public void setFirstPoints(int i, Manage_Info manageInfo) {
		this.lap = i;
		this.laps = manageInfo.getSeason().getRaces().get(0).getLaps();
		repaint();
	}
}