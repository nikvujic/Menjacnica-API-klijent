package menjacnica.gui.kontroler;

import java.awt.EventQueue;

import javax.swing.UIManager;

import menjacnica.Menjacnica;
import menjacnica.gui.GlavniProzor;

public class GUIKontroler {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor frame = new GlavniProzor();
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Menjacnica.dodajZemlje();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
