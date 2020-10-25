package friegaplatos;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Secador implements Runnable {

    private final BandejaDePlatos bandejaFregado;
    private final BandejaDePlatos bandejaFregadoSeco;




    public Secador(BandejaDePlatos bandejaFregado,BandejaDePlatos bandejaFregadoSeco){
        this.bandejaFregado=bandejaFregado;
        this.bandejaFregadoSeco=bandejaFregadoSeco;
    }


    @Override
    public void run() {
        Platos plato;
        while(!Thread.currentThread().isInterrupted()){
            try {
                plato= bandejaFregado.extraerDeLaBandeja();
            } catch (InterruptedException e) {
                return;
            } try {
                secadoPlato(plato);
            } catch (InterruptedException e) {
                return;
            }

        }

    }

    private void secadoPlato(Platos plato) throws InterruptedException {
        Random random = new Random();
        TimeUnit.SECONDS.sleep(random.nextInt(3)+1);
        bandejaFregadoSeco.añadirBandeja(plato);
    }

}
