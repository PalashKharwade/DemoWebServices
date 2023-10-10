package com.common.model.Business;

import java.util.ArrayList;
import java.util.Arrays;

public class PeerStamm {

	String U_Isin;
	String wkn;
	String mat_date;
	String val_date;
	Integer typ;
	String[] peermembers;
	ArrayList<MiniStamm> listPeermembers;
	
	public PeerStamm(String u_isin, String wkn, String mat_date, String val_date, Integer typ) {
		super();
		this.U_Isin = u_isin;
		this.wkn = wkn;
		this.mat_date = mat_date;
		this.val_date = val_date;
		this.typ = typ;
	}

	public ArrayList<MiniStamm> getListPeermembers() {
		return listPeermembers;
	}

	public void setListPeermembers(ArrayList<MiniStamm> listPeermembers) {
		this.listPeermembers = listPeermembers;
	}

	public String[] getPeermembers() {
		return peermembers;
	}

	public void setPeermembers(String peermembers) {
		
		this.peermembers = peermembers.split(";");
	}

	public Integer getTyp() {
		return typ;
	}

	public void setTyp(Integer typ) {
		this.typ = typ;
	}

	public String getU_isin() {
		return U_Isin;
	}

	public void setU_isin(String u_isin) {
		this.U_Isin = u_isin;
	}

	public String getWkn() {
		return wkn;
	}

	public void setWkn(String wkn) {
		this.wkn = wkn;
	}

	public String getMat_date() {
		return mat_date;
	}

	public void setMat_date(String mat_date) {
		this.mat_date = mat_date;
	}

	public String getVal_date() {
		return val_date;
	}

	public void setVal_date(String val_date) {
		this.val_date = val_date;
	}

	@Override
	public String toString() {
		return "PeerStamm [u_isin=" + U_Isin + ", wkn=" + wkn + ", mat_date=" + mat_date + ", val_date=" + val_date
				+ ", typ=" + typ + ", peermembers=" + Arrays.toString(peermembers) + "]";
	}
		
	
}
