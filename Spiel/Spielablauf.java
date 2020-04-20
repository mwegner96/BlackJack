package BlackJack;

import java.util.*;

public class Spielablauf {

	Scanner scan = new Scanner(System.in);

	Kartendeck kartendeck;
	int Guthaben = 0;
	int Spieleranzahl;
	Spieler[] spieler;
	Bank Bank;
	String ziehen, weiter;
	boolean verloren, BankVerloren, EingabeGuthabenOk, EingabeAnzahlOk, EingabeEinsatzOk;
	int Zaehler = 0;

	public void starteBlackJack() {

		System.out.println("----------------------------------");
		System.out.println("Herzlich Willkommen bei Black Jack");
		System.out.println("----------------------------------");
		System.out.println(" ");

		GeldEingeben();
		SpielerAnzahl();
		setSpieler();
		bankerstellen();

		do {
			Reset();
			KartendeckErstellen();
			Einsatz();
			AusteilenAnzeigen();
			BankKarteZiehen();
			ZiehenSpieler();
			ZiehenBank();
			GewinnPruefen();
			SpielerPleite();
			weiterSpielen();
		} while (weiter.equals("J") || weiter.equals("j"));
	}

	private void GeldEingeben() {

		do {
			System.out.println();
			System.out.print("Geben sie das Startkapital ein (Jeder Spieler bekokmmt die selbe Anzahl an Chips): ");

			try {
				EingabeGuthabenOk = true;
				Guthaben = scan.nextInt();
			} catch (Exception e) {
				String errStr = scan.next();
				System.out.println();
				System.out.println("-----------------------------------");
				System.out.println(errStr + " ist eine falsche eingabe!");
				System.out.println("-----------------------------------");
				EingabeGuthabenOk = false;
			}

			if ((Guthaben < 1 || Guthaben > 100000) && EingabeGuthabenOk == true) {
				System.out.println();
				System.out.println("-------------------------------");
				System.out.println("Guthaben auserhalb der Grenzen!");
				System.out.println("-------------------------------");
			}

		} while (Guthaben < 1 || Guthaben > 100000);
		System.out.println();
		System.out.println("Jeder Spieler hat " + Guthaben + " Chips");

		System.out.println();
		System.out.println("------------------------------------------");
	}

	private void SpielerAnzahl() {

		System.out.println();

		do {
			System.out.print("Geben sie die Anzahl der Spieler ein (1-7): ");
			try {
				EingabeAnzahlOk = true;
				Spieleranzahl = scan.nextInt();
			} catch (Exception e) {
				String errStr = scan.next();
				System.out.println();
				System.out.println("-----------------------------------");
				System.out.println(errStr + " ist eine falsche eingabe!");
				System.out.println("-----------------------------------");
				EingabeAnzahlOk = false;
			}
			if ((Spieleranzahl > 7 || Spieleranzahl < 1) && EingabeAnzahlOk == true) {
				System.out.println();
				System.out.println("------------------------");
				System.out.println("Ungueltige Spieleranzahl");
				System.out.println("------------------------");
				System.out.println();
			}
			System.out.println();
		} while (Spieleranzahl > 7 || Spieleranzahl < 1 || EingabeAnzahlOk == false);
		spieler = new Spieler[Spieleranzahl];
		
		System.out.println();
		System.out.println("------------------------------------------");
	}

	private void setSpieler() {
		for (int i = 0; i < spieler.length; i++) {

			String spielername = "";

			spieler[i] = new Spieler();

			System.out.println();
			System.out.print("Spieler " + (i + 1) + " -> wie ist dein Name? ");
			if (i == 0) {
				scan.nextLine();
			}
			spielername = scan.nextLine();
			spieler[i].setName(spielername);

			spieler[i].setGeld(Guthaben);
			System.out.println();
		}
		System.out.println();
		System.out.println("------------------------------------------");
	}

	private void bankerstellen() {

		Bank = new Bank();

	}

