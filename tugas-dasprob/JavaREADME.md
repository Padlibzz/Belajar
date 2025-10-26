# README: ATMsimple (ATM Sederhana) 💰

Proyek ini adalah implementasi sederhana dari sistem ATM menggunakan bahasa pemrograman Java. Ia memungkinkan pengguna untuk **login**, **mendaftar** akun baru, serta melakukan operasi dasar perbankan seperti **cek saldo**, **setor tunai**, **tarik tunai**, dan **transfer** antar akun yang sudah terdaftar.

## Struktur Kode

Kode terdiri dari dua kelas utama: `ATMsimple` dan `atm`.

### 1. Kelas `ATMsimple` (Utama)

Kelas ini berisi metode `main` yang berfungsi sebagai **titik masuk** aplikasi dan menampilkan **menu awal** (Login, Signin, Exit).

| Baris | Kode | Fungsi Terperinci |
| :---: | :--- | :--- |
| 1 | `import java.util.Scanner;` | Mengimpor kelas **Scanner** untuk membaca input dari pengguna (keyboard). |
| 2 | `import java.util.ArrayList;` | Mengimpor kelas **ArrayList** untuk menyimpan data yang dinamis (misalnya, daftar pengguna, PIN, saldo). |
| 3 | `import java.util.Arrays;` | Mengimpor kelas **Arrays** untuk membuat `ArrayList` dengan data awal. |
| 5 | `public class ATMsimple {` | Deklarasi kelas utama program. Nama file Java harus `ATMsimple.java`. |
| 6 | `;` | Baris ini tampaknya merupakan **kesalahan ketik** atau baris kosong yang tidak perlu. |
| 7 | `public static Scanner input = new Scanner(System.in);` | Deklarasi objek **Scanner** statis dan publik bernama `input` untuk digunakan di seluruh kelas, memungkinkan pembacaan input. |
| 9 | `public static void main(String[] args) {` | Metode **utama** yang dieksekusi saat program dimulai. |
| 10 | `while (true) {` | Memulai **loop tak terbatas** (`while(true)`) untuk menjaga agar menu utama terus berjalan hingga pengguna memilih untuk keluar. |
| 11-20 | `System.out.println(...)` | Menampilkan **header** dan **menu awal** ATM di konsol. |
| 21 | `int pilihan = input.nextInt();` | Membaca **pilihan menu** (1, 2, atau 3) yang dimasukkan pengguna sebagai bilangan bulat. |
| 22 | `input.nextLine();` | **Mengonsumsi baris baru** (`\n`) yang tersisa di buffer `Scanner` setelah `nextInt()`, mencegah masalah input di langkah selanjutnya. |
| 23 | `if (pilihan == 1) {` | Memeriksa jika pengguna memilih **1 (login)**. |
| 24 | `atm.login();` | Memanggil metode `login` dari kelas `atm`. |
| 25 | `atm.menu();` | Memanggil metode `menu` dari kelas `atm` (menu transaksi) setelah login berhasil. |
| 26 | `} else if (pilihan == 2) {` | Memeriksa jika pengguna memilih **2 (signin/buat akun)**. |
| 27 | `atm.adduser();` | Memanggil metode `adduser` dari kelas `atm` untuk mendaftar akun baru. |
| 28 | `continue;` | Melanjutkan ke iterasi `while` berikutnya, kembali menampilkan menu utama setelah pendaftaran selesai. |
| 29 | `} else if (pilihan == 3) {` | Memeriksa jika pengguna memilih **3 (exit)**. |
| 30 | `break;` | **Menghentikan** loop `while(true)`, mengakhiri program. |
| 31 | `} else {` | Blok kode jika pengguna memasukkan pilihan yang **tidak valid**. |
| 32 | `System.out.println("masukan nomber valid");` | Menampilkan pesan kesalahan. |
| 33 | `continue;` | Melanjutkan ke iterasi `while` berikutnya, menampilkan ulang menu utama. |
| 35 | `}` | Penutup `while` loop. |
| 37 | `}` | Penutup metode `main`. |
| 38 | `}` | Penutup kelas `ATMsimple`. |

---

### 2. Kelas `atm` (Logika Bisnis)

Kelas ini (seharusnya bernama `Atm` atau `ATM` sesuai konvensi Java) berisi **data pengguna** (nama, PIN, saldo) dan **logika semua transaksi** ATM.

