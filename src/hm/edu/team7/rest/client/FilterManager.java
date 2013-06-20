package hm.edu.team7.rest.client;

import hm.edu.team7.rest.filter.BereichFilter;
import hm.edu.team7.rest.filter.EntwicklungsstufeFilter;
import hm.edu.team7.rest.filter.JahrFilter;
import hm.edu.team7.rest.filter.KontoFilter;
import hm.edu.team7.rest.filter.MitarbeiterFilter;
import hm.edu.team7.rest.filter.MonatsFilter;
import hm.edu.team7.rest.filter.ProjektFilter;
import hm.edu.team7.rest.filter.QuartalFilter;
import hm.edu.team7.rest.jaxb.model.Daten;
import hm.edu.team7.rest.jaxb.model.Kennzahl;
import hm.edu.team7.rest.jaxb.model.posMitarbeiterValues;
import hm.edu.team7.rest.jaxb.model.posOrganisationValues;
import hm.edu.team7.rest.jaxb.model.posTimeValues;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/filter")
public class FilterManager {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HelloWorldObject getHelloWorld() {
		return new HelloWorldObject();
	}

	// ///// Zeitfilter
	// Liefert Jahr.
	@Path("/zeitfilter/{jahr}/{kennzahl}")
	@GET
	@Produces("application/json")
	public Daten getHTML(@PathParam("jahr") int year,
			@PathParam("kennzahl") Kennzahl kz) {
		Daten zd = new Daten();
		zd.setFilter(new JahrFilter(kz, year));
		zd.fetchForYear();
		return zd;
	}

	// Liefert Jahr/Monat
	@Path("/zeitfilter/{jahr}/monat/{monat}/{kennzahl}")
	@GET
	@Produces("application/json")
	public Daten getHTML(@PathParam("jahr") int year,
			@PathParam("monat") int month, @PathParam("kennzahl") Kennzahl kz) {
		Daten zd = new Daten();
		zd.setFilter(new MonatsFilter(kz, year, month));
		zd.fetchForMonth();
		return zd;
	}

	// Liefert Jahr/Quartal
	@Path("/zeitfilter/{jahr}/quartal/{quartal}/{kennzahl}")
	@GET
	@Produces("application/json")
	public Daten getHTML(@PathParam("quartal") int quartal,
			@PathParam("kennzahl") Kennzahl kz, @PathParam("jahr") int year) {
		Daten zd = new Daten();
		zd.setFilter(new QuartalFilter(kz, year, quartal));
		zd.fetchForQuartal();
		return zd;
	}

	// ///// OrganisationsFilter
	// Liefert BEREICH
	@Path("/organisationsfilter/{bereich}/{kennzahl}")
	@GET
	@Produces("application/json")
	public Daten getHTML(@PathParam("bereich") String bereich,
			@PathParam("kennzahl") Kennzahl kz) {
		Daten zd = new Daten();
		zd.setFilter(new BereichFilter(kz, bereich));
		zd.fetchForBereich();
		return zd;
	}

	// Liefert BEREICH/PROJEKT
	@Path("/organisationsfilter/{bereich}/{projekt}/{kennzahl}")
	@GET
	@Produces("application/json")
	public Daten getHTML(@PathParam("bereich") String bereich,
			@PathParam("projekt") String projekt,
			@PathParam("kennzahl") Kennzahl kz) {
		Daten zd = new Daten();
		zd.setFilter(new ProjektFilter(kz, bereich, projekt));
		zd.fetchForProjekt();
		return zd;
	}

	// Liefert BEREICH/PROJEKT/KONTO
	@Path("/organisationsfilter/{bereich}/{projekt}/{konto}/{kennzahl}")
	@GET
	@Produces("application/json")
	public Daten getHTML(@PathParam("bereich") String bereich,
			@PathParam("projekt") String projekt,
			@PathParam("konto") String konto, @PathParam("kennzahl") Kennzahl kz) {
		Daten zd = new Daten();
		zd.setFilter(new KontoFilter(kz, bereich, projekt, konto));
		zd.fetchForKonto();
		return zd;
	}

	// ///// MitarbeiterFilter
	// Liefert ENTWICKLUNGSSTUFE
	@Path("/mitarbeiterfilter/estufe/{entwicklungsstufe}/{kennzahl}")
	@GET
	@Produces("application/json")
	public Daten getEntwicklungsstufe(
			@PathParam("entwicklungsstufe") int estufe,
			@PathParam("kennzahl") Kennzahl kz) {
		Daten zd = new Daten();
		zd.setFilter(new EntwicklungsstufeFilter(kz, estufe));
		zd.fetchForEntStf();
		return zd;
	}

	// Liefert ENTWICKLUNGSSTUFE/MITARBEITER
	@Path("/mitarbeiterfilter/mitarbeiter/{mitarbeiter}/{kennzahl}")
	@GET
	@Produces("application/json")
	public Daten getMitarbeiter(@PathParam("mitarbeiter") String mitarbeiter,
			@PathParam("kennzahl") Kennzahl kz) {
		Daten zd = new Daten();
		zd.setFilter(new MitarbeiterFilter(kz, mitarbeiter));
		zd.fetchForMitarbeiter();
		return zd;
	}

	// Liefert possibles filter
	@Path("/getTimeFilterList")
	@GET
	@Produces("application/json")
	public posTimeValues getTimeFilter() {
		return new posTimeValues();
	}

	// Liefert possibles filter
	@Path("/getOrganisationFilterList")
	@GET
	@Produces("application/json")
	public posOrganisationValues getOrganisationFilter() {
		return new posOrganisationValues();
	}

	// Liefert possibles filter
	@Path("/getMitarbeiterFilterList")
	@GET
	@Produces("application/json")
	public posMitarbeiterValues getMitarbeiterFilter() {
		return new posMitarbeiterValues();
	}

}