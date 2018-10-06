package logico;

public class Principal extends Thread {
    public static void main(String[] args) {
        Impresora i1 = new Impresora("Canon", "A");
        Impresora i2 = new Impresora("HP", "A");
        Impresora i3 = new Impresora("Brother", "B");
        Impresora i4 = new Impresora("Epson", "B");
    }
}
