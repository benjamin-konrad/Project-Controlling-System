package hm.edu.team7.rest.jaxb.model;

import hm.edu.team7.rest.filter.Filter;
import hm.edu.team7.rest.filter.JahrFilter;
import hm.edu.team7.rest.filter.QuartalFilter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Daten {

	
	
	@XmlElement
	public Filter filter;

	
	@XmlElement
	public String value;
	
	public Filter getFilter(){
      return filter;
	}
	public String getValue(){
		return value;
	}
	
	

	
	
	public Daten(String i){
		value = i;
	}
	
	public Daten(){
		value = null;
	}
	
	
	public void setFilter(Filter filter) {
		this.filter = filter;
		
	}

	public void fetchForMitarbeiter() {
		// TODO Auto-generated method stub
		
	}
	public void fetchForEntStf() {
		// TODO Auto-generated method stub
		
	}
	public void fetchForKonto() {
		// TODO Auto-generated method stub
		
	}
	public void fetchForProjekt() {
		// TODO Auto-generated method stub
		
	}
	public void fetchForBereich() {
		// TODO Auto-generated method stub
		
	}
	public void fetchForQuartal() {
		// TODO Auto-generated method stub
		
	}
	public void fetchForMonth() {
		// TODO Auto-generated method stub
		
	}
	public void fetchForYear() {
		// TODO Auto-generated method stub
		
	}
	
	

}
