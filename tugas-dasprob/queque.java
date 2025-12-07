import java.util.ArrayDeque;
import java.util.Scanner;

public class queque {
  public static Scanner input = new Scanner(System.in);
  public static final int maxsize = 9;

  public static void main(String[] args) {
    ArrayDeque<String> daftar = new ArrayDeque<>();

    // Data awal
    daftar.add("Aminudin");
    daftar.add("Zaskia");
    daftar.add("Rina Melati");
    daftar.add("Farhan");
    daftar.add("Agnes Monica");
    daftar.add("Dian Susilo");
    daftar.add("Karman Julia");
    daftar.add("Dani Adipta");
    daftar.add("Kartika Putri");

    int pilihan;
    do {
      System.out.println("+-----------------+");
      System.out.println("|1. Ambil         |");
      System.out.println("|2. Tambah        |");
      System.out.println("|3. Antrian       |");
      System.out.println("|4. Exit          |");
      System.out.println("+-----------------+");
      System.out.print("Pilihan: ");
      pilihan = input.nextInt();
      input.nextLine();

      switch (pilihan) {
        case 1:
          if (!daftar.isEmpty()) {
            String keluar = daftar.remove();
            System.out.println(keluar + " keluar");
          } else {
            System.out.println("Antrean kosong");
          }
          break;

        case 2:
          if (daftar.size() >= maxsize) {
            System.out.println("Antrean penuh");
          } else {
            System.out.print("Masukkan nama: ");
            String nama = input.nextLine();
            daftar.add(nama);
            System.out.println(nama + " ditambahkan ke antrean");
          }
          break;

        case 3:
          if (daftar.isEmpty()) {
            System.out.println("Antrean kosong");
          } else {
            System.out.println("Daftar antrean:");
            for (String i : daftar) {
              System.out.println("- " + i);
            }
          }
          break;

        case 4:
          System.out.println("Keluar program");
          break;

        default:
          System.out.println("Pilihan salah!");
      }
    } while (pilihan != 4);
  }
}
