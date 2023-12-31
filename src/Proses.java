public class Proses {
    final int Pid;
    final int VarisZamani;
    int Oncelik;
    final int ProsesZamani;
    final int Mbyte;
    final int YaziciSayisi;
    final int TarayiciSayisi;
    final int ModemSayisi;
    final int CdSurucuSayisi;

    int CalismaSuresi = 0;

    public Proses(int pid, int varisZamani, int oncelik, int prosesZamani, int mbyte,
                  int yaziciSayisi, int tarayiciSayisi, int modemSayisi, int cdSurucuSayisi) {
        Pid = pid;
        VarisZamani = varisZamani;
        Oncelik = oncelik;
        ProsesZamani = prosesZamani;
        Mbyte = mbyte;
        YaziciSayisi = yaziciSayisi;
        TarayiciSayisi = tarayiciSayisi;
        ModemSayisi = modemSayisi;
        CdSurucuSayisi = cdSurucuSayisi;
    }
}
