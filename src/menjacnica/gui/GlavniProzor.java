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
import menjacnica.gui.kontroler.GUIKontroler;

public class GlavniProzor extends JFrame {

	public JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	private JLabel lblIznos;
	private JLabel lblIznos_1;
	public JTextField textField;
	public JTextField textField_1;
	private JButton btnKonvertuj;
	
	private GlavniProzor glavniProzor = this;

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
		
		GUIKontroler.napuniComboBoxove(glavniProzor);
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
		}
		return comboBox;
	}

	private JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
			comboBox_1.setBounds(262, 63, 131, 24);
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
					GUIKontroler.konvertuj(glavniProzor);
				}
			});
			btnKonvertuj.setBounds(172, 197, 105, 25);
		}
		return btnKonvertuj;
	}

}
