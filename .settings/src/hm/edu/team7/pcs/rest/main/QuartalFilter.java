package hm.edu.team7.pcs.rest.main;

import javax.xml.bind.annotation.XmlElement;

public class QuartalFilter extends Filter {

	@XmlElement
	private String filterType;
	
	public String getFilterType(){
		return filterType;
	}
	public QuartalFilter(FilterGruppe filGrp, Kennzahl kenz) {
		super(filGrp, kenz);
		this.filterType = "QUARTAL";
	}
	
}
