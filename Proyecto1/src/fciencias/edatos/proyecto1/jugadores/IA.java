package fciencias.edatos.proyecto1.jugadores;

import java.util.Random;

public class IA extends Jugador {
    private Random rand;

    public IA(String nombre) {
        this.nombre = nombre;
        this.rand = new Random();
    }

    @Override
    public int update(int otro) {
        return rand.nextInt(otro);
    }
    
}
