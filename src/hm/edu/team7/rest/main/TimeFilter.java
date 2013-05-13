package hm.edu.team7.rest.main;

import javax.xml.bind.annotation.XmlElement;

public class TimeFilter extends Filter{
	
	@XmlElement
	private String filterType;
	
	@XmlElement 
	private int year;
	
	@XmlElement
	private int month;
	
	public TimeFilter(FilterGruppe filGrp, Kennzahl kenz, int year, int month) {
		super(filGrp, kenz);
		this.year = year;
		this.month = month;
		this.filterType = "YEAR/MONTH";
	}
	

}
