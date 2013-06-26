package hm.edu.team7.backend.bigquery;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import java.io.IOException;
import java.util.List;

import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import com.google.api.services.bigquery.model.TableCell;
import com.google.api.services.bigquery.model.TableRow;

public class Bigquerybase {
	public static String fetchMitarbeiter(Kennzahl kennzahl, String mitarbeiter) {
		String query = buildKennzahlquery(kennzahl, "mitarbeiter", mitarbeiter,
				"", "");
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Mitarbeiter", query);
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
		return result.get(0);
	}

	public static String fetchKonto(Kennzahl kennzahl, String bereich,
			String projekt) {
		String query = buildKennzahlquery(kennzahl, "projekt", projekt,
				"bereich", bereich);
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Konto", query);
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
		return result.get(0);
	}

	public static String fetchProjekt(Kennzahl kennzahl, String projekt) {
		String query = buildKennzahlquery(kennzahl, "projekt", projekt, "", "");
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Projekt", query);
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
		return result.get(0);
	}

	public static String fetchBereich(Kennzahl kennzahl, String bereich) {
		String query = buildKennzahlquery(kennzahl, "Bereich", bereich, "", "");
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Bereich", query);
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
		return result.get(0);

	}

	public static String fetchQuartal(Kennzahl kennzahl, int quartal, int jahr) {
		String query = buildKennzahlquery(kennzahl, "quartal",
				String.valueOf(quartal), "jahr", String.valueOf(jahr));
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Quartal", query);
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
		return result.get(0);

	}

	public static String fetchMonat(Kennzahl kennzahl, int monat, int jahr) {
		String query = buildKennzahlquery(kennzahl, "monat",
				String.valueOf(monat), "jahr", String.valueOf(jahr));
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Monat", query);
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
		return result.get(0);

	}

	public static String fetchJahr(Kennzahl kennzahl, int jahr) {
		String query = buildKennzahlquery(kennzahl, "jahr",
				String.valueOf(jahr), "", "");
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Jahr", query);
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
		return result.get(0);
	}

