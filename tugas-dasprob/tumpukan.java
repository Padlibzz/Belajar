import java.util.Scanner;

public class tumoukan {
  static class Tumpukan {

    private final int MAKS_DATA = 5;
    private String[] tumpukan;
    private int puncak;

    public Tumpukan() {
      tumpukan = new String[MAKS_DATA];
      puncak = -1;
    }

    public void push(String data) {
      if (puncak == (MAKS_DATA - 1)) {

        System.out.println("Tumpukan BUKU Penuh(" + data + " tidak dimasukkan).");
      } else {
        puncak++;
        tumpukan[puncak] = data;
      }
    }

    public String pop() {
      if (!empty()) {
        String data = tumpukan[puncak];
        puncak--;
        return data;
      } else {
        return "Tumpukan BUKU kosong.";
      }
    }

    public boolean empty() {
      return (puncak == -1);
    }

    public void display() {
      if (empty()) {
        System.out.println("Tumpukan BUKU saat ini: [Kosong]");
        return;
      }

      System.out.print("Tumpukan BUKU saat ini : [");
      for (int i = puncak; i >= 0; i--) {
        System.out.print(tumpukan[i]);
        if (i > 0) {
          System.out.print(", ");
        }
      }
      System.out.println("]");
    }

    public int getMaksData() {
      return MAKS_DATA;
    }

    public int getSize() {
      return puncak + 1;
    }
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Tumpukan tumpukanBuku = new Tumpukan();

    tumpukanBuku.push("Java");
    tumpukanBuku.push("Visual Studio");
    tumpukanBuku.push("Codeigniter");
    tumpukanBuku.push("Algoritma");
    tumpukanBuku.push("Kalkulus");

    int pilihan = 0;

    System.out.println("==========================================");
    System.out.println("-----------TUGAS PADLI BADZARA------------");
    System.out.println("==========================================");

    do {
      System.out.println("--- MENU ---");
      System.out.println("1. Ambil ");
      System.out.println("2. Tambah");
      System.out.println("3. Daftar Buku");
      System.out.println("0. Keluar");
      System.out.print("Pilih 0 SAMPAI 3: ");

      if (input.hasNextInt()) {
        pilihan = input.nextInt();
        input.nextLine();
      } else {
        System.out.println("Pakai angka!!");
        input.nextLine();
        pilihan = -1;
        continue;
      }

      String data;

      switch (pilihan) {
        case 1:
          System.out.println("--POP--");
          data = tumpukanBuku.pop();
          if (data.equals("Tumpukan BUKU kosong.")) {
            System.out.println(data);
          } else {
            System.out.println("BERHASIL!! Buku yang diambil adalah : " + data + "'");
          }
          break;
        case 2:
          System.out.println("--PUSH--");
          if (tumpukanBuku.getSize() < tumpukanBuku.getMaksData()) {
            System.out.print("Masukkan Nama Buku: ");
            data = input.nextLine();
            tumpukanBuku.push(data);
          } else {
            System.out.println("GAGAL!! Tumpukan BUKU sudah penuh.");
          }
          break;
        case 3:
          System.out.println("Daftar Buku");
          tumpukanBuku.display();
          System.out.println("Jumlah Buku saat ini: " + tumpukanBuku.getSize() + " / " + tumpukanBuku.getMaksData());
          break;
        case 0:
          System.out.println("Program selesai. Terima kasih!");
          break;
        default:
          System.out.println("Tidak ada pilihan!! Silakan pilih 1, 2, 3, atau 0.");
      }

    } while (pilihan != 0);

    input.close();
  }
}
