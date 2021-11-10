package fciencias.edatos.proyecto1.jugadores;

import java.util.Iterator;

import fciencias.edatos.proyecto1.Carta;
import fciencias.edatos.proyecto1.OldMaid;

public class Humano extends Jugador{

    public Humano() {
        this.identificador = "Humano";
    }

    @Override
    public int update(int otro) {
        System.out.println("Este es su turno. Estas son sus cartas:");
        mostrarCartas();
        System.out.println("Elija la carta que le robará al jugador a su derecha. El tiene " + otro + " cartas.");
        return OldMaid.sc.nextInt();
    }

    public void mostrarCartas() {
        Iterator<Carta> iterador = this.mazo.listIterador();
        while (iterador.hasNext()) 
            System.out.println("    "+iterador.next());
        
    }
    
}
