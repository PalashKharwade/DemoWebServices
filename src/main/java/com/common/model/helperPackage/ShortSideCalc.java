package com.common.model.helperPackage;

import java.math.BigDecimal;

public class ShortSideCalc {
	
	BigDecimal end;
	BigDecimal anno;
	boolean calc;
	
	public ShortSideCalc(BigDecimal end, BigDecimal anno, boolean calc) {
		super();
		this.end = end;
		this.anno = anno;
		this.calc = calc;
	}

	public BigDecimal getEnd() {
		return end;
	}

	public BigDecimal getAnno() {
		return anno;
	}

	public boolean isCalc() {
		return calc;
	}

	@Override
	public String toString() {
		return "ShortSideCalc [end=" + end + ", anno=" + anno + ", calc=" + calc + ", toString()=" + super.toString()
				+ "]";
	}
	
}
