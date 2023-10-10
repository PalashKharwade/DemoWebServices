package com.common.model.master;

import java.io.Serializable;
import java.math.BigDecimal;

public class PricingUnderlying implements Serializable{
	
	String isin = "";
	int timestamp;
	BigDecimal ask;
	
	
	public PricingUnderlying(String isin, BigDecimal ask, int timestamp) {
		this.isin = isin;
		this.ask = ask;
		this.timestamp = timestamp;
	}
	
	public PricingUnderlying(String isin, BigDecimal ask) {
		this.isin = isin;
		this.ask = ask;
	}

	public String getIsin() {
		return isin;
	}


	public void setIsin(String isin) {
		this.isin = isin;
	}


	public int getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}


	public BigDecimal getAsk() {
		return ask;
	}


	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}


	@Override
	public String toString() {
		return "PricingUnderlying [isin=" + isin + ", timestamp=" + timestamp + ", ask=" + ask + "]";
	}
	
	

}
