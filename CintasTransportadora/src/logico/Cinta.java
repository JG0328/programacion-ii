package logico;

public class Cinta extends Thread {
    private String nombre;

    public Cinta(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        Start();
    }

    private void Start() {
        System.out.println(nombre + ": Se ha empezado a mover.");
    }

    private void Stop() {
        System.out.println(nombre + ": Se ha detenido.");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}