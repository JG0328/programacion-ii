package logico;

import java.util.ArrayList;

public class Sensor extends Thread {
    private String nombre;
    private ArrayList<Integer> usuarios;
    private Cinta cinta;

    private boolean primeraVez = true;
    private boolean activo = false;

    public Sensor(String nombre, Cinta cinta) {
        super();
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.cinta = cinta;
    }

    public void run() {
        try {
            Esperar();
        } catch (Exception e) {
            /// Mensaje de exception
        }
    }

    private void Esperar() throws InterruptedException {
        while (true) {
            if (!usuarios.isEmpty()) {
                PasarUsuario();
            }
        }
    }

    private void PasarUsuario() throws InterruptedException {
        if (primeraVez) {
            primeraVez = false;

            cinta.start();
        } else if (!activo) {
            synchronized (cinta) {
                cinta.notify();
            }
        }

        System.out.println(cinta.getNombre() + ": esperando..." + (usuarios.get(0)).toString() + " segundos.");
        EsperarTiempo(usuarios.get(0));
    }

    private void EsperarTiempo(int segundos) throws InterruptedException {
        Thread.sleep(segundos * 1000);
    }
}
