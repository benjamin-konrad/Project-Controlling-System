package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;


public class EntwicklungsstufeFilter extends Filter {
	
	@SuppressWarnings("unused")
	@XmlElement
	private int entwicklungsstufe;
	

	public EntwicklungsstufeFilter(Kennzahl kenz, int estufe) {
		super(FilterGruppe.MITARBEITER, kenz);
		this.entwicklungsstufe = estufe;
		setIdentifier(FilterIdentifier.ENTWICKLUNGSSTUFE);
	}


	public int getStufe() {
		// TODO Auto-generated method stub
		return entwicklungsstufe;
	}

}
