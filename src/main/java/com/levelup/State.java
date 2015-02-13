package com.levelup;

public class State {

	private int stateId;
    private String abbreviation;
    private String postalCode;
    private String state;
    
    
	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", abbreviation=" + abbreviation
				+ ", postalCode=" + postalCode + ", state=" + state + "]";
	}
	
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
    
	
}
