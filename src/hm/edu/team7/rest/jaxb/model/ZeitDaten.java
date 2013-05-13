package hm.edu.team7.rest.jaxb.model;

import hm.edu.team7.rest.main.Filter;
import hm.edu.team7.rest.main.FilterGruppe;
import hm.edu.team7.rest.main.Kennzahl;
import hm.edu.team7.rest.main.QuartalFilter;
import hm.edu.team7.rest.main.TimeFilter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZeitDaten {

	
	
	@XmlElement
	private Filter filter;

	
	@XmlElement
	public int value;
	
	public Filter getFilter(){
      return filter;
	}
	public int getValue(){
		return value;
	}
	
	
	public void setFilter(Kennzahl kz){
		filter = new QuartalFilter(FilterGruppe.ZEIT, kz);
	}
	
	
	public ZeitDaten(int i){
		value = i;
	}
	
	public ZeitDaten(){
		value = 0;
	}
	
	
	public void changeOrAdd(int value){
		this.value = value;
	}
	
	public void setFilter(Kennzahl kz, int year, int month) {
		filter = new TimeFilter(FilterGruppe.ZEIT, kz, year, month);
		
	}
	

}