	private void Einsatz() {

		int Geld = 0;

		for (int i = 0; i < Spieleranzahl; i++) {
			do {
				Geld = spieler[i].Guthaben;
				System.out.println();
				System.out.print(spieler[i].SpielerName + " -> Geben sie ihren Einsatz ein: ");
				try {
					EingabeEinsatzOk = true;
					spieler[i].Einsatz = scan.nextInt();
				} catch (Exception e) {
					String errStr = scan.next();
					System.out.println();
					System.out.println("-----------------------------------");
					System.out.println(errStr + " ist eine falsche eingabe!");
					System.out.println("-----------------------------------");
					EingabeEinsatzOk = false;
				}
				System.out.println();
				if (EingabeEinsatzOk == true) {
					if ((spieler[i].Einsatz > spieler[i].Guthaben)) {
						System.out.println();
						System.out.println("-----------------------------------");
						System.out.println("Sie haben nicht so viele Chips!");
						System.out.println("-----------------------------------");
						System.out.println();
					} else {
						if (spieler[i].Einsatz < 1) {
							System.out.println();
							System.out.println("-----------------------------------");
							System.out.println("Einsatz zu klein!");
							System.out.println("-----------------------------------");
							System.out.println();
						} else {
							if (spieler[i].Einsatz < spieler[i].Guthaben) {
								spieler[i].Guthaben -= spieler[i].Einsatz;
							}
						}
					}
				}
			} while (spieler[i].Einsatz > Geld || EingabeEinsatzOk == false || spieler[i].Einsatz < 1);
		}
		System.out.println();
		System.out.println("------------------------------------------");
	}

