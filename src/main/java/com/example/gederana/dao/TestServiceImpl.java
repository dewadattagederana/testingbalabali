/**
 * 
 */
package com.example.gederana.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.gederana.model.TestModelFX;

/**
 * @author gederanadewadatta
 *
 */
public class TestServiceImpl implements TestService {
 
	@Value("${app.otherurl}")
	private String otherurl;

	private String readHTML;
	 TestModelFX model = new TestModelFX();
	private Date date1;
	private Date date2;

	/**
	 * @return the readHTML
	 */
	public String getReadHTML() {
		return readHTML;
	}

	/**
	 * @param readHTML the readHTML to set
	 */
	public void setReadHTML(String readHTML) {
		this.readHTML = readHTML;
	}

	@Override
	public TestModelFX getFX(String second, String third, String fourth, String fifth) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		ArrayList<String> data = new ArrayList<String>();
		try {
			c.setTime(sdf.parse(fourth));
			c.add(Calendar.DAY_OF_MONTH, 7);
			String newDate = sdf.format(c.getTime());
			date1 = new SimpleDateFormat("yyyyMMdd").parse(newDate);
			date2 = new SimpleDateFormat("yyyyMMdd").parse(fifth);
			if (date1.before(date2)) {
				model.setCurrency(null);
				model.setDate(null);
				model.setKUPOVNI(null);
				model.setNr(null);
				model.setPRODAJNI(null);
				model.setSREDNJI(null); 
				
			} else {
				data = getData3rdParty(second, third, fourth, fifth);
				for(int i = 0; i <data.size();i++) {
					String[] datas = data.get(i).split(",",6);
					model.setNr(datas[0]);
					model.setDate(datas[1]);
					model.setCurrency(datas[2]);
					if(!datas[3].equals(null)) {
					model.setKUPOVNI(datas[3]);}
					if(!datas[4].equals(null)) {
					model.setSREDNJI(datas[4]);}
					if(!datas[5].equals(null)) {
					model.setPRODAJNI(datas[5]);}
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	public ArrayList getData3rdParty(String second, String third, String firstDate, String lastDate) throws ParseException {
		String nr;
		String[] secondss = second.split(",");
		String[] thirdss = third.split(",");
		ArrayList<String> ar = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");

		String newDate = sdf.format(firstDate);
		Calendar start = Calendar.getInstance();
		start.setTime(sdf.parse(firstDate));
		Calendar end = Calendar.getInstance();
		end.setTime(sdf.parse(lastDate));



		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {

			try {
				URL url = new URL(otherurl + date + ".htm");
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

				String inputLine;
				while ((inputLine = in.readLine()) != null)
					// System.out.println(inputLine);
					this.setReadHTML(inputLine);
				in.close();

			} catch (MalformedURLException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();

			}
			String[] temp1 = this.getReadHTML().split("TEČAJNA LISTA BROJ:");
			String[] temp2 = temp1[1].split("utvrđena");
			nr = temp2[0].replaceAll("\\s", "");
			String data = nr+","+date+",";
			try {
				Document root;
				root = Jsoup.parse(new URL(otherurl + date + ".htm"), 30000);
				// find all tables
				Elements tables = root.select("table");
				for (int m = 0; m < tables.size(); m++) {
					final Element table = tables.get(m);
					Elements th0 = table.select("tbody tr th");
					// find our tables
					if (th0 != null && th0.get(0).text().trim().equals("Zemlja")) {
						Elements es = table.select("tbody tr");
						for (int j = 1; j < es.size(); j++) {
							Elements td = es.get(j).select("td");
							String val = td.get(2).text();
							String KUPOVNI = td.get(4).text();
							String SREDNJI = td.get(5).text();
							String PRODAJNI = td.get(6).text();
//							System.out.println(val + " => " + KUPOVNI + ";" + SREDNJI + ";" + PRODAJNI);

							for (int i = 0; i < secondss.length; i++) {
								if (secondss[i] != null) {
									if (secondss[i].equals(val)) {
										data += val + ",";
									}
									for (int k = 0; k < thirdss.length; k++) {
										if (thirdss[i] != null) {
											if (thirdss[i].equals("KUPOVNI")) {
												data += KUPOVNI + ",";
												continue;
											} else if (thirdss[i].equals("SREDNJI")) {
												data += SREDNJI + ",";
												continue;
											} else if (thirdss[i].equals("PRODAJNI")) {
												data += PRODAJNI + ",";
												continue;
											} else {
												continue;
											}
										}
									}
									ar.add(data);

								}
							}
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return ar;
	}
}
