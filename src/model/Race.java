package model;

import java.util.ArrayList;

/**
 * Guarda tota la informació ja tractada de tal forma que 
 * ja podem omplir les taules d'estadístiques
 * 
 * @author Ranz
 */
public class Race {
	
	private double VmaxRace;
	private double VminRace;
	private float TmaxRace;
	private float TminRace;
	private double AverageMaxSpeed;
	private double AverageMinSpeed;
	private ArrayList<Lap> Laps;
	private double AverageSpeedRace;
	private int AverageTimeRaceMin;
	private int AverageTimeRaceSec;
	private String CircuitName;
	private double MaxDis;
	private String ID;
	private int TotalTimeMin;
	private int TotalTimeSec;
	private double TotalLength;
	private double AverageLength;
	
	/**
	 * Constructor de la classe
	 */
	public Race (){
		Laps = new ArrayList<Lap>();
	}
	
	public void Add_lap(Lap L) {
		Laps.add(L);
	}
	
	public double getVmaxRace() {
		return VmaxRace;
	}

	public void setVmaxRace(double vmaxRace) {
		VmaxRace = vmaxRace;
	}

	public double getVminRace() {
		return VminRace;
	}

	public void setVminRace(double vminRace) {
		VminRace = vminRace;
	}

	public float getTmaxRace() {
		return TmaxRace;
	}

	public void setTmaxRace(float tmaxRace) {
		TmaxRace = tmaxRace;
	}

	public float getTminRace() {
		return TminRace;
	}

	public void setTminRace(float tminRace) {
		TminRace = tminRace;
	}

	public ArrayList<Lap> getLaps() {
		return Laps;
	}

	public void setLaps(ArrayList<Lap> laps) {
		Laps = laps;
	}

	public double getAverageSpeedRace() {
		return AverageSpeedRace;
	}

	public void setAverageSpeedRace(double averageSpeedRace) {
		AverageSpeedRace = averageSpeedRace;
	}

	public String getCircuitName() {
		return CircuitName;
	}

	public void setCircuitName(String circuitName) {
		CircuitName = circuitName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public double getAverageMaxSpeed() {
		return AverageMaxSpeed;
	}

	public void setAverageMaxSpeed(double averageMaxSpeed) {
		AverageMaxSpeed = averageMaxSpeed;
	}

	public double getAverageMinSpeed() {
		return AverageMinSpeed;
	}

	public void setAverageMinSpeed(double averageMinSpeed) {
		AverageMinSpeed = averageMinSpeed;
	}

	public int getAverageTimeRaceMin() {
		return AverageTimeRaceMin;
	}

	public void setAverageTimeRaceMin(int averageTimeRaceMin) {
		AverageTimeRaceMin = averageTimeRaceMin;
	}

	public int getAverageTimeRaceSec() {
		return AverageTimeRaceSec;
	}

	public void setAverageTimeRaceSec(int averageTimeRaceSec) {
		AverageTimeRaceSec = averageTimeRaceSec;
	}

	public double getMaxDis() {
		return MaxDis;
	}

	public void setMaxDis(double maxDis) {
		MaxDis = maxDis;
	}

	public int getTotalTimeMin() {
		return TotalTimeMin;
	}

	public void setTotalTimeMin(int totalTimeMin) {
		TotalTimeMin = totalTimeMin;
	}

	public int getTotalTimeSec() {
		return TotalTimeSec;
	}

	public void setTotalTimeSec(int totalTimeSec) {
		TotalTimeSec = totalTimeSec;
	}

	public double getTotalLength() {
		return TotalLength;
	}

	public void setTotalLength(double totalLength) {
		TotalLength = totalLength;
	}

	public double getAverageLength() {
		return AverageLength;
	}

	public void setAverageLength(double averageLength) {
		AverageLength = averageLength;
	}		
}