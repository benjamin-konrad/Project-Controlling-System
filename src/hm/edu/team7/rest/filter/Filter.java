package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;

public class Filter {

	@SuppressWarnings("unused")
	@XmlElement
	private FilterGruppe filterGruppe;
	@SuppressWarnings("unused")
	@XmlElement
	private Kennzahl kennzahl;
	@SuppressWarnings("unused")
	@XmlElement
	private FilterIdentifier filterId;
	
	public Filter(FilterGruppe filGrp, Kennzahl kenz){
		filterGruppe = filGrp;
		kennzahl = kenz;
	}
	
	public void setIdentifier(FilterIdentifier i){
		this.filterId = i;
	}
	
	
}
