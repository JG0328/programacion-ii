package logico;

import java.util.ArrayList;

public class Impresora extends Thread {
    private String nombre;
    private String tipo;
    private volatile boolean imprimiendo;
    private volatile Documento documento;
    private volatile boolean primeraVez;
    private volatile boolean encendida;

    public Impresora(String n, String t) {
        nombre = n;
        tipo = t;
    }

    public void run() {
        try {
            Imprimir();
        } catch (Exception e) {

        }
    }

    public void Imprimir() throws Exception {
        imprimiendo = true;
        System.out.println(nombre + ": imprimiendo " + documento.getNombre());
        Thread.sleep(documento.getImpTiempo() * 1000);
        imprimiendo = false;
        documento = null;

        while (true) {
            if (!encendida || documento != null) {
                break;
            }
        }

        if (!encendida) {
            interrupt();
        } else if (documento != null) {
            Imprimir();
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

    public boolean isImprimiendo() {
        synchronized (this) {
            return imprimiendo;
        }
    }

    public void setImprimiendo(boolean imprimiendo) {
        this.imprimiendo = imprimiendo;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        synchronized (this) {
            this.documento = documento;
        }
    }

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(boolean primeraVez) {
        synchronized (this) {
            this.primeraVez = primeraVez;
        }
    }

    public boolean isEncendida() {
        return encendida;
    }

    public void setEncendida(boolean encendida) {
        synchronized (this) {
            this.encendida = encendida;
        }
    }
}
