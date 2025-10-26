# 🔬 Analisis Kode Rinci: `ATMsimple.java`

Analisis ini dibagi berdasarkan struktur kelas dan fungsi utama.

## I. Struktur Program dan Penggunaan Library 📚

Kode dibagi menjadi dua kelas: `SimpleATM` (Kelas Utama) dan `atm` (Kelas Logika).

| Baris Kode                       | Kategori                    | Penjelasan Detail                                                                                                                         |
| :------------------------------- | :-------------------------- | :---------------------------------------------------------------------------------------------------------------------------------------- |
| `import java.util.Scanner;`      | **Library (I/O)**           | Mengimpor kelas **`Scanner`** untuk membaca input dari pengguna melalui konsol (keyboard).                                                |
| `import java.util.ArrayList;`    | **Library (Struktur Data)** | Mengimpor kelas **`ArrayList`** untuk membuat daftar data yang ukurannya dapat berubah secara dinamis (misalnya, menambah pengguna baru). |
| `import java.util.Arrays;`       | **Library (Utility)**       | Mengimpor utilitas **`Arrays`** untuk membantu membuat `ArrayList` awal dengan data _default_.                                            |
| `public class SimpleATM { ... }` | **Kelas Utama**             | Kelas yang mengandung fungsi `main()`, titik awal eksekusi program.                                                                       |
| `class atm { ... }`              | **Kelas Logika**            | Kelas yang menyimpan semua data pengguna dan mengimplementasikan semua fungsi transaksi (login, menu, setor, tarik, transfer).            |

---

## II. Struktur Data Global (Kelas `atm`) 💾

Semua data master ATM disimpan sebagai `private static ArrayList` di dalam kelas `atm`.

| Variabel | Tipe Data            | Keterangan Tambahan                                                                             | Penggunaan Kunci                                                              |
| :------- | :------------------- | :---------------------------------------------------------------------------------------------- | :---------------------------------------------------------------------------- |
| `user`   | `ArrayList<String>`  | Daftar nama pengguna. Dibuat dengan data awal (`"padli"`, `"risca"`, dll.).                     | Digunakan untuk otentikasi dan transfer.                                      |
| `pin`    | `ArrayList<Integer>` | Daftar PIN pengguna. Indeksnya sesuai dengan `user`.                                            | Digunakan untuk verifikasi sandi dalam otentikasi.                            |
| `saldo`  | `ArrayList<Long>`    | Saldo saat ini. Menggunakan **`Long`** untuk presisi dan jangkauan nilai uang yang lebih besar. | Diperbarui saat transaksi keuangan (`depo`, `withdraw`, `transfer`).          |
| `userid` | `int`                | Menyimpan **indeks** (nomor urut) pengguna yang sedang **aktif login**.                         | Kunci untuk mengakses data saldo dan PIN yang benar dari `ArrayList` di atas. |
| `input`  | `Scanner`            | Objek untuk membaca input di dalam kelas `atm`.                                                 | Digunakan di semua fungsi yang memerlukan interaksi pengguna.                 |

---

## III. Fungsi Utama dan Kontrol Aliran (Flow Control) 🚦

### A. Aliran Program Utama (`SimpleATM.main`)

| Baris Kunci                                           | Operator/Fitur         | Tujuan                                                                                                    |
| :---------------------------------------------------- | :--------------------- | :-------------------------------------------------------------------------------------------------------- |
| `while (true) { ... }`                                | **Looping**            | Membuat Menu Awal (Login/Sign In/Exit) berjalan secara **berulang tanpa batas** hingga `break` dipanggil. |
| `if (pilihan == 1) { atm.login(); atm.menu(); }`      | **Kondisional (`==`)** | Jika `1` (Login) dipilih, panggil fungsi `login()` untuk otentikasi, lalu masuk ke `menu()` transaksi.    |
| `else if (pilihan == 2) { atm.adduser(); continue; }` | **Kondisional**        | Jika `2` (Sign In) dipilih, panggil `adduser()`, lalu **kembali (`continue`)** ke Menu Awal.              |
| `else if (pilihan == 3) { break; }`                   | **Kontrol Aliran**     | Jika `3` (Exit) dipilih, perintah **`break`** menghentikan `while (true)` dan program berakhir.           |

### B. Fungsi Otentikasi dan Registrasi

| Fungsi/Metode                          | Penggunaan Operator/Fitur Kunci         | Deskripsi                                                                                                                                                                              |
| :------------------------------------- | :-------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`autentikasiuser(String username)`** | `for` loop, `equals()`                  | Mencari `username` di `user` list. Mengembalikan **indeks** jika ditemukan, atau **`-1`** jika tidak ada.                                                                              |
| **`autentikasipin(int userpin)`**      | `==` (Sama Dengan), `get()`             | Membandingkan PIN yang dimasukkan dengan `pin.get(userid)`. Mengembalikan `true` atau `false`.                                                                                         |
| **`login()`**                          | `while(true)`, `==`, `!=`               | Mengatur alur login. Meminta Nama, lalu PIN. Jika otentikasi gagal, _loop_ akan berlanjut (`continue`).                                                                                |
| **`adduser()`**                        | `!=` (Tidak Sama Dengan), `add()`, `0L` | **Registrasi Akun Baru.** Memeriksa apakah nama sudah ada. Memvalidasi PIN. Jika sukses, menambahkan data ke **tiga** `ArrayList` (`user`, `pin`, `saldo`) dengan saldo awal **`0L`**. |

---

## IV. Fungsi Transaksi (Kelas `atm`) 💰

Fungsi-fungsi ini dipanggil dari `atm.menu()` setelah pengguna berhasil _login_.

| Fungsi/Metode                  | Operator/Fitur Utama                   | Logika Bisnis                                                                                                                                                                                  |
| :----------------------------- | :------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`ceksaldo()`**               | `get()`                                | Hanya menampilkan nilai `saldo.get(userid)` (saldo pengguna yang sedang _login_).                                                                                                              |
| **`depo()` (Setor Tunai)**     | `+` (Penambahan), `set()`, `<= 0`      | 1. Meminta nominal. 2. Memvalidasi nominal harus **`> 0`**. 3. Menghitung saldo baru: `saldo[userid] **+** setor`. 4. Memperbarui `ArrayList` dengan `saldo.set()`.                            |
| **`withdraw()` (Tarik Tunai)** | `-` (Pengurangan), `set()`, `&&` (DAN) | 1. Meminta nominal. 2. Memvalidasi nominal harus **`> 0`** **DAN** tidak boleh **`>`** dari saldo saat ini. 3. Menghitung saldo baru: `saldo[userid] **-** tarik`. 4. Memperbarui `ArrayList`. |
| **`transfer()` (Kirim Tunai)** | `+`, `-`, `==`, `                      |                                                                                                                                                                                                | ` (ATAU) | 1. Meminta nama penerima, cek keberadaan. 2. Memvalidasi penerima **`!= userid`** (tidak boleh diri sendiri). 3. Memvalidasi jumlah transfer **`> 0`** **ATAU** **`<`** saldo pengirim. 4. Mengurangi saldo pengirim (`-`) dan Menambah saldo penerima (`+`). |
| **`menu()`**                   | `else if`, `break`                     | Menampilkan opsi transaksi (1-5) setelah login. Jika pilih **`6` (EXIT)**, _loop_ dihentikan (`break`) dan kembali ke Menu Awal.                                                               |
