package fp.vinos;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public interface Vinoteca {
	// Vinoteca::agregarVino: añade un Vino dado como parámetro al objeto de tipo
	// Vinoteca sobre el que se aplica.
	void agregarVino(Vino v);

	// Vinoteca::eliminarVino: elimina un vino. Si no existe, eleva
	// IllegalArgumentException.
	void eliminarVino(Vino v);

	// Vinoteca::obtenerNumeroVinos: calcula el número total de vinos.
	Integer obtenerNumeroVinos();

	// Vinoteca::contieneVino: devuelve true si el contenedor contiene un objeto de
	// tipo Vino dado como parámetro.
	Boolean contieneVino(Vino v);

	// Vinoteca::agregarVinos: añade todos los vinos de una colección dada como
	// parámetro al objeto de tipo Vinoteca que lo invoca.
	void agregarVinos(Collection<Vino> c);

	// Vinoteca::contieneVinos: devuelve true si el contenedor contiene todos los
	// vinos de una colección de vinos dada como parámetro.
	Boolean contieneVinos(Collection<Vino> c);

	// Vinoteca::calcularNumeroVinosDePais: cuenta el número de vinos de un país
	// dado como parámetro.
	Integer calcularNumeroVinosDePais(String pais);

	// Vinoteca::obtenerVinosRangoPuntos: devuelve una colección de objetos de tipo
	// Vino solo con los vinos que estén valorados en un rango de puntos
	// determinado. El rango vendrá especificado por dos enteros (inf y sup) dados
	// como parámetros. Si el valor del límite inferior del rango es superior al
	// valor del límite superior, se elevará IllegalArgumentException.
	Collection<Vino> obtenerVinosRangoPuntos(Integer inf, Integer sup);

	// Vinoteca::calcularNumeroVinosDePaisConPuntuacionSuperior: devuelve el número
	// de vinos del país dado como parámetro que tienen una puntuación superior a un
	// umbral dado como parámetro.
	Integer calcularNumeroVinosDePaisConPuntuacionSuperior(String pais, Integer umbral);

	// Vinoteca::obtenerVinosBaratos: obtiene un conjunto con los vinos cuyo precio
	// es inferior a uno dado como parámetro.
	Set<Vino> obtenerVinosBaratos(Double precio);

	// Vinoteca::existeVinoDeUvaEnRegion: devuelve true si existe en la región dada
	// como parámetro un vino elaborado con la uva dada como parámetro.
	Boolean existeVinoDeUvaEnRegion(String uva, String region);

	// Apartado b - Tratamientos secuenciales en cadena.
	//

	// Vinoteca::calcularUvasDeRegion: devuelve un conjunto con los nombres de las
	// uvas que se usan en los vinos de una región dada como parámetro
	Set<String> calcularUvasDeRegion(String region);

	// Vinoteca::calcularTotalPuntosVinosDeRegion: devuelve la suma de las
	// puntuaciones de todos los vinos de una región dada como parámetro.
	Integer calcularTotalPuntosVinosDeRegion(String region);

	// Vinoteca::calcularMediaPuntosVinosDeUva: devuelve la puntuación media de los
	// vinos obtenidos a partir de un tipo de uva dado como parámetro. Si la media
	// no se puede calcular, devuelve cero.
	Double calcularMediaPuntosVinosDeUva(String uva);

	// Apartado c - Tratamientos secuenciales con criterios de ordenación
	//
	// Vinoteca::obtenerVinoMejorPuntuado: devuelve el objeto de tipo Vino con la
	// puntuación más alta. Si no se puede calcular eleva NoSuchElementException.
	Vino obtenerVinoMejorPuntuado();

	// Vinoteca::obtenerVinoMejorPuntuadoDePais: devuelve el objeto de tipo Vino con
	// la puntuación más alta de un país dado como parámetro. Si no se puede
	// calcular eleva NoSuchElementException.
	Vino obtenerVinoMejorPuntuadoDePais(String pais);

	// Vinoteca::obtenerNVinosRegionOrdenadosPrecio: devuelve una lista ordenada con
	// los N vinos más caros de una región dada como parámetro, ordenados del más
	// caro al más barato.
	List<Vino> obtenerNVinosRegionOrdenadosPrecio(String region, Integer n);

	// Apartado d - Tratamientos secuenciales con Map
	//
	// Vinoteca::agruparVinosPorPais: devuelve un Map que asocia a cada país una
	// lista con los objetos de tipo Vino de ese país.
	Map<String, List<Vino>> agruparVinosPorPais();

	// Vinoteca::agruparUvasPorPais: devuelve un Map que asocia los países con
	// conjuntos que contienen los nombres de las uvas usadas en los vinos del
	// respectivo país.
	Map<String, Set<String>> agruparUvasPorPais();

	// Vinoteca::calcularCalidadPrecioPorRegionMayorDe: devuelve un Map que asocia
	// las regiones con el número de vinos cuya relación calidad/precio supera un
	// umbral dado como parámetro.
	Map<String, Integer> calcularCalidadPrecioPorRegionMayorDe(Double umbral);

	// Vinoteca::calcularVinoMasCaroPorPais: devuelve un Map que asocia a cada país
	// el vino más caro de ese país.
	Map<String, Vino> calcularVinoMasCaroPorPais();

	// Vinoteca::calcularNMejoresVinosPorPais: devuelve un SortedMap que asocia a
	// cada país los N vinos mejor puntuados de ese país.
	SortedMap<String, List<Vino>> calcularNMejoresVinosPorPais(Integer n);

	// Vinoteca::calcularRegionConMejoresVinos: obtiene la región con mayor número
	// de vinos cuya relación calidad/precio supera un umbral dado como parámetro.
	String calcularRegionConMejoresVinos(Double umbral);
}
