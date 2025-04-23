package fp.vinos.test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fp.vinos.FactoriaVinoteca;
import fp.vinos.Vino;
import fp.vinos.Vinoteca;
import fp.vinos.VinotecaBucles;

public class TestVinotecaBucles {

	private static <T> void mostrarMapConColeccion(Map<T, List<Vino>> m, Integer n) {
		for(Map.Entry<T, List<Vino>> entry: m.entrySet()) {
			System.out.println(entry.getKey());
			List<Vino> lv = entry.getValue();
			if (n!= -1 && lv.size() >n){
				lv = lv.subList(0, n);
			}
			mostrarColeccion(lv);
		}	
	}

	private static void mostrarColeccion(Collection<Vino> vinos) {
		vinos.stream()
			.forEach(v->System.out.println("\t"+v));
	}
	
	public static void main(String[] args) {
		Vino vino1 = new Vino("US", "California", 96, 235.0, "Cabernet Sauvignon");
		Vino vino2 = new Vino("Spain", "Northern Spain", 96, 110.0, "Tinta de Toro");
		Vino vino3 = new Vino("US", "California", 96, 90.0, "Sauvignon Blanc");
		Vino vino4 = new Vino("US", "Oregon", 96, 65.0, "Pinot Noir");
		Vino vino5 = new Vino("France", "Provence", 95, 66.0, "Provence red blend");
		Vino vino6 = new Vino("Spain", "Northern Spain", 95, 73.0, "Tinta de Toro");
		Vino vino7 = new Vino("Spain", "Northern Spain", 95, 65.0, "Tinta de Toro");
		Vino vino8 = new Vino("Spain", "Northern Spain", 95, 110.0, "Tinta de Toro");
		Vino vino9 = new Vino("US", "Oregon", 95, 65.0, "Pinot Noir");
		Vino vino10 = new Vino("US", "California", 95, 60.0, "Pinot Noir");
		List<Vino> lista_vinos = List.of(vino1, vino2, vino3, vino4, vino5, vino6, vino7, vino8, vino9, vino10);
		Vinoteca vinos1 = new VinotecaBucles(lista_vinos);
		Vinoteca vinos2 = FactoriaVinoteca.leerVinoteca("data/wine_reviews.csv");

		System.out.println("\nCasos de prueba para el constructor");
		System.out.println("===================================");
		testConstructor(lista_vinos);

		System.out.println("\nCasos de prueba para calcularNumeroVinosDePais");
		System.out.println("================================================");
		testCalcularNumeroVinosPais(vinos1, "Spain");
		testCalcularNumeroVinosPais(vinos2, "Spain");
		
		System.out.println("\nCasos de prueba para obtenerVinosRangoPuntos");
		System.out.println("============================================");
		testObtenerVinosRangoPuntos(vinos1, 90, 95);
		testObtenerVinosRangoPuntos(vinos2, 90, 95);
		// Caso de prueba para ver si se gestionan bien los parámetros
		testObtenerVinosRangoPuntos(vinos1, 95, 90);
		testObtenerVinosRangoPuntos(vinos2, 95, 90);
		
		System.out.println("\nCasos de prueba para calcularNumeroVinosDePaisConPuntuacionSuperior");
		System.out.println("=====================================================================");
		testCalcularNumeroVinosDePaisConPuntuacionSuperior(vinos1, "Spain", 90);
		testCalcularNumeroVinosDePaisConPuntuacionSuperior(vinos2, "Spain", 90);
	
		System.out.println("\nCasos de prueba para obtenerVinosBaratos");
		System.out.println("==========================================");
		testObtenerVinosBaratos(vinos1, 10.0);
		testObtenerVinosBaratos(vinos2, 10.0);
		
		System.out.println("\nCasos de prueba para existeVinoDeUvaEnRegion");
		System.out.println("==============================================");
		testExisteVinoDeUvaEnRegion(vinos2, "Chardonnay", "California");
		testExisteVinoDeUvaEnRegion(vinos2, "Portuguese White","California");
		
		System.out.println("\nCasos de prueba para calcularUvasDeRegion");
		System.out.println("===========================================");
		testCalcularUvasDeRegion(vinos1, "California");
		testCalcularUvasDeRegion(vinos2, "California");
		
		System.out.println("\nCasos de prueba para calcularTotalPuntosVinosDeRegion");
		System.out.println("==============================================");
		testCalcularTotalPuntosVinosDeRegion(vinos1, "California");
		testCalcularTotalPuntosVinosDeRegion(vinos2, "California");

		System.out.println("\nCasos de prueba para calcularMediaPuntosVinosDeUva");
		System.out.println("==============================================");
		testCalcularMediaPuntosVinosDeUva(vinos1, "Chardonnay");
		testCalcularMediaPuntosVinosDeUva(vinos2, "Chardonnay");

		System.out.println("\nCasos de prueba para obtenerVinoMejorPuntuado");
		System.out.println("===============================================");
		testObtenerVinoMejorPuntuado(vinos1);
		testObtenerVinoMejorPuntuado(vinos2);

		System.out.println("\nCasos de prueba para obtenerVinoMejorPuntuadoDePais");
		System.out.println("=====================================================");
		testObtenerVinoMejorPuntuadoDePais(vinos1, "Spain");
		testObtenerVinoMejorPuntuadoDePais(vinos2, "Spain");
	
		System.out.println("\nCasos de prueba para obtenerNVinosRegionOrdenadosPrecio");
		System.out.println("=====================================================");
		testObtenerNVinosRegionOrdenadosPrecio(vinos1,"California", 3);
		testObtenerNVinosRegionOrdenadosPrecio(vinos2,"California", 10);
		
		System.out.println("\nCasos de prueba para agrupaVinosPorPais");
		System.out.println("=========================================");
		testCalcularVinosPorPais(vinos1);
		testCalcularVinosPorPais(vinos2);
		
		System.out.println("\nCasos de prueba para calcularUvasPais");
		System.out.println("=====================================");
		testAgruparUvasPorPais(vinos1);
		testAgruparUvasPorPais(vinos2);
		
		System.out.println("\nCasos de prueba para calcularCalidadPrecioPorRegionMayorDe");
		System.out.println("==========================================================");		
		testCalcularCalidadPrecioPorRegionMayorDe(vinos1, 1.2);
		testCalcularCalidadPrecioPorRegionMayorDe(vinos2, 1.2);
		
		System.out.println("\nCasos de prueba para calcularVinoMasCaroPorPais");
		System.out.println("==========================================================");		
		testCalcularVinoMasCaroPorPais(vinos1);
		testCalcularVinoMasCaroPorPais(vinos2);
		
		System.out.println("\nCasos de prueba para calcularNMejoresVinosPorPais");
		System.out.println("==========================================================");		
		testCalcularNMejoresVinosPorPais(vinos1, 3);
		testCalcularNMejoresVinosPorPais(vinos2, 3);
		
		System.out.println("\nCasos de prueba para calcularRegionConMejoresVinos");
		System.out.println("==========================================================");		
		testCalcularRegionConMejoresVinos(vinos1, 1.2);
		testCalcularRegionConMejoresVinos(vinos2, 1.2);
	}

