package fciencias.edatos.proyecto1.jugadores;

import java.util.Iterator;

import fciencias.edatos.proyecto1.Carta;
import fciencias.edatos.proyecto1.OldMaid;
import fciencias.edatos.proyecto1.colecciones.DoubleLinkedList;

/**
* Solterona, juego de cartas.
* @author Juan Carlos Zenteno Pompa, Carlos Cruz Rangel
* @version 1.0 noviembre 2021.
* @since Estructuras de datos 2022-1. Proyecto 1
*/

/**
 * ---------------------------------------------------------------------
 * Clase abstracta Jugador para el juego Solterona. El jugador físico y el jugador que es
 * Inteligencia artificial, heredarán ambos de esta clase.
 * ---------------------------------------------------------------------
 */

public abstract class Jugador {

	protected DoubleLinkedList<Carta> mazo = new DoubleLinkedList<Carta>();
	protected String nombre = "";

	/**
	 * Intercambia dos cartas del mazo del jugador.
	 * @param primera primera carta a intercambiar.
	 * @param segunda segunda carta a intercambiar.
	 * @throws IndexOutOfBoundsException
	 */
	public void intercambiar(int primera, int segunda) {
		mazo.intercambiar(primera, segunda);
	}

	public int cantidad() {
		return mazo.size();
	}

	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @param c carta que se va a tomar.
	 */
	public void tomarCarta(Carta c) {
		Iterator<Carta> iterador = mazo.listIterador();
		int indice = 0;
		while (iterador.hasNext()) {
			Carta actual = iterador.next();
			if (actual.getValor() == c.getValor()) {
				String suceso = nombre + " elimina un par de " + c.getValor();
				System.out.println(suceso);
				OldMaid.historial.push(suceso); //Mete al historial el suceso de se queda sin cartas el jugador.
				removerCarta(indice);
				return;
			}
			indice++;
		}
		this.mazo.push(c);
	}

	public Carta removerCarta(int indice) {
		Carta c = mazo.remove(indice);

		if (mazo.isEmpty()) {
			OldMaid.cartaVaciado(this);
		}
		
		return c;
	}

	public String cartas() {
        Iterator<Carta> iterador = this.mazo.listIterador();
		String returnable = "";
        while (iterador.hasNext()) 
            returnable += "    " + iterador.next() + "\n";
        return returnable;
    }

	/**
	 * Se manda a llamar en el turno del jugador.
	 * @param otro la cantidad de cartas que tiene el jugador a la derecha.
	 * @return indice de la carta que decidió robar, concluyendo el turno.
	 */
	public abstract int update(int otro);

}
