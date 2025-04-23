package fp.vinos;

import static fp.utiles.Checkers.check;

import java.util.ArrayList;
import java.util.List;

import fp.utiles.Ficheros;

public class FactoriaVinoteca {
	private static final String DELIM_1 = ",";

	// US,California,96,235.0,Cabernet Sauvignon
	private static Vino parseaVino(String lineaCSV) {
		String[] splits = lineaCSV.split(DELIM_1);
		check("Cadena no válida", splits.length == 5);

		String pais = splits[0].trim();
		String region = splits[1].trim();
		Integer puntuacion = Integer.valueOf(splits[2].trim());
		Double precio = Double.valueOf(splits[3].trim());
		String uva = splits[4].trim();
		
		return new Vino(pais, region, puntuacion, precio, uva);
	}
	
	public static Vinoteca leerVinoteca(String rutaFichero){
		List<Vino> result = new ArrayList<Vino>();
		List<String> lineas = Ficheros.leeLineas(rutaFichero);
		lineas.remove(0);
		for(String linea: lineas) {
			result.add(parseaVino(linea));
		}
		
		return new VinotecaBucles(result);
	}
}
