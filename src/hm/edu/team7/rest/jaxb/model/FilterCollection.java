package hm.edu.team7.rest.jaxb.model;

import hm.edu.team7.backend.bigquery.Bigquerybase;
import hm.edu.team7.rest.filter.FilterGruppe;
import hm.edu.team7.rest.filter.FilterIdentifier;
import hm.edu.team7.rest.filter.JahrFilter;
import hm.edu.team7.rest.filter.QuartalFilter;

import javax.xml.bind.annotation.XmlElement;

public class FilterCollection {

	@XmlElement
	public String id;
	@XmlElement
	public FilterGruppe fgruppe;
	@XmlElement
	public FilterIdentifier fident;
	@XmlElement
	public String name; // wert zb 2013
	@XmlElement
	public String parent;

	public FilterCollection setYearDatas(int year, String string,
			FilterIdentifier f) {
		parent = string;
		fident = f;
		fgruppe = FilterGruppe.ZEIT;
		name = year + "";
		id = name;
		return this;
	}

	public FilterCollection setQuartalDatas(int quartal, int year,
			FilterIdentifier f) {
		parent = year + "";
		fident = f;
		fgruppe = FilterGruppe.ZEIT;
		name = quartal + "";
		id = name;
		return this;
	}

	public FilterCollection setMonthDatas(int month, int year,
			FilterIdentifier f) {
		parent = year + "";
		fident = f;
		fgruppe = FilterGruppe.ZEIT;
		name = month + "";
		id = name;
		return this;
	}
	
	public FilterCollection setBereichData(String bereich, String parent,
			FilterIdentifier fid) {
		this.parent = parent;
		fident = fid;
		fgruppe = FilterGruppe.ORGANISATION;
		name = bereich + "";
		id = name;
		return this;
	}//name = bereich1 // id = bereich //parent = filter


	public FilterCollection setProjektData(String projekt, String parent,
			FilterIdentifier fid) {
		this.parent = parent;
		fident = fid;
		fgruppe = FilterGruppe.ORGANISATION;
		name = projekt + "";
		id = parent + name;
		return this;
	}//name = projekt1 // id = bereich1projekt1 // parent = bereich


	public FilterCollection setKontenData(String konto, String parent,
			FilterIdentifier fid) {
		this.parent = parent;
		fident = fid;
		fgruppe = FilterGruppe.ORGANISATION;
		name = konto + "";
		id = name;
		return this;
	}//name = konto1 // id = konto1 // parent = bereich1projekt1
	
	public FilterCollection setEntwcklStufeData(String estufe, String parent,
			FilterIdentifier fid) {
		this.parent = parent;
		fident = fid;
		fgruppe = FilterGruppe.MITARBEITER;
		name = estufe + "";
		id = name;
		return this;
	}
	
	public FilterCollection setMitarbeiterData(String mitarbeiter,
			String parent, FilterIdentifier fid) {
		this.parent = parent;
		fident = fid;
		fgruppe = FilterGruppe.MITARBEITER;
		name = mitarbeiter + "";
		id = name;
		return this;
	}


	public static int[] getAllYears() {
		Integer[] value = Bigquerybase.fetchallYears();
		int[] result = new int[value.length];
		int i = 0;
		for(Integer val:value)
		{
			result[i] = val;
			i++;
		}
		
		return result;
	}

	public static int[] getAllMonth(int year) {
		Integer[] value = Bigquerybase.fetchallMonth(year);
		int[] result = new int[value.length];
		int i = 0;
		for(Integer val:value)
		{
			result[i] = val;
			i++;
		}
		
		return result;
	}

	public static int[] getAllQuartal(int quartal) {
		Integer[] value = Bigquerybase.fetchallQuartal(quartal);
		int[] result = new int[value.length];
		int i = 0;
		for(Integer val:value)
		{
			result[i] = val;
			i++;
		}
		
		return result;
	}

	public static String[] getDistinctBereiche() {
		String[] value = Bigquerybase.fetchallBereiche();
		return value;
	}
	
	public static String[] getDistinctProjekte(String bereich) {
		String[] value = Bigquerybase.fetchallProjekte(bereich);
		return value;
	}
	
	public static String[] getDistinctKonten(String bereich, String projekt) {
		String[] value = Bigquerybase.fetchallKonten(bereich,projekt);
		return value;
	}

	public static String[] getDistinctEntwcklStufen() {
		String[] value = Bigquerybase.fetchallStufen();
		return value;
	}

	public static String[] getDistinctMitarbeiter(String estufe) {
		String[] value = Bigquerybase.fetchallMitarbeiter(estufe);
		return value;
	}



}
