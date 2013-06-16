package edu.hm.cs.swe2.pcs.cvsgen;

import java.io.File;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Properties {

	private static final Preferences PREFS = Preferences.systemRoot().node(
			"edu/hm/cs/swe2/pcs/cvsgen");

	public static final String MAID_RANGE = "maidRange";
	public static final String MITARBEITER_PATH = "mitarbeiterPath";
	public static final String ENTWICKLUNGSSTUFE_RANGE = "entwicklungsstufeRange";
	public static final String STUNDEN_RANGE = "stundenRange";
	public static final String MONAT_RANGE = "monatRange";
	public static final String PROJEKT_PATH = "projektPath";
	public static final String BEREICH_PATH = "bereichPath";
	public static final String KONTO_PATH = "kontoPath";
	public static final String GRENZKOSTEN_RANGE = "grenzkostenRange";
	public static final String VERRECHNUNGSSATZ_RANGE = "verrechnugssatzRange";

	private Properties() throws IllegalAccessException {
		throw new IllegalAccessException("Static class!!!");
	}
	
	private static String getFileName(String name){
		switch (name) {
		case MITARBEITER_PATH :
			return "mitarbeiter";
			
		case PROJEKT_PATH:
			return "projekt";
			
		case BEREICH_PATH:
			return "bereich";
			
		case KONTO_PATH:
			return "konto";		
		}
		return null;
	}

	public static String getFilePath(String name) {
		String fileName = getFileName(name) + ".list";
		final String path = PREFS.get(
				name,
				(System.getProperty("user.home")
						+ System.getProperty("file.separator") + "CVSGen" + System.getProperty("file.separator")));
		File dir = new File(path);
		dir.mkdirs();
		String output = path + fileName;
		try {
			new File(output).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static void setFilePath(String name, String path) {
		String fileName = getFileName(name) + ".list";
		
		PREFS.put(name, path.replaceAll(fileName, ""));
		try {
			PREFS.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	public static int[] getIntegerRange(String name) {
		final String[] pair = PREFS.get(name, "0,0").split(",");
		return new int[]{ Integer.parseInt(pair[0]), Integer.parseInt(pair[1])};
	}
	
	public static float[] getFloatRange(String name) {
		final String[] pair = PREFS.get(name, "0.0,0.0").split(",");
		return new float[]{ Float.parseFloat(pair[0]), Float.parseFloat(pair[1])};
	}
	
	public static void setRange(String name, String first, String second) {
		StringBuilder output = new StringBuilder();
		output.append(first).append(",").append(second);
		PREFS.put(name, output.toString());
		try {
			PREFS.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
}
