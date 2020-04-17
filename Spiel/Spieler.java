package BlackJack;

import java.util.*;

public class Spieler extends Teilnehmer {
	
	Scanner scan = new Scanner(System.in);
	int Einsatz;
	boolean EingabeAsswertOk;
	
    public Spieler() {
        super();
    }

    
    public void HandkartenWert() {
    	
    	
    	Karte = HandKarten.get(HandKarten.size() - 1);
    	Karte = Karte.substring(0,2);
    	
    	if (Karte.equals("As")) {
    		Kartenwert = Kartenwert + Asswertwaehlen();
    	}
    	else if (Karte.equals("Ki") || Karte.equals("Qu") || Karte.equals("Ja")) {
    		Kartenwert = Kartenwert + 10;
    	}
    	else {
    		switch(Karte) {
    		
    		case "2 ":
    		Kartenwert = Kartenwert + 2;
    		break;
    		
    		case "3 ":
        	Kartenwert = Kartenwert + 3;
        	break;
        		
    		case "4 ":
        	Kartenwert = Kartenwert + 4;
        	break;
        		
    		case "5 ":
        	Kartenwert = Kartenwert + 5;
        	break;
        		
    		case "6 ":
        	Kartenwert = Kartenwert + 6;
        	break;
        		
    		case "7 ":
        	Kartenwert = Kartenwert + 7;
        	break;
        		
    		case "8 ":
        	Kartenwert = Kartenwert + 8;
        	break;
        		
    		case "9 ":
        	Kartenwert = Kartenwert + 9;
        	break;
        		
    		case "10":
        	Kartenwert = Kartenwert + 10;
        	break;
        		
        	default:
        	break;
    		}
    	}
    	
    	Karte = HandKarten.get(HandKarten.size() - 1);
    }
    
    public int Asswertwaehlen() {
    	

    	int Asswert = 0;
    	
    	System.out.println(SpielerName + " -> Handkarte:" + HandKarten);
    	
    	do {
    		System.out.println();
    		System.out.print("Welchen Wert soll das Ass haben: ");
    		try {
    			EingabeAsswertOk = true;
    		Asswert = scan.nextInt();
    		}catch (Exception e) {
				String errStr = scan.next();
				System.out.println();
				System.out.println("-----------------------------------");
				System.out.println(errStr + " ist eine falsche eingabe!");
				System.out.println("-----------------------------------");
				EingabeAsswertOk = false;
			}
    		
    		if (Asswert != 11 && Asswert != 1) {
    			System.out.println();
    			System.out.println("Ungueltig Eingabe");
    		}
			System.out.println();
    	}while((Asswert != 1 && Asswert != 11) || EingabeAsswertOk == false);
    	
    	return Asswert;
    }

	public void verloren(boolean verloren) {
		
		this.verloren = verloren;
	}
	
	public void ResetKarten() {
		HandKarten.clear();
	}

}