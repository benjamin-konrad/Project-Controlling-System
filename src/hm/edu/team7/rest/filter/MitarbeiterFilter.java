package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;


public class MitarbeiterFilter extends Filter {
	
	@SuppressWarnings("unused")
	@XmlElement
	private String mitarbeiter;

	public MitarbeiterFilter(Kennzahl kenz, String mitarbeiter) {
		super(FilterGruppe.MITARBEITER, kenz);
		setIdentifier(FilterIdentifier.MITARBEITER);
		this.mitarbeiter = mitarbeiter;
	}

}
