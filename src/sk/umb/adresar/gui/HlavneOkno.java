package sk.umb.adresar.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sk.umb.adresar.model.Osoba;

/**
 * Trieda hlavneho okna
 */
@SuppressWarnings("serial")
public class HlavneOkno extends JFrame{
	
	private static final Logger logger = Logger.getLogger("sk.umb.adresar");
	
	private JButton vytvor;
	private JButton zmaz;
	private JButton zmen;
	
	private JTextField hladaj;
	private JLabel hladajLabel;
	
	private JList<Osoba> zoznamOsob;
	DefaultListModel<Osoba> model;
	
	public HlavneOkno() {
		super("Evidencia osob");
		
		logger.info("Nastavujem UI");
		setUI();
		logger.info("Nastavujem okno");
		setWindow();
	}
	
	private void setWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void setUI() {
		hladaj = new JTextField(20);
		hladajLabel = new JLabel("Hladaj:");
		hladajLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		JPanel hladanie = new JPanel(new FlowLayout());
		hladanie.add(hladajLabel);
		hladanie.add(hladaj);
		
		model = new DefaultListModel<Osoba>();
		zoznamOsob = new JList<Osoba>(model);
		
		hladaj.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				zoznamOsob.clearSelection();
				hladaj();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				hladaj();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				hladaj();
			}
			
			private void hladaj(){
				for(int i = 0;i<model.size(); i++ ) {
					if(model.getElementAt(i).getMeno().startsWith(hladaj.getText()) || model.getElementAt(i).getPriezvisko().startsWith(hladaj.getText())) {
						zoznamOsob.setSelectedIndex(i);
						break;
					}else {
						zoznamOsob.clearSelection();
					}
				}
			}
		});
		
		vytvor = new JButton("Vytvor");
		
		vytvor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				OsobaDetail od = new OsobaDetail(HlavneOkno.this,new Osoba(), false);
				od.setVisible(true);
				if(od.getOsoba() != null) {
					logger.info("Pidavam osobu");
					model.addElement(od.getOsoba());
				}
			}
			
		});
		
		zmaz = new JButton("Zmaz");
		
		zmaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Mazem osobu");
				model.remove(zoznamOsob.getSelectedIndex());
			}
		});
		
		zmen = new JButton("Zmen");
		
		zmen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OsobaDetail od = new OsobaDetail(HlavneOkno.this,model.getElementAt(zoznamOsob.getSelectedIndex()), true);
				od.loadOsoba(model.getElementAt(zoznamOsob.getSelectedIndex()));
				od.setVisible(true);
			}
		});
		
		hladaj.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				zoznamOsob.clearSelection();
			}
		});
		
		
		
		JPanel tlacidla = new JPanel(new FlowLayout());
		tlacidla.add(vytvor);
		tlacidla.add(zmen);
		tlacidla.add(zmaz);
		
		this.add(hladanie, BorderLayout.NORTH);
		this.add(zoznamOsob, BorderLayout.CENTER);
		this.add(tlacidla, BorderLayout.SOUTH);
	}
	
}
