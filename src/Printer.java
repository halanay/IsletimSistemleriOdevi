public class Printer {
    private static final String format = "%-4s|%-5s|%-7s|%-4s|%-6s|%-4s|%-4s|%-5s|%-4s|%-10s\n";
    private static String rapor = String.format(format,"Pid","varış","öncelik","cpu","MBytes","prn","scn","modem","cd","status")
            + "===============================================================\n";

    enum Durum {
        ProsesBasladi,
        ProsesCalisti,
        ProsesDurdu,
        ProsesBitti,
        HataKaynak,
        HataBellek,
        HataZaman,
    }

    public static void Report(Durum durum, Proses proses) {
        switch (durum) {
            case ProsesBasladi -> System.out.println(proses.Pid + ": Proses baslatildi.");
            case ProsesCalisti -> System.out.println(proses.Pid + ": Proses calisti.");
            case ProsesDurdu -> System.out.println(proses.Pid + ": Proses durduruldu.");
            case ProsesBitti -> {
                    System.out.println(proses.Pid + ": Proses bitti.");
                    rapor += String.format(format, proses.Pid, proses.VarisZamani, proses.Oncelik,
                        proses.ProsesZamani, proses.Mbyte, proses.YaziciSayisi, proses.TarayiciSayisi,
                        proses.ModemSayisi, proses.CdSurucuSayisi, "COMPLETED");
            }
            case HataKaynak ->
                    rapor += String.format("%-4s HATA - %sProses çok sayıda kaynak talep ediyor - proses silindi\n",
                            proses.Pid, proses.Oncelik == 0 ? "Gerçek-Zamanlı " : "");
            case HataBellek ->
                    rapor += String.format("%-4s HATA - %sProses (%3d MB) tan daha fazla bellek talep ediyor – proses silindi\n",
                            proses.Pid, proses.Oncelik == 0 ? "Gerçek-Zamanlı " : "", proses.Oncelik == 0 ? 64 : 960);
            case HataZaman -> {
                    rapor += String.format("%-4s HATA - Proses zaman aşımı (20 sn de tamamlanamadı\n)",
                    proses.Pid);
                    System.out.println(proses.Pid + ": Proses zaman aşımına uğradı.");

            }
        }
    }

    public static void FinalReport() {
        System.out.println(rapor);
    }

}
