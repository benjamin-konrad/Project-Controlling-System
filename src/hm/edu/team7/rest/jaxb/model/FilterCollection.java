package hm.edu.team7.rest.jaxb.model;

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
	}


	public FilterCollection setProjektData(String projekt, String parent,
			FilterIdentifier fid) {
		this.parent = parent;
		fident = fid;
		fgruppe = FilterGruppe.ORGANISATION;
		name = projekt + "";
		id = name;
		return this;
	}


	public FilterCollection setKontenData(String konto, String parent,
			FilterIdentifier fid) {
		this.parent = parent;
		fident = fid;
		fgruppe = FilterGruppe.ORGANISATION;
		name = konto + "";
		id = name;
		return this;
	}
	
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
		return new int[] { 2013, 2012 };
	}

	public static int[] getAllMonth(int year) {
		return new int[] { 12, 11 };
	}

	public static int[] getAllQuartal(int quartal) {
		return new int[] { 1, 2 };
	}

	public static String[] getDistinctBereiche() {
		return new String[] { "bereich1", "bereich2" };
	}
	
	public static String[] getDistinctProjekte(String bereich) {
		return new String[] { "projekt1", "projekt2"};
	}
	
	public static String[] getDistinctKonten(String bereich, String projekt) {
		return new String[] {"KOnto1", "Konto2"};
	}

	public static String[] getDistinctEntwcklStufen() {
		return new String[] {"1", "2"};
	}

	public static String[] getDistinctMitarbeiter(String estufe) {
		return new String[] {"herbert", "maier"};
	}



}