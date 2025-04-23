package fp.utiles;

/**
 * @author reinaqu
 * Clase de utilidad con métodos para facilitar la gestión de restricciones.
 */
public class Checkers {

	/**
	 * @param textoRestriccion
	 *     Mensaje que se incluirá en la excepción elevada
	 * @param condicion
	 *     Expresión booleana que ha de cumplirse para que no se eleve una excepci�n
	 * @throws IllegalArgumentException si no se cumple la condición
	 *  
	 */
	public static void check(String textoRestriccion, Boolean condicion) {
		if (!condicion) {
			throw new IllegalArgumentException(
					Thread.currentThread().getStackTrace()[2].getClassName() +
					"." + 
					Thread.currentThread().getStackTrace()[2].getMethodName() +
					": " + 
					textoRestriccion);
		}
	}

	/**
	 * @param parametros Lista de parámetros para los que se quiere realizar una comprobación de nulidad.
	 * @throws IllegalArgumentException si alguno de los parámetros es nulo.
	 */
	public static void checkNoNull(Object... parametros) {
		for (int i = 0; i < parametros.length; i++) {
			if (parametros[i] == null) {
				throw new IllegalArgumentException(
						Thread.currentThread().getStackTrace()[2].getClassName() +
						"." + 
						Thread.currentThread().getStackTrace()[2].getMethodName() +
						": el parámetro " + (i + 1) + " es nulo");
			}
		}
	}
}
