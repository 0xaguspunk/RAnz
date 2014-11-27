package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Lap;
import model.Manage_Info;
import model.Point;

/**
 * Classe que dibuixa el plot amb la informació de les diferents voltes
 * 
 * @author Ranz
 */
public class FinestraPlot extends JPanel{
	private Manage_Info manageInfo;
	private int lap = -1;
	private boolean []lapsToPaint;
	
	/**
	 * Constructor de la classe
	 * 
	 * @param manageInfo tota la informació de voltes i estadístiques
	 */
	public FinestraPlot(Manage_Info manageInfo) {
		
		this.manageInfo = manageInfo;
		setBackground(Color.black);	
	}
	
	public void setLap(int lap, boolean []lapsToPaint){
		this.lap = lap;
		this.lapsToPaint = lapsToPaint;
		this.repaint();
		
	}
	
	public void paintComponent(Graphics g){
		int i=0;
		ArrayList<Lap> laps = new ArrayList<Lap>();
		laps = manageInfo.getSeason().getRaces().get(0).getLaps();
			
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawLine(75,75,75,275); //Ejes
		g.drawLine(75,275,595,275);
		g.setColor(Color.DARK_GRAY);
		for(i=75;i<=235;i=i+40)
			g.drawLine(75,i,595,i);
		for(i=115;i<=595;i=i+40)
			g.drawLine(i,75,i,275);
		g.setColor(Color.white);
		g.drawString("250", 51, 80);
		g.drawString("200", 51, 120);
		g.drawString("150", 51, 160);
		g.drawString("100", 51, 200);
		g.drawString("50", 58, 240);
		g.drawString("0", 65, 280);
		
		int degrees = -90; // rotate text counter-clockwise
	    AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(degrees));
		g.setFont((g.getFont()).deriveFont(at));
		g.drawString("speed (km/h)", 26, 210);
		degrees = 0;
		at = AffineTransform.getRotateInstance(Math.toRadians(degrees));
		g.setFont((g.getFont()).deriveFont(at));
		g.drawString("distance (m)", 320, 310);
			
		double x=0,y=0,xi=0,yi=0;
		int k;
		int max = (int)(manageInfo.getSeason().getRaces().get(0).getMaxDis()*1000);
		int restar = max/13;
		int aux=max;
		
		if(this.lap == -1 || lap == laps.size()+1){
			
			for(i=585;i>=75;i=i-40)
			{
				g.drawString(Integer.toString(aux), i, 290);
			 	aux = aux - restar;
			}
			
			for(i=0;i<laps.size();i++)
			{
				ArrayList<Point> points = new ArrayList<Point>();
				points = laps.get(i).getPoints();
				Color(g,i+1);
				for(k=0;k<points.size()-1;k++)
				{
					x = points.get(k).getDistance()*1000*520/max + 75;
					y = 200 - points.get(k).getSpeedKM()*200/250 + 75;
					xi = points.get(k+1).getDistance()*1000*520/max + 75;
					yi = 200 - points.get(k+1).getSpeedKM()*200/250 + 75;
					g.drawLine((int)x,(int)y,(int)xi,(int)yi);
					
				}
			}
		}
		else { 		
			max=0;
			for(int p=0;p<laps.size();p++){
				if(lapsToPaint[p]==true){
					if((int)(laps.get(p).getDistance()*1000)>=max)
						max = (int)(laps.get(p).getDistance()*1000);
				}
			}
			
			restar = max/13;
			aux=max;
			
			for(i=585;i>=75;i=i-40)
			{
				g.setColor(Color.white);
				g.drawString(Integer.toString(aux), i, 290);
			 	aux = aux - restar;
			}
			
			for(int t=0;t<laps.size();t++)
			{		
				if(lapsToPaint[t]==true){
					
					ArrayList<Point> points = new ArrayList<Point>();
					points = laps.get(t).getPoints();
					Color(g,t+1);
					for(k=0;k<points.size()-1;k++)
					{
						x = points.get(k).getDistance()*1000*520/max + 75;
						y = 200 - points.get(k).getSpeedKM()*200/250 + 75;
						xi = points.get(k+1).getDistance()*1000*520/max + 75;
						yi = 200 - points.get(k+1).getSpeedKM()*200/250 + 75;
						g.drawLine((int)x,(int)y,(int)xi,(int)yi);
						
					}
			}}
				
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
}