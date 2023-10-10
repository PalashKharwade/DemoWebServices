package com.common.model.Business;

import java.io.Serializable;
import java.time.LocalDate;

public class TestClass implements Serializable{
	private String name;
	private LocalDate maturity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getMaturity() {
		return maturity;
	}
	public void setMaturity(LocalDate maturity) {
		this.maturity = maturity;
	}
	public TestClass(String name, LocalDate maturity) {
		super();
		this.name = name;
		this.maturity = maturity;
	}
	public TestClass() {
		super();
	}
	
	

}