	public static String fetchStufe(Kennzahl kennzahl, int stufe) {
		String query = buildKennzahlquery(kennzahl, "entwicklungsstufe",
				String.valueOf(stufe), "", "");
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("Stufe", query);
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
		return result.get(0);

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
				Integer monat = Integer.parseInt(((String) field.getV())
						.substring(0, 1));
				Integer jahr = Integer.parseInt(((String) field.getV())
						.substring(1));
				if (!result.contains(jahr))
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
				Integer monat = Integer.parseInt(((String) field.getV())
						.substring(0, 1));
				Integer jahr = Integer.parseInt(((String) field.getV())
						.substring(1));

				if (!result.contains(monat) && jahr.equals(year))
					result.add(monat);

			}
		}
		return (Integer[]) result.toArray();
	}

	public static Integer[] fetchallQuartal(int quartal) {
		GetQueryResultsResponse queryResult = null;
		try {
			queryResult = bigquerytest.queryBig("AllQuartals",
					String.valueOf(quartal));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		List<TableRow> rows = queryResult.getRows();
		List<Integer> result = null;
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				Integer monat = Integer.parseInt(((String) field.getV())
						.substring(0, 1));
				Integer jahr = Integer.parseInt(((String) field.getV())
						.substring(1));
				int quart = 0;
				if (monat < 3 && jahr.equals(quartal))
					quart = 1;
				else if (monat < 6 && jahr.equals(quartal))
					quart = 2;
				else if (monat < 9 && jahr.equals(quartal))
					quart = 3;
				else
					quart = 4;
				if (!result.contains(quart))
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

	private static String buildKennzahlquery(Kennzahl kennzahl, String filter,
			String filtervalue, String filter2, String filtervalue2) {
		String query = "";
		String filterquery = "where ";
		if (filter.equals("mitarbeiter"))
			filterquery = filterquery + filter + " = " + "\"" + filtervalue
					+ "\"";
		if (filter.equals("bereich") && filter2.equals("projekt"))
			filterquery = filterquery + filter + " = " + "\"" + filtervalue
					+ "\" and " + filter + " = " + "\"" + filtervalue + "\"";
		if (filter.equals("bereich"))
			filterquery = filterquery + filter + " = " + "\"" + filtervalue
					+ "\"";
		if (filter.equals("projekt"))
			filterquery = filterquery + filter + " = " + "\"" + filtervalue
					+ "\"";
		if (filter.equals("entwicklungsstufe"))
			filterquery = filterquery + filter + " = " + "" + filtervalue + "";
		if (filter.equals("monat"))
			filterquery = filterquery + filter + " = " + "" + filtervalue + "-"
					+ filtervalue2;
		if (filter.equals("quartal")) {
			if (filtervalue.equals("1"))
				filterquery = filterquery + filter + " = 01-" + filtervalue2
						+ " or " + filter + "=02-" + filtervalue2 + " or "
						+ filter + "=03-" + filtervalue2;
			if (filtervalue.equals("2"))
				filterquery = filterquery + filter + " = 04-" + filtervalue2
						+ " or " + filter + "=05-" + filtervalue2 + " or "
						+ filter + "=06-" + filtervalue2;
			if (filtervalue.equals("3"))
				filterquery = filterquery + filter + " = 07-" + filtervalue2
						+ " or " + filter + "=08-" + filtervalue2 + " or "
						+ filter + "=09-" + filtervalue2;
			if (filtervalue.equals("4"))
				filterquery = filterquery + filter + " = 10-" + filtervalue2
						+ " or " + filter + "=11-" + filtervalue2 + " or "
						+ filter + "=12-" + filtervalue2;
		}

		// wildcard
		if (filter.equals("jahr"))
			filterquery = filterquery + filter + " contains " + filtervalue
					+ "";
		if (kennzahl == Kennzahl.LEISTUNG) {
			query = "select sum(stunden) from 480761361715:csv_data.data where konto != \"URLAUB\" and konto!=\"KRANK\"";
			if (filterquery.length() > 1)
				query = query + " and " + filterquery.substring(5);

		} else if (kennzahl == Kennzahl.AUSLASTUNG) {
			// FL/L
			String filterquery1 = "where fakturierbar=\"ja\"";
			String filterquery2 = "where konto != \"URLAUB\" and konto!=\"KRANK\"";
			if (filterquery.length() > 1)
				filterquery1 = filterquery1 + " and "
						+ filterquery.substring(5);
			filterquery2 = filterquery2 + " and " + filterquery.substring(5);

			query = "select sum(t1.stunden)/sum(t2.stunden) from (select stunden, maid from 480761361715:csv_data.data "
					+ filterquery1
					+ ") as t1 left join(select  stunden , maid from 480761361715:csv_data.data "
					+ filterquery2 + ")as t2 on t1.maid = t2.maid;";
		} else if (kennzahl == Kennzahl.ERTRAG) {// LU-Kosten
			String filterquery2 = "where fakturierbar=\"ja\"";
			if (filterquery.length() > 1)
				filterquery2 = filterquery2 + " and "
						+ filterquery.substring(5);

			query = "select sum(t1.grenzkosten*t1.stunden)-sum(t2.grenzkosten*t2.stunden) from (select grenzkosten, stunden, maid from 480761361715:csv_data.data where "
					+ filterquery
					+ ") as t1 left join(select grenzkosten, stunden , maid from 480761361715:csv_data.data "
					+ filterquery2 + ")as t2 on t1.maid = t2.maid;";
		} else if (kennzahl == Kennzahl.FAKLEISTUNG) {
			query = "select sum(stunden) from 480761361715:csv_data.data where fakturierbar=\"ja\"";
			if (filterquery.length() > 1)
				query = query + " and " + filterquery.substring(5);

		} else if (kennzahl == Kennzahl.KOSTEN) {// Sum(Grenzkosten*Stunden)
			query = "select sum(Grenzkosten*Stunden) from 480761361715:csv_data.data ";
			if (filterquery.length() > 1)
				query = query + filterquery;
		} else if (kennzahl == Kennzahl.KRANKHEITSQUOTE) {
			// sum(stunden) where = krank / L
			String filterquery1 = "where konto==\"KRANK\"";
			String filterquery2 = "where konto != \"URLAUB\" and konto!=\"KRANK\"";
			if (filterquery.length() > 1)
				filterquery1 = filterquery1 + " and "
						+ filterquery.substring(5);
			filterquery2 = filterquery2 + " and " + filterquery.substring(5);

			query = "select sum(t1.stunden)/sum(t2.stunden) from (select stunden, maid from 480761361715:csv_data.data "
					+ filterquery1
					+ ") as t1 left join(select  stunden , maid from 480761361715:csv_data.data "
					+ filterquery2 + ")as t2 on t1.maid = t2.maid;";
		} else// (kennzahl == Kennzahl.LEITUNGSUMSATZ)
		{
			query = "select sum(grenzkosten*stunden) from 480761361715:csv_data.data where fakturierbar=\"ja\"";
			if (filterquery.length() > 1)
				query = query + " and " + filterquery.substring(5);
		}
		return query;
	}

}
