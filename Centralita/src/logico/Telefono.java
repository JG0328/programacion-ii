package logico;

public class Telefono extends Thread {
    private int numero;
    private Centralita centralita;
    private volatile int estado = 0; // 0 -> colgado, 1 -> descolgado, 2 -> hablando

    public Telefono(int n, Centralita c) {
        start();
        synchronized (this) {
            numero = n;
            centralita = c;
        }
    }

    public void Descolgar() throws Exception {
        synchronized (this) {
            estado = 1;
        }
        System.out.println(numero + ": descolgado");
        System.out.println(numero + ": " + centralita.DarSignal());
    }

    public void Marcar(int n) throws Exception {
        System.out.println(numero + ": marcando...");
        Telefono t = centralita.ObtenerTelefono(n);
        if (t != null) {
            Thread.sleep(2000);
            System.out.println(numero + ": marcado " + n);
            if (centralita.MarcarNumero(t)) {
                Thread.sleep(800);
                System.out.println(numero + ": piii-piii");
                Thread.sleep(2000);
                Hablar(n);

                synchronized (t) {
                    t.Hablar(numero);
                }

            } else {
                Thread.sleep(2000);
                System.out.println(numero + ": tuu-tuu-tuu");
            }
        } else {
            Thread.sleep(2000);
            System.out.println(numero + ": este número no existe, " + n);
        }
    }

    public void Colgar(Telefono t) {
        synchronized (this) {
            notify();
            System.out.println(numero + ": colgado");
            estado = 0;
            if (t != null) {
                System.out.println(t.getNumero() + ": tuu-tuu-tuu");
            }
        }
    }

    public void Hablar(int n) {
        System.out.println(numero + ": hablando con " + n);
        synchronized (this) {
            estado = 2;
        }
    }

    public void Sonar() {
        System.out.println(numero + ": ring-ring");
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
