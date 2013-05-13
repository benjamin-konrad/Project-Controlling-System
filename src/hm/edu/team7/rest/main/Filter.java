package hm.edu.team7.rest.main;

import javax.xml.bind.annotation.XmlElement;

public class Filter {

	@SuppressWarnings("unused")
	@XmlElement
	private FilterGruppe filterGruppe;
	@SuppressWarnings("unused")
	@XmlElement
	private Kennzahl kennzahl;
	
	public Filter(FilterGruppe filGrp, Kennzahl kenz){
		filterGruppe = filGrp;
		kennzahl = kenz;
	}
	
	
	public String getFilterType(){
		return null;
	}
	
}
