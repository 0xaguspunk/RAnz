package model;

/**
 * Controla la informació referent als punts del fitxer GPS
 * 
 * @author Ranz
 */
public class Point {
	
	private int  Hour;
	private int Minuts;
	private int Seconds;
	private double CoordX;
	private double CoordY;
	private double SpeedKM;
	private double Distance;
	
	/**
	 * Constructor de la classe
	 */
	public Point (){
		
	}
	
	public double getDistance() {
		return Distance;
	}

	public void setDistance(double distance) {
		Distance = distance;
	}

	public int getHour() {
		return Hour;
	}

	public void setHour(int hour) {
		Hour = hour;
	}

	public int getMinuts() {
		return Minuts;
	}

	public void setMinuts(int minuts) {
		Minuts = minuts;
	}

	public int getSeconds() {
		return Seconds;
	}

	public void setSeconds(int seconds) {
		Seconds = seconds;
	}

	public double getCoordX() {
		return CoordX;
	}

	public void setCoordX(double coordX) {
		CoordX = coordX;
	}

	public double getCoordY() {
		return CoordY;
	}

	public void setCoordY(double coordY) {
		CoordY = coordY;
	}

	public double getSpeedKM() {
		return SpeedKM;
	}

	public void setSpeedKM(double speedKM) {
		SpeedKM = speedKM;
	}
}