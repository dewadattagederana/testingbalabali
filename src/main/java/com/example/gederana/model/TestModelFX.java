/**
 * 
 */
package com.example.gederana.model;

/**
 * @author anitamarsafira
 *
 */
public class TestModelFX {
	private String currency;
	private String nr;
	private String KUPOVNI;
	private String SREDNJI;
	private String PRODAJNI;
	private String date; 
	/**
	 * @return the nr
	 */
	public String getNr() {
		return nr;
	}
	/**
	 * @param nr the nr to set
	 */
	public void setNr(String nr) {
		this.nr = nr;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the kUPOVNI
	 */
	public String getKUPOVNI() {
		return KUPOVNI;
	}
	/**
	 * @param kUPOVNI the kUPOVNI to set
	 */
	public void setKUPOVNI(String kUPOVNI) {
		KUPOVNI = kUPOVNI;
	}
	/**
	 * @return the sREDNJI
	 */
	public String getSREDNJI() {
		return SREDNJI;
	}
	/**
	 * @param sREDNJI the sREDNJI to set
	 */
	public void setSREDNJI(String sREDNJI) {
		SREDNJI = sREDNJI;
	}
	/**
	 * @return the pRODAJNI
	 */
	public String getPRODAJNI() {
		return PRODAJNI;
	}
	/**
	 * @param pRODAJNI the pRODAJNI to set
	 */
	public void setPRODAJNI(String pRODAJNI) {
		PRODAJNI = pRODAJNI;
	}
	@Override
	public String toString() {
		return currency+"[ nr=" + nr + ", KUPOVNI=" + KUPOVNI + ", SREDNJI=" + SREDNJI
				+ ", PRODAJNI=" + PRODAJNI + ", date=" + date + "]";
	}
	 
	  
	
}
