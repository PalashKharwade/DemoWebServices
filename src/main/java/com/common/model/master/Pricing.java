package com.common.model.master;

import java.math.BigDecimal;

public class Pricing {
	
	String wkn;
	public BigDecimal ask;
	public BigDecimal bid;
	int timestamp;
	int asksize;
	int bidsize;
	
	
	public Pricing(String wkn, BigDecimal ask, BigDecimal bid) {
		this.wkn = wkn;
		this.ask = ask;
		this.bid = bid;
	}

	public Pricing(String wkn, BigDecimal ask, BigDecimal bid, int timestamp, int asksize) {
		this.wkn = wkn;
		this.ask = ask;
		this.bid = bid;
		this.asksize = asksize;
		//this.bidsize = bidsize;
		this.timestamp = timestamp;
	}

	public BigDecimal getSpread() {
		return this.ask.subtract(this.bid);
	}

	public int getAsksize() {
		return asksize;
	}

	public void setAsksize(int asksize) {
		this.asksize = asksize;
	}

	public int getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}



	public String getWkn() {
		return wkn;
	}


	public void setWkn(String wkn) {
		this.wkn = wkn;
	}


	public BigDecimal getAsk() {
		return ask;
	}


	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}


	public BigDecimal getBid() {
		return bid;
	}


	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}
	
	@Override
	public String toString() {
		return "Pricing [wkn=" + wkn + ", ask=" + ask + ", bid=" + bid + ", timestamp=" + timestamp + ", asksize="
				+ asksize + ", bidsize=" + bidsize + "]";
	}
	

}
