public class Gorevlendirici {
    FCFS GercekZamanliProsesYonetici = new FCFS();
    GBG KullaniciProsesYonetici = new GBG();

    Proses YurutulenProses = null;

    int Zaman = 0;
    int YaziciSayisi = 2;
    int TarayiciSayisi = 1;
    int ModemSayisi = 1;
    int CdSurucuSayisi = 2;
    int BellekMiktariMb = 960;
    int GercekZamanliBellekMiktariMb = 64;

    public void Add(Proses proses) {
        if (KaynakTalepEt(proses)) {
            if (proses.Oncelik == 0 && proses.Mbyte <= 64) GercekZamanliProsesYonetici.add(proses);
            else if (proses.Mbyte <= 980) KullaniciProsesYonetici.add(proses);
            else Printer.Report(Printer.Durum.HataBellek,proses);
        } else {
            Printer.Report(Printer.Durum.HataKaynak,proses);
        }
    }


    public void ZamanIlerlet() {
        ZamanAsimiKontrolEt();
        int oncelik = YurutulenProses != null ? YurutulenProses.Oncelik : 4;
        if (oncelik != 0) {
            Proses proses = DahaOncelikliBul(oncelik);
            if (proses != null) {
                if (YurutulenProses != null) {
                    Printer.Report(Printer.Durum.ProsesDurdu, YurutulenProses);
                    if (oncelik != 1) YurutulenProses.Oncelik--;
                    KullaniciProsesYonetici.add(YurutulenProses);
                }
                YurutulenProses = proses;
                Printer.Report(Printer.Durum.ProsesBasladi, YurutulenProses);
            }
        }
        if (YurutulenProses != null) {
            Printer.Report(Printer.Durum.ProsesCalisti, YurutulenProses);
            if (YurutulenProses.ProsesZamani - YurutulenProses.CalismaSuresi++ == 0) {
                Printer.Report(Printer.Durum.ProsesBitti, YurutulenProses);
                KaynakBosalt(YurutulenProses);
                YurutulenProses = null;
            }
        }

        Zaman++;
    }

    private void ZamanAsimiKontrolEt() {
        if (YurutulenProses != null && Zaman - YurutulenProses.VarisZamani >= 20) {
            Printer.Report(Printer.Durum.HataZaman,YurutulenProses);
            YurutulenProses = null;
        }
        GercekZamanliProsesYonetici.ZamanAsimiKontrolEt(Zaman);
        KullaniciProsesYonetici.ZamanAsimiKontrolEt(Zaman);
    }

    public boolean KaynakTalepEt(Proses proses) {
        if (proses.Oncelik == 0) {
            if (GercekZamanliBellekMiktariMb + BellekMiktariMb < proses.Mbyte) return false;
            if (GercekZamanliBellekMiktariMb >= proses.Mbyte) {
                GercekZamanliBellekMiktariMb -= proses.Mbyte;
            } else {
                BellekMiktariMb -= proses.Mbyte - GercekZamanliBellekMiktariMb;
                GercekZamanliBellekMiktariMb = 0;
            }
            return true;
        }
        if (YaziciSayisi < proses.YaziciSayisi) return false;
        if (TarayiciSayisi < proses.TarayiciSayisi) return false;
        if (ModemSayisi < proses.ModemSayisi) return false;
        if (CdSurucuSayisi < proses.CdSurucuSayisi) return false;
        if (BellekMiktariMb < proses.Mbyte) return false;

        YaziciSayisi -= proses.YaziciSayisi;
        TarayiciSayisi -= proses.TarayiciSayisi;
        ModemSayisi -= proses.ModemSayisi;
        CdSurucuSayisi -= proses.CdSurucuSayisi;
        BellekMiktariMb -= proses.Mbyte;

        return true;
    }

    public void KaynakBosalt(Proses proses) {
        if (proses.Oncelik == 0) {
            if (GercekZamanliBellekMiktariMb < 64) {
                if (GercekZamanliBellekMiktariMb + proses.Mbyte > 64) {
                    BellekMiktariMb += GercekZamanliBellekMiktariMb + proses.Mbyte - 64;
                    GercekZamanliBellekMiktariMb = 64;
                    return;
                }
                GercekZamanliBellekMiktariMb += proses.Mbyte;
                return;
            }
            BellekMiktariMb += proses.Mbyte;
        }
        YaziciSayisi += proses.YaziciSayisi;
        TarayiciSayisi += proses.TarayiciSayisi;
        ModemSayisi += proses.ModemSayisi;
        CdSurucuSayisi += proses.CdSurucuSayisi;
        BellekMiktariMb += proses.Mbyte;
    }

    public Proses DahaOncelikliBul(int oncelik) {
        Proses proses = null;
        proses = GercekZamanliProsesYonetici.Get();
        if (proses != null) return proses;
        proses = KullaniciProsesYonetici.Get(oncelik - 1);
        if (proses != null) return proses;
        if (oncelik == 3)
            return KullaniciProsesYonetici.Get(3);
        return null;

    }

    public boolean isEmpty() {
        return YurutulenProses == null && GercekZamanliProsesYonetici.prosesler.isEmpty() && KullaniciProsesYonetici.Oncelik1.isEmpty() && KullaniciProsesYonetici.Oncelik2.isEmpty() && KullaniciProsesYonetici.Oncelik3.prosesler.isEmpty();
    }
}
