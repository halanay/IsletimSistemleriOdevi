import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Gorevlendirici gorevlendirici = new Gorevlendirici();
        List<Proses> butunProsesler = new ArrayList<>();

        File file = new File("giriş.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sc.useDelimiter(", |\\r\\n");
        int pid = 0;
        while (sc.hasNext()) {
            Proses proses = new Proses(pid++, sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());
            butunProsesler.add(proses);

        }


        while (!butunProsesler.isEmpty() || !gorevlendirici.isEmpty()) {
            var list = butunProsesler.stream().filter(proses1 -> proses1.VarisZamani == gorevlendirici.Zaman).toList();
            for (Proses proses : list) {
                gorevlendirici.Add(proses);
            }
            gorevlendirici.ZamanIlerlet();
            butunProsesler.removeAll(list);
        }
        Printer.FinalReport();
        System.out.println("Hello world!");
    }
}