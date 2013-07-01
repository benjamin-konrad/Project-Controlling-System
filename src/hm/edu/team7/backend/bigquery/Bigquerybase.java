package hm.edu.team7.backend.bigquery;

import hm.edu.team7.rest.jaxb.model.Kennzahl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.datanucleus.query.expression.CastExpression;

import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import com.google.api.services.bigquery.model.TableCell;
import com.google.api.services.bigquery.model.TableRow;

public class Bigquerybase {
	public static String fetchMitarbeiter(Kennzahl kennzahl, String mitarbeiter) {
		String query = buildKennzahlquery(kennzahl, "mitarbeiter", mitarbeiter,
				"", "");
		GetQueryResultsResponse queryResult = bigquerytest.queryBig(
				"Mitarbeiter", query);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				try {
					result.add(((String) field.getV()));
				} catch (ClassCastException e) {
					result.add("0");
				}
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray[0];
	}

	public static String fetchKonto(Kennzahl kennzahl, String bereich,
			String projekt) {
		String query = buildKennzahlquery(kennzahl, "projekt", projekt,
				"bereich", bereich);
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Konto",
				query);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				try {
					result.add(((String) field.getV()));
				} catch (ClassCastException e) {
					result.add("0");
				}
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray[0];
	}

	public static String fetchProjekt(Kennzahl kennzahl, String projekt) {
		String query = buildKennzahlquery(kennzahl, "projekt", projekt, "", "");
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Projekt",
				query);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				try {
					result.add(((String) field.getV()));
				} catch (ClassCastException e) {
					result.add("0");
				}
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray[0];
	}

	public static String fetchBereich(Kennzahl kennzahl, String bereich) {
		String query = buildKennzahlquery(kennzahl, "bereich", bereich, "", "");
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Bereich",
				query);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				try {
					result.add(((String) field.getV()));
				} catch (ClassCastException e) {
					result.add("0");
				}
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray[0];

	}

	public static String fetchQuartal(Kennzahl kennzahl, int quartal, int jahr) {
		String query = buildKennzahlquery(kennzahl, "quartal",
				String.valueOf(quartal), "jahr", String.valueOf(jahr));
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Quartal",
				query);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				try {
					result.add(((String) field.getV()));
				} catch (ClassCastException e) {
					result.add("0");
				}
			}
		}
		return result.get(0);

	}

	public static String fetchMonat(Kennzahl kennzahl, int monat, int jahr) {
		String query = buildKennzahlquery(kennzahl, "monat",
				String.valueOf(monat), "jahr", String.valueOf(jahr));
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Monat",
				query);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				try {
					result.add(((String) field.getV()));
				} catch (ClassCastException e) {
					result.add("0");
				}

			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray[0];

	}

	public static String fetchJahr(Kennzahl kennzahl, int jahr) {
		String query = buildKennzahlquery(kennzahl, "jahr",
				String.valueOf(jahr), "", "");
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Jahr",
				query);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				try {
					result.add(((String) field.getV()));
				} catch (ClassCastException e) {
					result.add("0");
				}

			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray[0];
	}

	public static String fetchStufe(Kennzahl kennzahl, int stufe) {
		String query = buildKennzahlquery(kennzahl, "entwicklungsstufe",
				String.valueOf(stufe), "", "");
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Stufe",
				query);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				try {
					result.add(((String) field.getV()));
				} catch (ClassCastException e) {
					result.add("0");
				}

			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray[0];

	}

	public static Integer[] fetchallYears() {
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("AllYears",
				"");
		List<TableRow> rows = queryResult.getRows();
		List<Integer> result = new ArrayList<Integer>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				String all = (String) field.getV();
				Integer jahr = Integer.parseInt(all);
				if (!result.contains(jahr))
					result.add(jahr);

			}
		}
		Integer[] returnarray = new Integer[result.size()];
		int i = 0;
		for (Integer res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray;
	}

	public static Integer[] fetchallMonth(int year) {
		GetQueryResultsResponse queryResult = bigquerytest.queryBig(
				"AllMonths", String.valueOf(year));

		List<TableRow> rows = queryResult.getRows();
		List<Integer> result = new ArrayList<Integer>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				Integer monat = Integer.parseInt(((String) field.getV()));
				if (!result.contains(monat))
					result.add(monat);

			}
		}
		Integer[] returnarray = new Integer[result.size()];
		int i = 0;
		for (Integer res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray;
	}

	public static Integer[] fetchallQuartal(int quartal) {
		GetQueryResultsResponse queryResult = bigquerytest.queryBig(
				"AllQuartals", String.valueOf(quartal));
		List<TableRow> rows = queryResult.getRows();
		List<Integer> result = new ArrayList<Integer>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {
				Integer monat = Integer.parseInt(((String) field.getV()));
				int quart = 0;
				if (monat <= 3)
					quart = 1;
				else if (monat <= 6)
					quart = 2;
				else if (monat <= 9)
					quart = 3;
				else
					quart = 4;
				if (!result.contains(quart))
					result.add(quart);
			}
		}
		Integer[] returnarray = new Integer[result.size()];
		int i = 0;
		for (Integer res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray;
	}

	public static String[] fetchallBereiche() {
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Bereiche",
				"");
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray;
	}

	public static String[] fetchallProjekte(String bereich) {
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Projekte",
				bereich);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray;
	}

	public static String[] fetchallKonten(String bereich, String projekt) {
		GetQueryResultsResponse queryResult = bigquerytest.queryBig("Konten",
				bereich + "," + projekt);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray;
	}

	public static String[] fetchallStufen() {
		GetQueryResultsResponse queryResult = bigquerytest.queryBig(
				"alleStufen", "");
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray;
	}

	public static String[] fetchallMitarbeiter(String estufe) {
		GetQueryResultsResponse queryResult = bigquerytest.queryBig(
				"alleMitarbeiter", estufe);
		List<TableRow> rows = queryResult.getRows();
		List<String> result = new ArrayList<String>();
		for (TableRow row : rows) {
			for (TableCell field : row.getF()) {

				result.add((String) field.getV());
			}
		}
		String[] returnarray = new String[result.size()];
		int i = 0;
		for (String res : result) {
			returnarray[i] = res;
			i++;
		}
		return returnarray;
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
			{String val = filtervalue;
			if(Integer.parseInt(filtervalue) < 10)
				val = "0"+filtervalue;
			filterquery = filterquery + filter + " = " + "\"" + val
					+ "-" + filtervalue2 + "\"";}
		if (filter.equals("quartal")) {
			filter = "monat";
			if (filtervalue.equals("1"))

				filterquery = filterquery + filter + " = \"01-" + filtervalue2
						+ "\" or " + filter + "=\"02-" + filtervalue2
						+ "\" or " + filter + "=\"03-" + filtervalue2 + "\"";
			if (filtervalue.equals("2"))
				filterquery = filterquery + filter + " = \"04-" + filtervalue2
						+ "\" or " + filter + "=\"05-" + filtervalue2
						+ "\" or " + filter + "=\"06-" + filtervalue2 + "\"";
			if (filtervalue.equals("3"))
				filterquery = filterquery + filter + " = \"07-" + filtervalue2
						+ "\" or " + filter + "=\"08-" + filtervalue2
						+ "\" or " + filter + "=\"09-" + filtervalue2 + "\"";
			if (filtervalue.equals("4"))
				filterquery = filterquery + filter + " = \"10-" + filtervalue2
						+ "\" or " + filter + "=\"11-" + filtervalue2
						+ "\" or " + filter + "=\"12-" + filtervalue2 + "\"";
		}

		// wildcard
		if (filter.equals("jahr"))
			filterquery = filterquery + "monat" + " contains \"" + filtervalue
					+ "\"";
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
