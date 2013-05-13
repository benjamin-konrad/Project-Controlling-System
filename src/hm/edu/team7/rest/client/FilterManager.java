package hm.edu.team7.rest.client;

import hm.edu.team7.rest.jaxb.model.ZeitDaten;
import hm.edu.team7.rest.main.Kennzahl;

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
  
  
  // Liefert zeitfilter.
  @Path("/zeitfilter/y{jahr}/m{monat}/{kennzahl}")
  @GET
  @Produces("application/json")
  public ZeitDaten getHTML(@PathParam("jahr") int year, @PathParam("monat") int month, @PathParam("kennzahl") Kennzahl kz){
	  ZeitDaten zd = new ZeitDaten();
	  zd.changeOrAdd(666);
	  zd.setFilter(kz, year, month);
	  return zd;
  }
  
  @Path("/zeitfilter/q{quartal}/{kennzahl}")
  @GET
  @Produces("application/json")
 // @Produces({MediaType.TEXT_XML})
  public ZeitDaten getHTML(@PathParam("quartal") int quartal, @PathParam("kennzahl") Kennzahl kz){
	  ZeitDaten zd = new ZeitDaten();
	  zd.changeOrAdd(500);
	  zd.setFilter(kz);
	  return zd;
  }  
  
  
  
  
  
  


} 