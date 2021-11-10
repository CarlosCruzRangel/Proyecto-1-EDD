package fciencias.edatos.proyecto1;

import java.util.Iterator;
import java.util.Scanner;

import fciencias.edatos.proyecto1.colecciones.DoubleLinkedList;
import fciencias.edatos.proyecto1.colecciones.Queue;
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

	public static void main(String[] args) {
		DoubleLinkedList<Jugador> jugadores = new DoubleLinkedList<Jugador>();

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
		
		Queue<Jugador> fila = new Queue<>();
		Iterator<Jugador> iter = jugadores.listIterador();
		// Metemos a todos los jugadores en una fila.
		while (iter.hasNext())
			fila.enqueue(iter.next());
		Iterator<Carta> baraja = Carta.baraja().listIterador();

		while (baraja.hasNext()) {
			Jugador j = fila.dequeue();
			j.tomarCarta(baraja.next());
			fila.enqueue(j);
		}
		
		
		
		
		//Main loop. El juego termina cuando solo queda un jugador.
		while (1 < fila.size()) {
			Jugador proletariado = fila.dequeue();
			Jugador burguesía = fila.first();

			System.out.println(proletariado.getNombre()+" ha tomado una carta del jugador "+burguesía.getNombre());
			//Esta línea realiza la redistribución de la riqueza.
			proletariado.tomarCarta(burguesía.removerCarta(proletariado.update(burguesía.cantidad())));

			if (proletariado.cantidad() != 0) {
				fila.enqueue(proletariado);
			} else {
				System.out.println(proletariado.cantidad());
			}
		}

		System.out.println("El perdedor es " + jugadores.pop().getNombre());
		sc.close();
	}
}