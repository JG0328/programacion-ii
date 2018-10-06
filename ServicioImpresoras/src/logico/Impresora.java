package logico;

import java.util.ArrayList;

public class Impresora extends Thread {
    private String nombre;
    private String tipo;
    private volatile ArrayList<Documento> documentos;
    private volatile boolean imprimiendo;

    public Impresora(String n, String t) {
        nombre = n;
        tipo = t;
        documentos = new ArrayList<>();
    }

    public void AddDocumento(Documento d) {
        synchronized (this) {
            if (imprimiendo) {
                System.out.println(nombre + ": ocupada, documento añadido a la cola.");
            }
            documentos.add(d);
        }
    }

    public void Imprimir() throws Exception {
        synchronized (this) {
            System.out.println(nombre + ": imprimiendo " + documentos.get(0).getNombre());
            imprimiendo = true;
            Thread.sleep(documentos.get(0).getImpTiempo() * 1000);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
