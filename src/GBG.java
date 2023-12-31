import java.util.LinkedList;
import java.util.List;

public class GBG {
    LinkedList<Proses> Oncelik1 = new LinkedList<>();
    LinkedList<Proses> Oncelik2 = new LinkedList<>();
    RR Oncelik3 = new RR();

    public void add(Proses proses) {
        switch (proses.Oncelik) {
            case 1:
                Oncelik1.add(proses);
                break;
            case 2:
                Oncelik2.add(proses);
                break;
            case 3:
                Oncelik3.add(proses);
                break;
        }
    }

    public Proses Get(int oncelik) {
        if (oncelik >= 1 && !Oncelik1.isEmpty()) return Oncelik1.removeFirst();
        if (oncelik >= 2 && !Oncelik2.isEmpty()) return  Oncelik2.removeFirst();
        if (oncelik >= 3 && !Oncelik3.isEmpty()) return Oncelik3.removeFirst();
        return null;
    }

    public void ZamanAsimiKontrolEt(int zaman) {
        for (Proses proses : Oncelik1) {
            if (zaman - proses.VarisZamani >= 20) {
                Printer.Report(Printer.Durum.HataZaman,proses);
                Oncelik1.remove(proses);
            }
        }
        for (Proses proses : Oncelik2) {
            if (zaman - proses.VarisZamani >= 20) {
                Printer.Report(Printer.Durum.HataZaman,proses);
                Oncelik2.remove(proses);
            }
        }
        Oncelik3.ZamanAsimiKontrolEt(zaman);
    }
}
