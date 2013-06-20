package hm.edu.team7.rest.jaxb.model;

import hm.edu.team7.rest.filter.FilterIdentifier;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class posOrganisationValues extends posTimeValues {
	@XmlElement
	List<FilterCollection> possibleThingis = new ArrayList<FilterCollection>();
	
	public posOrganisationValues(){
		String[] bereiche = FilterCollection.getDistinctBereiche();
		for(String bereich: bereiche){
			possibleThingis.add(new FilterCollection().setBereichData(bereich, "filter",FilterIdentifier.BEREICH));
			String[] projekte = FilterCollection.getDistinctProjekte(bereich);
			for(String projekt: projekte){
				possibleThingis.add(new FilterCollection().setProjektData(projekt, bereich,FilterIdentifier.PROJEKT));
				String[] konten = FilterCollection.getDistinctKonten(bereich, projekt);
				for(String konto: konten){
					possibleThingis.add(new FilterCollection().setKontenData(konto, bereich + projekt,FilterIdentifier.KONTO));
				}
			}
		}
		
	}

}
