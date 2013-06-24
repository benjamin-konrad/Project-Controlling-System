package hm.edu.team7.backend.bigquery;

import java.io.IOException;
import java.util.List;

import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import com.google.api.services.bigquery.model.TableCell;
import com.google.api.services.bigquery.model.TableRow;

public class Bigquerybase {
	public static String fetchMitarbeiter(String mitarbeiter) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Mitarbeiter", mitarbeiter);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String) result.toArray()[0];

	}

	public static String fetchKonto(String konto) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Konto", konto);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String) result.toArray()[0];

	}

	public static String fetchProjekt(String projekt) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Projekt", projekt);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String) result.toArray()[0];
	}

	public static String fetchBereich(String bereich) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Bereich", bereich);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String) result.toArray()[0];
	}

	public static String fetchQuartal(int quartal) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Quartal",
					String.valueOf(quartal));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String) result.toArray()[0];
	}

	public static String fetchMonat(int monat) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Monat", String.valueOf(monat));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String) result.toArray()[0];
	}

	public static String fetchJahr(int jahr) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Jahr", String.valueOf(jahr));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String) result.toArray()[0];
	}

	public static String fetchStufe(int stufe) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Stufe", String.valueOf(stufe));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String) result.toArray()[0];
	}

	public static Integer[] fetchallYears() {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("AllYears", "");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<Integer> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				Integer monat = Integer.parseInt(((String) field.getV()).substring(0, 1));
				Integer jahr = Integer.parseInt(((String) field.getV()).substring(1));
				if(!result.contains(jahr))
					result.add(jahr);
			
			}
		}
		return (Integer[]) result.toArray();
	}

	public static Integer[] fetchallMonth(int year) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("AllMonths",
					String.valueOf(year));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<Integer> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				Integer monat = Integer.parseInt(((String) field.getV()).substring(0, 1));
				Integer jahr = Integer.parseInt(((String) field.getV()).substring(1));

				if(!result.contains(monat) && jahr.equals(year))
					result.add(monat);
			
			}
		}
		return (Integer[]) result.toArray();
	}

	public static Integer[] fetchallQuartal(int quartal) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("AllMonths",
					String.valueOf(quartal));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<Integer> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				Integer monat = Integer.parseInt(((String) field.getV()).substring(0, 1));
				Integer jahr = Integer.parseInt(((String) field.getV()).substring(1));
				int quart =0;
				if(monat < 3 && jahr.equals(quartal))
					quart = 1;
				else if(monat < 6 && jahr.equals(quartal))
					quart = 2;
				else if(monat <9 && jahr.equals(quartal))
					quart = 3;
				else
					quart = 4;
				if(!result.contains(quart))
					result.add(quart);
			}
		}
		return (Integer[]) result.toArray();
	}

	public static String[] fetchallBereiche() {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Bereiche", "");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String[]) result.toArray();
	}

	public static String[] fetchallProjekte(String bereich) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Projekte", bereich);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String[]) result.toArray();
	}

	public static String[] fetchallKonten(String bereich, String projekt) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Konten", bereich + ","
					+ projekt);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String[]) result.toArray();
	}

	public static String[] fetchallStufen() {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("alleStufen", "");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String[]) result.toArray();
	}

	public static String[] fetchallMitarbeiter(String estufe) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("alleMitarbeiter", estufe);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<String> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		return (String[]) result.toArray();
	}

}
