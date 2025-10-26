# 🚀 ATMsimple: ATM Sederhana dengan Java

Selamat datang di proyek **ATMsimple**\! Ini adalah simulasi sederhana dari mesin ATM menggunakan bahasa pemrograman Java. Program ini memungkinkan pengguna untuk login, membuat akun baru (sign up), mengecek saldo, menabung (deposit), menarik tunai (withdraw), dan mentransfer uang.

## 🌟 Cara Kerja Program Ini

Secara garis besar, program ini dibagi menjadi dua bagian utama:

1.  **`ATMsimple` (Program Utama):** Tugasnya menampilkan menu awal (Login, Sign In, Exit) dan menjalankan program secara berulang.
2.  **`atm` (Logika Bisnis):** Ini adalah "otak" dari ATM, tempat semua data (nama, PIN, saldo) disimpan dan semua transaksi (login, cek saldo, transfer, dll.) dilakukan.

Mari kita bedah setiap fungsinya\!

-----

## 💻 Detail Kode per Bagian

### 1\. Persiapan Awal (Import dan Kelas Utama)

Bagian ini ibarat menyiapkan alat dan bahan yang dibutuhkan sebelum kita memasak.

| Barisan Kode | Penjelasan untuk Pemula |
| :--- | :--- |
| `import java.util.Scanner;` | **Memanggil Alat Baca Input:** Ini seperti mengambil "alat pembaca" agar program bisa menerima ketikan atau masukan dari kita, misalnya saat kita mengetik PIN atau jumlah uang. |
| `import java.util.ArrayList;` | **Memanggil Kotak Penyimpanan Fleksibel:** Ini seperti mengambil kotak ajaib yang ukurannya bisa bertambah atau berkurang sesuai kebutuhan. Kita akan gunakan untuk menyimpan daftar nama, PIN, dan saldo. |
| `import java.util.Arrays;` | **Memanggil Alat Bantu Pengisian Kotak:** Alat ini membantu kita mengisi kotak penyimpanan (`ArrayList`) dengan data awal yang sudah disiapkan, seperti nama-nama pengguna pertama. |
| `public class ATMsimple { ... }` | **Kelas Program Utama (Lobi ATM):** Ini adalah pintu masuk atau "gedung" utama tempat program Anda dimulai. |
| `public static Scanner input = new Scanner(System.in);` | **Penciptaan Alat Baca:** Ini adalah perintah untuk membuat alat baca input yang tadi dipanggil, dan menamainya `input`. Alat ini siap digunakan di seluruh bagian program utama. |
| `public static void main(String[] args) { ... }` | **Titik Awal Program:** Ini adalah gerbang pertama yang akan dieksekusi oleh komputer saat program dijalankan. Semua aksi akan dimulai dari sini. |

<br>

### 2\. Menu Awal dan Pilihan Aksi

Bagian ini menampilkan tampilan ATM saat pertama kali dinyalakan.

| Barisan Kode | Penjelasan untuk Pemula |
| :--- | :--- |
| `while (true) { ... }` | **Looping Abadi (Tampilan Berulang):** Ini memastikan menu awal akan terus muncul setelah setiap aksi (misalnya, setelah Sign In) sampai kita memilih untuk `exit` (keluar). |
| `System.out.println(...);` | **Menampilkan Teks di Layar:** Barisan ini hanya berfungsi mencetak teks-teks keren dan garis-garis kotak ke layar untuk membentuk tampilan menu yang artistik. |
| `int pilihan = input.nextInt();` | **Menerima Pilihan:** Program menunggu kita memasukkan angka (1, 2, atau 3) dari keyboard, lalu menyimpannya dalam variabel `pilihan`. |
| `input.nextLine();` | **Membersihkan Keyboard:** Ini adalah trik penting di Java. Setelah kita memasukkan angka, kita harus "membersihkan" sisa-sisa tombol **Enter** agar tidak mengganggu input teks berikutnya (seperti saat memasukkan nama). |
| `if (pilihan == 1) { ... }` | **Logika Pilihan 1 (Login):** Jika kita pilih angka **1**, program akan memanggil fungsi `atm.login()` untuk masuk, dan setelah berhasil akan memanggil `atm.menu()` untuk menampilkan menu transaksi. |
| `else if (pilihan == 2) { ... }` | **Logika Pilihan 2 (Sign In):** Jika kita pilih angka **2**, program akan memanggil `atm.adduser()` untuk membuat akun baru. |
| `else if (pilihan == 3) { break; }` | **Logika Pilihan 3 (Exit):** Jika kita pilih angka **3**, kata kunci `break` akan menghentikan `while (true)` tadi, dan program pun selesai. |

<br>

-----

## 💡 Detail Kode per Fungsi (`class atm`)

Kelas `atm` adalah tempat data pengguna disimpan dan semua logika transaksi berjalan.

### 3\. Penyimpanan Data Pengguna

Data-data penting seperti nama, PIN, dan saldo disimpan di sini.

