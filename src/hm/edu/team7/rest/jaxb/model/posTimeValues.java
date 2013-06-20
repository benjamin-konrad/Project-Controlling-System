package hm.edu.team7.rest.jaxb.model;

import hm.edu.team7.rest.filter.FilterIdentifier;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class posTimeValues {
	@XmlElement
	List<FilterCollection> possibleThingis = new ArrayList<FilterCollection>();

	public posTimeValues() {

		int[] years = FilterCollection.getAllYears();
		for (int year : years) {
			possibleThingis.add(new FilterCollection().setYearDatas(year, "filter", FilterIdentifier.JAHR));
			int[] quartals = FilterCollection.getAllQuartal(year);
			for (int quartal : quartals)
				possibleThingis.add(new FilterCollection().setQuartalDatas(quartal, year, FilterIdentifier.QUARTAL));
			int[] months = FilterCollection.getAllMonth(year);
			for (int month : months) {
				possibleThingis.add(new FilterCollection().setMonthDatas(month, year,FilterIdentifier.MONAT));
			}
		}

	}

}
