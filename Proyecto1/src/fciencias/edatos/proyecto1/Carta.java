package fciencias.edatos.proyecto1;

import java.util.Random;

import fciencias.edatos.proyecto1.colecciones.DoubleLinkedList;

/**
* Solterona, juego de cartas.
* @author Juan Carlos Zenteno Pompa, Carlos Cruz Rangel
* @version 1.0 noviembre 2021.
* @since Estructuras de datos 2022-1. Proyecto 1
*/

/**
 * ---------------------------------------------------------------------
 * Clase que se usará para representar a todas las cartas en el juego de Solterona.
 * ---------------------------------------------------------------------
 */

public class Carta {

	//Esto va a actuar como una función f(valor) -> Nombre.
	private static final String[] NOMBRES = {
		"As", "Dos", "Tres", "Cuatro", "Cinco", 
		"Seis", "Siete", "Ocho", "Nueve", "Diez",
		"Jack", "Reina", "Rey"
	};

	private int valor;
	private Palo palo;

	private Carta(int valor, Palo palo){
		this.valor = valor;
		this.palo = palo;
	}

	public int getValor() {
		return this.valor;
	}

	public Palo geTipo() {
		return this.palo;
	}

	/**
	 * Genera una baraja completa y revuelta.
	 * @return Una lista con todas las cartas de una baraja clásica.
	 */
	public static DoubleLinkedList<Carta> baraja() {
		DoubleLinkedList<Carta> baraja = new DoubleLinkedList<Carta>();
		Random rand = new Random();

		for (int i = 1; i <= NOMBRES.length; i++) {
			int position = rand.nextInt(baraja.size() + 1);
			baraja.add(position, new Carta(i, Palo.DIAMANTE));
			position = rand.nextInt(baraja.size() + 1);
			baraja.add(position, new Carta(i, Palo.CORAZÓN));
			position = rand.nextInt(baraja.size() + 1);
			baraja.add(position, new Carta(i, Palo.PICA));
			position = rand.nextInt(baraja.size() + 1);
			baraja.add(position, new Carta(i, Palo.TRÉBOL));
		}

		return baraja;
	}

	@Override
	public String toString() {
		switch (this.palo) {
			case DIAMANTE:
				return NOMBRES[this.valor - 1] + " de Diamantes";
			case CORAZÓN:
				return NOMBRES[this.valor - 1] + " de Corazones";
			case TRÉBOL:
				return NOMBRES[this.valor - 1] + " de Tréboles";
			case PICA:
				return NOMBRES[this.valor - 1] + " de Picas";
		}
		return null; //Esto está aquí sólo para que el compilador no se queje. NUNCA se va a llegar a esta expresión.
	}
}