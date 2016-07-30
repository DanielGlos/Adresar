package sk.umb.adresar.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.*;

import sk.umb.adresar.model.Osoba;
import sk.umb.adresar.model.enums.Pohlavie;
import sk.umb.adresar.model.enums.RodStav;
import sk.umb.adresar.model.enums.Vzdelanie;

/**
 * Trieda reprezentujuca okno zobrazujuce detail osoby
 * kde sa nastavuju jej hodnoty
 */
@SuppressWarnings("serial")
public class OsobaDetail extends JDialog{
	
	private static final Logger logger = Logger.getLogger("sk.umb.adresar");
	
	private JButton vytvor;
	private JButton storno;
	private JTextField meno;
	private JTextField priezvisko;
	private JTextField tel;
	private JTextField datNar;
	private ButtonGroup pohGrp;
	private JRadioButton muz;
	private JRadioButton zena;
	private JSpinner rodStav;
	private final String[] rodStavy = { "slobodny/a", "zadany/a" };
	private JComboBox<String> vzdelanie;
	private JCheckBox zverejnit;
	private JSlider body;
	private JTextArea poznamka;
	
	private Osoba osoba;
	
	public OsobaDetail(HlavneOkno hlavneOkno,Osoba o, boolean zmen) {
		super(hlavneOkno, "Nova osoba",  true);
		osoba = o;
		setUI(zmen);
		setWindow();
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {
				osoba = null;
			}
			@Override
			public void windowClosed(WindowEvent e) {}
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}
	
