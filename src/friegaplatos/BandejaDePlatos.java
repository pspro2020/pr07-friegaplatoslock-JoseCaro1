package friegaplatos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BandejaDePlatos {
    private List<Platos> platos = new ArrayList<>();
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
    Lock lock = new ReentrantLock();
    Condition isEmpty = lock.newCondition();

    public BandejaDePlatos(List<Platos> platos) {
        this.platos = platos;
    }

    public BandejaDePlatos() {

    }


    protected void añadirBandeja(Platos plato) throws InterruptedException {
        lock.lock();
        try {
            System.out.printf("%s - %s plato nº %d\n", LocalTime.now().format(format), Thread.currentThread().getName(), plato.getNumSerie());
            platos.add(plato);
            isEmpty.signal();
        } finally {
            lock.unlock();
        }

    }

    protected Platos extraerDeLaBandeja() throws InterruptedException {
        Platos plato = null;
        lock.lock();
        try {
            while (platos.isEmpty()) {
               isEmpty.await();
            }
            plato = platos.remove(0);
            return plato;
        } finally {
            lock.unlock();
        }
    }


}

