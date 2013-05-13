package hm.edu.team7.pcs.rest.main;

import javax.xml.bind.annotation.XmlElement;

public class Filter {

	@XmlElement
	private FilterGruppe filterGruppe;
	@XmlElement
	private Kennzahl kennzahl;
	
	public Filter(FilterGruppe filGrp, Kennzahl kenz){
		setFilterGruppe(filGrp);
		setKennzahl(kenz);
	}
	
	public String getFilterType(){
		return null;
	}

	public FilterGruppe getFilterGruppe() {
		return filterGruppe;
	}

	public void setFilterGruppe(FilterGruppe filterGruppe) {
		this.filterGruppe = filterGruppe;
	}

	public Kennzahl getKennzahl() {
		return kennzahl;
	}

	public void setKennzahl(Kennzahl kennzahl) {
		this.kennzahl = kennzahl;
	}
	
}
