package hm.edu.team7.rest.jaxb.model;

import hm.edu.team7.rest.filter.Filter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Daten {

	
	
	@XmlElement
	private Filter filter;

	
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
		value = "blub";
	}
	
	
	public void setFilter(Filter filter) {
		this.filter = filter;
		
	}
	public void fetchFromDB() {
		value = "Dummy Value";
		
	}
	
	

}
