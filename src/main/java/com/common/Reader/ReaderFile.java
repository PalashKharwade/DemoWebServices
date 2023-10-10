package com.common.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import com.common.model.helperPackage.HelperCurrencie;
import com.common.model.master.Pricing;
import com.common.model.master.PricingUnderlying;


public class ReaderFile {
	public static HelperCurrencie readBackHms(String filename){
		HashMap<String, String> hm = new HashMap<String, String>();
		HashMap<String, String> hmIsinName = new HashMap<String, String>();
		HashMap<String, String> hmIndex = new HashMap<String, String>();
		String val;
		String vals[];
		
		try {			
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			int counter = 0;
			
			while (reader.ready()) {
				val = reader.readLine();
				vals = val.split(";");
				if(vals[1].length()==12) {
					if(val.startsWith("EUR/") || val.startsWith("USD/") || val.startsWith("GBP/") || val.startsWith("AUD/")) {
					   hm.put(vals[0].replace("/", "").substring(0, 6),vals[1]);
					   hmIsinName.put(vals[1],vals[0].replace("/", "").substring(0, 6));
					}
					if(vals[4].equals("Index")) {
						hmIndex.put(vals[1],vals[0]);
					}
				}
			}
			//System.exit(0);
		}catch(Exception e) {
			System.out.println("readCurrenciepairs:" + e);
		}
		return new HelperCurrencie(hm, hmIndex, hmIsinName);
	}
	
	public static HashMap<String, PricingUnderlying> readPricingUnderlyingsCurrencies(String filename, HashMap<String, Pricing> currencies, HashMap<String, String> currenciepairs) {
		HashMap<String, PricingUnderlying> pricingcur = new HashMap<String, PricingUnderlying>();
		HashMap<String, PricingUnderlying> endpricing = new HashMap<String, PricingUnderlying>();
		
		
		int i = 0;
		String tmpwert = "";
		
		try {			
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			
			String vals[];
			String val;
			int a = 0;
			long start = System.currentTimeMillis();
			System.out.println("StartIndizes:" + start);
			while (reader.ready()) {
				val = reader.readLine();
				tmpwert = val;
				i++;
				vals = val.split(";");
				if(vals.length > 4) {
					if(vals[7].length() == 9 || vals[7].length() == 12) {
						if(new BigDecimal(vals[2]).compareTo(BigDecimal.ZERO) > 0 && new BigDecimal(vals[4]).compareTo(BigDecimal.ZERO) > 0) {
							if(vals[7].length() == 9) {
								String keyIsin = vals[7].substring(0,3);
								pricingcur.put("EURUSD", new PricingUnderlying("USD", new BigDecimal(vals[2])));
							}
							if(vals[7].length() == 12) {
								//if(!vals[7].substring(0,6).equals("")) {
									String keyIsin = vals[7].substring(0,6);
									pricingcur.put(keyIsin, new PricingUnderlying(keyIsin, new BigDecimal(vals[2])));
								//}
							}
						}
					}
				}
	            a++;				
				
	        }
			long end = System.currentTimeMillis();
			System.out.println("filename: " + filename+ " i:" + i + " Dauer:"  + (end-start)/1000);
		}catch (Exception e) {
			System.out.println("Fehler11: " + filename + i + " " + e + " " + tmpwert);
			System.exit(0);
		}		

		currenciepairs.forEach((key, value) -> {
			
			if(pricingcur.containsKey(key)) {
				//System.out.println(key + " " + value + " " + pricingcur.get(key).getAsk());
				endpricing.put(value, new PricingUnderlying(value, pricingcur.get(key).getAsk()));
			}else {
	        
	        	BigDecimal kurs1 = null;
	        	BigDecimal kurs2 = null;
	        	if(currencies.containsKey(key.substring(0,3))) {
	        	   kurs1 = currencies.get(key.substring(0,3)).getAsk();	
	        	}
	        	if(currencies.containsKey(key.substring(3,6))) {
		           kurs2 = currencies.get(key.substring(3,6)).getAsk();	
		        }
	        	if(!(kurs1 == null || kurs2 == null))endpricing.put(value, new PricingUnderlying(value, kurs2.divide(kurs1,4,RoundingMode.HALF_UP)));
	        	//System.out.println(key + " " + value + " " + kurs2.divide(kurs1,4,RoundingMode.HALF_UP));
	        	//System.out.println(key.substring(3,6) + " " + key.substring(0,3));
	        }
	    });
		
		
		return endpricing;
	}
}
