package model;

/**
 * Guarda la informació del fitxer GPS ja tractada per a poder
 * treballar posteriorment amb ella
 * 
 * @author Ranz
 */
public class POST_InfoGPS {
	private  int Hour;
	private int Minuts;
	private int Seconds;
	private int TotalSeconds;
	private double CoordX;
	private double CoordY;
	private double SpeedKM;
	private int Day;
	private int Month;
	private int Year;
	
	/**
	 * Constructor de la classe
	 */
	public POST_InfoGPS(){
		
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

	public int getTotalSeconds() {
		return TotalSeconds;
	}

	public void setTotalSeconds(int totalSeconds) {
		TotalSeconds = totalSeconds;
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

	public int getDay() {
		return Day;
	}

	public void setDay(int day) {
		Day = day;
	}

	public int getMonth() {
		return Month;
	}

	public void setMonth(int month) {
		Month = month;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}	
}