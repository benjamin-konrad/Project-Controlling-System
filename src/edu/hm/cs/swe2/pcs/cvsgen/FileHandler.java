package edu.hm.cs.swe2.pcs.cvsgen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

	private TableRowEntry createEntry(String cvsRow){
		String[] values = cvsRow.split(",");
		int i = 0;
		int maId = Integer.parseInt(values[i++]);
		String mitarbeiter = values[i++];
		int entwicklungsstufe = Integer.parseInt(values[i++]);
		float stunden = Float.parseFloat(values[i++]);
		String monat = values[i++];
		String projekt = values[i++];
		String bereich = values[i++];
		String konto = values[i++];
		boolean fakturierbar = "ja".equalsIgnoreCase(values[i++])? true:false;
		float grenzkosten = Float.parseFloat(values[i++]);
		float verrechnungssatz = Float.parseFloat(values[i++]);
		return new TableRowEntry(maId, mitarbeiter, entwicklungsstufe, stunden, monat, projekt, bereich, konto, fakturierbar, grenzkosten, verrechnungssatz);
	}
	public List<TableRowEntry> loadCVS(String path){
		List<TableRowEntry> output = new ArrayList<>();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(path));
			String line = null;
			while(( line = input.readLine()) != null)
				output.add(createEntry(line));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(input != null)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return output;
	}
	
	public void saveCSV(List<TableRowEntry> entries, String path){
		BufferedWriter output = null;
		try {
			 output = new BufferedWriter(new FileWriter(path));
			 for(TableRowEntry entry: entries){
				 output.append(entry.toCVSString());
				 output.newLine();
			 }
			 output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(output != null)
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
