package fp.vinos.test;
/**
 * @author José A. Troyano
 * 
 * @since 2019-02-26
 */

import fp.vinos.Vino;

public class TestVino {
		
	public static void main(String[] args) {	
		System.out.println("\nTEST del Constructor. Comportamiento normal");
		testConstructor("Spain","Northern Spain",96,110.0,"Tinta de Toro");
	
		System.out.println("\nTEST del Constructor. Comportamiento excepcional");	
		testConstructor("Spain","Northern Spain",101,110.0,"Tinta de Toro");

		System.out.println("\nTEST del Constructor. Comportamiento excepcional");	
		testConstructor("Spain","Northern Spain",96,-110.0,"Tinta de Toro");

	}

	private static void testConstructor(String pais, String region, Integer puntos, 
			Double precio, String uva) {
		try {
			Vino vino = new Vino(pais, region, puntos, precio, uva);
			System.out.println("   VINO: "+ vino);		
		} catch(Exception e) {
			System.out.println("Excepción capturada:\n   " + e);	
		}
	}	
}
