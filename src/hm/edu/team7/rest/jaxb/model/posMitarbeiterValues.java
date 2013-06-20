package hm.edu.team7.rest.jaxb.model;

import hm.edu.team7.rest.filter.FilterIdentifier;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class posMitarbeiterValues extends posTimeValues {
	
	@XmlElement
	List<FilterCollection> possibleThingis = new ArrayList<FilterCollection>();
	
	public posMitarbeiterValues(){
		String[] estufen = FilterCollection.getDistinctEntwcklStufen();
		for(String estufe: estufen){
			possibleThingis.add(new FilterCollection().setEntwcklStufeData(estufe, "filter",FilterIdentifier.ENTWICKLUNGSSTUFE));
			String[] mitarbeiters = FilterCollection.getDistinctMitarbeiter(estufe);
			for(String mitarbeiter: mitarbeiters){
				possibleThingis.add(new FilterCollection().setMitarbeiterData(mitarbeiter, estufe, FilterIdentifier.MITARBEITER));
			}
		}
	}

}
