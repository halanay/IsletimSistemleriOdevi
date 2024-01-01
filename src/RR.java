import java.util.LinkedList;
import java.util.List;

public class RR {
    LinkedList<Proses> prosesler = new LinkedList<>();
    public void add(Proses proses) {
        prosesler.add(proses);
    }

    public boolean isEmpty() {
        return prosesler.isEmpty();
    }

    public Proses removeFirst() {
        return prosesler.removeFirst();
    }
    public void ZamanAsimiKontrolEt(int zaman) {
        for (Proses proses : prosesler) {
            if (zaman - proses.VarisZamani >= 20) {
                Printer.Report(Printer.Durum.HataZaman,proses);
                prosesler.remove(proses);
                
            }
        }
    }
}
