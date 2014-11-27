package model;

import java.util.ArrayList;

/**
 * Guarda les diferents carreres o sigui sessions (diferents
 * fitxers GPS) que podem arribar a carregar
 * 
 * @author Ranz
 */
public class Season {
	
	private ArrayList<Race> Races;

	/**
	 * Constructor de la classe
	 */
	public Season (){
		Races =  new ArrayList<Race>();
	}
	
	public ArrayList<Race> getRaces() {
		return Races;
	}

	public void Add_race(Race R) {
		Races.add(R);
	}
}