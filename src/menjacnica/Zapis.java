package menjacnica;

import java.util.Date;

public class Zapis {
	private String datumVreme;
	private String izValuta;
	private String uValuta;
	private double kurs;
	
	public Zapis(String datumVreme, String izValuta, String uValutu, double kurs) {
		this.datumVreme = datumVreme;
		this.izValuta = izValuta;
		this.uValuta = uValutu;
		this.kurs = kurs;
	}
	
	
	public Zapis() {
		
	}

	public String getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(String datumVreme) {
		this.datumVreme = datumVreme;
	}

	public String getIzValuta() {
		return izValuta;
	}

	public void setIzValuta(String izValuta) {
		this.izValuta = izValuta;
	}

	public String getuValuta() {
		return uValuta;
	}

	public void setuValuta(String uValuta) {
		this.uValuta = uValuta;
	}

	public double getKurs() {
		return kurs;
	}

	public void setKurs(double kurs) {
		this.kurs = kurs;
	}
	
	@Override
	public String toString() {
		return "Datum: " + getDatumVreme() + ", iz: " + getIzValuta() + ", u: " + getuValuta() + ", kurs: " + getKurs();
	}
}
