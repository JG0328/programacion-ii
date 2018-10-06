package logico;

public class Principal extends Thread {
    public static void main(String[] args) throws Exception {
        Centralita c = new Centralita("Claro");
        Telefono t1 = new Telefono(1, c);
        Telefono t2 = new Telefono(2, c);
        c.AddTelefono(t1);
        c.AddTelefono(t2);
        c.start();
        // Se coge el teléfono
        t1.Descolgar();
        Thread.sleep(2500);
        // Se marca al teléfono 2 y hablan
        t1.Marcar(2);
        Thread.sleep(6000);
        // El teléfono 1 cierra
        t1.Colgar(t2);
        Thread.sleep(3000);
        // El teléfono 2 cierra
        t2.Colgar(null);
    }
}