	private void setWindow() {
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private void setUI(boolean zmen) {
		
		JPanel mainPanel = (JPanel) getContentPane();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints pos = new GridBagConstraints();
		
		
		JPanel cell_1 = new JPanel(new GridBagLayout());
		JPanel cell_2 = new JPanel(new GridBagLayout());
		
		
		pos.anchor = GridBagConstraints.WEST;
		pos.insets = new Insets(10,5,0,5);
		cell_1.add(new JLabel("Meno:"), pos);
		meno = new JTextField(17);
		cell_1.add(meno, pos);
		pos.gridy = 1;
		cell_1.add(new JLabel("Priezvisko:"),pos);
		priezvisko = new JTextField(17);
		cell_1.add(priezvisko,pos);
		pos.gridy = 2;
		cell_1.add(new JLabel("Datum narodenia:"), pos);
		JPanel datNarPan = new JPanel();
		datNar = new JTextField(10);
		datNar.setText("01.01.1970");		
		datNarPan.add(datNar);
		datNarPan.add(new JLabel("(dd.mm.rrrr)"));
		cell_1.add(datNarPan, pos);
		pos.gridy = 3;
		cell_1.add(new JLabel("Pohlavie:"), pos);
		pohGrp = new ButtonGroup();
		muz = new JRadioButton("muz");
		zena = new JRadioButton("zena");
		pohGrp.add(muz);
		pohGrp.add(zena);
		JPanel pohPan = new JPanel();
		pohPan.add(muz);
		pohPan.add(zena);
		muz.setSelected(true);
		cell_1.add(pohPan, pos);
		pos.gridy = 4;
		cell_1.add(new JLabel("Rodinny stav:"), pos);
		rodStav = new JSpinner(new SpinnerListModel(rodStavy));
		cell_1.add(rodStav, pos);
		pos.gridy = 5;
		cell_1.add(new JLabel("Vzdelanie:"), pos);
		String[] vzdelanieStrings = { "zakladna skola", "stredna skola", "vysoka skola" };
		vzdelanie = new JComboBox<String>(vzdelanieStrings);
		vzdelanie.setSelectedIndex(0);
		cell_1.add(vzdelanie, pos);
		pos.gridy = 6;
		cell_1.add(new JLabel("Zverejnit:"), pos);
		zverejnit = new JCheckBox();
		cell_1.add(zverejnit, pos);
		
		pos.gridy = 0;
		tel = new JTextField(13);
		JPanel telPan = new JPanel();
		telPan.add(new JLabel("Telefon:"));
		telPan.add(tel);
		cell_2.add(telPan, pos);
		pos.gridy = 1;
		cell_2.add(new JLabel("Pocet bodov:"), pos);
		pos.gridy = 2;
		pos.fill = GridBagConstraints.HORIZONTAL;
		body = new JSlider(0,50);
		cell_2.add(body, pos);
		pos.fill = GridBagConstraints.NONE;
		pos.gridy = 3;
		cell_2.add(new JLabel("Poznamka:"), pos);
		pos.gridy = 4;
		poznamka = new JTextArea(7,25);
		poznamka.setLineWrap(true);
		poznamka.setWrapStyleWord(true);
		poznamka.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		cell_2.add(poznamka, pos);
		
		
		
		pos.gridx = 0;
		pos.gridy = 0;
		pos.anchor = GridBagConstraints.NORTH;
		mainPanel.add(cell_1,pos);
		pos.gridx = 1;
		mainPanel.add(cell_2, pos);
		
		storno = new JButton("Storno");
		if(zmen) {
			vytvor = new JButton("Uloz");
			storno.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}else {
			vytvor = new JButton("Vytvor");
			storno.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					osoba = null;
					dispose();
				}
			});
		}
		
		pos.gridx = 0;
		pos.gridy = 7;
		pos.anchor = GridBagConstraints.CENTER;
		pos.insets = new Insets(25,0,10,0);
		pos.gridwidth = 4;
		JPanel akcie = new JPanel();
		akcie.add(vytvor);
		akcie.add(storno);
		mainPanel.add(akcie, pos);
		
		
		/**
		 * Odstrani napovedu ked okno ziska focus
		 * ak je obsah == "" tak nastavi znova na 
		 * napovedu
		 */
		datNar.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(datNar.getText().equals("")) {
					datNar.setText("01.01.1970");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(datNar.getText().equals("01.01.1970")) {
					datNar.setText(null);
				}
			}
		});
		
		
		/**
		 * Ked sa klikne na tlacidlo "vytvorit"
		 * skontroluje ci su zadane povinne udaje
		 * a vytvori novu osobu
		 */
		vytvor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(meno.getText().equals("") || priezvisko.getText().equals("")) {
					JOptionPane.showMessageDialog(OsobaDetail.this, "Meno a priezvisko su povinne udaje");
				}else {
					ulozOsobu();
					dispose();
				}
				
			}
		});
		
		
		
	}
	
	private void ulozOsobu() {
		osoba.setMeno(meno.getText());
		osoba.setPriezvisko(priezvisko.getText());
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			osoba.setDatNar(format.parse(datNar.getText()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(muz.isSelected())
			osoba.setPohl(Pohlavie.MUZ);
		else
			osoba.setPohl(Pohlavie.ZENA);
		switch(rodStav.getValue().toString()) {
		case "zadany/a" :
			osoba.setRodStav(RodStav.ZADANY);
			break;
		case "slobodny/a" :
			osoba.setRodStav(RodStav.SLOBODNY);
			break;
		}
		switch(vzdelanie.getSelectedItem().toString()) {
		case "zakladna skola" :
			osoba.setVzdelanie(Vzdelanie.ZAKLADNA);
			break;
		case "stredna skola" :
			osoba.setVzdelanie(Vzdelanie.STREDNA);
			break;
		case "vysoka skola" :
			osoba.setVzdelanie(Vzdelanie.VYSOKA);
			break;
		}
		osoba.setZverejnit(zverejnit.isSelected());
		osoba.setTel(tel.getText());
		osoba.setBody(body.getValue());
		osoba.setPoznamka(poznamka.getText());
		logger.info("Osoba " + osoba.getPriezvisko() + " " + osoba.getMeno() + " bola ulozena");
	}
	
	public void loadOsoba(Osoba o) {
		logger.info("Nacitavam osobu " + o.getPriezvisko() + " " + o.getMeno());
		meno.setText(o.getMeno());
		priezvisko.setText(o.getPriezvisko());
		tel.setText(o.getTel());
		datNar.setText(new SimpleDateFormat("dd.MM.yyyy").format(o.getDatNar()));
		switch(o.getPohl()) {
		case MUZ:
			muz.setSelected(true);
			break;
		case ZENA:
			zena.setSelected(true);
			break;
		}
		switch(o.getRodStav()) {
		case SLOBODNY:
			rodStav.setValue(rodStavy[0]);
			break;
		case ZADANY:
			rodStav.setValue(rodStavy[1]);
			break;
		}
		switch(o.getVzdelanie()) {
		case ZAKLADNA:
			vzdelanie.setSelectedIndex(Vzdelanie.ZAKLADNA.ordinal());
			break;
		case STREDNA:
			vzdelanie.setSelectedIndex(Vzdelanie.STREDNA.ordinal());
			break;
		case VYSOKA:
			vzdelanie.setSelectedIndex(Vzdelanie.VYSOKA.ordinal());
			break;
		}
		zverejnit.setSelected(o.isZverejnit());
		body.setValue(o.getBody());
		poznamka.setText(o.getPoznamka());
	}
	
	public Osoba getOsoba() {
		return osoba;
	}
}
