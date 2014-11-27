package model;

import java.util.ArrayList;

/**
 * Controla la informació de les voltes
 * 
 * @author Ranz
 */
public class Lap {
	
	private ArrayList<Point> Points;
	private int MinLapTime;
	private int SecLapTime;
	private double MaxLapSpeed;
	private double AverageLapSpeed;
	private double MinLapSpeed;
	private double Distance;
	
	/**
	 * Constructor de la classe
	 */
	public Lap(){
		Points = new ArrayList<Point>();
	}
	
	/**
	 * Afegeix els punts que se li passen
	 * 
	 * @param P Punt per afegir
	 */
	public void Add_point(Point P) {
		Points.add(P);
	}

	public ArrayList<Point> getPoints() {
		return Points;
	}
	
	public void setPoints(ArrayList<Point> points) {
		Points = points;
	}
	
	public int getMinLapTime() {
		return MinLapTime;
	}
	
	public void setMinLapTime(int minLapTime) {
		MinLapTime = minLapTime;
	}
	
	public int getSecLapTime() {
		return SecLapTime;
	}
	
	public void setSecLapTime(int secLapTime) {
		SecLapTime = secLapTime;
	}
	
	public double getMaxLapSpeed() {
		return MaxLapSpeed;
	}
	
	public void setMaxLapSpeed(double maxLapSpeed) {
		MaxLapSpeed = maxLapSpeed;
	}
	
	public double getAverageLapSpeed() {
		return AverageLapSpeed;
	}
	
	public void setAverageLapSpeed(double averageLapSpeed) {
		AverageLapSpeed = averageLapSpeed;
	}
	
	public double getMinLapSpeed() {
		return MinLapSpeed;
	}
	
	public void setMinLapSpeed(double minLapSpeed) {
		MinLapSpeed = minLapSpeed;
	}
	
	public double getDistance() {
		return Distance;
	}
	
	public void setDistance(double distance) {
		Distance = distance;
	}
}