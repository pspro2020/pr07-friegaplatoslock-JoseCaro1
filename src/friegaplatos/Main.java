package friegaplatos;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Platos> platos = new ArrayList<>(List.of(new Platos(0), new Platos(1), new Platos(2), new Platos(3), new Platos(4), new Platos(5), new Platos(6), new Platos(7)));
        BandejaDePlatos bandejaSucia = new BandejaDePlatos(platos);
        BandejaDePlatos bandejaFregar = new BandejaDePlatos();
        BandejaDePlatos bandejaFregarSeco = new BandejaDePlatos();
        BandejaDePlatos bandejaGuardar = new BandejaDePlatos();
        Thread fregador = new Thread(new Fregador(bandejaSucia, bandejaFregar), "Fregar");
        Thread secador = new Thread(new Secador(bandejaFregar, bandejaFregarSeco), "Secar");
        Thread organizador = new Thread(new Organizador(bandejaFregarSeco, bandejaGuardar), "Guardar");


        fregador.start();
        secador.start();
        organizador.start();
        Thread.sleep(60000);
        fregador.interrupt();
        secador.interrupt();
        organizador.interrupt();
        fregador.join();
        secador.join();
        organizador.join();

        System.out.println("Feliz cumpleaños");


    }
}