| Barisan Kode | Penjelasan untuk Pemula |
| :--- | :--- |
| `class atm { ... }` | **Kelas Logika ATM (Otak Transaksi):** Ini adalah tempat semua fungsi ATM (deposit, transfer, dll.) berada. |
| `private static ArrayList<String> user = new ArrayList<>(Arrays.asList("padli", "risca", "apif"));` | **Daftar Nama Pengguna:** Kotak fleksibel yang menyimpan daftar nama pengguna (tipe teks/String). Tiga nama pertama sudah diisi sebagai contoh. |
| `private static ArrayList<Integer> pin = new ArrayList<>(Arrays.asList(111, 222, 333));` | **Daftar PIN Pengguna:** Kotak fleksibel yang menyimpan PIN (tipe angka bulat/Integer). Setiap PIN berpasangan dengan nama di daftar di atas (Padli PIN-nya 111, Risca 222, dst.). |
| `private static ArrayList<Long> saldo = new ArrayList<>(Arrays.asList(1000000L, 2000000L, 3000000L));` | **Daftar Saldo Pengguna:** Kotak fleksibel yang menyimpan saldo (tipe angka besar/Long). Sama, setiap saldo berpasangan dengan nama dan PIN. |
| `private static int userid;` | **Penyimpanan ID Pengguna Saat Ini:** Variabel ini akan menyimpan "nomor urut" (indeks) dari pengguna yang sedang login. Penting agar transaksi dilakukan hanya pada akun yang benar. |
| `public static Scanner input = new Scanner(System.in);` | **Alat Baca di Kelas Logika:** Sama seperti di kelas utama, ini adalah alat baca input khusus untuk kelas `atm`. |

<br>

### 4\. Fungsi Utama (Login, Autentikasi, dan Buat Akun)

| Fungsi/Barisan Kode | Penjelasan untuk Pemula |
| :--- | :--- |
| **`autentikasiuser(String username)`** | **Fungsi Cek Nama:** Tugasnya mencari nama yang dimasukkan di dalam daftar `user`. Jika ketemu, fungsi ini akan mengembalikan **nomor urut** (indeks) dari nama tersebut. Jika tidak ketemu, ia mengembalikan **-1**. |
| **`autentikasipin(int userpin)`** | **Fungsi Cek PIN:** Tugasnya membandingkan PIN yang dimasukkan dengan PIN yang tersimpan di daftar `pin` pada **nomor urut** (`userid`) pengguna yang baru saja ditemukan. Hasilnya hanya *Benar* atau *Salah*. |
| **`login()`** | **Fungsi Masuk Akun:** Meminta nama dan PIN. Ia menggunakan `autentikasiuser` dan `autentikasipin` untuk memverifikasi data. Jika berhasil, menampilkan pesan sambutan dan keluar dari loop (masuk ke menu transaksi). Jika gagal, akan meminta ulang input. |
| **`adduser()`** | **Fungsi Buat Akun Baru (Sign In):** Meminta nama dan sandi baru. Cek apakah nama sudah ada. Jika nama unik, meminta konfirmasi sandi. Jika sandi cocok, nama, PIN, dan saldo awal (Rp 0) akan **ditambahkan** ke kotak penyimpanan (`ArrayList`) dan akun berhasil dibuat. |

<br>

-----

### 5\. Fungsi Transaksi (Menu, Cek Saldo, Deposit, Withdraw, Transfer)

Ini adalah inti dari semua fitur ATM.

| Fungsi/Barisan Kode | Penjelasan untuk Pemula |
| :--- | :--- |
| **`menu()`** | **Fungsi Menu Transaksi:** Mirip dengan menu awal, tapi isinya adalah pilihan transaksi (Cek Saldo, Setor, Tarik, Kirim, Buat Akun, EXIT). Ia akan terus muncul berulang-ulang selama pengguna belum memilih `EXIT` (6). |
| **`ceksaldo()`** | **Fungsi Cek Saldo:** Mengambil dan menampilkan nilai saldo dari daftar `saldo` pada **nomor urut** (`userid`) pengguna yang sedang login. |
| **`depo()`** | **Fungsi Setor Tunai (Deposit):** Meminta jumlah yang ingin disetor. Jika jumlah valid (lebih dari 0), saldo lama pengguna akan **dijumlahkan** dengan nominal setoran, dan saldo baru di-update. |
| **`withdraw()`** | **Fungsi Tarik Tunai (Withdraw):** Meminta jumlah yang ingin ditarik. Cek apakah jumlah valid (tidak kurang dari 0 dan tidak melebihi saldo saat ini). Jika valid, saldo lama akan **dikurangi** dengan nominal penarikan, dan saldo baru di-update. |
| **`transfer()`** | **Fungsi Kirim Tunai (Transfer):** Meminta nama penerima dan jumlah transfer. Program akan: 1) Mencari dan memverifikasi nama penerima. 2) Memastikan jumlah transfer valid (tidak kurang dari 0 dan tidak melebihi saldo pengirim). 3) Jika valid, saldo pengirim **dikurangi** dan saldo penerima **ditambahkan**. |

-----

Dengan penjelasan ini, kini Anda tahu bagaimana setiap baris kode bekerja sama untuk membuat simulasi ATM yang sederhana\! Proyek ini menunjukkan konsep dasar penting dalam pemrograman: **menyimpan data**, **memproses input**, dan **membuat logika keputusan** (`if-else` dan `while`). Selamat mencoba\! 🎉
