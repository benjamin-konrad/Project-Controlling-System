package edu.hm.cs.swe2.pcs.cvsgen.gui;

import static edu.hm.cs.swe2.pcs.cvsgen.Properties.BEREICH_PATH;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.ENTWICKLUNGSSTUFE_RANGE;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.GRENZKOSTEN_RANGE;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.KONTO_PATH;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.MAID_RANGE;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.MITARBEITER_PATH;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.MONAT_RANGE;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.PROJEKT_PATH;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.STUNDEN_RANGE;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.VERRECHNUNGSSATZ_RANGE;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.setFilePath;
import static edu.hm.cs.swe2.pcs.cvsgen.Properties.setRange;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import edu.hm.cs.swe2.pcs.cvsgen.Properties;
import edu.hm.cs.swe2.pcs.cvsgen.TableModelData;
import edu.hm.cs.swe2.pcs.cvsgen.TableRowEntry;
import edu.hm.cs.swe2.pcs.cvsgen.UIController;
import javax.swing.ListSelectionModel;

public class CVSGenMain {

	private JFrame frmCvsgen;
	private JTable tableCVSEntries;
	private JTextField textFieldMitarbeiterlistePath;
	private JTextField textFieldProjektlistPath;
	private JTextField textFieldBereichlistePath;
	private JTextField textFieldKontolistePath;
	private JTextField textFieldMAIDMin;
	private JTextField textFieldMAIDMax;
	private JTextField textFieldEntwicklungstufeMin;
	private JTextField textFieldEntwicklungsstufeMax;
	private JTextField textFieldStundenMin;
	private JTextField textFieldStundenMax;
	private JTextField textFieldJahrMin;
	private JTextField textFieldJahrMax;
	private JTextField textFieldGrenzkostenMin;
	private JTextField textFieldGrenzkostenMax;
	private JTextField textFieldVerrechnungssatzMin;
	private JTextField textFieldVerrechnungssatzMax;
	private JButton btnSaveSettings;

	private UIController uiController;

	private TableModelData data;

	private JPanel panelTableSettings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CVSGenMain window = new CVSGenMain();
					window.frmCvsgen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CVSGenMain() {
		uiController = new UIController();
		data = new TableModelData(new Object[][] {}, new String[] { "MA-ID",
				"Mitarbeiter", "Entwicklungstufe", "Stunden", "Monat",
				"Projekt", "Bereich", "Konto", "fakturierbar", "Grenzkosten",
				"Verrechnungssatz" }, new Class[] { Integer.class,
				String.class, Integer.class, Float.class, String.class,
				String.class, String.class, String.class, String.class,
				Float.class, Float.class },
				new boolean[] { false, false, false, false, false, false,
						false, false, false, false, false },
				new ArrayList<TableRowEntry>());
		initialize();
		initEinstellungen();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCvsgen = new JFrame();
		frmCvsgen.setResizable(false);
		frmCvsgen.setTitle("CVSGen");
		frmCvsgen.setBounds(100, 100, 755, 600);
		frmCvsgen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmCvsgen.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE,
								729, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(11)
						.addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE,
								550, Short.MAX_VALUE).addContainerGap()));

		JPanel panel = new JPanel();
		tabbedPane_1.addTab("CVSGenerierung", null, panel, null);

		JButton btnSaveCvs = new JButton("CVS speichern");
		btnSaveCvs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClickSaveCvs(e);
			}
		});

		JButton btnLoadCVS = new JButton("CVS laden");
		btnLoadCVS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClickLoadCvs(e);
			}
		});

		JButton btnCreateEntry = new JButton("Eintrag erzeugen");
		btnCreateEntry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClickCreateEntry(e);
			}
		});

		panelTableSettings = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addGroup(
														Alignment.LEADING,
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		scrollPane,
																		GroupLayout.DEFAULT_SIZE,
																		704,
																		Short.MAX_VALUE))
												.addGroup(
														Alignment.LEADING,
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		panelTableSettings,
																		GroupLayout.DEFAULT_SIZE,
																		704,
																		Short.MAX_VALUE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap(
																		405,
																		Short.MAX_VALUE)
																.addComponent(
																		btnCreateEntry)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnLoadCVS)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnSaveCvs)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						Alignment.TRAILING,
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(panelTableSettings,
										GroupLayout.PREFERRED_SIZE, 65,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 400,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnSaveCvs)
												.addComponent(btnCreateEntry)
												.addComponent(btnLoadCVS))
								.addContainerGap()));

		tableCVSEntries = new JTable(){

			@Override
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
			
		};
		tableCVSEntries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCVSEntries.setFillsViewportHeight(true);
		tableCVSEntries.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(tableCVSEntries);
