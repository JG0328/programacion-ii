package logico;

import java.util.ArrayList;

public class Principal extends Thread {
    private static ArrayList<Impresora> impresoras;
    private static ArrayList<Documento> documentos;

    public static void main(String[] args) throws Exception {
        impresoras = new ArrayList<>();
        documentos = new ArrayList<>();

        Impresora i1 = new Impresora("Canon", "A");
        Impresora i2 = new Impresora("HP", "A");
        Impresora i3 = new Impresora("Brother", "B");
        Impresora i4 = new Impresora("Epson", "B");

        impresoras.add(i1);
        impresoras.add(i2);
        impresoras.add(i3);
        impresoras.add(i4);

        Documento d1 = new Documento("hola.doc", 5, "A");
        Documento d2 = new Documento("como.xls", 3, "B");
        Documento d3 = new Documento("hola.ppt", 2, "C");
        Documento d4 = new Documento("hola.xls", 6, "B");
        Documento d5 = new Documento("bio.doc", 3, "A");
        Documento d6 = new Documento("expo.ppt", 2, "C");
        Documento d7 = new Documento("dinero.xls", 3, "B");
        Documento d8 = new Documento("pres.doc", 2, "A");

        documentos.add(d1);
        documentos.add(d2);
        documentos.add(d3);
        documentos.add(d4);
        documentos.add(d5);
        documentos.add(d6);
        documentos.add(d7);
        documentos.add(d8);

        try {
            DistribuirDocumentos();
        } catch (Exception e) {

        }
    }

    private static void DistribuirDocumentos() throws Exception {
        boolean volverEmpezar = true;
        boolean imprimir = false;
        Documento d = null;
        Impresora i = null;

        while (volverEmpezar) {
            for (Documento doc : documentos) {
                for (Impresora imp : impresoras) {
                    if (doc.getTipo() == imp.getTipo() || doc.getTipo().equalsIgnoreCase("c")) {
                        if (!imp.isImprimiendo()) {
                            d = doc;
                            i = imp;
                            imprimir = true;
                            break;
                        }
                    }
                }

                if (!imprimir) {
                    System.out.println("Esperando...");

                    while (!Disponible(doc)) {
                        yield();
                    }
                    volverEmpezar = true;
                    break;
                }

                if (imprimir || volverEmpezar) {
                    break;
                }
            }

            if (imprimir) {
                i.setDocumento(d);

                if (!i.isEncendida()) {
                    i.setEncendida(true);
                    i.start();
                } else {

                }
                while (!i.isImprimiendo()) {
                    yield();
                }
                documentos.remove(d);
                imprimir = false;
            }

            if (documentos.isEmpty()) {
                volverEmpezar = false;
            }
        }

        for (Impresora imp : impresoras) {
            imp.setEncendida(false);
        }
    }

    private static boolean Disponible(Documento doc) {
        for (Impresora imp : impresoras) {
            if (!imp.isImprimiendo()) {
                if (doc.getTipo() == imp.getTipo() || doc.getTipo().equalsIgnoreCase("c")) {
                    return true;
                }
            }
        }
        return false;
    }
}
