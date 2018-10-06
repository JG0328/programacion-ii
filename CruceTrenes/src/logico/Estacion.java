package logico;

import java.util.ArrayList;

public class Estacion extends Thread {
    private String nombre;
    private volatile ArrayList<Tren> trenes;
    private volatile boolean semaforo;

    public Estacion(String n) {
        nombre = n;
        trenes = new ArrayList<>();
    }

    public void AddTren(Tren t) {
        synchronized (this) {
            boolean u = t.isUrgente();
            t.setEstacion(this);
            trenes.add(t);
            if (u) {
                Principal.AddToUrgente(t);
            } else {
                Principal.AddToNoUrgente(t);
            }
        }
    }

    public void SacarTren(Tren t) throws Exception {
        t.start();
        Thread.sleep(5000);
        System.out.println(nombre + ": " + t.getNombre() + " ha pasado el sensor.");
        semaforo = false;
        Thread.sleep(2000);
        System.out.println(nombre + ": se apagó el sensor.");
        Thread.sleep(2000);
        System.out.println(nombre + ": semáforo en rojo");
        Thread.sleep(2000);
        trenes.remove(t);
        Thread.sleep(2000);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSemaforo() {
        return semaforo;
    }

    public void setSemaforo(boolean semaforo) throws Exception {
        synchronized (this) {
            this.semaforo = semaforo;
            Thread.sleep(2000);
        }
    }
}