//		scrollPane.setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 0;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 1;
//		c.weighty = 0;
//		c.insets = new Insets(10, 10, 10, 10);
//		panel.add(tableCVSEntries, c);
		tableCVSEntries.setModel(data
		// {
		// Class[] columnTypes = new Class[] {
		// Integer.class, String.class, Integer.class, Float.class,
		// String.class, String.class, String.class, String.class,
		// Boolean.class, Float.class, Float.class
		// };
		// public Class getColumnClass(int columnIndex) {
		// return columnTypes[columnIndex];
		// // }
		// boolean[] columnEditables = new boolean[] {
		// false, false, false, false, false, false, false, false,
		// false, false,
		// false
		// };
		// public boolean isCellEditable(int row, int column) {
		// return columnEditables[column];
		// }
		// }
				);
		panelTableSettings.setLayout(new GridLayout(0, 3, 0, 0));

		JCheckBox chckbxMAID = new JCheckBox("MA-ID");
		chckbxMAID.setEnabled(true);
		panelTableSettings.add(chckbxMAID);

		JCheckBox chckbxMitarbeiter = new JCheckBox("Mitarbeiter");
		chckbxMitarbeiter.setEnabled(true);
		panelTableSettings.add(chckbxMitarbeiter);

		JCheckBox chckbxEntwicklungsstufe = new JCheckBox("Entwicklungsstufe");
		chckbxEntwicklungsstufe.setEnabled(true);
		panelTableSettings.add(chckbxEntwicklungsstufe);

		JCheckBox chckbxStunden = new JCheckBox("Stunden");
		chckbxStunden.setEnabled(true);
		panelTableSettings.add(chckbxStunden);

		JCheckBox chckbxMonat = new JCheckBox("Monat");
		chckbxMonat.setEnabled(true);
		panelTableSettings.add(chckbxMonat);

		JCheckBox chckbxProjekt = new JCheckBox("Projekt");
		chckbxProjekt.setEnabled(true);
		panelTableSettings.add(chckbxProjekt);

		JCheckBox chckbxBereich = new JCheckBox("Bereich");
		chckbxBereich.setEnabled(true);
		panelTableSettings.add(chckbxBereich);

		JCheckBox chckbxKonto = new JCheckBox("Konto");
		chckbxKonto.setEnabled(true);
		panelTableSettings.add(chckbxKonto);

		JCheckBox chckbxFrakturierbar = new JCheckBox("fakturierbar");
		chckbxFrakturierbar.setEnabled(true);
		panelTableSettings.add(chckbxFrakturierbar);

		JCheckBox chckbxGrenzkosten = new JCheckBox("Grenzkosten");
		chckbxGrenzkosten.setEnabled(true);
		panelTableSettings.add(chckbxGrenzkosten);

		JCheckBox chckbxVerrechnungssatz = new JCheckBox("Verrechnungssatz");
		chckbxVerrechnungssatz.setEnabled(true);
		panelTableSettings.add(chckbxVerrechnungssatz);
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Einstellungen", null, panel_1, null);

		textFieldMitarbeiterlistePath = new JTextField();
		textFieldMitarbeiterlistePath
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldMitarbeiterlistePath.setColumns(10);

		JLabel lblPfadZuMitarbeiter = new JLabel("Pfad zur Mitarbeiterliste");

		JButton btnMitarbeiterlistPicker = new JButton("Durchsuchen");
		btnMitarbeiterlistPicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClickPathSettings(e, textFieldMitarbeiterlistePath);
			}
		});

		JLabel lblPfadZurProjekt = new JLabel("Pfad zur Projektliste");

		textFieldProjektlistPath = new JTextField();
		textFieldProjektlistPath
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldProjektlistPath.setColumns(10);

		JButton btnProjektlistePicker = new JButton("Durchsuchen");
		btnProjektlistePicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClickPathSettings(e, textFieldProjektlistPath);
			}
		});

		JLabel lblPfadZuBereichlist = new JLabel("Pfad zur Bereichliste");

		textFieldBereichlistePath = new JTextField();
		textFieldBereichlistePath
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldBereichlistePath.setColumns(10);

		JButton btnBereichlistePicker = new JButton("Durchsuchen");
		btnBereichlistePicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClickPathSettings(e, textFieldBereichlistePath);
			}
		});

		JLabel lblPfadZuKontoliste = new JLabel("Pfad zur Kontoliste");

		textFieldKontolistePath = new JTextField();
		textFieldKontolistePath
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldKontolistePath.setColumns(10);

		JButton btnKontolistePicker = new JButton("Durchsuchen");
		btnKontolistePicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClickPathSettings(e, textFieldKontolistePath);
			}
		});

		JLabel lblMaidBereich = new JLabel("MA-ID Bereich");

		textFieldMAIDMin = new JTextField();
		textFieldMAIDMin
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldMAIDMin.setColumns(10);

		textFieldMAIDMax = new JTextField();
		textFieldMAIDMax
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldMAIDMax.setColumns(10);

		JLabel lblEntwicklungsstufeBereich = new JLabel(
				"Entwicklungsstufe Bereich");

		textFieldEntwicklungstufeMin = new JTextField();
		textFieldEntwicklungstufeMin
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldEntwicklungstufeMin.setColumns(10);

		textFieldEntwicklungsstufeMax = new JTextField();
		textFieldEntwicklungsstufeMax
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldEntwicklungsstufeMax.setColumns(10);

		JLabel lblStunden = new JLabel("Stunden Bereich");

		textFieldStundenMin = new JTextField();
		textFieldStundenMin
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldStundenMin.setColumns(10);

		textFieldStundenMax = new JTextField();
		textFieldStundenMax
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldStundenMax.setColumns(10);

		JLabel lblJahrBereich = new JLabel("Jahr Bereich");

		textFieldJahrMin = new JTextField();
		textFieldJahrMin
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldJahrMin.setColumns(10);

		textFieldJahrMax = new JTextField();
		textFieldJahrMax
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldJahrMax.setColumns(10);

		JLabel lblGrenzkostenBereich = new JLabel("Grenzkosten Bereich");

		textFieldGrenzkostenMin = new JTextField();
		textFieldGrenzkostenMin
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldGrenzkostenMin.setColumns(10);

		textFieldGrenzkostenMax = new JTextField();
		textFieldGrenzkostenMax
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldGrenzkostenMax.setColumns(10);

		JLabel lblVerrschnungssatzBereich = new JLabel(
				"Verrschnungssatz Bereich");

		textFieldVerrechnungssatzMin = new JTextField();
		textFieldVerrechnungssatzMin
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldVerrechnungssatzMin.setColumns(10);

		textFieldVerrechnungssatzMax = new JTextField();
		textFieldVerrechnungssatzMax
				.setText("C:\\Users\\Mateusz\\PBLV_Password\\passwordsFile.xml");
		textFieldVerrechnungssatzMax.setColumns(10);

		btnSaveSettings = new JButton("Speichern");
		btnSaveSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClickSaveSettings(e);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPfadZuMitarbeiter, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textFieldMitarbeiterlistePath, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnMitarbeiterlistPicker, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPfadZurProjekt, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textFieldProjektlistPath, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnProjektlistePicker, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPfadZuBereichlist, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textFieldBereichlistePath, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnBereichlistePicker, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPfadZuKontoliste, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textFieldKontolistePath, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnKontolistePicker, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textFieldMAIDMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldMAIDMax, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMaidBereich))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblEntwicklungsstufeBereich, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStunden, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(textFieldStundenMin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
											.addGap(10)
											.addComponent(textFieldStundenMax, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textFieldEntwicklungstufeMin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldEntwicklungsstufeMax, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblJahrBereich, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textFieldJahrMin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldJahrMax, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGrenzkostenBereich, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textFieldGrenzkostenMin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldGrenzkostenMax, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVerrschnungssatzBereich, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textFieldVerrechnungssatzMin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textFieldVerrechnungssatzMax, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(148, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(606, Short.MAX_VALUE)
					.addComponent(btnSaveSettings, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPfadZuMitarbeiter)
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldMitarbeiterlistePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMitarbeiterlistPicker))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPfadZurProjekt)
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldProjektlistPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnProjektlistePicker))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPfadZuBereichlist)
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldBereichlistePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBereichlistePicker))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPfadZuKontoliste)
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldKontolistePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnKontolistePicker))
					.addGap(19)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblMaidBereich)
							.addGap(5)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldMAIDMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldMAIDMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStunden)
								.addComponent(lblEntwicklungsstufeBereich))
							.addGap(5)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldStundenMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldStundenMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEntwicklungstufeMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEntwicklungsstufeMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblVerrschnungssatzBereich)
							.addGap(5)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldVerrechnungssatzMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldVerrechnungssatzMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblJahrBereich)
								.addGap(5)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(textFieldJahrMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textFieldJahrMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblGrenzkostenBereich)
								.addGap(5)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(textFieldGrenzkostenMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textFieldGrenzkostenMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(120)
					.addComponent(btnSaveSettings)
					.addGap(61))
		);
		panel_1.setLayout(gl_panel_1);
		frmCvsgen.getContentPane().setLayout(groupLayout);
	}

	private String onClickPicker(MouseEvent event, int selectionMode) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(selectionMode);
		int state = fileChooser.showOpenDialog(frmCvsgen);
		if (state == JFileChooser.APPROVE_OPTION) {
			File dir = fileChooser.getSelectedFile();
			return dir.getAbsolutePath();
		}
		return null;
	}

	private String onClickDirectoryPicker(MouseEvent event) {
		return onClickPicker(event, JFileChooser.DIRECTORIES_ONLY);
	}

	private String onClickFilePicker(MouseEvent event) {
		return onClickPicker(event, JFileChooser.FILES_ONLY);
	}

	private void onClickPathSettings(MouseEvent event, JTextField textField) {
		String path = onClickDirectoryPicker(event);
		textField.setText(path);
	}

	private void onClickLoadCvs(MouseEvent event) {
		String path = onClickFilePicker(event);
		if(path != null){			
			List<TableRowEntry> entries = uiController.onClickLoadFile(path);
			data.setEntries(entries);
		}
	}

	private void onClickSaveCvs(MouseEvent event) {
		String path = onClickDirectoryPicker(event);
		if(path != null){			
			if(!path.contains(".cvs")){
				if(System.getProperty("file.separator").equalsIgnoreCase(Character.toString(path.charAt(path.length()-1))))
					path = path.concat("output.cvs");
				else
					path = path.concat(".cvs");
			}
			uiController.onClickSaveFile(data.getEntries(), path);
		}
	}

	private void onClickCreateEntry(MouseEvent e) {
		int selectedIndex = this.tableCVSEntries.getSelectedRow();
		TableRowEntry entry = uiController.onClickGenerateEntry();
		if (selectedIndex > -1) {
			boolean[] selected = new boolean[11];
			Component[] components = panelTableSettings.getComponents();
			for (int i = 0; i < selected.length; i++)
				selected[i] = ((JCheckBox) components[i]).isSelected();
			int i = 0;
			entry = uiController.onClickGenerateEntry(
					this.data.getEntry(selectedIndex), selected[i++],
					selected[i++], selected[i++], selected[i++], selected[i++],
					selected[i++], selected[i++], selected[i++], selected[i++],
					selected[i++], selected[i++]);
		}
		data.addEntry(entry);
	}

	private void onClickSaveSettings(MouseEvent event) {
		setRange(MAID_RANGE, textFieldMAIDMin.getText(),
				textFieldMAIDMax.getText());
		setFilePath(MITARBEITER_PATH, textFieldMitarbeiterlistePath.getText());
		setRange(ENTWICKLUNGSSTUFE_RANGE,
				textFieldEntwicklungstufeMin.getText(),
				textFieldEntwicklungsstufeMax.getText());
		setRange(STUNDEN_RANGE, textFieldStundenMin.getText(),
				textFieldStundenMax.getText());
		setRange(MONAT_RANGE, textFieldJahrMin.getText(),
				textFieldJahrMax.getText());
		setFilePath(PROJEKT_PATH, textFieldProjektlistPath.getText());
		setFilePath(BEREICH_PATH, textFieldBereichlistePath.getText());
		setFilePath(KONTO_PATH, textFieldKontolistePath.getText());
		setRange(GRENZKOSTEN_RANGE, textFieldGrenzkostenMin.getText(),
				textFieldGrenzkostenMax.getText());
		setRange(VERRECHNUNGSSATZ_RANGE,
				textFieldVerrechnungssatzMin.getText(),
				textFieldVerrechnungssatzMax.getText());
		uiController.onClickSaveSettings();
	}

	private void initEinstellungen() {
		textFieldBereichlistePath.setText(Properties.getFilePath(BEREICH_PATH));

		int[] entwicklungsstufe = Properties
				.getIntegerRange(ENTWICKLUNGSSTUFE_RANGE);
		textFieldEntwicklungsstufeMax.setText(Integer
				.toString(entwicklungsstufe[1]));
		textFieldEntwicklungstufeMin.setText(Integer
				.toString(entwicklungsstufe[0]));

		float[] grenzkosten = Properties.getFloatRange(GRENZKOSTEN_RANGE);
		textFieldGrenzkostenMax.setText(Float.toString(grenzkosten[1]));
		textFieldGrenzkostenMin.setText(Float.toString(grenzkosten[0]));

		int[] jahr = Properties.getIntegerRange(MONAT_RANGE);
		textFieldJahrMax.setText(Integer.toString(jahr[1]));
		textFieldJahrMin.setText(Integer.toString(jahr[0]));

		textFieldKontolistePath.setText(Properties.getFilePath(KONTO_PATH));

		int[] maId = Properties.getIntegerRange(MAID_RANGE);
		textFieldMAIDMax.setText(Integer.toString(maId[1]));
		textFieldMAIDMin.setText(Integer.toString(maId[0]));

		textFieldMitarbeiterlistePath.setText(Properties
				.getFilePath(MITARBEITER_PATH));

		textFieldProjektlistPath.setText(Properties.getFilePath(PROJEKT_PATH));

		float[] stunden = Properties.getFloatRange(STUNDEN_RANGE);
		textFieldStundenMax.setText(Float.toString(stunden[1]));
		textFieldStundenMin.setText(Float.toString(stunden[0]));

		float[] verrechnungssatz = Properties
				.getFloatRange(VERRECHNUNGSSATZ_RANGE);
		textFieldVerrechnungssatzMax.setText(Float
				.toString(verrechnungssatz[1]));
		textFieldVerrechnungssatzMin.setText(Float
				.toString(verrechnungssatz[0]));
	}
}
