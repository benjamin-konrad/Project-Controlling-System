package edu.hm.cs.swe2.pcs.cvsgen;

import java.util.Random;

public class EntryGenerator {

	private Picker<Integer> maIdPicker, entwicklungsstufePicker;
	private Picker<String> mitarbeiterPicker, monatPicker, projektPicker,
			bereichPicker, kontoPicker;
	private Picker<Float> stundenPicker, grenzkostenPicker,
			verrechnungssatzPicker;

	public EntryGenerator(Picker<Integer> maIdPicker,
			Picker<Integer> entwicklungsstufePicker,
			Picker<String> mitarbeiterPicker, Picker<String> monatPicker,
			Picker<String> projektPicker, Picker<String> bereichPicker,
			Picker<String> kontoPicker, Picker<Float> stundenPicker,
			Picker<Float> granzkostenPicker,
			Picker<Float> verrechnungssatzPicker) {
		super();
		this.maIdPicker = maIdPicker;
		this.entwicklungsstufePicker = entwicklungsstufePicker;
		this.mitarbeiterPicker = mitarbeiterPicker;
		this.monatPicker = monatPicker;
		this.projektPicker = projektPicker;
		this.bereichPicker = bereichPicker;
		this.kontoPicker = kontoPicker;
		this.stundenPicker = stundenPicker;
		this.grenzkostenPicker = granzkostenPicker;
		this.verrechnungssatzPicker = verrechnungssatzPicker;
	}

	public Picker<Integer> getMaIdPicker() {
		return maIdPicker;
	}

	public void setMaIdPicker(Picker<Integer> maIdPicker) {
		this.maIdPicker = maIdPicker;
	}

	public Picker<Integer> getEntwicklungsstufePicker() {
		return entwicklungsstufePicker;
	}

	public void setEntwicklungsstufePicker(
			Picker<Integer> entwicklungsstufePicker) {
		this.entwicklungsstufePicker = entwicklungsstufePicker;
	}

	public Picker<String> getMitarbeiterPicker() {
		return mitarbeiterPicker;
	}

	public void setMitarbeiterPicker(Picker<String> mitarbeiterPicker) {
		this.mitarbeiterPicker = mitarbeiterPicker;
	}

	public Picker<String> getMonatPicker() {
		return monatPicker;
	}

	public void setMonatPicker(Picker<String> monatPicker) {
		this.monatPicker = monatPicker;
	}

	public Picker<String> getProjektPicker() {
		return projektPicker;
	}

	public void setProjektPicker(Picker<String> projektPicker) {
		this.projektPicker = projektPicker;
	}

	public Picker<String> getBereichPicker() {
		return bereichPicker;
	}

	public void setBereichPicker(Picker<String> bereichPicker) {
		this.bereichPicker = bereichPicker;
	}

	public Picker<String> getKontoPicker() {
		return kontoPicker;
	}

	public void setKontoPicker(Picker<String> kontoPicker) {
		this.kontoPicker = kontoPicker;
	}

	public Picker<Float> getStundenPicker() {
		return stundenPicker;
	}

	public void setStundenPicker(Picker<Float> stundenPicker) {
		this.stundenPicker = stundenPicker;
	}

	public Picker<Float> getGrenzkostenPicker() {
		return grenzkostenPicker;
	}

	public void setGrenzkostenPicker(Picker<Float> grenzkostenPicker) {
		this.grenzkostenPicker = grenzkostenPicker;
	}

	public Picker<Float> getVerrechnungssatzPicker() {
		return verrechnungssatzPicker;
	}

	public void setVerrechnungssatzPicker(Picker<Float> verrechnungssatzPicker) {
		this.verrechnungssatzPicker = verrechnungssatzPicker;
	}

	public TableRowEntry generateEntry() {
		return generateEntry(null, false, false, false, false,
				false, false, false, false, false, false, false);
	}

	public TableRowEntry generateEntry(TableRowEntry entry, boolean maId,
			boolean mitarbeiter, boolean entwicklungsstufe, boolean stunden,
			boolean monat, boolean projekt, boolean bereich, boolean konto,
			boolean frakturierbar, boolean grenzkosten, boolean verrechnungssatz) {
		TableRowEntry output = null;
		if(entry == null)
			output = new TableRowEntry();
		else
			output = new TableRowEntry(entry);
		if (!maId)
			output.setMaId(this.maIdPicker.giveValue());
		if (!mitarbeiter)
			output.setMitarbeiter(this.mitarbeiterPicker.giveValue());
		if (!entwicklungsstufe)
			output.setEntwicklungsstufe(this.entwicklungsstufePicker.giveValue());
		if (!stunden)
			output.setStunden(this.stundenPicker.giveValue());
		if (!monat)
			output.setMonat(this.monatPicker.giveValue());
		if (!projekt)
			output.setProjekt(this.projektPicker.giveValue());
		if (!bereich)
			output.setBereich(this.bereichPicker.giveValue());
		if (!konto)
			output.setKonto(this.kontoPicker.giveValue());
		if (!frakturierbar) {
			Random random = new Random();
			output.setFakturierbar(random.nextBoolean());
		}
		if (!grenzkosten)
			output.setGrenzkosten(grenzkostenPicker.giveValue());
		if (!verrechnungssatz)
			output.setVerrechnungssatz(verrechnungssatzPicker.giveValue());
		return output;
	}
}
