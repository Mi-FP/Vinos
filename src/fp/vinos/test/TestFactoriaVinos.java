package fp.vinos.test;
/**
 * @author José A. Troyano
 * @reviewed Toñi Reina
 * 
 * @reviewed_date 2022-03-10
 */

import fp.vinos.FactoriaVinoteca;
import fp.vinos.Vinoteca;

public class TestFactoriaVinos {
	
	public static void main(String[] args) {
		testCreacionVinos("data/wine_reviews.csv");
	}

	private static void testCreacionVinos(String filename) {
		System.out.println("\nTEST de la creacion de vinos");
		try {
			Vinoteca vinos = FactoriaVinoteca.leerVinoteca(filename);
			System.out.println("   VINOS: "+ vinos);
		} catch(Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);	
		}
	}
}
