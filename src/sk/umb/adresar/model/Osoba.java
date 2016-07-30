package sk.umb.adresar.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sk.umb.adresar.model.enums.*;

/**
 * Trieda reprezentujuca model osoby
 * kde su udrziavane vsetky jej atributy
 */
public class Osoba {
	
	private String meno;
	private String priezvisko;
	private String tel;
	private int body;
	private Date datNar;
	private Pohlavie pohl;
	private RodStav rodStav;
	private Vzdelanie vzdelanie;
	private boolean zverejnit;
	private String poznamka;
	
	
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getBody() {
		return body;
	}
	public void setBody(int body) {
		this.body = body;
	}
	public Date getDatNar() {
		return datNar;
	}
	public void setDatNar(Date datNar) {
		this.datNar = datNar;
	}
	public Pohlavie getPohl() {
		return pohl;
	}
	public void setPohl(Pohlavie pohl) {
		this.pohl = pohl;
	}
	public RodStav getRodStav() {
		return rodStav;
	}
	public void setRodStav(RodStav rodStav) {
		this.rodStav = rodStav;
	}
	public Vzdelanie getVzdelanie() {
		return vzdelanie;
	}
	public void setVzdelanie(Vzdelanie vzdelanie) {
		this.vzdelanie = vzdelanie;
	}
	public boolean isZverejnit() {
		return zverejnit;
	}
	public void setZverejnit(boolean zverejnit) {
		this.zverejnit = zverejnit;
	}
	public String getPoznamka() {
		return poznamka;
	}
	public void setPoznamka(String poznamka) {
		this.poznamka = poznamka;
	}
	public String getPriezvisko() {
		return priezvisko;
	}
	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}
	
	@Override
	public String toString() {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return String.format(meno + " " + priezvisko + " | dat.Nar:" + format.format(datNar) + " | tel:" + tel + " " + " | body:" + Integer.toString(body));
	}
	
	
	
}
