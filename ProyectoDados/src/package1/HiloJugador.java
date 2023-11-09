package package1;

import java.util.Random;

public class HiloJugador extends Thread {

    public int noJugador;
    Ventana p;
    HiloJugador sig;
    int tiro;
    int sumaTiros = 0;
    int tiroAnterior;

    public HiloJugador(int noJugador, Ventana p) {
        this.noJugador = noJugador;
        this.p = p;
    }

    public void nextThread(HiloJugador thread) {
        this.sig = thread;
    }

    @Override
    public void run() {

        for (int i = 0; i < 2; i++) {
            tiro = tiro();
            sumaTiros += tiro;
            p.mostrarTiro(tiro, noJugador);

            try {
                sleep(500);
            } catch (Exception e) {
            }
        }

        p.tiros[p.index] = sumaTiros;

        if (noJugador > 1) {
            tiroAnterior = p.tiros[p.index - 1];

            if (sumaTiros > tiroAnterior) {
                p.JugadorMejorTiro = noJugador;
            }
        }
        p.index++;
        if (noJugador == 3) {
            p.mostrarGanador();
        }
        if (!(sig == null)) {
            sig.start();
        }
    }

    private int tiro() {
        return new Random().nextInt(6) + 1;
    }
}
