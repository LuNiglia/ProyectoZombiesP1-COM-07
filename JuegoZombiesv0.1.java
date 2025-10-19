package juego;

import java.awt.Color;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
    // El objeto Entorno que controla el tiempo y otros
    private Entorno entorno;
    private Zombie zombie;

    // Configuración de cuadrícula
    int filas = 6;
    int celdas = 12;
    int anchoCelda = 100;
    int altoCelda = 100;
    int altoHUD = 100;

    Color[][] colores = new Color[filas][celdas];

    Juego() {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "Plantas contra Grinchs Zombies", 1200, 600);

        // Crear el zombie UNA SOLA VEZ, fuera del tick
        int fila = 4; // fila (0 = arriba)
        int columna = celdas - 1; // última columna (derecha)
        int x = columna * anchoCelda + anchoCelda / 2;
        int y = fila * altoCelda + altoCelda / 2;

        this.zombie = new Zombie(x, y, 70, Color.red.darker().darker());

        // Inicia el entorno
        this.entorno.iniciar();
    }

    /**
     * Durante el juego, el método tick() será ejecutado en cada instante.
     * Aquí se actualiza el estado interno del juego.
     */
    public void tick() {
        // Dibuja la cuadrícula y HUD
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < celdas; c++) {
                int x = c * anchoCelda + anchoCelda / 2;
                int y = f * altoCelda + altoCelda / 2;

                Color[] paleta = { Color.green.darker().darker().darker(), Color.green.darker().darker() };
                colores[f][c] = paleta[(f + c) % paleta.length];

                entorno.dibujarRectangulo(x, y, anchoCelda - 4, altoCelda - 4, 0, colores[f][c]);
            }
        }

        entorno.dibujarRectangulo(entorno.ancho() / 2, altoHUD / 2, entorno.ancho(), altoHUD, 0, Color.black);

        // Dibuja y mueve el zombie
        zombie.dibujar(entorno);
        zombie.moverIzq();
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
