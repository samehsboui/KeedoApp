package tn.esprit.pi.entities;

import java.util.ArrayList;
import java.util.List;

public class Retour<T> {
	private String stringValue;
	private List<T> objectValue = new ArrayList<T>();

	public Retour() {
		super();
	}

	public Retour(String stringValue, List<T> objectValue) {
		super();
		this.stringValue = stringValue;
		this.objectValue = objectValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public List<T> getObjectValue() {
		return objectValue;
	}

	public void setObjectValue(List<T> objectValue) {
		this.objectValue = objectValue;
	}

	@Override
	public String toString() {
		return "Retour [stringValue=" + stringValue + ", objectValue=" + objectValue + "]";
	}

}
