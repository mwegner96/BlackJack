package BlackJack;

import java.util.*;

public abstract class Teilnehmer {

	List<String> HandKarten = new ArrayList<String>();
    String SpielerName;
    int Guthaben;
    String Karte;
    int Kartenwert;
	boolean verloren;
    
	public void setName(String name ) {
		
		this.SpielerName = name;
		
	}
	
	public void setGeld(int geld) {
		
		this.Guthaben = geld;
		
	}
    
	public void addHandKarte(String karte) {
		
		HandKarten.add(karte);
		 		
	}
	
	public String getName() {
		
		return SpielerName;
		
	}
	
	public int getPunkte() {
		
		return Kartenwert; 
	}
	
	public String getKarte() {
		
		return Karte;
		
	}
	
	public void ResetPunkte() {
		this.Kartenwert = 0;
	}
	
	public void ResetVerloren() {
		this.verloren = false;
	}
	
}