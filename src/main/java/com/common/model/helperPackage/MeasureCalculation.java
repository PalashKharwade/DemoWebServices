package com.common.model.helperPackage;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MeasureCalculation {
	
	private static final BigDecimal ONE_HUNDRED = BigDecimal.TEN.multiply(BigDecimal.TEN);
	private static final BigDecimal ONE_YEAR = new BigDecimal(365);
	private static final BigDecimal ONE = BigDecimal.ONE;
	private static final int AFTER_POINT = 20;

	public final static MathContext mc = new MathContext(AFTER_POINT, RoundingMode.HALF_UP);
	public final static MathContext mcPow = new MathContext(AFTER_POINT, RoundingMode.HALF_UP);
	

	/*public static BigDecimal calculateRateOptimizedBigDecimal(double endPrice, double price) {
		BigDecimal priceBd = new BigDecimal(price);
		BigDecimal endPriceBd = new BigDecimal(endPrice);
	    return endPriceBd.subtract(priceBd).multiply(ONE_HUNDRED).divide(priceBd, mc);
	}*/
	
	
						 	
	public static double calculateAnnualeRateOriginal(double rate, int restDays) {
		if(restDays<=0) {
			return 0d;
		}
		double pow = (double) ONE_YEAR.intValue() / (double) restDays;
		return (Math.pow((ONE.intValue() + rate / ONE_HUNDRED.doubleValue()), (pow)) - ONE.intValue()) * ONE_HUNDRED.intValue();
	}
	
						     
	public static BigDecimal calculateRateOptimizedBigDecimal(BigDecimal endPrice, BigDecimal price) {
		return endPrice.subtract(price).multiply(ONE_HUNDRED).divide(price, mc);
	}
	
	/*public static double calculateAnnualRateOptimized(BigDecimal rateBd, int restDays) {
		if(restDays<=0) {
			return 0d;
		}
		BigDecimal restDaysBd = new BigDecimal(restDays, MathContext.DECIMAL64);
		BigDecimal pow = ONE_YEAR.divide(restDaysBd, mcPow);
	    return BigDecimalMath.pow(ONE_HUNDRED.add(rateBd).divide(ONE_HUNDRED, mc),pow).subtract(ONE).multiply(ONE_HUNDRED).doubleValue();
	}*/
	
	public static BigDecimal calculateAddOn(Integer typ, BigDecimal ask, BigDecimal bv, BigDecimal bw, BigDecimal knockin, String wkn, boolean isCur, boolean isQuanto, BigDecimal curfaktor, boolean isIndex, BigDecimal currenciePartnerValue) {
		
		
		
		if(curfaktor == null) {
			curfaktor = new BigDecimal("1.00");
		}
		
		try {
			//( [ (basiswert_kurs - strike) / basiswert_kurs * Ratio ] - ask ) / Ratio
			if(typ == 22 || typ == 40 || typ == 42) {
				if(!isCur) {
					return (    (ask.divide(bv, 4, RoundingMode.HALF_UP)).subtract((knockin.subtract(bw)).divide(curfaktor, 4, RoundingMode.HALF_UP)   )  );
				}else {
					//( ask - [ (strike - basiswertkurs) / basiswertkurs * Ratio ]) / Ratio
					return ((ask.subtract(((knockin.subtract(bw)).divide(currenciePartnerValue,6, RoundingMode.HALF_UP)).multiply(bv)))).divide(bv,6, RoundingMode.HALF_UP);
				}
			}
			//( ask - [ (basiswert_kurs - strike) / basiswert_kurs * Ratio ] ) / Ratio
			if(typ == 23 || typ == 41 || typ == 43) {
				
				/*System.out.println("bw:" +  bw);
				System.out.println("knockin:" + knockin);
				System.out.println("ask:" + ask);
				System.out.println("ask:" + bv);
				System.out.println("curfaktor:" + curfaktor);
				System.out.println("currenciePartnerValue:" + currenciePartnerValue);*/
				
				if(!isCur) {
					
				//	System.out.println("--->" + (bw.subtract(knockin)));
				//	System.out.println("--->" + (bw.subtract(knockin)).divide(curfaktor, 4, RoundingMode.HALF_UP)   );
					
					
					return (    (ask.divide(bv, 4, RoundingMode.HALF_UP)).subtract((bw.subtract(knockin)).divide(curfaktor, 4, RoundingMode.HALF_UP)   )  );
				}else {
					
					return ((ask.subtract(((bw.subtract(knockin)).divide(currenciePartnerValue,6, RoundingMode.HALF_UP)).multiply(bv)))).divide(bv,6, RoundingMode.HALF_UP);
				}
			}
			return new BigDecimal("0.00");
			
		}catch(Exception e) {
			System.out.println("Fehler13:" + e);
			System.out.println("Fehler133:" + ask + " " +  bv + " " + bw + " " + knockin);
		}
		return new BigDecimal("0.00");  
		
	}

}
