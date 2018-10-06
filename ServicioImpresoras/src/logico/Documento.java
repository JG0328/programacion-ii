package logico;

public class Documento {
    private String nombre;
    private int impTiempo;
    private String tipo;

    public Documento(String n, int t, String tip) {
        nombre = n;
        impTiempo = t;
        tipo = tip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImpTiempo() {
        return impTiempo;
    }

    public void setImpTiempo(int impTiempo) {
        this.impTiempo = impTiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
