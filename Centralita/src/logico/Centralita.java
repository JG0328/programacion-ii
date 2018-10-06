package logico;

import java.util.ArrayList;

public class Centralita extends Thread {
    private String nombre;
    private volatile ArrayList<Telefono> telefonos;

    public Centralita(String n) {
        nombre = n;
        telefonos = new ArrayList<>();
    }

    public String DarSignal() throws Exception {
        Thread.sleep(1000);
        return "piii...";
    }

    public boolean MarcarNumero(Telefono t) throws Exception {
        Thread.sleep(1500);
        // Si está colgado...
        if (t.getEstado() == 0) {
            t.Sonar();
            return true;
        }
        return false;
    }

    public Telefono ObtenerTelefono(int n) {
        for (Telefono t : telefonos) {
            if (n == t.getNumero()) {
                return t;
            }
        }

        return null;
    }

    public void AddTelefono(Telefono t) {
        synchronized (this) {
            telefonos.add(t);
        }
    }
}