| Baris | Kode | Fungsi Terperinci |
| :---: | :--- | :--- |
| 40 | `class atm {` | Deklarasi kelas yang berisi semua fungsionalitas ATM. |
| 41 | `private static ArrayList<String> user = new ArrayList<>(Arrays.asList("padli", "risca", "apif"));` | Menyimpan daftar **nama pengguna** (`user`) sebagai `ArrayList` string, diinisialisasi dengan 3 pengguna awal. |
| 42 | `private static ArrayList<Integer> pin = new ArrayList<>(Arrays.asList(111, 222, 333));` | Menyimpan daftar **PIN** pengguna sebagai `ArrayList` integer, sesuai dengan indeks nama pengguna. |
| 43 | `private static ArrayList<Long> saldo = new ArrayList<>(Arrays.asList(1000000L, 2000000L, 3000000L));` | Menyimpan daftar **saldo** pengguna sebagai `ArrayList` Long, sesuai dengan indeks nama pengguna. |
| 44 | `private static int userid;` | Variabel statis yang menyimpan **indeks** pengguna yang saat ini **login** di `ArrayList`. |
| 46 | `public static Scanner input = new Scanner(System.in);` | Objek **Scanner** statis dan publik untuk input di dalam kelas `atm`. |
| 48 | `public static void menu() {` | Metode untuk menampilkan dan mengelola **menu transaksi** utama (setelah login). |
| 49 | `while (true) {` | Memulai loop tak terbatas untuk menu transaksi. |
| 50-67 | `System.out.println(...)` | Menampilkan **menu transaksi** (Cek Saldo, Setor, Tarik, Kirim, Buat Akun, Exit). |
| 69 | `int pilihan = input.nextInt();` | Membaca pilihan menu transaksi. |
| 70 | `input.nextLine();` | Mengonsumsi baris baru yang tersisa. |
| 71 | `if (pilihan == 6) {` | Memeriksa jika pengguna memilih **6 (EXIT)**. |
| 72 | `break;` | **Menghentikan** loop menu transaksi, kembali ke menu awal (`main`). |
| 73-81 | `else if (pilihan == 1) {...} else if (pilihan == 5) {...}` | Blok `if-else if` untuk memanggil metode transaksi yang sesuai berdasarkan pilihan pengguna. |
| 86 | `public static int autentikasiuser(String username) {` | Metode untuk **mencari nama pengguna** dalam daftar `user`. |
| 87-91 | `for (int i = 0; i < user.size(); i++) { ... }` | Mengulang melalui daftar `user`. Jika nama ditemukan, mengembalikan **indeks** (`i`). |
| 92 | `return -1;` | Mengembalikan **-1** jika nama pengguna **tidak ditemukan**. |
| 95 | `public static boolean autentikasipin(int userpin) {` | Metode untuk **memeriksa PIN** pengguna. |
| 97 | `return userpin == pin.get(userid);` | Mengembalikan `true` jika PIN yang dimasukkan sama dengan PIN yang tersimpan pada indeks `userid` yang sedang login. |
| 100 | `public static void login() {` | Metode untuk **proses login** (meminta nama dan PIN). |
| 101 | `while (true) {` | Memulai loop login hingga berhasil atau pengguna memilih keluar. |
| 102 | `System.out.print("Masukan nama : ");` | Meminta input nama pengguna. |
| 103 | `String username = input.nextLine();` | Membaca nama pengguna. |
| 104 | `userid = autentikasiuser(username);` | Mencari nama pengguna dan menyimpan indeksnya di `userid`. |
| 105 | `if (userid == -1) { ... continue; }` | Jika `userid` -1, nama pengguna tidak ditemukan, kembali ke awal loop login. |
| 108-111 | `System.out.print("Masukan pin : "); ... input.nextLine();` | Jika nama ditemukan, meminta dan membaca PIN. |
| 112 | `if (autentikasipin(userpin)) { ... break; }` | Memeriksa PIN. Jika benar, menampilkan pesan selamat datang dan **keluar dari loop login**. |
| 115 | `else { ... continue; }` | Jika PIN salah, menampilkan pesan kesalahan dan kembali ke awal loop login. |
| 122 | `public static void adduser() {` | Metode untuk **membuat akun baru** (signin). |
| 123 | `while (true) {` | Memulai loop pendaftaran. |
| 124 | `System.out.print("masukan nama : ");` | Meminta nama untuk akun baru. |
| 126 | `int ceknama = autentikasiuser(nama);` | Memeriksa apakah nama tersebut sudah ada. |
| 127 | `if (ceknama == -1) {` | Jika nama belum ada (lanjutkan pendaftaran). |
| 128-132 | Meminta dan membaca sandi (PIN) baru dan konfirmasi sandi. |
| 133 | `if (sandi1 != sandi2) { ... continue; }` | Memeriksa apakah sandi dan konfirmasi sandi sama. Jika tidak, pesan error dan ulangi loop. |
| 136-139 | `user.add(nama); pin.add(sandi1); saldo.add(0L);` | Jika sandi cocok, **menambahkan** nama, PIN, dan saldo awal `0L` ke `ArrayList`. Akun berhasil dibuat, dan keluar dari loop. |
| 142 | `} else { ... }` | Jika nama sudah ada, menampilkan pesan kesalahan dan kembali ke awal loop. |
| 147 | `public static void ceksaldo() {` | Metode untuk **menampilkan saldo** pengguna yang sedang login. |
| 148 | `System.out.println("saldomu adalah : " + saldo.get(userid));` | Mengambil dan menampilkan saldo dari `ArrayList saldo` pada indeks `userid`. |
| 151 | `public static void depo() {` | Metode untuk **setor tunai**. |
| 154 | `long depo = input.nextLong();` | Membaca nominal deposit. |
| 155 | `if (depo <= 0) { ... continue; }` | Memeriksa apakah nominal valid (positif). Jika tidak, pesan error dan ulangi. |
| 158 | `depo = saldo.get(userid) + depo;` | Menghitung saldo baru: saldo lama + nominal deposit. |
| 159 | `saldo.set(userid, depo);` | **Memperbarui** saldo di `ArrayList saldo` dengan nilai baru. Sukses dan keluar dari loop. |
| 167 | `public static void withdraw() {` | Metode untuk **tarik tunai**. |
| 170 | `long withdraw = input.nextLong();` | Membaca nominal penarikan. |
| 171 | `if (withdraw < 0 && withdraw > saldo.get(userid)) { ... continue; }` | **Perhatian:** Logika kondisi ini **salah**. Seharusnya dicek jika `withdraw <= 0` **ATAU** `withdraw > saldo.get(userid)`. |
| 174 | `withdraw = saldo.get(userid) - withdraw;` | Menghitung saldo baru: saldo lama - nominal penarikan. |
| 175 | `saldo.set(userid, withdraw);` | **Memperbarui** saldo di `ArrayList saldo` dengan nilai baru. Sukses dan keluar dari loop. |
| 183 | `public static void transfer() {` | Metode untuk **transfer dana** antar pengguna. |
| 186 | `String cekpenerima = input.nextLine();` | Membaca nama penerima. |
| 187 | `int penerima = autentikasiuser(cekpenerima);` | Mencari indeks penerima. |
| 188 | `if (penerima == -1) { ... continue; }` | Jika penerima tidak ditemukan, ulangi. |
| 191 | `else if (penerima == userid) { ... continue; }` | Jika pengguna mencoba transfer ke diri sendiri, ulangi. |
| 194-196 | Meminta dan membaca jumlah transfer. |
| 197 | `if (transfer < 0 || transfer > saldo.get(userid)) { ... continue; }` | Memeriksa apakah nominal valid (positif dan tidak melebihi saldo). Jika tidak, pesan error dan ulangi. |
| 201 | `long terima = saldo.get(penerima) + transfer;` | Menghitung saldo penerima baru. |
| 202 | `saldo.set(penerima, terima);` | **Menambahkan** dana ke saldo penerima. |
| 203 | `long beri = saldo.get(userid) - transfer;` | Menghitung saldo pengirim baru. |
| 204 | `saldo.set(userid, beri);` | **Mengurangi** dana dari saldo pengirim. Berhasil, dan keluar dari loop. |
| 211 | `}` | Penutup kelas `atm`. |

