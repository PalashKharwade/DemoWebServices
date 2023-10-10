package com.common.model.Business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MiniStamm {

	String isin;
	String wkn;
	String emi;
	String waehrung;
	Integer typ;
	Integer size;
	Integer exerciseType;
	String u_isin;
	BigDecimal ask;
	BigDecimal bid;
	BigDecimal mid;
	
	BigDecimal askCur;
	BigDecimal bidCur;
	BigDecimal midCur;
	
	BigDecimal strike;
	BigDecimal addon;
	BigDecimal addonBid;
	BigDecimal addonMid;
	BigDecimal knockin;
	BigDecimal bv;
	BigDecimal abstand;
	BigDecimal spread;
	BigDecimal curfaktor;
	
	Boolean quanto;
//	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate maturity;
	Double cap;
	BigDecimal underlyingprice;
	BigDecimal floor;
	BigDecimal barriere;
	BigDecimal maxRendite;
	Double maxRenditepa;
	Double sideAnno;
	Double sideAnnoMid;
	Double sideAnnoBid;
	BigDecimal side;
	BigDecimal sideBid;
	BigDecimal sideMid;	
	
	BigDecimal maxRenditeBid;
	Double maxRenditepaBid;
	BigDecimal maxRenditeMid;
	Double maxRenditepaMid;
	BigDecimal measure;
	
	int underlyingtime;
	int derivativetime;
	long restdays;
	boolean measureNullor0 = false;
	
	   private int rankMeasure;
	   private int rankSpread;
	   private int rankSize;
	   

	   private String wkn1Measure;
	   private String wkn1Spread;
	   private String wkn1Size;

	   private String emi1Measure;
	   private String emi1Spread;
	   private String emi1Size;

	   private String wkn2Measure;
	   private String wkn2Spread;
	   private String wkn2Size;   
	   
	   private String emi2Measure;
	   private String emi2Spread;
	   private String emi2Size;
	
	   private BigDecimal distanceMeasure;
	   private BigDecimal distanceSpread;
	   private Integer distanceSize;
	   
	   private BigDecimal distanceMeasure2;
	   private BigDecimal distanceSpread2;
	   private Integer distanceSize2;

	   private boolean inPeer = false;
	   
	   private Integer bwtime;
	   private Integer dvtime;
	   
	   private BigDecimal measure3;
	   private BigDecimal measure4;
	   private BigDecimal measure5;
	   private BigDecimal measure6;
	   private BigDecimal measure7;
	   
	   private BigDecimal spread3;
	   private BigDecimal spread4;
	   private BigDecimal spread5;
	   private BigDecimal spread6;
	   private BigDecimal spread7;
	   
	   private Integer size3;
	   private Integer size4;
	   private Integer size5;
	   private Integer size6;
	   private Integer size7;
	   
	   
	   
	   
	public MiniStamm() {
		
	}
	
	public MiniStamm(Integer typ, String isin, String wkn, String u_isin, String emi, String waehrung, BigDecimal bv, BigDecimal knockin) {
		// TODO Auto-generated constructor stub
		this.isin = isin;
		this.wkn = wkn;
		this.emi = emi;
		this.waehrung = waehrung;
		this.typ = typ;
		this.bv = bv;
		this.knockin = knockin;	
		this.u_isin = u_isin;
	}
	
	public MiniStamm(Integer typ, String isin, String wkn, String u_isin, String emi, String waehrung, BigDecimal bv, Double cap) {
		// TODO Auto-generated constructor stub
		this.isin = isin;
		this.wkn = wkn;
		this.emi = emi;
		this.waehrung = waehrung;
		this.typ = typ;
		this.bv = bv;
		this.u_isin = u_isin;
		this.cap = cap;
	}
	
	
	
	public BigDecimal getStrike() {
		return strike;
	}

	public void setStrike(BigDecimal strike) {
		this.strike = strike;
	}

	public BigDecimal getAskCur() {
		return askCur;
	}

	public void setAskCur(BigDecimal askCur) {
		this.askCur = askCur;
	}

	public BigDecimal getBidCur() {
		return bidCur;
	}

	public void setBidCur(BigDecimal bidCur) {
		this.bidCur = bidCur;
	}

	public BigDecimal getMidCur() {
		return midCur;
	}

	public void setMidCur(BigDecimal midCur) {
		this.midCur = midCur;
	}

	public Integer getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(Integer exerciseType) {
		this.exerciseType = exerciseType;
	}

	public BigDecimal getMid() {
		return mid;
	}

	public void setMid(BigDecimal mid) {
		this.mid = mid;
	}

	public BigDecimal getMaxRenditeMid() {
		return maxRenditeMid;
	}

	public void setMaxRenditeMid(BigDecimal maxRenditeMid) {
		this.maxRenditeMid = maxRenditeMid;
	}

	public Double getMaxRenditepaMid() {
		return maxRenditepaMid;
	}

	public void setMaxRenditepaMid(Double maxRenditepaMid) {
		this.maxRenditepaMid = maxRenditepaMid;
	}

	public BigDecimal getAddonMid() {
		return addonMid;
	}

	public void setAddonMid(BigDecimal addonMid) {
		this.addonMid = addonMid;
	}

	public Double getSideAnnoMid() {
		return sideAnnoMid;
	}

	public void setSideAnnoMid(Double sideAnnoMid) {
		this.sideAnnoMid = sideAnnoMid;
	}

	public BigDecimal getSideMid() {
		return sideMid;
	}

	public void setSideMid(BigDecimal sideMid) {
		this.sideMid = sideMid;
	}

	public BigDecimal getMeasure3() {
		return measure3;
	}

	public void setMeasure3(BigDecimal measure3) {
		this.measure3 = measure3;
	}



	public BigDecimal getMeasure4() {
		return measure4;
	}



	public void setMeasure4(BigDecimal measure4) {
		this.measure4 = measure4;
	}



	public BigDecimal getMeasure5() {
		return measure5;
	}



	public void setMeasure5(BigDecimal measure5) {
		this.measure5 = measure5;
	}



	public BigDecimal getMeasure6() {
		return measure6;
	}



	public void setMeasure6(BigDecimal measure6) {
		this.measure6 = measure6;
	}



	public BigDecimal getMeasure7() {
		return measure7;
	}



	public void setMeasure7(BigDecimal measure7) {
		this.measure7 = measure7;
	}



	public BigDecimal getSpread3() {
		return spread3;
	}



	public void setSpread3(BigDecimal spread3) {
		this.spread3 = spread3;
	}



	public BigDecimal getSpread4() {
		return spread4;
	}



	public void setSpread4(BigDecimal spread4) {
		this.spread4 = spread4;
	}



	public BigDecimal getSpread5() {
		return spread5;
	}



	public void setSpread5(BigDecimal spread5) {
		this.spread5 = spread5;
	}



	public BigDecimal getSpread6() {
		return spread6;
	}



	public void setSpread6(BigDecimal spread6) {
		this.spread6 = spread6;
	}



	public BigDecimal getSpread7() {
		return spread7;
	}



	public void setSpread7(BigDecimal spread7) {
		this.spread7 = spread7;
	}



	public Integer getSize3() {
		return size3;
	}



	public void setSize3(Integer size3) {
		this.size3 = size3;
	}



	public Integer getSize4() {
		return size4;
	}



	public void setSize4(Integer size4) {
		this.size4 = size4;
	}



	public Integer getSize5() {
		return size5;
	}



	public void setSize5(Integer size5) {
		this.size5 = size5;
	}



	public Integer getSize6() {
		return size6;
	}



	public void setSize6(Integer size6) {
		this.size6 = size6;
	}



	public Integer getSize7() {
		return size7;
	}



	public void setSize7(Integer size7) {
		this.size7 = size7;
	}



	public BigDecimal getAddonBid() {
		return addonBid;
	}

	public void setAddonBid(BigDecimal addonBid) {
		this.addonBid = addonBid;
	}

	public Integer getBwtime() {
		return bwtime;
	}

	public BigDecimal getMaxRenditeBid() {
		return maxRenditeBid;
	}

	public void setMaxRenditeBid(BigDecimal maxRenditeBid) {
		this.maxRenditeBid = maxRenditeBid;
	}

	public Double getMaxRenditepaBid() {
		return maxRenditepaBid;
	}

	public void setMaxRenditepaBid(Double maxRenditepaBid) {
		this.maxRenditepaBid = maxRenditepaBid;
	}

	public Integer getDvtime() {
		return dvtime;
	}




	public void setBwtime(Integer bwtime) {
		this.bwtime = bwtime;
	}



	public void setDvtime(Integer dvtime) {
		this.dvtime = dvtime;
	}
	
	public boolean isInPeer() {
		return inPeer;
	}

	public void setInPeer(boolean inPeer) {
		this.inPeer = inPeer;
	}



	public BigDecimal getDistanceMeasure() {
		return distanceMeasure;
	}

	public void setDistanceMeasure(BigDecimal distanceMeasure) {
		this.distanceMeasure = distanceMeasure;
	}



	public BigDecimal getDistanceSpread() {
		return distanceSpread;
	}



	public void setDistanceSpread(BigDecimal distanceSpread) {
		this.distanceSpread = distanceSpread;
	}



	public Integer getDistanceSize() {
		return distanceSize;
	}



	public void setDistanceSize(Integer distanceSize) {
		this.distanceSize = distanceSize;
	}



	public BigDecimal getDistanceMeasure2() {
		return distanceMeasure2;
	}



	public void setDistanceMeasure2(BigDecimal distanceMeasure2) {
		this.distanceMeasure2 = distanceMeasure2;
	}



	public BigDecimal getDistanceSpread2() {
		return distanceSpread2;
	}



	public void setDistanceSpread2(BigDecimal distanceSpread2) {
		this.distanceSpread2 = distanceSpread2;
	}



	public Integer getDistanceSize2() {
		return distanceSize2;
	}



	public void setDistanceSize2(Integer distanceSize2) {
		this.distanceSize2 = distanceSize2;
	}



	public int getRankMeasure() {
		return rankMeasure;
	}



	public void setRankMeasure(int rankMeasure) {
		this.rankMeasure = rankMeasure;
	}



	public int getRankSpread() {
		return rankSpread;
	}



	public void setRankSpread(int rankSpread) {
		this.rankSpread = rankSpread;
	}



	public int getRankSize() {
		return rankSize;
	}



	public void setRankSize(int rankSize) {
		this.rankSize = rankSize;
	}



	public String getWkn1Measure() {
		return wkn1Measure;
	}



	public void setWkn1Measure(String wkn1Measure) {
		this.wkn1Measure = wkn1Measure;
	}



	public String getWkn1Spread() {
		return wkn1Spread;
	}



	public void setWkn1Spread(String wkn1Spread) {
		this.wkn1Spread = wkn1Spread;
	}



	public String getWkn1Size() {
		return wkn1Size;
	}



	public void setWkn1Size(String wkn1Size) {
		this.wkn1Size = wkn1Size;
	}



	public String getEmi1Measure() {
		return emi1Measure;
	}



	public void setEmi1Measure(String emi1Measure) {
		this.emi1Measure = emi1Measure;
	}



	public String getEmi1Spread() {
		return emi1Spread;
	}



	public void setEmi1Spread(String emi1Spread) {
		this.emi1Spread = emi1Spread;
	}



	public String getEmi1Size() {
		return emi1Size;
	}



	public void setEmi1Size(String emi1Size) {
		this.emi1Size = emi1Size;
	}



	public String getWkn2Measure() {
		return wkn2Measure;
	}



	public void setWkn2Measure(String wkn2Measure) {
		this.wkn2Measure = wkn2Measure;
	}



	public String getWkn2Spread() {
		return wkn2Spread;
	}



	public void setWkn2Spread(String wkn2Spread) {
		this.wkn2Spread = wkn2Spread;
	}



	public String getWkn2Size() {
		return wkn2Size;
	}



	public void setWkn2Size(String wkn2Size) {
		this.wkn2Size = wkn2Size;
	}



	public String getEmi2Measure() {
		return emi2Measure;
	}



	public void setEmi2Measure(String emi2Measure) {
		this.emi2Measure = emi2Measure;
	}



	public String getEmi2Spread() {
		return emi2Spread;
	}



	public void setEmi2Spread(String emi2Spread) {
		this.emi2Spread = emi2Spread;
	}



	public String getEmi2Size() {
		return emi2Size;
	}



	public void setEmi2Size(String emi2Size) {
		this.emi2Size = emi2Size;
	}



	public BigDecimal getMeasure() {
		return measure;
	}

	public void setMeasure(BigDecimal measure) {
		this.measure = measure.setScale(5, RoundingMode.HALF_UP);
	}

	public BigDecimal getCurfaktor() {
		return curfaktor;
	}

	public void setCurfaktor(BigDecimal curfaktor) {
		this.curfaktor = curfaktor;
	}

	public Boolean getQuanto() {
		return quanto;
	}

	public void setQuanto(Boolean quanto) {
		this.quanto = quanto;
	}

	public boolean isMeasureNullor0() {
		return measureNullor0;
	}

	public void setMeasureNullor0(boolean measureNullor0) {
		this.measureNullor0 = measureNullor0;
	}

	public Double getSideAnnoBid() {
		return sideAnnoBid;
	}

	public void setSideAnnoBid(Double sideAnnoBid) {
		this.sideAnnoBid = sideAnnoBid;
	}

	public BigDecimal getSideBid() {
		return sideBid;
	}

	public void setSideBid(BigDecimal sideBid) {
		this.sideBid = sideBid;
	}

	public BigDecimal getSide() {
		return side;
	}


	public void setSide(BigDecimal side) {
		this.side = side;
	}


	public BigDecimal getMaxRendite() {
		return maxRendite;
	}


	public void setMaxRendite(BigDecimal maxRendite) {
		this.maxRendite = maxRendite;
	}

	public Double getMaxRenditepa() {
		return maxRenditepa;
	}

	public void setMaxRenditepa(Double maxRenditepa) {
		this.maxRenditepa = maxRenditepa;
	}

	public BigDecimal getBarriere() {
		return barriere;
	}

	public void setBarriere(BigDecimal barriere) {
		this.barriere = barriere;
	}


	public BigDecimal getFloor() {
		return floor;
	}


	public void setFloor(BigDecimal floor) {
		this.floor = floor;
	}


	public Double getSideAnno() {
		return sideAnno;
	}


	public void setSideAnno(Double sideAnno) {
		this.sideAnno = sideAnno;
	}

//
	public void setMaturity(LocalDate maturity) {
		this.maturity = maturity;
	}


	public LocalDate getMaturity() {
		return maturity;
	}
//
//
//	public void setMaturity(String maturity) {
//		this.maturity = LocalDate.parse(maturity);
//	}


	public Double getCap() {
		return cap;
	}

	public void setCap(Double cap) {
		this.cap = cap;
	}

	public Integer getSize() {
		return size;
	}


	public void setSize(Integer size) {
		this.size = size;
	}


	public String getU_isin() {
		return u_isin;
	}


	public void setU_isin(String u_isin) {
		this.u_isin = u_isin;
	}


	public long getRestdays() {
		//long restDays = TimeUnit.DAYS.convert(derivative.getValdate().getTime() - new Date().getTime(), TimeUnit.MILLISECONDS);
		return ChronoUnit.DAYS.between(LocalDate.now(),maturity);
	}


	public void setRestdays(long restdays) {
		this.restdays = restdays;
	}


	public BigDecimal getUnderlyingprice() {
		return underlyingprice;
	}

	public void setUnderlyingprice(BigDecimal underlyingprice) {
		this.underlyingprice = underlyingprice;
	}

	public BigDecimal getSpread() {
		return this.ask.subtract(this.bid);
	}
	
	public void setSpread(BigDecimal spread) {
		this.spread = spread;
	}

	public BigDecimal getBid() {
		return bid;
	}


	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}



	public BigDecimal getAbstand() {
		return abstand;
	}



	public void setAbstand(BigDecimal abstand) {
		this.abstand = abstand;
	}



	public BigDecimal getAddon() {
		return addon;
	}



	public void setAddon(BigDecimal addon) {
		this.addon = addon;
	}



	public int getUnderlyingtime() {
		return underlyingtime;
	}



	public void setUnderlyingtime(int underlyingtime) {
		this.underlyingtime = underlyingtime;
	}



	public int getDerivativetime() {
		return derivativetime;
	}



	public void setDerivativetime(int derivativetime) {
		this.derivativetime = derivativetime;
	}



	public BigDecimal getAsk() {
		return ask;
	}



	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	public Integer getTyp() {
		return this.typ;
	}
	
	public String getU_Isin() {
		return this.u_isin;
	}

	public void setU_Isin(String isin) {
		this.u_isin = isin;
	}

	

	public BigDecimal getKnockin() {
		return knockin;
	}

	public void setKnockin(BigDecimal knockin) {
		this.knockin = knockin;
	}

	public BigDecimal getBv() {
		return bv;
	}

	public void setBv(BigDecimal bv) {
		this.bv = bv;
	}

	public void setTyp(Integer typ) {
		this.typ = typ;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getWkn() {
		return wkn;
	}

	public void setWkn(String wkn) {
		this.wkn = wkn;
	}

	public String getEmi() {
		return emi;
	}

	public void setEmi(String emi) {
		this.emi = emi;
	}

	public String getWaehrung() {
		return waehrung;
	}

	public void setWaehrung(String waehrung) {
		this.waehrung = waehrung;
	}
	

//	@Override
//	public String toString() {
/*		if(typ == 1) {
			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
					+ ";" + u_isin + ";" + cap + ";" + sideAnno + ";" + restdays + ";" + ask + ";" + bid + ";" + underlyingprice + ";" + bv
					+ ";" + underlyingtime + ";" + derivativetime + ";" + size + ";" + maturity + ";" + maturity.getYear()+"-" + maturity.getMonthValue() + "\n";
		}else if(typ == 7 || typ == 8) {
			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
					+ ";" + u_isin + ";" + cap + ";" + sideAnno + ";" + restdays + ";" + ask + ";" + bid + ";" + underlyingprice + ";" + bv
					+ ";" + underlyingtime + ";" + derivativetime + ";" + size + ";" + maturity + ";" + maturity.getYear()+"-" + maturity.getMonthValue() + ";" + floor + "\n";
		}else if(typ == 2 || typ == 4) {
			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
					+ ";" + u_isin + ";" + cap + ";" + sideAnno + ";" + restdays + ";" + ask + ";" + bid + ";" + underlyingprice + ";" + bv
					+ ";" + underlyingtime + ";" + derivativetime + ";" + size + ";" + maturity + ";" + maturity.getYear()+"-" + maturity.getMonthValue() + "\n";
		}else {
			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
				+ ";" + u_isin + ";" + ask + ";" + bid + ";" + addon + ";" + underlyingprice + ";" + knockin + ";" + bv
				+ ";" + underlyingtime + ";" + derivativetime + ";" + abstand + ";" + size + "\n";
		}*/
		
//		if(typ == 42) {
//			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
//					+ ";" + u_isin + ";" + cap + ";" + (measureNullor0 ? "\\N" : sideAnno) + ";" + restdays + ";" + ask + ";" + bid + ";" + underlyingprice + ";" + bv
//					+ ";" + underlyingtime + ";" + derivativetime + ";" + size + ";" + maturity + ";\\N;" + (curfaktor == null ? "\\N;" : curfaktor) + ";\\N" + ";" + sideAnnoBid + ";" + sideAnnoMid + ";" + askCur + ";" + bidCur + "\n" ;
//			}else if(typ == 7 || typ == 8) {
//			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
//					+ ";" + u_isin + ";" + cap + ";" + (measureNullor0 ? "\\N" : sideAnno) + ";" + restdays + ";" + ask + ";" + bid + ";" + underlyingprice + ";" + bv
//					+ ";" + underlyingtime + ";" + derivativetime + ";" + size + ";" + maturity + ";" + maturity.getYear()+"-" + maturity.getMonthValue() + ";" + floor  + ";" +  (curfaktor == null ? ";\\N;" : curfaktor)  + "\\N" + ";" + sideAnnoBid + ";" + sideAnnoMid + ";" + askCur + ";" + bidCur + "\n";
//		}else if(typ == 2 || typ == 4) {
//			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
//					+ ";" + u_isin + ";" + cap + ";" + (measureNullor0 ? "\\N" : sideAnno) + ";" + restdays + ";" + ask + ";" + bid + ";" + underlyingprice + ";" + bv
//					+ ";" + underlyingtime + ";" + derivativetime + ";" + size + ";" + maturity + ";" + maturity.getYear()+"-" + maturity.getMonthValue() + ";" + barriere + ";" + (curfaktor == null ? ";\\N;" : curfaktor) + "\\N" + ";" + sideAnnoBid  + ";" + sideAnnoMid + ";" + askCur + ";" + bidCur + "\n";
//		}else if(typ == 31) {
//			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
//					+ ";" + u_isin + ";" + barriere + ";" + (measureNullor0 ? "\\N" : side) + ";" + restdays + ";" + ask + ";" + bid + ";" + underlyingprice + ";" + bv
//					+ ";" + underlyingtime + ";" + derivativetime + ";" + size + ";" + maturity + ";" + maturity.getYear()+"-" + maturity.getMonthValue() + ";" + cap + ";" + (curfaktor == null ? "\\N;" : curfaktor) + ";\\N" + ";" + sideAnnoBid  + ";" + sideAnnoMid + ";" + askCur + ";" + bidCur + "\n";
//		}else if(typ == 22 || typ == 23) {
//			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
//				    + ";" + u_isin +      ";" + knockin + ";"    + addon +     ";\\N;"  + ask + ";" + bid + ";" + underlyingprice + ";"  + bv
//				+ ";" + underlyingtime + ";" + derivativetime + ";" + size +   ";" + maturity + ";" +  + maturity.getYear()+"-" + maturity.getMonthValue() + ";" + abstand + ";" + (curfaktor == null ? "\\N;" : curfaktor) + ";" + strike + ";" + addonBid + ";" + addonMid + ";" + askCur + ";" + bidCur + "\n";
//		}else {
//			return isin + ";" + wkn + ";" + emi + ";" + waehrung + ";" + typ
//				    + ";" + u_isin +      ";" + knockin + ";"    + addon +     ";\\N;"  + ask + ";" + bid + ";" + underlyingprice + ";"  + bv
//				+ ";" + underlyingtime + ";" + derivativetime + ";" + size +   ";\\N;" +  "\\N;" + abstand  + ";" + (curfaktor == null ? "\\N" : curfaktor) + ";" + strike + ";" + addonBid  + ";" + addonMid + ";" + askCur + ";" + bidCur + "\n";
//		}
//	}
	
	

}
