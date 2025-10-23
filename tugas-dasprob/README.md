# ATM Sederhana (ATMsimple) 🏧

Repositori ini berisi implementasi **Aplikasi ATM Sederhana** yang ditulis dalam bahasa pemrograman Java. Aplikasi ini berfungsi sebagai contoh dasar untuk simulasi transaksi perbankan seperti otentikasi pengguna, cek saldo, setor tunai, tarik tunai, dan transfer dana.

## 🚀 Cara Menjalankan

1.  **Kompilasi:** Pastikan Anda memiliki Java Development Kit (JDK) terinstal.
    ```bash
    javac ATMsimple.java
    ```
2.  **Jalankan:**
    ```bash
    java ATMsimple
    ```
3.  **Interaksi:** Ikuti instruksi menu yang muncul di konsol.

-----

## 🔬 Analisis Rinci Kode

Kode ini ditulis dalam satu kelas publik (`ATMsimple`) dan menggunakan *array* statis untuk menyimpan data pengguna dan transaksi.

### 1\. Struktur Data dan Variabel Global (Bagian Awal Kelas)

Bagian ini mendefinisikan semua data dan alat yang diperlukan untuk menjalankan program, yang diakses oleh semua fungsi.

| Variabel | Tipe Data | Fungsi | Kegunaan |
| :--- | :--- | :--- | :--- |
| `input` | `static Scanner` | Alat untuk membaca input dari pengguna (konsol). | Memungkinkan program menerima nama, PIN, dan jumlah transaksi. |
| `user` | `static String[]` | *Array* yang berisi daftar nama pengguna yang terdaftar. | **Data Master** nama pengguna (`{"padli", "risca", "apif"}`). |
| `pin` | `static int[]` | *Array* yang berisi PIN/sandi untuk setiap pengguna. | **Data Master** PIN. Indeks PIN harus sesuai dengan indeks nama pengguna di `user`. |
| `saldo` | `static double[]` | *Array* yang berisi saldo saat ini untuk setiap pengguna. | **Data Master** Saldo. Indeks saldo harus sesuai dengan indeks pengguna yang terkait. |
| `userid` | `static int` | Variabel untuk menyimpan **indeks** pengguna yang saat ini terotentikasi. | Nilai ini (`0`, `1`, atau `2`) digunakan untuk mengakses saldo dan nama pengguna yang benar dalam *array* data master. |

### 2\. Fungsi Otentikasi

Dua fungsi ini bertanggung jawab untuk memverifikasi identitas pengguna berdasarkan nama dan PIN yang mereka masukkan.

#### `autentikasiuser(String nama)`

| Parameter | Tipe Data | Fungsi |
| :--- | :--- | :--- |
| `nama` | `String` | Nama pengguna yang dimasukkan oleh pengguna. |

**Cara Kerja:**

  * Melakukan perulangan melalui *array* `user`.
  * Jika nama yang dimasukkan cocok dengan salah satu nama di *array*, ia akan mengembalikan **indeks** (`i`) dari pengguna tersebut (misalnya, `0` untuk "padli").
  * Jika nama tidak ditemukan, ia mengembalikan **`-1`**.

#### `autentikasipin(int userid, int sandi)`

| Parameter | Tipe Data | Fungsi |
| :--- | :--- | :--- |
| `userid` | `int` | Indeks pengguna yang didapat dari `autentikasiuser`. |
| `sandi` | `int` | PIN/sandi yang dimasukkan oleh pengguna. |

**Cara Kerja:**

  * Membandingkan `sandi` yang dimasukkan dengan nilai yang tersimpan di *array* `pin` pada posisi `userid` (indeks pengguna yang terotentikasi).
  * Mengembalikan `true` jika PIN cocok, dan `false` jika tidak cocok.

### 3\. Fungsi Utama Transaksi

Ini adalah fungsi-fungsi yang menjalankan logika bisnis utama dari sistem ATM.

#### `cekuser()`

**Fungsi:** Bertanggung jawab untuk alur login **Nama** dan **PIN**.

**Cara Kerja:**

