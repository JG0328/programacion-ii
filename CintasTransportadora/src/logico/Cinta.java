package logico;

public class Cinta extends Thread {
    private String nombre;
    private volatile int estado;
    private volatile boolean activa;

    private volatile boolean primeraVez = true;

    public Cinta(String n) {
        nombre = n;
    }

    public void run() {
        activa = true;
        try {
            Funcionar();
        } catch (Exception e) {

        }
    }

    public void Funcionar() throws Exception {
        while (true) {
            if (estado == 1) {
                Stop();
            }
        }
    }

    public void Start() {
        System.out.println(nombre + " : se empezó a mover.");
    }

    public void Stop() throws InterruptedException {
        synchronized (this) {
            System.out.println(nombre + " : se detuvo.");
            activa = false;
            wait();
            activa = true;
            estado = 0;
            primeraVez = false;
            Start();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(boolean primeraVez) {
        this.primeraVez = primeraVez;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
