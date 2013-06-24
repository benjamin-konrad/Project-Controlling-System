package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;

public class BereichFilter extends Filter{
	
	@SuppressWarnings("unused")
	@XmlElement
	private String bereich;
	

	public BereichFilter( Kennzahl kenz, String bereich) {
		super(FilterGruppe.ORGANISATION, kenz);
		this.bereich = bereich;
		setIdentifier(FilterIdentifier.BEREICH);
	}


	public String getBereich() {
		return bereich;
	}
	

}
