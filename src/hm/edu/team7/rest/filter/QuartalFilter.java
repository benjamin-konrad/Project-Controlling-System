package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;

public class QuartalFilter extends JahrFilter {

	
	@SuppressWarnings("unused")
	@XmlElement
	private int quartal;
	
	public QuartalFilter(Kennzahl kenz, int jahr, int quartal) {
		super(kenz, jahr);
		this.quartal = quartal;
		setIdentifier(FilterIdentifier.QUARTAL);
	}
	
}
