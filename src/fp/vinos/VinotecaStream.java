package fp.vinos;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import fp.utiles.Checkers;

public class VinotecaStream extends VinotecaBucles implements Vinoteca {
	
	public VinotecaStream() {
		super();
	}

	public VinotecaStream(Collection<Vino> result) {
		super(result);
	}

	public Integer calcularNumeroVinosDePais(String pais) {
		Predicate<Vino> predicate = v -> v.pais().equals(pais);
		Long l = vinos.stream().filter(predicate).count();
		return l.intValue();
	}

	public Set<String> calcularUvasDeRegion(String region) {
		return vinos.stream().filter(v -> v.region().equals(region)).map(Vino::uva).collect(Collectors.toSet());
	}

	public Collection<Vino> obtenerVinosRangoPuntos(Integer inf, Integer sup) {
		Checkers.check("Parámetros no válidos", inf < sup);
		return vinos.stream().filter(v -> v.puntuacion() > inf && v.puntuacion() < sup).toList();
	}

	@Override
	public Integer calcularNumeroVinosDePaisConPuntuacionSuperior(String pais, Integer umbral) {
		Long l = vinos.stream().filter(v -> v.pais().equals(pais) && v.puntuacion() >= umbral).count();
		return l.intValue();
	}

	public Boolean existeVinoDeUvaEnRegion(String uva, String region) {
		Predicate<Vino> predicate0 = v -> v.region().equals(region);
		Predicate<Vino> predicate1 = v -> v.uva().equals(uva);

		return vinos.stream().anyMatch(predicate0.and(predicate1));
	}

	@Override
	public Integer calcularTotalPuntosVinosDeRegion(String region) {
		return vinos.stream().filter(v -> v.region().equals(region)).mapToInt(Vino::puntuacion).sum();
	}

	@Override
	public Double calcularMediaPuntosVinosDeUva(String uva) {

		OptionalDouble opcional = vinos.stream().filter(v -> v.uva().equals(uva)).mapToInt(Vino::puntuacion).average();
		return opcional.orElse(0.0);
	}

	@Override
	public Vino obtenerVinoMejorPuntuado() {
		Comparator<Vino> cmp = Comparator.comparing(Vino::puntuacion);
		return vinos.stream().max(cmp).get();
	}

	@Override
	public Vino obtenerVinoMejorPuntuadoDePais(String pais) {
		Comparator<Vino> cmp = Comparator.comparing(Vino::puntuacion);
		return vinos.stream().filter(v -> v.pais().equals(pais)).max(cmp).get();
	}

	@Override
	public List<Vino> obtenerNVinosRegionOrdenadosPrecio(String region, Integer n) {
		Comparator<Vino> cmp = Comparator.comparing(Vino::precio).reversed();
		return vinos.stream().filter(v -> v.region().equals(region)).sorted(cmp).limit(n).toList();
	}

	public Map<String, List<Vino>> agruparVinosPorPais() {
		Function<Vino, String> funcionClave = Vino::pais;// vino->vino.pais();
		return vinos.stream().collect(Collectors.groupingBy(funcionClave, Collectors.toList()));
	}

	public Map<String, Set<String>> agruparUvasPorPais() {
		Function<Vino, String> funcionClave = Vino::pais;// vino->vino.pais();
		Function<List<Vino>, Set<String>> funcionTransformacion = (List<Vino> lista) -> vinosAUvas(lista);

		Map<String, Set<String>> aux = vinos.stream().collect(Collectors.groupingBy(funcionClave,
				Collectors.collectingAndThen(Collectors.toList(), funcionTransformacion)));
		return aux;
	}

	private Set<String> vinosAUvas(List<Vino> vinos) {
		return vinos.stream().map(Vino::uva).collect(Collectors.toSet());
	}

	public Map<String, Set<String>> agruparUvasPorPais2() {
		Function<Vino, String> funcionClave = Vino::pais;// vino->vino.pais();

		Map<String, Set<String>> aux = vinos.stream()
				.collect(Collectors.groupingBy(funcionClave, Collectors.mapping(Vino::uva, Collectors.toSet())));
		return aux;
	}

	public Map<String, Vino> calcularVinoMasCaroPorPais() {
		Function<Vino, String> funcionClave = Vino::pais;
		Comparator<Vino> cmp = Comparator.comparing(Vino::precio);

		return vinos.stream()
				.collect(Collectors.groupingBy(funcionClave, Collectors.collectingAndThen(Collectors.toList(),
						(List<Vino> listaVinos) -> listaVinos.stream().max(cmp).get())));
	}

	public Map<String, Vino> calcularVinoMasCaroPorPais2() {
		Function<Vino, String> funcionClave = Vino::pais;
		Comparator<Vino> cmp = Comparator.comparing(Vino::precio);

		return vinos.stream().collect(Collectors.groupingBy(funcionClave,
				Collectors.collectingAndThen(Collectors.maxBy(cmp), optional -> optional.get())));
	}

	public SortedMap<String, List<Vino>> calcularNMejoresVinosPorPais(Integer n) {
		Comparator<Vino> cmp = Comparator.comparing(Vino::puntuacion);
		Supplier<SortedMap<String, List<Vino>>> suministrador = TreeMap::new;
		// ()->new TreeMap<String, List<Vino>>();
		Function<Vino, String> funcionClave = Vino::pais;
		return vinos.stream().collect(Collectors.groupingBy(funcionClave, suministrador, Collectors.collectingAndThen(
				Collectors.toList(), (List<Vino> agrupados) -> agrupados.stream().sorted(cmp).limit(n).toList())));
	}

	public Map<String, Integer> calcularCalidadPrecioPorRegionMayorDe(Double umbral) {
		return vinos.stream()
				.collect(Collectors.groupingBy(Vino::region,
						Collectors.collectingAndThen(Collectors.toList(),
								(List<Vino> agrupados) -> 
										Long.valueOf(
										agrupados.stream().filter(vino -> vino.getCalidadPrecio() > umbral).count()
										)
										.intValue())));
	}
	
	public String calcularRegionConMejoresVinos(Double umbral) {
		Map<String, Integer> vinosMejoresPorRegion = calcularCalidadPrecioPorRegionMayorDe(umbral);
		Comparator<String> cmp = Comparator.comparing(region->vinosMejoresPorRegion.get(region));
		return vinosMejoresPorRegion.keySet().stream().max(cmp).get();
	}

}