1.  Meminta pengguna memasukkan nama dan memanggil `autentikasiuser()`.
2.  Jika `userid` ditemukan (`!= -1`), ia meminta PIN dan memanggil `autentikasipin()`.
3.  Jika kedua otentikasi berhasil, menampilkan pesan selamat datang. Jika gagal, menampilkan pesan kesalahan.
    > 💡 **Catatan:** Fungsi ini sering dipanggil oleh fungsi transaksi lainnya (`ceksaldo`, `setortunai`, dll.) untuk memastikan pengguna telah login sebelum melakukan transaksi.

#### `ceksaldo()`

**Fungsi:** Menampilkan saldo terkini pengguna.

**Cara Kerja:**

1.  Memanggil `cekuser()` untuk otentikasi.
2.  Jika otentikasi berhasil, ia mencetak nilai `saldo` dari *array* `saldo` menggunakan `userid` sebagai indeks.

#### `setortunai()`

**Fungsi:** Menambah saldo pengguna.

**Cara Kerja:**

1.  Memanggil `cekuser()` untuk otentikasi.
2.  Meminta jumlah setoran.
3.  Melakukan validasi: jika jumlah setor `< 0`, ia menampilkan pesan error.
4.  Jika valid, ia memperbarui saldo: `saldo[userid] = saldo[userid] + setor;`.
5.  Menampilkan saldo baru.

#### `tariktunai()`

**Fungsi:** Mengurangi saldo pengguna.

**Cara Kerja:**

1.  Memanggil `cekuser()` untuk otentikasi.
2.  Meminta jumlah penarikan.
3.  Melakukan validasi: jika jumlah tarik `< 0` **ATAU** jumlah tarik `>` saldo yang tersedia, ia menampilkan pesan error.
4.  Jika valid, ia memperbarui saldo: `saldo[userid] = saldo[userid] - tarik;`.
5.  Menampilkan saldo baru.

#### `kirimtunai()`

**Fungsi:** Mengirim dana dari satu pengguna ke pengguna lain.

**Cara Kerja:**

1.  Memanggil `cekuser()` untuk otentikasi.
2.  Meminta nama penerima dan mencari indeksnya (`penerima`) menggunakan `autentikasiuser()`.
3.  Melakukan validasi:
      * Pengirim tidak boleh sama dengan penerima.
      * Penerima harus ada di sistem (`!= -1`).
4.  Meminta jumlah kiriman.
5.  Melakukan validasi: jumlah kirim `< 0` atau `>` saldo pengirim.
6.  Jika semua valid, ia:
      * Mengurangi saldo pengirim: `saldo[userid] = saldo[userid] - kirim;`
      * Menambah saldo penerima: `saldo[penerima] = saldo[penerima] + kirim;`
7.  Menampilkan saldo pengirim setelah transfer.

### 4\. `main(String[] args)` (Logika Utama)

**Fungsi:** Mengontrol alur program dan menampilkan menu utama.

**Cara Kerja:**

1.  Menggunakan *loop* **`while(true)`** (loop tak terbatas) untuk terus menampilkan menu hingga pengguna memilih "Exit".
2.  Menampilkan menu opsi (Cek Saldo, Setor, Tarik, Kirim, Exit).
3.  Membaca `pilihan` pengguna.
    > 💡 **Catatan:** Setelah `input.nextInt()`, ada `input.nextLine()` untuk membersihkan *buffer* input, ini penting agar *input* berikutnya (seperti nama) bisa dibaca dengan benar.
4.  Menggunakan serangkaian pernyataan **`else if`** untuk memanggil fungsi yang sesuai berdasarkan pilihan pengguna.
5.  Jika pilihan adalah **`5` (Exit)**, perintah `break` akan menghentikan *loop* `while(true)`, dan program berakhir.

-----

## 🛠️ Peningkatan Potensial

  * **Keamanan PIN:** PIN disimpan sebagai *array* integer di kode sumber (**tidak aman** untuk sistem nyata). Sebaiknya menggunakan *hashing* seperti BCrypt.
  * **Struktur Data:** Menggunakan *class* terpisah (`User`) alih-alih *array* terpisah akan membuat data lebih terstruktur dan mudah dikelola (konsep **OOP**).
  * **Validasi Input:** Tambahkan penanganan kesalahan (`try-catch`) untuk input non-angka (misalnya, pengguna memasukkan huruf saat diminta memasukkan PIN atau jumlah).