## Cara Menjalankan

1.  Simpan kode di atas sebagai file bernama `ATMsimple.java`.
2.  Kompilasi kode menggunakan Java compiler: `javac ATMsimple.java`
3.  Jalankan program: `java ATMsimple`

Program akan menampilkan menu awal, meminta Anda untuk Login (1), Signin (2), atau Exit (3).

## Batasan dan Peningkatan yang Disarankan

* **Penyimpanan Data:** Data pengguna (nama, PIN, saldo) disimpan di `ArrayList` statis, yang berarti semua data akan **hilang** saat program dihentikan.
    * **Peningkatan:** Terapkan penyimpanan ke **file** (misalnya, TXT, CSV, atau JSON) atau gunakan **database** agar data persisten.
* **Validasi `withdraw`:** Kondisi validasi di metode `withdraw` (`withdraw < 0 && withdraw > saldo.get(userid)`) tidak sepenuhnya benar. Perlu diperbaiki menjadi `withdraw <= 0 || withdraw > saldo.get(userid)`.
* **Keamanan PIN:** PIN disimpan sebagai `Integer` dan ditampilkan di kode.
    * **Peningkatan:** Gunakan **hashing** (misalnya, bcrypt) untuk menyimpan PIN secara aman.
* **Handling Input:** Saat ini hanya menerima input numerik dan string.
    * **Peningkatan:** Tambahkan **penanganan *exception*** (misalnya, `try-catch`) untuk input non-numerik (misalnya, jika pengguna memasukkan "abc" saat diminta PIN/nominal) untuk mencegah program *crash*.
