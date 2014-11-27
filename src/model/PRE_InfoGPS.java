package model;

/**
 * Recull la informació del fitxer GPS sense tractar
 * No podem treballar amb ella directament
 * 
 * @author Ranz
 */
public class PRE_InfoGPS {

	private int Hour ;
	private int Minuts;
	private int Seconds;
	private float LatDeg;
	private float LatMin;
	private float LatSec;
	private float LongDeg;
	private float LongMin;
	private float LongSec;
	private String Orientation;
	private float SpeedKN;
	private int Day;
	private int Month;
	private int Year;
	
	/**
	 * Constructor de la classe
	 */
	public PRE_InfoGPS() {
	
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

	public float getLatDeg() {
		return LatDeg;
	}

	public void setLatDeg(float latDeg) {
		LatDeg = latDeg;
	}

	public float getLatMin() {
		return LatMin;
	}

	public void setLatMin(float latMin) {
		LatMin = latMin;
	}

	public float getLatSec() {
		return LatSec;
	}

	public void setLatSec(float latSec) {
		LatSec = latSec;
	}

	public float getLongDeg() {
		return LongDeg;
	}

	public void setLongDeg(float longDeg) {
		LongDeg = longDeg;
	}

	public float getLongMin() {
		return LongMin;
	}

	public void setLongMin(float longMin) {
		LongMin = longMin;
	}

	public float getLongSec() {
		return LongSec;
	}

	public void setLongSec(float longSec) {
		LongSec = longSec;
	}

	public String getOrientation() {
		return Orientation;
	}

	public void setOrientation(String orientation) {
		Orientation = orientation;
	}

	public float getSpeedKN() {
		return SpeedKN;
	}

	public void setSpeedKN(float speedKN) {
		SpeedKN = speedKN;
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