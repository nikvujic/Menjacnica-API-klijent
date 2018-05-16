package gui.kontroler;

import java.awt.EventQueue;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import gui.GlavniProzor;
import klase.Zapis;
import klase.Zemlja;
import logika.Menjacnica;

public class GUIKontroler {
	private static LinkedList<Zemlja> zemlje = new LinkedList<Zemlja>();
	private static LinkedList<Zapis> zapisi = new LinkedList<Zapis>();
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static Menjacnica menjacnica = new Menjacnica();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					zapisi = Menjacnica.vratiListuZapisa();
					napuniZemljeList();
					GlavniProzor frame = new GlavniProzor();
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void napuniZemljeList() {
		menjacnica.dodajZemlje();
		try (FileReader input = new FileReader("data/countries.json")) {
			JsonObject jsonData = gson.fromJson(input, JsonObject.class);
			jsonData = (JsonObject) jsonData.get("results");

			for (Map.Entry<String, JsonElement> valueEntry : jsonData.entrySet()) {
				JsonObject element = (JsonObject) valueEntry.getValue();
				Zemlja zemlja = new Zemlja();
				for (Map.Entry<String, JsonElement> cE : element.entrySet()) {
					String[] data = cE.toString().split("=");

					String comp = data[0].toString();

					if (comp.equals("alpha3")) {
						zemlja.setAlpha3(data[1].replace("\"", ""));
					} else if (comp.equals("currencyId")) {
						zemlja.setCurrencyId(data[1].replace("\"", ""));
					} else if (comp.equals("currencyName")) {
						zemlja.setCurrencyName(data[1].replace("\"", ""));
					} else if (comp.equals("currencySymbol")) {
						zemlja.setCurrencySymbol(data[1].replace("\"", ""));
					} else if (comp.equals("id")) {
						zemlja.setId(data[1].replace("\"", ""));
					} else if (comp.equals("name")) {
						zemlja.setName(data[1].replace("\"", ""));
					}
				}
				zemlje.add(zemlja);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void konvertuj(GlavniProzor glavniProzor) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:s.SSSSSS");
		String datum = sdf.format(date);

		String fromPunNaziv = (String) glavniProzor.comboBox.getSelectedItem();
		String toPunNaziv = (String) glavniProzor.comboBox_1.getSelectedItem();

		String from = "";
		String to = "";
		
		for (int i = 0; i < zemlje.size(); i++) {
			if (zemlje.get(i).getName().equals(fromPunNaziv)) {
				from = zemlje.get(i).getCurrencyId();
			}
			if (zemlje.get(i).getName().equals(toPunNaziv)) {
				to = zemlje.get(i).getCurrencyId();
			}
		}

		double kurs = 0;
		try {
			kurs = Menjacnica.getKurs(from, to);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor.contentPane,
					"Nema rezultata za date parametre. Moguci razlozi:  \nNiste konektovani na internet\nGreska servera",
					to, JOptionPane.INFORMATION_MESSAGE);
		}

		double fromVrednost = 0;
		try {
			fromVrednost = Double.parseDouble(glavniProzor.textField.getText());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(glavniProzor.contentPane, "Morate uneti ispravan broj!", to,
					JOptionPane.INFORMATION_MESSAGE);
			glavniProzor.textField.setText("");
			return;
		}
		double toVrednost = fromVrednost * kurs;

		glavniProzor.textField_1.setText("" + toVrednost);

		Zapis zapis = new Zapis(datum, from, to, kurs);
		zapisi.add(zapis);

		Menjacnica.azurirajZapise(zapisi);  // TODO: 

	}

	@SuppressWarnings("unchecked")
	public static void napuniComboBoxove(GlavniProzor glavniProzor) {
		for (int i = 0; i < zemlje.size(); i++) {
			glavniProzor.comboBox.addItem(zemlje.get(i).getName());
		}
		
		for (int i = 0; i < zemlje.size(); i++) {
			glavniProzor.comboBox_1.addItem(zemlje.get(i).getName());
		}
	}
}
