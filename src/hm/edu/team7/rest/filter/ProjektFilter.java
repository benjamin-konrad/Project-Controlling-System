package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;

public class ProjektFilter extends BereichFilter{
	
	@SuppressWarnings("unused")
	@XmlElement
	private String projekt;

	public ProjektFilter(Kennzahl kenz, String bereich, String projekt) {
		super(kenz, bereich);
		this.projekt = projekt;
		setIdentifier(FilterIdentifier.PROJEKT);
	}

}
