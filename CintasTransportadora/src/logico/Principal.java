package logico;

import java.util.ArrayList;

public class Principal extends Thread {
    public static void main(String[] args) throws Exception {
        Cinta c1 = new Cinta("Cinta1");
        Sensor s1 = new Sensor("Sensor1", c1);

        ArrayList<Integer> i = new ArrayList<>();

        s1.start();

        // Llega un usuario después de 2 seg
        Thread.sleep(2000);
        s1.AddUsuario(5);
        // Llegan dos más...
        Thread.sleep(10000);
        s1.AddUsuario(2);
        s1.AddUsuario(3);
        // Uno más...
        Thread.sleep(10000);
        s1.AddUsuario(6);
        // El sensor1 se queda esperando...
        Thread.sleep(10000);
        s1.setTerminar(true);
    }
}
