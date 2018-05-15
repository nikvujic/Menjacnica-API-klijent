package menjacnica.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import menjacnica.Menjacnica;
import menjacnica.Zapis;
import menjacnica.Zemlja;

public class GlavniProzor extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel lblIznos;
	private JLabel lblIznos_1;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnKonvertuj;

	static LinkedList<Zemlja> zemlje = new LinkedList<Zemlja>();
	static LinkedList<Zapis> zapisi = new LinkedList<Zapis>();
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menjacnica.napuniListuZapisa(zapisi);
					fillZemljeList();
					GlavniProzor frame = new GlavniProzor();
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GlavniProzor() {
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 283);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlje());
		contentPane.add(getLblUValutuZemlje());
		contentPane.add(getComboBox());
		contentPane.add(getComboBox_1());
		contentPane.add(getLblIznos());
		contentPane.add(getLblIznos_1());
		contentPane.add(getTextField());
		contentPane.add(getTextField_1());
		contentPane.add(getBtnKonvertuj());
	}

	private JLabel getLblIzValuteZemlje() {
		if (lblIzValuteZemlje == null) {
			lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
			lblIzValuteZemlje.setBounds(54, 36, 131, 15);
		}
		return lblIzValuteZemlje;
	}

	private JLabel getLblUValutuZemlje() {
		if (lblUValutuZemlje == null) {
			lblUValutuZemlje = new JLabel("U valutu zemlje:");
			lblUValutuZemlje.setBounds(262, 36, 131, 15);
		}
		return lblUValutuZemlje;
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(54, 63, 131, 24);
			for (int i = 0; i < zemlje.size(); i++) {
				comboBox.addItem(zemlje.get(i).getName());
			}
		}
		return comboBox;
	}

	private JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
			comboBox_1.setBounds(262, 63, 131, 24);
			for (int i = 0; i < zemlje.size(); i++) {
				comboBox_1.addItem(zemlje.get(i).getName());
			}
		}
		return comboBox_1;
	}

	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos:");
			lblIznos.setBounds(54, 111, 131, 15);
		}
		return lblIznos;
	}

	private JLabel getLblIznos_1() {
		if (lblIznos_1 == null) {
			lblIznos_1 = new JLabel("Iznos:");
			lblIznos_1.setBounds(262, 111, 66, 15);
		}
		return lblIznos_1;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(54, 138, 131, 19);
			textField.setColumns(10);
		}
		return textField;
	}

	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(262, 138, 131, 19);
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:s.SSSSSS");
					String datum = sdf.format(date);

					String fromPunNaziv = (String) comboBox.getSelectedItem();
					String toPunNaziv = (String) comboBox_1.getSelectedItem();

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
						JOptionPane.showMessageDialog(contentPane,
								"Nema rezultata za date parametre. Moguci razlozi:  \nNiste konektovani na internet\nGreska servera",
								to, JOptionPane.INFORMATION_MESSAGE);
					}

					double fromVrednost = 0;
					try {
						fromVrednost = Double.parseDouble(textField.getText());
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(contentPane,
								"Morate uneti ispravan broj!",
								to, JOptionPane.INFORMATION_MESSAGE);
						textField.setText("");
						return;
					}
					double toVrednost = fromVrednost * kurs;

					textField_1.setText("" + toVrednost);
					
					Zapis zapis = new Zapis(datum, from, to, kurs);
					zapisi.add(zapis);
					
					Menjacnica.azurirajZapise(zapisi);
				}
			});
			btnKonvertuj.setBounds(172, 197, 105, 25);
		}
		return btnKonvertuj;
	}

	private static void fillZemljeList() {
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
}
