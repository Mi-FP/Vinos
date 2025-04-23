package fp.vinos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import fp.utiles.Checkers;

public class VinotecaBucles implements Vinoteca {

	protected Set<Vino> vinos;

	public VinotecaBucles() {
		vinos = new HashSet<Vino>();
	}

	public VinotecaBucles(Collection<Vino> result) {
		vinos = new HashSet<Vino>(result);
	}

	@Override
	public String toString() {
		return "VinotecaBucles [" + vinos.size() + "]";
	}

	public int hashCode() {
		return Objects.hash(true, obtenerNumeroVinos());
	}

	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof Vinoteca) {
			Vinoteca v = (Vinoteca) o;
			result = Objects.equals(true, v.contieneVinos(vinos))
					&& Objects.equals(obtenerNumeroVinos(), v.obtenerNumeroVinos());
		}
		return result;
	}

	@Override
	public void agregarVino(Vino v) {
		vinos.add(v);
	}

	@Override
	public void eliminarVino(Vino v) {
		if (!vinos.contains(v)) {
			throw new IllegalArgumentException();
		}
		vinos.remove(v);
	}

	@Override
	public Integer obtenerNumeroVinos() {
		return vinos.size();
	}

	public Boolean contieneVino(Vino v) {
		return vinos.contains(v);
	}

	@Override
	public void agregarVinos(Collection<Vino> c) {
		vinos.addAll(c);
	}

	@Override
	public Boolean contieneVinos(Collection<Vino> c) {
		return vinos.containsAll(c);
	}

	@Override
	public Integer calcularNumeroVinosDePais(String pais) {
		List<Vino> result = new ArrayList<>();
		for (Vino v : vinos) {
			if (v.pais().equals(pais)) {
				result.add(v);
			}
		}
		return result.size();
	}

	private List<Vino> getVinosRegion(String region) {
		List<Vino> filtrado = new ArrayList<>();
		for (Vino v : vinos) {
			if (v.region().equals(region)) {
				filtrado.add(v);
			}
		}
		return filtrado;
	}

	@Override
	public Set<String> calcularUvasDeRegion(String region) {
		Set<String> result = new HashSet<String>();
		for (Vino v : getVinosRegion(region)) {
			result.add(v.uva());
		}

		return result;
	}

	public Collection<Vino> obtenerVinosRangoPuntos(Integer inf, Integer sup) {
		Checkers.check("Parámetros no válidos", inf < sup);
		Set<Vino> result = new HashSet<Vino>();
		for (Vino v : vinos) {
			if (v.puntuacion() > inf && v.puntuacion() < sup) {
				result.add(v);
			}
		}
		return result;
	}

	@Override
	public Integer calcularNumeroVinosDePaisConPuntuacionSuperior(String pais, Integer umbral) {
		List<Vino> contador = new ArrayList<Vino>();
		for (Vino v : vinos) {
			if (v.pais().equals(pais) && v.puntuacion() >= umbral) {
				contador.add(v);
			}
		}

		return contador.size();
	}

	@Override
	public Set<Vino> obtenerVinosBaratos(Double precio) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean existeVinoDeUvaEnRegion(String uva, String region) {
		Boolean result = false;
		for (Vino v : getVinosRegion(region)) {
			if (v.uva().equals(uva)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public Integer calcularTotalPuntosVinosDeRegion(String region) {
		Integer result = 0;
		for (Vino v : getVinosRegion(region)) {
			result += v.puntuacion();
		}
		return result;
	}

	private List<Vino> getVinosUva(String uva) {
		List<Vino> result = new ArrayList<Vino>();
		for (Vino v : vinos) {
			if (v.uva().equals(uva)) {
				result.add(v);
			}
		}
		return result;
	}

	@Override
	public Double calcularMediaPuntosVinosDeUva(String uva) {
		Double suma = 0.0;
		List<Vino> filtrado = getVinosUva(uva);
		for (Vino v : filtrado) {
			suma += v.puntuacion();
		}

		return (filtrado.size() == 0 ? 0.0 : suma / filtrado.size());
	}

	@Override
	public Vino obtenerVinoMejorPuntuado() {
		Comparator<Vino> cmp = Comparator.comparing(Vino::puntuacion);
		return Collections.max(vinos, cmp);
	}

	@Override
	public Vino obtenerVinoMejorPuntuadoDePais(String pais) {
		Comparator<Vino> cmp = Comparator.comparing(Vino::puntuacion);
		List<Vino> filtrados = new ArrayList<Vino>();
		for (Vino v : vinos) {
			if (v.pais().equals(pais)) {
				filtrados.add(v);
			}
		}
		return Collections.max(filtrados, cmp);
	}

	@Override
	public List<Vino> obtenerNVinosRegionOrdenadosPrecio(String region, Integer n) {
		List<Vino> result = getVinosRegion(region);
		Comparator<Vino> cmp = Comparator.comparing(Vino::precio).reversed();
		Collections.sort(result, cmp);
		return result.subList(0, n);
	}

	@Override
	public Map<String, List<Vino>> agruparVinosPorPais() {
		Map<String, List<Vino>> result = new HashMap<String, List<Vino>>();
		for (Vino actual : vinos) {
			String key = actual.pais();
			if (result.containsKey(key)) {
				List<Vino> valor = result.get(key);
				valor.add(actual);
				result.put(key, valor);
			} else {
				List<Vino> valor = new ArrayList<Vino>();
				valor.add(actual);
				result.put(key, valor);
			}
		}
		return result;
	}

	@Override
	public Map<String, Set<String>> agruparUvasPorPais() {
		// Agrupación
		Map<String, List<Vino>> vinosPorPais = agruparVinosPorPais();
		// Transformación
		Map<String, Set<String>> result = new HashMap<String, Set<String>>();
		for (String pais : vinosPorPais.keySet()) {
			result.put(pais, vinosAUvas(vinosPorPais.get(pais)));
		}
		return result;
	}

	private Set<String> vinosAUvas(List<Vino> vinos) {
		Set<String> result = new HashSet<String>();
		for (Vino v : vinos) {
			result.add(v.uva());
		}
		return result;
	}

	@Override
	public Map<String, Vino> calcularVinoMasCaroPorPais() {
		Comparator<Vino> cmp = Comparator.comparing(Vino::precio);
		Map<String, Vino> result = new HashMap<String, Vino>();
		Map<String, List<Vino>> vinosPorPais = agruparVinosPorPais();
		for (String claveAgregado : vinosPorPais.keySet()) {
			List<Vino> valorAgregado = vinosPorPais.get(claveAgregado);
			Vino valorTransformado = Collections.max(valorAgregado, cmp);
			result.put(claveAgregado, valorTransformado);
		}
		return result;
	}

	@Override
	public SortedMap<String, List<Vino>> calcularNMejoresVinosPorPais(Integer n) {
		Comparator<Vino> cmp = Comparator.comparing(Vino::puntuacion);
		SortedMap<String, List<Vino>> result = new TreeMap<String, List<Vino>>();
		Map<String, List<Vino>> vinosPorPais = agruparVinosPorPais();
		for (String clave : vinosPorPais.keySet()) {
			List<Vino> valor = vinosPorPais.get(clave);
			Collections.sort(valor, cmp);
			List<Vino> valorTransformado = valor.subList(0, Math.min(valor.size(), n));
			result.put(clave, valorTransformado);
		}

		return result;
	}

	@Override
	public Map<String, Integer> calcularCalidadPrecioPorRegionMayorDe(Double umbral) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		Map<String, List<Vino>> vinosPorRegion = agruparVinosPorRegion();
		for(String region: vinosPorRegion.keySet()) {
			result.put(region, cuentaVinosUmbral(vinosPorRegion.get(region), umbral));
		}
		return result;
	}

	private Integer cuentaVinosUmbral(List<Vino> list, Double umbral) {
		List<Vino> filtro = new ArrayList<Vino>();
		for(Vino v: list) {
			if(v.getCalidadPrecio()>umbral) {
				filtro.add(v);
			}
		}
		return filtro.size();
	}

	private Map<String, List<Vino>> agruparVinosPorRegion() {
		Map<String, List<Vino>> result = new HashMap<String, List<Vino>>();
		for (Vino actual : vinos) {
			String key = actual.region();
			if (result.containsKey(key)) {
				List<Vino> valor = result.get(key);
				valor.add(actual);
				result.put(key, valor);
			} else {
				List<Vino> valor = new ArrayList<Vino>();
				valor.add(actual);
				result.put(key, valor);
			}
		}
		return result;
	}

	@Override
	public String calcularRegionConMejoresVinos(Double umbral) {
		Map<String, Integer> vinosMejoresPorRegion = calcularCalidadPrecioPorRegionMayorDe(umbral);
		Set<String> regiones = vinosMejoresPorRegion.keySet();
		Comparator<String> cmp = Comparator.comparing(region->vinosMejoresPorRegion.get(region));
		return Collections.max(regiones, cmp);
	}

}
