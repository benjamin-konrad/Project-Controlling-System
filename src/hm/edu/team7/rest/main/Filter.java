package hm.edu.team7.rest.main;

import javax.xml.bind.annotation.XmlElement;

public class Filter {

	@XmlElement
	private FilterGruppe filterGruppe;
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
