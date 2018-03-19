package com.lv.CountDownLatchTest;

public enum CountyEmes {
	ONE(1,"韩"),TWO(2,"魏"),THREE(3,"齐"),FOUR(4,"楚"),FIVE(5,"赵"),SIX(6,"燕");
	private Integer retCode;
	private String retName;
	private CountyEmes(Integer retCode, String retName) {
		this.retCode = retCode;
		this.retName = retName;
	}
	public Integer getRetCode() {
		return retCode;
	}
	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
	public String getRetName() {
		return retName;
	}
	public void setRetName(String retName) {
		this.retName = retName;
	}
	public static CountyEmes forEachCountyEmes(Integer index) {
		for (CountyEmes element : values()) {
			if(element.getRetCode()==index) {
			return	element;
			}
		}
		return null;
	}
}
