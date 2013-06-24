package hm.edu.team7.rest.filter;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import javax.xml.bind.annotation.XmlElement;

public class KontoFilter extends ProjektFilter {
	
	@SuppressWarnings("unused")
	@XmlElement
	private String konto;

	public KontoFilter(Kennzahl kenz, String bereich, String projekt, String konto) {
		super(kenz, bereich, projekt);
		this.konto = konto;
		setIdentifier(FilterIdentifier.KONTO);
	}

	public String getKonto() {
		return konto;
	}

}
