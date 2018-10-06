package logico;

public class Principal extends Thread {
    public static void main(String[] args) throws Exception {
        Centralita c = new Centralita("Claro");
        Telefono t1 = new Telefono(1, c);
        Telefono t2 = new Telefono(2, c);
        c.AddTelefono(t1);
        c.AddTelefono(t2);
        c.start();
        t1.Descolgar();
        Thread.sleep(2500);
        t1.Marcar(2);
        Thread.sleep(6000);
        t1.Colgar(t2);
        Thread.sleep(3000);
        t2.Colgar(null);
    }
}
