package com.common.model.helperPackage;

import java.util.HashMap;

public class HelperCurrencie {
	
	private HashMap<String, String> hm1;
	private HashMap<String, String> hm2;
	private HashMap<String, String> hm3;
	
	public HelperCurrencie() {
		
	}
	
	public HelperCurrencie(HashMap<String, String> hm1,
			HashMap<String, String> hm2) {
		super();
		this.hm1 = hm1;
		this.hm2 = hm2;
	}
	
	public HelperCurrencie(HashMap<String, String> hm1,
			HashMap<String, String> hm2,
			HashMap<String, String> hm3) {
		super();
		this.hm1 = hm1;
		this.hm2 = hm2;
		this.hm3 = hm3;
	}

	public HashMap<String, String> getHm3() {
		return hm3;
	}

	public void setHm3(HashMap<String, String> hm3) {
		this.hm3 = hm3;
	}

	public HashMap<String, String> getHm1() {
		return hm1;
	}

	public HashMap<String, String> getHm2() {
		return hm2;
	}


}
