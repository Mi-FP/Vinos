package fp.vinos;

import static fp.utiles.Checkers.*;

public record Vino(String pais, String region, Integer puntuacion, Double precio, String uva) {
	public Vino {
		check("Puntuación no válida:[0, 100]", puntuacion >= 0 && puntuacion <= 100);
		check("Precio no válido:(0, +inf)", precio > 0 );
	}

	public Double getCalidadPrecio() {
		return puntuacion / precio;
	}
}
