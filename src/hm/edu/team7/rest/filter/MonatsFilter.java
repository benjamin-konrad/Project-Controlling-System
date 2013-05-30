package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;

public class MonatsFilter extends JahrFilter{
	
	@SuppressWarnings("unused")
	@XmlElement
	private int month;
	
	public MonatsFilter(Kennzahl kenz, int year, int month) {
		super(kenz, year);
		this.month = month;
		setIdentifier(FilterIdentifier.MONAT);

	}
	

}
