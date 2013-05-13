package hm.edu.team7.pcs.rest.main;

import javax.xml.bind.annotation.XmlElement;

public class TimeFilter extends Filter{
	
	@SuppressWarnings("unused")
	@XmlElement
	private String filterType;
	
	@SuppressWarnings("unused")
	@XmlElement 
	private int year;
	
	@SuppressWarnings("unused")
	@XmlElement
	private int month;
	
	public TimeFilter(FilterGruppe filGrp, Kennzahl kenz, int year, int month) {
		super(filGrp, kenz);
		this.year = year;
		this.month = month;
		this.filterType = "YEAR/MONTH";
	}
	

}
