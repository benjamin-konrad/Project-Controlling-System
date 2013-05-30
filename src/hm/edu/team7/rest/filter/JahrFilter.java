package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;

public class JahrFilter extends Filter {
	
	@SuppressWarnings("unused")
	@XmlElement
	private int jahr;
	@SuppressWarnings("unused")
	@XmlElement
	private FilterIdentifier filterId;

	public JahrFilter(Kennzahl kenz, int jahr) {
		super(FilterGruppe.ZEIT, kenz);
		this.jahr = jahr;
		filterId = FilterIdentifier.JAHR;
	}
	
	public void setIdentifier(FilterIdentifier i){
		this.filterId = i;
	}

}
