package fp.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ficheros {

	public static List<String> leeLineas(String rutaFichero) {
		List<String> lineas = new ArrayList<>();
		try {
			lineas = Files.readAllLines(Path.of(rutaFichero));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineas;
	}

}