	private void AusteilenAnzeigen() {
		kartendeck.KartendeckErstellen();
		for (int i = 0; i < Spieleranzahl; i++) {
			for (int j = 0; j < 2; j++) {
				spieler[i].addHandKarte(kartendeck.giveNextCard());
				spieler[i].HandkartenWert();
				System.out.println(spieler[i].getName() + " -> Handkarte: " + spieler[i].getKarte()
						+ " / Handkartenwert: " + spieler[i].getPunkte());
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("------------------------------------------");
	}

	private void BankKarteZiehen() {
		for (int j = 0; j < 2; j++) {
			Bank.addHandKarte(kartendeck.giveNextCard());
			Bank.HandkartenWert();
		}
		System.out.println();
		System.out.println("Die Bank hat: " + Bank.getPunkte() + " Punkte auf der Hand");
		System.out.println();
		System.out.println("------------------------------------------");
	}

	public void ZiehenSpieler() {
		System.out.println();
		scan.nextLine();
		for (Zaehler = 0; Zaehler < Spieleranzahl; Zaehler++) {
			verloren = false;
			do {
				System.out.print(spieler[Zaehler].getName() + " -> Wollen sie nochmal ziehen (J oder N): ");
				ziehen = scan.nextLine();

				if (!ziehen.equals("J") && !ziehen.equals("j") && !ziehen.equals("N") && !ziehen.equals("n")) {
					System.out.println();
					System.out.println("---------------");
					System.out.println("Eingabe falsch!");
					System.out.println("---------------");
				}

				if (ziehen.equals("J") || ziehen.equals("j")) {
					spieler[Zaehler].addHandKarte(kartendeck.giveNextCard());
					spieler[Zaehler].HandkartenWert();
					System.out.println(spieler[Zaehler].getName() + " -> Handkarte: " + spieler[Zaehler].getKarte()
							+ " / Handkartenwert: " + spieler[Zaehler].getPunkte());
					System.out.println();
					Ueberkauft();
					if (spieler[Zaehler].verloren) {
						break;
					}
				}
				System.out.println();
			} while (!ziehen.equals("N") && !ziehen.equals("n"));
		}
		System.out.println();
		System.out.println("------------------------------------------");
	}

	private void Ueberkauft() {
		if (spieler[Zaehler].getPunkte() > 21) {
			System.out.println(spieler[Zaehler].getName() + " -> Sie haben verloren!");
			System.out.println();
			spieler[Zaehler].verloren(true);
		}
	}

	private void ZiehenBank() {
		System.out.println();
		while (Bank.getPunkte() < 17) {
			Bank.addHandKarte(kartendeck.giveNextCard());
			Bank.HandkartenWert();
			System.out.println("Die Bank hat: " + Bank.getPunkte());
			System.out.println();
			BankUeberkauft();
			if (BankVerloren) {
				break;
			}
		}
		System.out.println();
		System.out.println("------------------------------------------");
	}

	private void BankUeberkauft() {

		if (Bank.getPunkte() > 21) {
			System.out.println();
			System.out.println("Die Bank hat sich überkauft");
			System.out.println();
			BankVerloren = true;
		}
	}

	private void GewinnPruefen() {
		System.out.println();
		for (int i = 0; i < Spieleranzahl; i++) {
			if (spieler[i].verloren) {
				System.out.println(spieler[i].getName() + " -> sie haben verloren!");
				System.out.println(spieler[i].getName() + " -> sie haben noch " + spieler[i].Guthaben + " Chips");
			} else if (BankVerloren == true && spieler[i].verloren == false) {
				System.out.println(spieler[i].getName() + " -> sie haben gewonnen!");
				spieler[i].Guthaben = spieler[i].Guthaben + spieler[i].Einsatz * 2;
				System.out.println(spieler[i].getName() + " -> sie haben jetzt " + spieler[i].Guthaben + " Chips");
			} else if (spieler[i].getPunkte() > Bank.getPunkte() && BankVerloren == false) {
				System.out.println(spieler[i].getName() + " -> sie haben gewonnen!");
				spieler[i].Guthaben = spieler[i].Guthaben + spieler[i].Einsatz * 2;
				System.out.println(spieler[i].getName() + " -> sie haben jetzt " + spieler[i].Guthaben + " Chips");
			} else if (spieler[i].getPunkte() == Bank.getPunkte()) {
				System.out.println(spieler[i].getName() + " -> sie haben ein Unendschieden mit der Bank!");
				spieler[i].Guthaben = spieler[i].Guthaben + spieler[i].Einsatz;
				System.out.println(spieler[i].getName() + "-> sie haben jetzt " + spieler[i].Guthaben + " Chips");
			} else if (spieler[i].getPunkte() < Bank.getPunkte()) {
				System.out.println(spieler[i].getName() + " -> sie haben Verloren!");
				System.out.println(spieler[i].getName() + " -> sie haben jetzt " + spieler[i].Guthaben + " Chips");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("------------------------------------------");
	}

	private String weiterSpielen() {

		do {
			System.out.println();
			System.out.print("Moechten Sie weiterspielen? (J/N): ");
			weiter = scan.nextLine();

			if (!ziehen.equals("J") && !ziehen.equals("j") && !ziehen.equals("N") && !ziehen.equals("n")) {
				System.out.println();
				System.out.println("---------------");
				System.out.println("Eingabe falsch!");
				System.out.println("---------------");
			}

			if (weiter.equals("N") || weiter.equals("n")) {
				System.out.println();
				System.out.println("------------------------------------------");
				System.out.println("Auf Wiedersehen!");
			}

		} while (!ziehen.equals("J") && !ziehen.equals("j") && !ziehen.equals("N") && !ziehen.equals("n"));

		System.out.println("------------------------------------------");
		System.out.println("------------------------------------------");

		return weiter;
	}

	private void Reset() {
		for (int i = 0; i < Spieleranzahl; i++) {
			spieler[i].ResetPunkte();
			spieler[i].ResetVerloren();
			spieler[i].ResetKarten();
		}

		Bank.ResetPunkte();
		Bank.ResetVerloren();
		verloren = false;
		BankVerloren = false;
	}

	private void SpielerPleite() {

		int anzahlSpielerRaus = 0;

		for (int i = 0; i < Spieleranzahl; i++) {
			if (spieler[i].Guthaben <= 0) {
				System.out.println(spieler[i].getName() + " -> sie haben keine Chips mehr! Sie sind raus!");
				anzahlSpielerRaus++;

				for (int j = i; j < Spieleranzahl; j++) {
					if (j < (Spieleranzahl - 1)) {
						spieler[j] = spieler[j + 1];
					}
				}

				if (i < (Spieleranzahl - 1)) {
					i--;
				}
			}

			if (anzahlSpielerRaus == Spieleranzahl) {
				System.out.println();
				System.out.println("-----------------------------------------------------------");
				System.out.println("Alle Spieler haben keine Chips mehr! Programm wird beendet!");
				System.out.println("-----------------------------------------------------------");
				System.exit(0);
			}
		}
		Spieleranzahl -= anzahlSpielerRaus;
	}

	private void KartendeckErstellen() {
		kartendeck = new Kartendeck(6);
	}
}
