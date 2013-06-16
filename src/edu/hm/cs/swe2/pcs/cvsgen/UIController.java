package edu.hm.cs.swe2.pcs.cvsgen;

import static edu.hm.cs.swe2.pcs.cvsgen.Properties.*;

import java.util.List;

public class UIController {

	private EntryGenerator entryGenerator;
	
	private FileHandler fileHandler;

	public UIController() {
		initEntryGenerator();
		this.fileHandler = new FileHandler();
	}

	private void initEntryGenerator() {
		int[] idRange = Properties.getIntegerRange(MAID_RANGE);

		Picker<Integer> maIdPicker = new RangeIdPicker(idRange[0], idRange[1]);

		Picker<String> mitarbeiterPicker = new FilePicker(
				Properties.getFilePath(MITARBEITER_PATH));

		int[] levelRange = Properties.getIntegerRange(ENTWICKLUNGSSTUFE_RANGE);
		Picker<Integer> entwicklungsstufePicker = new RangeIntegerPicker(
				levelRange[0], levelRange[1]);

		float[] hoursRange = Properties.getFloatRange(STUNDEN_RANGE);
		Picker<Float> stundenPicker = new RangeFloatPicker(hoursRange[0],
				hoursRange[1]);

		int[] yearRange = Properties.getIntegerRange(MONAT_RANGE);
		Picker<String> monatPicker = new DatePicker(yearRange[0], yearRange[1]);

		Picker<String> projektPicker = new FilePicker(getFilePath(PROJEKT_PATH));

		Picker<String> bereichPicker = new FilePicker(getFilePath(BEREICH_PATH));

		Picker<String> kontoPicker = new FilePicker(getFilePath(KONTO_PATH));

		float[] costsRange = Properties.getFloatRange(GRENZKOSTEN_RANGE);
		Picker<Float> grenzkostenPicker = new RangeFloatPicker(costsRange[0],
				costsRange[1]);

		float[] setRange = Properties.getFloatRange(VERRECHNUNGSSATZ_RANGE);
		Picker<Float> verrechnungssatzPicker = new RangeFloatPicker(
				setRange[0], setRange[1]);

		entryGenerator = new EntryGenerator(maIdPicker,
				entwicklungsstufePicker, mitarbeiterPicker, monatPicker,
				projektPicker, bereichPicker, kontoPicker, stundenPicker,
				grenzkostenPicker, verrechnungssatzPicker);
	}

	public TableRowEntry onClickGenerateEntry(TableRowEntry entry,
			boolean maId, boolean mitarbeiter, boolean entwicklungsstufe,
			boolean stunden, boolean monat, boolean projekt, boolean bereich,
			boolean konto, boolean fakturierbar, boolean grenzkosten,
			boolean verrechnungssatz) {
		return this.entryGenerator.generateEntry(entry, maId, mitarbeiter,
				entwicklungsstufe, stunden, monat, projekt, bereich, konto,
				fakturierbar, grenzkosten, verrechnungssatz);
	}

	public TableRowEntry onClickGenerateEntry() {
		return onClickGenerateEntry(new TableRowEntry(), false, false, false,
				false, false, false, false, false, false, false, false);
	}

	public void onClickSaveSettings() {
		initEntryGenerator();
	}
	
	public List<TableRowEntry> onClickLoadFile(String path){
		List<TableRowEntry> output = this.fileHandler.loadCVS(path);
		for(TableRowEntry entry: output)
			((RangeIdPicker) entryGenerator.getMaIdPicker()).addUsedValue(entry.getMaId());
		return output;
	}
	
	public void onClickSaveFile(List<TableRowEntry> entries, String path){
		this.fileHandler.saveCSV(entries, path);
	}
}
