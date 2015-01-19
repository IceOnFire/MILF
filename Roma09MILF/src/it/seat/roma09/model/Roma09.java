package it.seat.roma09.model;

import java.util.Vector;

public class Roma09 {
	public final static String NUOTO = "Nuoto";
	public final static String NUOTO_IN_ACQUE_LIBERE = "Nuoto in acque libere";
	public final static String TUFFI = "Tuffi";
	public final static String PALLANUOTO_MASCHILE = "Pallanuoto maschile";
	public final static String PALLANUOTO_FEMMINILE = "Pallanuoto femminile";
	public final static String NUOTO_SINCRONIZZATO = "Nuoto sincronizzato";
	public final static String CERIMONIE = "Cerimonie";

	private static Roma09 singleton;

	private Vector eventi;
	private String specialitaCorrente;
	private int giornoCorrente;
	private int meseCorrente;

	public static Roma09 getInstance() {
		if (singleton == null) {
			singleton = new Roma09();
		}
		return singleton;
	}

	private Roma09() {
		eventi = new Vector();

		/* 18 luglio 2009 */
		aggiungiEvento("Trampolino 1m U PRELIMINARI", TUFFI, 18, 7, 2009, 10,
				0, 13, 0);
		aggiungiEvento("Piattaforma 10m D PRELIMINARI", TUFFI, 18, 7, 2009, 14,
				30, 17, 15);
		aggiungiEvento("Team tecnico PRELIMINARI", NUOTO_SINCRONIZZATO, 18, 7,
				2009, 11, 0, 13, 30);
		aggiungiEvento("CERIMONIA D'APERTURA", CERIMONIE, 18, 7, 2009, 20, 0,
				22, 0);

		/* 19 luglio 2009 */
		aggiungiEvento("5km D", NUOTO_IN_ACQUE_LIBERE, 19, 7, 2009, 9, 0, 10,
				30);
		aggiungiEvento("5km U", NUOTO_IN_ACQUE_LIBERE, 19, 7, 2009, 9, 0, 10,
				30);
		aggiungiEvento("Trampolino 1m U SF", TUFFI, 19, 7, 2009, 10, 0, 11, 30);
		aggiungiEvento("Piattaforma 10m D SF", TUFFI, 19, 7, 2009, 12, 15, 13,
				45);
		aggiungiEvento("Trampolino 1m U FINALI", TUFFI, 19, 7, 2009, 16, 0, 16,
				50);
		aggiungiEvento("Piattaforma 10m D FINALI", TUFFI, 19, 7, 2009, 18, 0,
				19, 15);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_FEMMINILE, 19, 7,
				2009, 9, 30, 14, 30);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_FEMMINILE, 19, 7,
				2009, 17, 0, 22, 0);
		aggiungiEvento("Team tecnico FINALI", NUOTO_SINCRONIZZATO, 19, 7, 2009,
				11, 0, 12, 30);
		aggiungiEvento("Singolo tecnico PRELIMINARI", NUOTO_SINCRONIZZATO, 19,
				7, 2009, 17, 0, 20, 0);

		/* 20 luglio 2009 */
		aggiungiEvento("Trampolino Sincronizzato 3m U PRELIMINARI", TUFFI, 20,
				7, 2009, 10, 0, 12, 0);
		aggiungiEvento("Piattaforma Sincronizzata 10m D PRELIMINARI", TUFFI,
				20, 7, 2009, 12, 45, 14, 0);
		aggiungiEvento("Trampolino Sincronizzato 3m U FINALI", TUFFI, 20, 7,
				2009, 16, 0, 17, 15);
		aggiungiEvento("Piattaforma Sincronizzata 10m D FINALI", TUFFI, 20, 7,
				2009, 18, 0, 19, 15);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_MASCHILE, 20, 7,
				2009, 9, 30, 14, 30);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_MASCHILE, 20, 7,
				2009, 17, 0, 22, 0);
		aggiungiEvento("Singolo tecnico FINALI", NUOTO_SINCRONIZZATO, 20, 7,
				2009, 11, 0, 12, 15);
		aggiungiEvento("Duo tecnico PRELIMINARI", NUOTO_SINCRONIZZATO, 20, 7,
				2009, 17, 0, 20, 30);

		/* 21 luglio 2009 */
		aggiungiEvento("10km D", NUOTO_IN_ACQUE_LIBERE, 21, 7, 2009, 10, 0, 12,
				30);
		aggiungiEvento("Trampolino 3m U PRELIMINARI", TUFFI, 21, 7, 2009, 10,
				0, 14, 45);
		aggiungiEvento("Trampolino 1m D PRELIMINARI", TUFFI, 21, 7, 2009, 16,
				0, 19, 0);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_FEMMINILE, 21, 7,
				2009, 9, 30, 14, 30);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_FEMMINILE, 21, 7,
				2009, 17, 0, 22, 0);
		aggiungiEvento("Duo tecnico FINALI", NUOTO_SINCRONIZZATO, 21, 7, 2009,
				11, 0, 12, 30);
		aggiungiEvento("Libero combinato PRELIMINARI", NUOTO_SINCRONIZZATO, 21,
				7, 2009, 17, 0, 19, 45);

		/* 22 luglio 2009 */
		aggiungiEvento("10km U", NUOTO_IN_ACQUE_LIBERE, 22, 7, 2009, 10, 0, 12,
				30);
		aggiungiEvento("Trampolino 1m D SF", TUFFI, 22, 7, 2009, 10, 0, 11, 15);
		aggiungiEvento("Trampolino 3m U SF", TUFFI, 22, 7, 2009, 12, 15, 14, 0);
		aggiungiEvento("Trampolino 1m D FINALI", TUFFI, 22, 7, 2009, 16, 0, 16,
				50);
		aggiungiEvento("Trampolino 1m U FINALI", TUFFI, 22, 7, 2009, 18, 0, 19,
				30);

		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_MASCHILE, 22, 7,
				2009, 9, 30, 14, 30);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_MASCHILE, 22, 7,
				2009, 17, 0, 22, 0);
		aggiungiEvento("Libero combinato FINALI", NUOTO_SINCRONIZZATO, 22, 7,
				2009, 11, 0, 13, 05);
		aggiungiEvento("Singolo libero PRELIMINARI", NUOTO_SINCRONIZZATO, 22,
				7, 2009, 17, 0, 20, 30);

		/* 23 luglio 2009 */
		aggiungiEvento("Trampolino 3m D PRELIMINARI", TUFFI, 23, 7, 2009, 10,
				0, 11, 20);
		aggiungiEvento("Piattaforma 10m U PRELIMINARI", TUFFI, 23, 7, 2009, 16,
				0, 19, 15);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_FEMMINILE, 23, 7,
				2009, 9, 30, 14, 30);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_FEMMINILE, 23, 7,
				2009, 17, 0, 22, 0);
		aggiungiEvento("Singolo libero FINALI", NUOTO_SINCRONIZZATO, 23, 7,
				2009, 11, 0, 12, 30);
		aggiungiEvento("Duo libero PRELIMINARI", NUOTO_SINCRONIZZATO, 23, 7,
				2009, 17, 0, 20, 30);

		/* 24 luglio 2009 */
		aggiungiEvento("25 km D", NUOTO_IN_ACQUE_LIBERE, 24, 7, 2009, 10, 0,
				16, 0);
		aggiungiEvento("Trampolino 3m D SF", TUFFI, 24, 7, 2009, 10, 0, 11, 30);
		aggiungiEvento("Piattaforma 10m U SF", TUFFI, 24, 7, 2009, 12, 15, 14,
				0);
		aggiungiEvento("Trampolino 3m D FINALE", TUFFI, 24, 7, 2009, 15, 0, 16,
				15);
		aggiungiEvento("Piattaforma 10m U FINALI", TUFFI, 24, 7, 2009, 17, 0,
				18, 30);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_MASCHILE, 24, 7,
				2009, 9, 30, 14, 30);
		aggiungiEvento("PRELIMINARI 4 Partite", PALLANUOTO_MASCHILE, 24, 7,
				2009, 17, 0, 22, 0);
		aggiungiEvento("Duo libero FINALI", NUOTO_SINCRONIZZATO, 24, 7, 2009,
				11, 0, 12, 30);
		aggiungiEvento("Team libero PRELIMINARI", NUOTO_SINCRONIZZATO, 24, 7,
				2009, 17, 0, 19, 55);

		/* 25 luglio 2009 */
		aggiungiEvento("25 km U", NUOTO_IN_ACQUE_LIBERE, 25, 7, 2009, 10, 0,
				15, 30);
		aggiungiEvento("Trampolino Sincronizzato 3m D PRELIMINARI", TUFFI, 25,
				7, 2009, 10, 0, 11, 30);
		aggiungiEvento("Piattaforma Sincronizzato 10m U PRELIMINARI", TUFFI,
				25, 7, 2009, 12, 15, 13, 45);
		aggiungiEvento("Trampolino Sincronizzato 3m D FINALI", TUFFI, 25, 7,
				2009, 15, 30, 16, 45);
		aggiungiEvento("Piattaforma Sincronizzato 10m U FINALI", TUFFI, 25, 7,
				2009, 17, 0, 18, 30);
		aggiungiEvento("QUARTI DI FINALE 3 Partite", PALLANUOTO_FEMMINILE, 25,
				7, 2009, 9, 0, 12, 40);
		aggiungiEvento("QUARTI DI FINALE 2 Partite", PALLANUOTO_FEMMINILE, 25,
				7, 2009, 15, 30, 17, 50);
		aggiungiEvento("QUARTI DI FINALE 1 Partita", PALLANUOTO_FEMMINILE, 25,
				7, 2009, 21, 0, 22, 0);
		aggiungiEvento("Team libero FINALI", NUOTO_SINCRONIZZATO, 25, 7, 2009,
				17, 0, 19, 55);

		/* 26 luglio 2009 */
		aggiungiEvento(
				"100m Farfalla D\n400m Stile libero U\n200m Misti D\n50m Farfalla U\n400m Stile libero D\n100m Rana U\n4x100m Stile libero D\n4x100m Stile libero U",
				NUOTO, 26, 7, 2009, 9, 30, 12, 30);
		aggiungiEvento(
				"100m Farfalla D SF\n400m Stile libero U\n200m Misti D SF\n50m Farfalla U SF\n400m Stile libero D\n100m Rana U SF\n4x100m Stile libero D\n4x100m Stile libero U",
				NUOTO, 26, 7, 2009, 18, 00, 20, 30);
		aggiungiEvento("QUARTI DI FINALE 3 Partite", PALLANUOTO_MASCHILE, 26,
				7, 2009, 9, 00, 12, 40);
		aggiungiEvento("QUARTI DI FINALE 2 Partite", PALLANUOTO_MASCHILE, 26,
				7, 2009, 15, 30, 17, 50);
		aggiungiEvento("QUARTI DI FINALE 1 Partita", PALLANUOTO_MASCHILE, 26,
				7, 2009, 21, 00, 22, 00);

		/* 27 luglio 2009 */
		aggiungiEvento(
				"100m Dorso D\n200m Stile libero U\n100m Rana D\n100m Dorso U\n1.500m Stile libero D",
				NUOTO, 27, 7, 2009, 9, 30, 11, 30);
		aggiungiEvento(
				"100m Rana U\n100m Farfalla D\n100m Dorso U SF\n100m Rana D SF\n50m Farfalla U\n100m Dorso D SF\n200m Stile libero U SF\n200m Misti D",
				NUOTO, 27, 7, 2009, 18, 00, 20, 30);
		aggiungiEvento("QUARTI DI FINALE 4 Partite", PALLANUOTO_FEMMINILE, 27,
				7, 2009, 9, 00, 14, 00);
		aggiungiEvento("QUARTI DI FINALE 3 Partite", PALLANUOTO_FEMMINILE, 27,
				7, 2009, 14, 00, 17, 40);
		aggiungiEvento("QUARTI DI FINALE 1 Partita", PALLANUOTO_FEMMINILE, 27,
				7, 2009, 21, 00, 22, 00);

		/* 28 luglio 2009 */
		aggiungiEvento(
				"100m Farfalla D\n400m Stile libero U\n200m Misti D\n50m Farfalla U\n400m Stile libero D\n100m Rana U\n4x100m Stile libero D\n4x100m Stile libero U",
				NUOTO, 28, 7, 2009, 9, 30, 11, 30);
		aggiungiEvento(
				"200m Stile Libero U\n100m Dorso D\n50m Rana U SF\n1.500m Stile Libero D\n100m Dorso U\n200m Stile Libero D SF\n200m Farfalla U SF\n100m Rana D",
				NUOTO, 28, 7, 2009, 18, 00, 20, 30);
		aggiungiEvento("QUARTI DI FINALE 4 Partite", PALLANUOTO_MASCHILE, 28,
				7, 2009, 9, 00, 12, 40);
		aggiungiEvento("QUARTI DI FINALE 3 Partite", PALLANUOTO_MASCHILE, 28,
				7, 2009, 14, 00, 17, 40);
		aggiungiEvento("QUARTI DI FINALE 1 Partita", PALLANUOTO_MASCHILE, 28,
				7, 2009, 21, 00, 22, 00);

		/* 29 luglio 2009 */
		aggiungiEvento(
				"50m Dorso D\n100m Stile libero U\n200m Farfalla D\n200m Misti U",
				NUOTO, 29, 7, 2009, 9, 30, 11, 30);
		aggiungiEvento(
				"100m Stile Libero U SF\n50m Dorso D SF\n200m Farfalla U\n200m Stile Libero D\n50m Rana U\n200m Farfalla D SF\n200m Misti U SF\n800m Stile Libero U",
				NUOTO, 29, 7, 2009, 18, 00, 20, 30);
		aggiungiEvento("QUARTI DI FINALE 3 Partite", PALLANUOTO_FEMMINILE, 29,
				7, 2009, 9, 00, 12, 40);
		aggiungiEvento("QUARTI DI FINALE 2 Partite", PALLANUOTO_FEMMINILE, 29,
				7, 2009, 15, 30, 17, 50);
		aggiungiEvento("QUARTI DI FINALE 1 Partita", PALLANUOTO_FEMMINILE, 29,
				7, 2009, 21, 00, 22, 00);

		/* 30 luglio 2009 */
		aggiungiEvento(
				"100m Stile Libero D\n200m Dorso U\n200m Rana D\n200m Rana U\n4x200m Stile libero D",
				NUOTO, 30, 7, 2009, 9, 30, 11, 30);
		aggiungiEvento(
				"100m Stile Libero D SF\n200m Misti U\n200m Rana D SF\n100m Stile Libero U\n200m Farfalla D\n200m Rana U SF\n50m Dorso D\n200m Dorso U SF\n4x200m Stile Libero D",
				NUOTO, 30, 7, 2009, 18, 00, 20, 30);
		aggiungiEvento("SEMIFINALI 3 Partite", PALLANUOTO_MASCHILE, 30, 7,
				2009, 9, 00, 12, 40);
		aggiungiEvento("SEMIFINALI 2 Partite", PALLANUOTO_MASCHILE, 30, 7,
				2009, 15, 30, 17, 50);
		aggiungiEvento("SEMIFINALI 1 Partita", PALLANUOTO_MASCHILE, 30, 7,
				2009, 21, 00, 22, 00);

		/* 31 luglio 2009 */
		aggiungiEvento(
				"50m Stile Libero D\n50m Farfalla D\n100m Farfalla D\n200m Dorso D\n4x200m Stile Libero U\n800m Stile Libero D",
				NUOTO, 31, 7, 2009, 9, 30, 11, 30);
		aggiungiEvento(
				"100m Stile Libero D\n200m Dorso U\n50m Farfalla D SF\n50m Stile Libero U SF\n200m Rana D\n100m Farfalla U SF\n200m Dorso D SF\n200m Rana U\n4x200m Stile Libero U",
				NUOTO, 31, 7, 2009, 18, 00, 20, 30);
		aggiungiEvento("FINALI (5°-8°posto) 2 Partite", PALLANUOTO_FEMMINILE,
				31, 7, 2009, 9, 00, 11, 40);
		aggiungiEvento("FINALI BRONZO 1 Partite", PALLANUOTO_FEMMINILE, 31, 7,
				2009, 15, 00, 18, 00);
		aggiungiEvento("FINALI ORO 1 Partita", PALLANUOTO_FEMMINILE, 31, 7,
				2009, 21, 00, 22, 00);

		/* 1 agosto 2009 */
		aggiungiEvento(
				"50m Stile Libero U\n50m Rana U\n50m Dorso U\n4x100m Mista D\n1.500m Stile Libero U",
				NUOTO, 1, 8, 2009, 9, 30, 12, 30);
		aggiungiEvento(
				"50m Farfalla D\n50m Stile Libero U\n200m Dorso D\n50m Rana D SF\n100m Farfalla U\n50m Stile Libero D SF\n50m Dorso U SF\n800m Stile Libero D\n4x100m Mista D",
				NUOTO, 1, 8, 2009, 18, 00, 20, 30);
		aggiungiEvento("FINALI (5°-8°posto) 2 Partite", PALLANUOTO_MASCHILE, 1,
				8, 2009, 9, 00, 11, 40);
		aggiungiEvento("FINALI BRONZO 1 Partite", PALLANUOTO_MASCHILE, 1, 8,
				2009, 15, 00, 18, 00);
		aggiungiEvento("FINALI ORO 1 Partita", PALLANUOTO_MASCHILE, 1, 8, 2009,
				21, 00, 22, 00);

		/* 2 agosto 2009 */
		aggiungiEvento("400m Misti U\n400m Misti D\n4x100m Mista U", NUOTO,
				2, 8, 2009, 9, 30, 11, 30);
		aggiungiEvento(
				"50m Dorso U\n50m Rana D\n400m Misti U\n50m Stile Libero D\n1.500m Stile Libero U\n400m Misti D\n4x100m Mista U",
				NUOTO, 2, 8, 2009, 18, 00, 20, 30);
	}

	public String getSpecialitaCorrente() {
		return specialitaCorrente;
	}

	public void setSpecialitaCorrente(String specialitaCorrente) {
		this.specialitaCorrente = specialitaCorrente;
	}

	public int getGiornoCorrente() {
		return giornoCorrente;
	}

	public void setGiornoCorrente(int giornoCorrente) {
		this.giornoCorrente = giornoCorrente;
	}

	public int getMeseCorrente() {
		return meseCorrente;
	}

	public void setMeseCorrente(int meseCorrente) {
		this.meseCorrente = meseCorrente;
	}

	private void aggiungiEvento(String descrizione, String specialita,
			int giorno, int mese, int anno, int startHour, int startMin,
			int endHour, int endMin) {
		Evento evento = new Evento(descrizione, specialita, giorno, mese, anno,
				startHour, startMin, endHour, endMin);
		eventi.addElement(evento);
	}

	public Vector getEventiBySpecialita(String specialita) {
		Vector eventiSpecialita = new Vector();

		for (int i = 0; i < eventi.size(); i++) {
			Evento evento = (Evento) eventi.elementAt(i);
			if (evento.getSpecialita().equals(specialita)) {
				eventiSpecialita.addElement(evento);
			}
		}
		return eventiSpecialita;
	}

	public Vector getEventiByData(int giorno, int mese, int anno) {
		Vector eventiData = new Vector();

		for (int i = 0; i < eventi.size(); i++) {
			Evento evento = (Evento) eventi.elementAt(i);
			if (evento.avviene(giorno, mese, anno)) {
				eventiData.addElement(evento);
			}
		}

		return eventiData;
	}

	public static void main(String[] args) {
		Roma09 roma09 = Roma09.getInstance();

		System.out.println("SPECIALITA'");
		Vector eventiSpecialita = roma09.getEventiBySpecialita(TUFFI);
		for (int i = 0; i < eventiSpecialita.size(); i++) {
			System.out.println(eventiSpecialita.elementAt(i));
		}

		System.out.println("DATE");
		Vector eventiDate = roma09.getEventiByData(19, 7, 2009);
		for (int i = 0; i < eventiDate.size(); i++) {
			System.out.println(eventiDate.elementAt(i));
		}
	}
}
