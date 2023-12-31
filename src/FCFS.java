import java.util.LinkedList;


public class FCFS {
    LinkedList<Proses> prosesler = new LinkedList<>();
    Proses yurutulenProses;

    public void add(Proses proses) {
        prosesler.add(proses);
    }

    public Proses Get() {
        if (prosesler.isEmpty()) return null;
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
