package logico;

public class Tren extends Thread {
    private String nombre;
    private boolean urgente;
    private Estacion estacion;

    public Tren(String n, boolean u) {
        nombre = n;
        urgente = u;
    }

    public void run() {
        System.out.println(nombre + ": ha salido de " + estacion.getNombre());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        this.interrupt();
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public String getNombre() {
        return nombre;
    }
}
