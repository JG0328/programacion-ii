package logico;

import java.util.ArrayList;
import java.util.EnumSet;

public class Sensor extends Thread {
    private String nombre;
    private Cinta cinta;

    private long tiempoEspera = 3000;
    private long tiempoFinal;

    private volatile boolean activo;
    private volatile boolean tengoCola;
    private volatile boolean primeraVez = false;
    private volatile boolean aviso = false;

    private volatile ArrayList<Integer> usuarios;

    public Sensor(String n, Cinta c) {
        nombre = n;
        cinta = c;
        usuarios = new ArrayList<>();
    }

    public void run() {
        System.out.println(nombre + ": esperando...");
        tiempoFinal = System.currentTimeMillis() + tiempoEspera;
        try {
            Esperar();
        } catch (Exception e) {

        }
    }

    public void Esperar() throws Exception {
        while (true) {
            if (tengoCola) {
                System.out.println(nombre + ": llegó un usuario.");
                EncenderCinta();
                PasarUsuario();
                tiempoFinal = System.currentTimeMillis() + tiempoEspera;
            } else if (!tengoCola && !aviso) {
                System.out.println(nombre + ": esperaré " + (tiempoEspera / 1000) + " segundos.");
                aviso = true;
            } else if (System.currentTimeMillis() > tiempoFinal && !tengoCola) {
                cinta.setEstado(1);
                if (primeraVez) {
                    while (cinta.isActiva()) {
                        yield();
                    }
                    System.out.println(nombre + ": esperando...");
                    primeraVez = false;
                }
                synchronized (this) {
                    this.wait();
                    aviso = false;
                }
            }
        }
    }


    private void EncenderCinta() {
        synchronized (cinta) {
            if (cinta.isPrimeraVez()) {
                cinta.start();
                cinta.Start();
                cinta.setPrimeraVez(false);
            } else {
                synchronized (cinta) {
                    cinta.notify();
                }
            }
        }
    }

    private void EsperarTiempo(int segundos) throws Exception {
        Thread.sleep(segundos * 1000);
    }

    private void PasarUsuario() throws Exception {
        synchronized (this) {

            while (!cinta.isActiva()) {
                yield();
            }

            System.out.println(cinta.getNombre() + " : esperando... " + usuarios.get(0).toString() + " segundos.");
            EsperarTiempo(usuarios.get(0));
            usuarios.remove(0);
            primeraVez = true;

            if (usuarios.isEmpty()) {
                tengoCola = false;
            }
            aviso = false;
        }

    }

    public void AddUsuario(Integer i) throws Exception {
        synchronized (this) {
            usuarios.add(i);
            tengoCola = true;
            activo = true;
            this.notify();
        }
    }
}
