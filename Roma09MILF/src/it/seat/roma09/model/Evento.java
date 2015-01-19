package it.seat.roma09.model;

import java.util.Calendar;
import java.util.Date;

public class Evento {
	private String descrizione;
	private String specialita;
	private Date dataInizio, dataFine;

	public Evento(String descrizione, String specialita, int giorno, int mese,
			int anno, int startHour, int startMin, int endHour, int endMin) {
		this.descrizione = descrizione;
		this.specialita = specialita;

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, anno);
		calendar.set(Calendar.MONTH, mese - 1);
		calendar.set(Calendar.DAY_OF_MONTH, giorno);
		calendar.set(Calendar.HOUR_OF_DAY, startHour);
		calendar.set(Calendar.MINUTE, startMin);
		dataInizio = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, endHour);
		calendar.set(Calendar.MINUTE, endMin);
		dataFine = calendar.getTime();
	}

	public Evento(String descrizione, String specialita) {
		this.descrizione = descrizione;
		this.specialita = specialita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getSpecialita() {
		return specialita;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String formattaData() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInizio);
		String string = calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.YEAR);
		return string;
	}

	public String formattaOra() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInizio);
		String string = "Orario " + calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ formatMinute(calendar.get(Calendar.MINUTE)) + " - ";
		calendar.setTime(dataFine);
		string += calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ formatMinute(calendar.get(Calendar.MINUTE));
		return string;
	}

	public boolean avviene(int giorno, int mese, int anno) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInizio);
		return calendar.get(Calendar.YEAR) == anno
				&& calendar.get(Calendar.MONTH) == mese - 1
				&& calendar.get(Calendar.DAY_OF_MONTH) == giorno;
	}

	private String formatMinute(int minute) {
		String minuteString = "" + minute;
		if (minuteString.length() == 1) {
			minuteString = "0" + minuteString;
		}
		return minuteString;
	}

	public String toString() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInizio);
		String string = calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.YEAR) + "\n";
		string += descrizione + "\n";
		string += "Orario " + calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ formatMinute(calendar.get(Calendar.MINUTE)) + " - ";
		calendar.setTime(dataFine);
		string += calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ formatMinute(calendar.get(Calendar.MINUTE));

		return string;
	}
}
