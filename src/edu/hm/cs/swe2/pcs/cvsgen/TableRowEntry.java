package edu.hm.cs.swe2.pcs.cvsgen;

public class TableRowEntry {

	private int maId;
	private String mitarbeiter;
	private int entwicklungsstufe;
	private float stunden;
	private String monat;
	private String projekt;
	private String bereich;
	private String konto;
	private boolean fakturierbar;
	private float grenzkosten;
	private float verrechnungssatz;

	public TableRowEntry() {
		this(0, null, 0, 0.0f, null, null, null, null, false, 0.0f, 0.0f);
	}
	
	public TableRowEntry(TableRowEntry that){
		this.maId = that.maId;
		this.mitarbeiter = that.mitarbeiter;
		this.entwicklungsstufe = that.entwicklungsstufe;
		this.stunden = that.stunden;
		this.monat = that.monat;
		this.projekt = that.projekt;
		this.bereich = that.bereich;
		this.konto = that.konto;
		this.fakturierbar = that.fakturierbar;
		this.grenzkosten = that.grenzkosten;
		this.verrechnungssatz = that.verrechnungssatz;
	}

	public TableRowEntry(int maId, String mitarbeiter, int entwicklungsstufe,
			float stunden, String monat, String projekt, String bereich,
			String konto, boolean fakturierbar, float grenzkosten,
			float verrechnungssatz) {
		super();
		this.maId = maId;
		this.mitarbeiter = mitarbeiter;
		this.entwicklungsstufe = entwicklungsstufe;
		this.stunden = stunden;
		this.monat = monat;
		this.projekt = projekt;
		this.bereich = bereich;
		this.konto = konto;
		this.fakturierbar = fakturierbar;
		this.grenzkosten = grenzkosten;
		this.verrechnungssatz = verrechnungssatz;
	}

	public int getMaId() {
		return maId;
	}

	public void setMaId(int maId) {
		this.maId = maId;
	}

	public String getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(String mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public int getEntwicklungsstufe() {
		return entwicklungsstufe;
	}

	public void setEntwicklungsstufe(int entwicklungsstufe) {
		this.entwicklungsstufe = entwicklungsstufe;
	}

	public float getStunden() {
		return stunden;
	}

	public void setStunden(float stunden) {
		this.stunden = stunden;
	}

	public String getMonat() {
		return monat;
	}

	public void setMonat(String monat) {
		this.monat = monat;
	}

	public String getProjekt() {
		return projekt;
	}

	public void setProjekt(String projekt) {
		this.projekt = projekt;
	}

	public String getBereich() {
		return bereich;
	}

	public void setBereich(String bereich) {
		this.bereich = bereich;
	}

	public String getKonto() {
		return konto;
	}

	public void setKonto(String konto) {
		this.konto = konto;
	}

	public boolean isFakturierbar() {
		return fakturierbar;
	}

	public void setFakturierbar(boolean fakturierbar) {
		this.fakturierbar = fakturierbar;
	}

	public float getGrenzkosten() {
		return grenzkosten;
	}

	public void setGrenzkosten(float grenzkosten) {
		this.grenzkosten = grenzkosten;
	}

	public float getVerrechnungssatz() {
		return verrechnungssatz;
	}

	public void setVerrechnungssatz(float verrechnungssatz) {
		this.verrechnungssatz = verrechnungssatz;
	}

	public String toCVSString() {
		StringBuilder output = new StringBuilder();
		output.append(this.maId).append(",").append(this.mitarbeiter)
				.append(",").append(entwicklungsstufe).append(",")
				.append(stunden).append(",").append(monat).append(",")
				.append(projekt).append(",").append(bereich).append(",")
				.append(konto).append(",").append(fakturierbar ? "ja" : "nein")
				.append(",").append(grenzkosten).append(",")
				.append(verrechnungssatz);
		return output.toString();
	}
	
	public Object[] toArray(){
		return new Object[]{
			this.maId, this.mitarbeiter, this.entwicklungsstufe, this.stunden, this.monat, this.projekt, this.bereich, this.konto, this.fakturierbar, this.grenzkosten, this.verrechnungssatz
		};
	}
}
