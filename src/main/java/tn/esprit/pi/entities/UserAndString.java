package tn.esprit.pi.entities;

import java.util.List;


public class UserAndString {

	private String stringValue;
	private List<User> userValue;

	public UserAndString() {
		super();
	}


	public UserAndString(String stringValue, List<User> userValue) {
		super();
		this.stringValue = stringValue;
		this.userValue = userValue;
	}

	public String getStringValue() {
		return stringValue;
	}


	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}


	public List<User> getUserValue() {
		return userValue;
	}


	public void setUserValue(List<User> userValue) {
		this.userValue = userValue;
	}


	@Override
	public String toString() {
		return "UserAndString [stringValue=" + stringValue + ", userValue=" + userValue + "]";
	}

}