	public static void testConstructor(Collection<Vino> coleccion_vinos) {
		System.out.println("\nTEST del Constructor");
		try {
			Vinoteca vinos = new VinotecaBucles(coleccion_vinos);
			System.out.println("\tVINOS: " + vinos + "\n");
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testCalcularNumeroVinosPais(Vinoteca vinos, String pais) {
		System.out.println("\nTEST de calcularNumeroVinosDePais");
		try {
			System.out.println(String.format("\tHay %d  vinos de %s: ",
					                 vinos.calcularNumeroVinosDePais(pais), pais));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testObtenerVinosRangoPuntos(Vinoteca vinos, Integer inf, Integer sup) {
		System.out.println("\nTEST de obtenerVinosRangoPuntos");
		try {
			String msg = String.format("\tLos vinos del rango [%d,%d] son", inf, sup);
			System.out.println(msg);
			mostrarColeccion(vinos.obtenerVinosRangoPuntos(inf, sup));
		} catch (IllegalArgumentException iae) {
			System.out.println("Excepción capturada: " + iae);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
	
	public static void testCalcularNumeroVinosDePaisConPuntuacionSuperior (Vinoteca vinos, String pais, Integer umbralPuntuacion) {
		System.out.println("\nTEST de calcularNumeroVinosDePaisConPuntuacionSuperior");
		try {
			System.out.println(String.format("\tHay %d  vinos del pais %s con puntuación superior a %d: ",
					                 vinos.calcularNumeroVinosDePaisConPuntuacionSuperior(pais, umbralPuntuacion), 
					                 pais, umbralPuntuacion));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
	
	private static void testObtenerVinosBaratos(Vinoteca vinos, Double precio) {
		System.out.println("\nTEST de obtenerVinosBaratos");
		try {
			String msg = String.format("\tLos vinos del con precio inferior a %.2fson", precio);
			System.out.println(msg);
			mostrarColeccion(vinos.obtenerVinosBaratos(precio));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}

	private static void testExisteVinoDeUvaEnRegion(Vinoteca vinos, String uva, String region) {
		System.out.println("\nTEST de existeVinoDeUvaEnRegion");
		try {
			String msg = String.format("\t¿Hay algún vino de la uva %s en la región %s? %s",
					uva, region, vinos.existeVinoDeUvaEnRegion(uva, region));
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}
	
	public static void testCalcularUvasDeRegion(Vinoteca vinos, String region) {
		System.out.println("\nTEST de calcularUvasDeRegion");
		try {
			Set<String> uvas = vinos.calcularUvasDeRegion(region);
			String msg = String.format("\tHay %d uvas de la región %s y son",
					uvas.size(), region );
			System.out.println(msg);
			uvas.stream()
				.forEach(uva->System.out.println("\t"+ uva));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	private static void testCalcularTotalPuntosVinosDeRegion(Vinoteca vinos, String region) {
		System.out.println("\nTEST de calcularTotalPuntosVinosDeRegion");
		try {
			String msg = String.format("\tEl total de puntos de la región %s es %d",
					 region, vinos.calcularTotalPuntosVinosDeRegion(region));
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}
	
	private static void testCalcularMediaPuntosVinosDeUva(Vinoteca vinos, String uva) {
		System.out.println("\nTEST de calcularMediaPuntosVinosDeUva");
		try {
			String msg = String.format("\tLa media de puntos de los vinos de la uva %s es %.2f",
					 uva, vinos.calcularMediaPuntosVinosDeUva(uva));
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testObtenerVinoMejorPuntuado(Vinoteca vinos) {
		System.out.println("\nTEST de obtenerVinoMejorPuntuado");
		try {
			System.out.println("\tEl vino mejor puntuado es: " + vinos.obtenerVinoMejorPuntuado());
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	private static void testObtenerVinoMejorPuntuadoDePais(Vinoteca vinos, String pais) {
		System.out.println("\nTEST de obtenerVinoMejorPuntuadoDePais");
		try {
			System.out.println(String.format("\tEl vino mejor puntuado del pais %s es: %s",
					pais, vinos.obtenerVinoMejorPuntuadoDePais(pais)));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}
	
	private static void testObtenerNVinosRegionOrdenadosPrecio(Vinoteca vinos, String region, Integer n) {
		System.out.println("\nTEST de obtenerNVinosRegionOrdenadosPrecio");
		try {
			String msg = String.format("\tLos %d vinos más caros de la región %s son", n, region);
			System.out.println(msg);
			mostrarColeccion(vinos.obtenerNVinosRegionOrdenadosPrecio(region, n));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}
	
	public static void testCalcularVinosPorPais(Vinoteca vinos) {
		System.out.println("\nTEST de agruparVinosPorPais");
		try {
			System.out.println("  Vinos por pais: " );
			mostrarMapConColeccion(vinos.agruparVinosPorPais(), 10);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testAgruparUvasPorPais(Vinoteca vinos) {
		System.out.println("\nTEST de agruparUvasPorPais");
		try {
			System.out.println("Las uvas de cada país son: ");
			Map<String, Set<String>> m= vinos.agruparUvasPorPais();
			m.entrySet().stream()
				.forEach(e->System.out.println("\t"+e));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testCalcularCalidadPrecioPorRegionMayorDe(Vinoteca vinos, Double umbral) {
		System.out.println("\nTEST de calcularCalidadPrecioPorRegionMayorDe");
		try {
			System.out.println(String.format("\tTotal vinos de calidad/precio mayor de %.1f: \n", umbral)
					+ "\t"+vinos.calcularCalidadPrecioPorRegionMayorDe(umbral));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
	
	public static void testCalcularVinoMasCaroPorPais(Vinoteca vinos) {
		System.out.println("\nTEST de calcularVinoMasCaroPorPais");
		try {
			System.out.println(String.format("\tVino más caro por pais: " )
					+ "\t"+vinos.calcularVinoMasCaroPorPais());
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
	
	public static void testCalcularNMejoresVinosPorPais(Vinoteca vinos, Integer n) {
		System.out.println("\nTEST de calcularNMejoresVinosPorPais");
		try {
			System.out.println(String.format("\tLos " + n + " mejores vinos por pais: " )
					+ "\t"+ vinos.calcularNMejoresVinosPorPais(n));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
	
	public static void testCalcularRegionConMejoresVinos(Vinoteca vinos, Double umbral) {
		System.out.println("\nTEST de calcularRegionConMejoresVinos");
		try {
			System.out.println(String.format("\tLa región con mejores vinos con una calidad-precio superior o igual a %.1f: \n", umbral)
					+ "\t"+ vinos.calcularRegionConMejoresVinos(umbral));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
}
