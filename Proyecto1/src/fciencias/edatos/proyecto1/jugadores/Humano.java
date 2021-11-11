package fciencias.edatos.proyecto1.jugadores;

import java.util.Iterator;

import fciencias.edatos.proyecto1.Carta;
import fciencias.edatos.proyecto1.OldMaid;

public class Humano extends Jugador{


    public Humano() {
        this.nombre = "Humano";
    }

    @Override
    public int update(int otro) {
        System.out.println("Tienes cartas: "+ mazo.size());
        System.out.println("Este es su turno. Estas son sus cartas:");
        mostrarCartas();
        while (true) {
            System.out.println("Presione 1 para intercambiar de posición dos cartas. Presione 2 para mostrar el historial. Presione cualquier otra cosa para elegir una carta de su contrincante.");
            String opción = OldMaid.sc.next();
            if (opción.equals("1")) {
                int a;
                int b;
                System.out.println("Introduzca el indice de la primera carta que desea cambiar de posición.");
                while (true) {
                    if (OldMaid.sc.hasNextInt()) {
                        int entrada = OldMaid.sc.nextInt();
                        if (entrada < 0 || mazo.size() <= entrada) {
                            System.out.println("No tienes ninguna carta en ese índice. Intentalo de nuevo.");
                            continue;
                        }
                        a = entrada;
                        break;
                    }
                }

                System.out.println("Introduzca el indice de la segunda carta que desea cambiar de posición.");
                while (true) {
                    if (OldMaid.sc.hasNextInt()) {
                        int entrada = OldMaid.sc.nextInt();
                        if (entrada < 0 || mazo.size() <= entrada) {
                            System.out.println("No tienes ninguna carta en ese índice. Intentalo de nuevo.");
                            continue;
                        }
                        b = entrada;
                        break;
                    }
                }

                mazo.intercambiar(a, b);
                System.out.println("Sus cartas ahora se ven así:");
                mostrarCartas();
            } else if (opción.equals("2")) {
                System.out.println("Historial:");
                OldMaid.imprimirHistorial();
            } else break;
            
        }

        System.out.println("Elija la carta que le robará al jugador a su derecha. El tiene " + otro + " cartas.");
        while (true) {
            if (OldMaid.sc.hasNextInt()) {
                int i = OldMaid.sc.nextInt();
                if (0 <= i && i < otro) {
                    return i;
                } else {
                    System.out.println("Por favor, introduzca un número entre 0 y " + (i-1));
                }
            } else {
                System.out.println("Por favor, introduzca un número.");
            }
        }
    }

    public void mostrarCartas() {
        System.out.println(this.cartas());
    }
    
}
