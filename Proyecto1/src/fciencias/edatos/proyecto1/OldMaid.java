package fciencias.edatos.proyecto1;

import java.util.Iterator;
import java.util.Scanner;

import fciencias.edatos.proyecto1.colecciones.DoubleLinkedList;
import fciencias.edatos.proyecto1.jugadores.*;

/**
* Solterona, juego de cartas.
* @author Juan Carlos Zenteno Pompa, Carlos Cruz Rangel
* @version 1.0 noviembre 2021.
* @since Estructuras de datos 2022-1. Proyecto 1
*/

/**
 * Clase principal del juego.
 */

public class OldMaid {
	public static Scanner sc = new Scanner(System.in);
	public static DoubleLinkedList<Jugador> jugadores = new DoubleLinkedList<Jugador>();
	public static DoubleLinkedList<String> historial = new DoubleLinkedList<String>();
	public static void main(String[] args) {
		

		System.out.println("Bienvenido al juego del  ̷c̷a̷l̷a̷m̷a̷r̷ Solterona.");
		System.out.println("Introduzca con cuantos jugadores va a competir.");
		while (true) 
			if (sc.hasNextInt()) {
				int cantidad = sc.nextInt();
				if (cantidad < 1 || 10 < cantidad) {
					System.out.println("Cantidad inválida. Use un número entre 1 y 9");
					continue;
				}

				jugadores.push(new Humano());
				for (int i = 1; i <= cantidad; i++) {
					jugadores.push(new IA("Computadora "+i));
				}
				break;
			} else {
				sc.next();
				System.out.println("Por favor, introduzca un número");
			}

		//Barajeamos. 
		
		
		// Metemos a todos los jugadores en una fila.
		
		DoubleLinkedList<Carta> baraja = Carta.baraja(); 
		Iterator<Carta> cartas = baraja.listIterador();
		int contador = 0;
		while (cartas.hasNext()) { //Eliminamos UNA Reina, la primera en la baraja.
			if (cartas.next().getValor() == 12 ) {
				baraja.remove(contador);
				break;
			}
			contador++;
		}
		
		cartas = baraja.listIterador();
		Iterator<Jugador> fila = jugadores.listIterador();
		while (cartas.hasNext()) {
			if (!fila.hasNext()) fila = jugadores.listIterador();
			fila.next().tomarCarta(cartas.next());
		}
		
		
		//Main loop. El juego termina cuando solo queda un jugador.
		while (1 < jugadores.size()) {
			
			Jugador proletariado = jugadores.pop();
			Jugador burguesía = jugadores.get(0);
			jugadores.push(proletariado);

			//Inicia el turno.
			OldMaid.historial.push("Turno de " + proletariado.getNombre());
			OldMaid.historial.push("Cartas:");
			OldMaid.historial.push(proletariado.cartas());
			
			//Redistribución de la riqueza.
			Carta redistribuída = burguesía.removerCarta(proletariado.update(burguesía.cantidad()));
			System.out.println(proletariado.getNombre()+" ha tomado una carta del jugador "+burguesía.getNombre());
			//Registra el robo de carta
			OldMaid.historial.push(proletariado.getNombre()+" roba la carta: " + redistribuída);
			proletariado.tomarCarta(redistribuída);

			
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {}
		}

		System.out.println("El perdedor es " + jugadores.pop().getNombre());
		sc.close();
	}

	/**
	 * Para llamarse cuando un jugador ya no tiene cartas.
	 * @param sujeto id del jugador que se puede retirar del juego.
	 */
	public static void cartaVaciado(Jugador sujeto) {
		String suceso = sujeto.getNombre() + " se queda sin cartas. Sale del juego.";
		System.out.println(suceso);
		OldMaid.historial.push(suceso);

		int contador = 0;
		Iterator<Jugador> iter = jugadores.listIterador();
		while (iter.hasNext()) {
			if (iter.next() == sujeto) {
				jugadores.remove(contador);
				break;
			}
			contador++;
		}
	}

	//Imprime el historial.
	public static void imprimirHistorial() {
		Iterator<String> iterador = historial.listIterador();
		System.out.println("----------------------------------------------");
		while (iterador.hasNext()) {
			System.out.println("	"+iterador.next());
		}
		System.out.println("----------------------------------------------");
	}
}