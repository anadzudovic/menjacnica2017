package menjacnica.gui;

import java.awt.EventQueue;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.interfejs.MenjacnicaInterface;

public class GUIKontroler {
	private static MenjacnicaGUI frame;
	private static DodajKursGUI dodajKursGUI;
	private static ObrisiKursGUI obrisiKursGUI;
	private static IzvrsiZamenuGUI izvrsiZamenuGUI;
	private static MenjacnicaInterface sistem;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame= new MenjacnicaGUI();
					sistem= new Menjacnica();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void prikaziDodajKursGUI(){
		dodajKursGUI = new DodajKursGUI();
		dodajKursGUI.setVisible(true);
		dodajKursGUI.setLocationRelativeTo(frame);
		
		}
	public static void prikaziObrisiKursGUI(Valuta v){
		obrisiKursGUI = new ObrisiKursGUI(v);
		obrisiKursGUI.setVisible(true);
		obrisiKursGUI.setLocationRelativeTo(frame);
		
		}
	public static void prikaziIzvrsiZamenuGUI(Valuta v){
		izvrsiZamenuGUI = new IzvrsiZamenuGUI( v);
		izvrsiZamenuGUI.setVisible(true);
		izvrsiZamenuGUI.setLocationRelativeTo(frame);
		
		}
	
         public static void ucitajIzFajla() {
	 		try {
	 			JFileChooser fc = new JFileChooser();
	 			int returnVal = fc.showOpenDialog(frame);
	 
	 			if (returnVal == JFileChooser.APPROVE_OPTION) {
	 				File file = fc.getSelectedFile();
	 				sistem.ucitajIzFajla(file.getAbsolutePath());
	 				frame.prikaziSveValute();
	 			}	
	 		} catch (Exception e1) {
	 			JOptionPane.showMessageDialog(frame, e1.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
	 		}
	 	}
    
         public static void sacuvajUFajl() {
        	 		try {
        	 			JFileChooser fc = new JFileChooser();
        	 			int returnVal = fc.showSaveDialog(frame);
        	 
        	 			if (returnVal == JFileChooser.APPROVE_OPTION) {
        	 				File file = fc.getSelectedFile();
        	 
        	 			sistem.sacuvajUFajl(file.getAbsolutePath());
        	 			}
        	 		} catch (Exception e1) {
        	 			JOptionPane.showMessageDialog(frame, e1.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
        	 		}
        	 	}
         public static void ugasiAplikaciju() {
        	 		int opcija = JOptionPane.showConfirmDialog(frame,"Da li ZAISTA zelite da izadjete iz aplikacije", "Izlazak", JOptionPane.YES_NO_OPTION);
        	 
        	 		if (opcija == JOptionPane.YES_OPTION)
        	 			System.exit(0);
        	 	}
        	 	
        	 	public static void prikaziAboutProzor(){
        	 		JOptionPane.showMessageDialog(frame,"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",	JOptionPane.INFORMATION_MESSAGE);
        	 	}
	
	public static void dodajValutu(String naziv, String skraceniNaziv, int sifra, String prodajniKurs, String kupovniKurs, String srednjiKurs) {
		Valuta valuta= new Valuta();
		try {
			valuta.setNaziv(naziv);
			valuta.setSkraceniNaziv(skraceniNaziv);
			valuta.setSifra(sifra);
			valuta.setProdajni(Double.parseDouble(prodajniKurs));
			valuta.setKupovni(Double.parseDouble(kupovniKurs));
			valuta.setSrednji(Double.parseDouble(srednjiKurs));
			sistem.dodajValutu(valuta);
			frame.prikaziSveValute();
			dodajKursGUI.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public static void obrisiValutu(Valuta valuta) {
		
		try {
			sistem.obrisiValutu(valuta);
			frame.prikaziSveValute();
			obrisiKursGUI.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public static double  izvrsiZamenu(Valuta valuta, boolean prodaja, String iznos) {
		double konacniIznos=0;
		try {
			double i= Double.parseDouble(iznos);
              konacniIznos = sistem.izvrsiTransakciju(valuta, prodaja, i);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
	 
		return konacniIznos;
	}
	public static List<Valuta> vratiValute() {
		 	return sistem.vratiKursnuListu();
		 	}
	

}


