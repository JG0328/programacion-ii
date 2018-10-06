package logico;

import java.util.ArrayList;

public class Principal extends Thread {
    private static Estacion e1;
    private static Estacion e2;
    private static ArrayList<Tren> urgentes;
    private static ArrayList<Tren> noUrgentes;

    public static void main(String[] args) throws Exception {
        urgentes = new ArrayList<>();
        noUrgentes = new ArrayList<>();
        e1 = new Estacion("Estación1");
        e2 = new Estacion("Estación2");

        Tren t1 = new Tren("Tren1", false);
        Tren t2 = new Tren("Tren2", true);
        Tren t3 = new Tren("Tren3", false);
        Tren t4 = new Tren("Tren4", true);
        Tren t5 = new Tren("Tren5", true);

        e1.AddTren(t1);
        e1.AddTren(t2);
        e2.AddTren(t3);
        e2.AddTren(t4);
        e1.AddTren(t5);
        e1.start();
        e2.start();

        ComprobarTrenes();

        Thread.sleep(3000);
        synchronized (e1) {
            e1.interrupt();
        }
        synchronized (e2) {
            e2.interrupt();
        }
    }

    private static void ComprobarTrenes() throws Exception {
        System.out.println("Analizando peticiones...");
        while (true) {
            if (!urgentes.isEmpty()) {
                urgentes.get(0).getEstacion().setSemaforo(true);
                System.out.println(urgentes.get(0).getEstacion().getNombre() + ": semáforo en verde.");
                Thread.sleep(2000);
                urgentes.get(0).getEstacion().SacarTren(urgentes.get(0));
                urgentes.remove(0);
            } else if (!noUrgentes.isEmpty()) {
                noUrgentes.get(0).getEstacion().setSemaforo(true);
                System.out.println(noUrgentes.get(0).getEstacion().getNombre() + ": semáforo en verde.");
                Thread.sleep(2000);
                noUrgentes.get(0).getEstacion().SacarTren(noUrgentes.get(0));
                noUrgentes.remove(0);
            } else {
                break;
            }
        }
    }

    public static void AddToUrgente(Tren t) {
        urgentes.add(t);
    }

    public static void AddToNoUrgente(Tren t) {
        noUrgentes.add(t);
    }

    public static void RemoveFromUrgente(Tren t) {
        urgentes.remove(t);
    }

    public static void RemoveFromNoUrgente(Tren t) {
        noUrgentes.remove(t);
    }

    public static ArrayList<Tren> getUrgentes() {
        return urgentes;
    }

    public static void setUrgentes(ArrayList<Tren> urgentes) {
        Principal.urgentes = urgentes;
    }

    public static ArrayList<Tren> getNoUrgentes() {
        return noUrgentes;
    }

    public static void setNoUrgentes(ArrayList<Tren> noUrgentes) {
        Principal.noUrgentes = noUrgentes;
    }

    public static Estacion getE1() {
        return e1;
    }


    public static Estacion getE2() {
        return e2;
    }
}
