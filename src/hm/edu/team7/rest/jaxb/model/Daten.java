package hm.edu.team7.rest.jaxb.model;

import hm.edu.team7.backend.bigquery.Bigquerybase;
import hm.edu.team7.rest.filter.BereichFilter;
import hm.edu.team7.rest.filter.EntwicklungsstufeFilter;
import hm.edu.team7.rest.filter.Filter;
import hm.edu.team7.rest.filter.JahrFilter;
import hm.edu.team7.rest.filter.KontoFilter;
import hm.edu.team7.rest.filter.MitarbeiterFilter;
import hm.edu.team7.rest.filter.MonatsFilter;
import hm.edu.team7.rest.filter.ProjektFilter;
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
		MitarbeiterFilter fil = (MitarbeiterFilter) filter;
		value = Bigquerybase.fetchMitarbeiter(fil.getKennzahl(), fil.getMitarbeiter());
		
	}
	public void fetchForEntStf() {
		EntwicklungsstufeFilter fil = (EntwicklungsstufeFilter) filter;
		value = Bigquerybase.fetchStufe(fil.getKennzahl(), fil.getStufe());
		
	}
	public void fetchForKonto() {
		KontoFilter fil = (KontoFilter) filter;
		value = Bigquerybase.fetchKonto(fil.getKennzahl(), fil.getBereich(), fil.getProjekt());
	}
	public void fetchForProjekt() {
		ProjektFilter fil = (ProjektFilter) filter;
		value = Bigquerybase.fetchProjekt(fil.getKennzahl(), fil.getProjekt());
		
	}
	public void fetchForBereich() {
		BereichFilter fil = (BereichFilter) filter;
		value = Bigquerybase.fetchBereich(fil.getKennzahl(), fil.getBereich());
		
	}
	public void fetchForQuartal() {
		QuartalFilter fil = (QuartalFilter) filter;
		value = Bigquerybase.fetchQuartal(fil.getKennzahl(), fil.getQuartal(), fil.getJahr());
		
	}
	public void fetchForMonth() {
		MonatsFilter fil = (MonatsFilter) filter;
		value = Bigquerybase.fetchMonat(fil.getKennzahl(), fil.getMonat(), fil.getJahr());
		
	}
	public void fetchForYear() {
		JahrFilter fil = (JahrFilter) filter;
		value = Bigquerybase.fetchJahr(fil.getKennzahl(), fil.getJahr());
		
	}
	
	

}
