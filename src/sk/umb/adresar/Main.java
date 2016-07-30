package sk.umb.adresar;

import java.util.logging.Logger;

import javax.swing.UIManager;

import sk.umb.adresar.gui.HlavneOkno;

/**
 * Hlavna trieda
 */
public class Main {

	private static final Logger logger = Logger.getLogger("sk.umb.adresar");
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Spustam aplikaciu");
		
		
		new HlavneOkno();
	}

}
