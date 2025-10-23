# ATM Sederhana (ATMsimple) 🏧

Repositori ini berisi implementasi **Aplikasi ATM Sederhana** yang ditulis dalam bahasa pemrograman **C++**. Aplikasi ini berfungsi sebagai contoh dasar untuk simulasi transaksi perbankan seperti otentikasi pengguna, cek saldo, setor tunai, tarik tunai, dan transfer dana, menggunakan _array_ dan fungsi global.

## 🚀 Cara Menjalankan

1.  **Kompilasi:** Pastikan Anda memiliki _compiler_ C++ (misalnya, g++).
    ```bash
    g++ -o ATMsimple ATMsimple.cpp
    ```
    _(Asumsikan nama berkas Anda adalah `ATMsimple.cpp`)_
2.  **Jalankan:**
    ```bash
    ./ATMsimple
    ```
3.  **Interaksi:** Ikuti instruksi menu yang muncul di konsol.

---

## 🔬 Analisis Rinci Kode

Kode ini ditulis dalam bahasa C++ dan menggunakan variabel global dan _array_ global untuk menyimpan data pengguna dan transaksi. Fungsi-fungsi dideklarasikan di awal dan didefinisikan setelah fungsi `main()`.

### 1\. Struktur Data dan Variabel Global

Bagian ini mendefinisikan semua data dan alat yang diperlukan untuk menjalankan program, yang diakses oleh semua fungsi.

| Variabel     | Tipe Data  | Fungsi                                                                           | Kegunaan                                                                              |
| :----------- | :--------- | :------------------------------------------------------------------------------- | :------------------------------------------------------------------------------------ |
| `user`       | `string[]` | _Array_ yang berisi daftar nama pengguna yang terdaftar.                         | **Data Master** nama pengguna (`{"padli", "risca", "apif"}`).                         |
| `pin`        | `int[]`    | _Array_ yang berisi PIN/sandi untuk setiap pengguna.                             | **Data Master** PIN. Indeks PIN harus sesuai dengan indeks nama pengguna di `user`.   |
| `saldo`      | `double[]` | _Array_ yang berisi saldo saat ini untuk setiap pengguna.                        | **Data Master** Saldo. Indeks saldo harus sesuai dengan indeks pengguna yang terkait. |
| `userid`     | `int`      | Variabel global untuk menyimpan **indeks** pengguna yang saat ini terotentikasi. | Digunakan untuk mengakses saldo/data pengguna yang benar setelah login.               |
| `sandi`      | `int`      | Variabel global untuk menyimpan PIN yang dimasukkan pengguna saat otentikasi.    | Digunakan untuk pemeriksaan otentikasi.                                               |
| `userlength` | `int`      | Menyimpan panjang (_size_) dari _array_ pengguna.                                | Digunakan sebagai batas dalam perulangan otentikasi.                                  |

---

### 2\. Fungsi Otentikasi

Fungsi-fungsi ini bertanggung jawab untuk memverifikasi identitas pengguna berdasarkan nama dan PIN yang mereka masukkan.

#### `autentikasiuser(string nama)`

| Parameter | Tipe Data | Fungsi                                       |
| :-------- | :-------- | :------------------------------------------- |
| `nama`    | `string`  | Nama pengguna yang dimasukkan oleh pengguna. |

**Cara Kerja:**

- Melakukan perulangan melalui _array_ global `user` (sebanyak `userlength`).
- Jika `nama` yang dimasukkan cocok dengan salah satu nama di _array_, ia akan mengembalikan **indeks** (`i`) dari pengguna tersebut (misalnya, `0` untuk "padli").
- Jika nama tidak ditemukan setelah seluruh _array_ diperiksa, ia mengembalikan **`-1`**.

#### `autentikasipin(int userid, int sandi)`

| Parameter | Tipe Data | Fungsi                                               |
| :-------- | :-------- | :--------------------------------------------------- |
| `userid`  | `int`     | Indeks pengguna yang didapat dari `autentikasiuser`. |
| `sandi`   | `int`     | PIN/sandi yang dimasukkan oleh pengguna.             |

**Cara Kerja:**

- Secara langsung membandingkan `sandi` yang dimasukkan dengan nilai yang tersimpan di _array_ global `pin` pada posisi `userid`.
- Mengembalikan `true` jika PIN cocok, dan `false` jika tidak cocok.

#### `cekuser()`

**Fungsi:** Mengatur alur login **Nama** dan **PIN** serta mengatur variabel global `userid` dan `sandi`.

**Cara Kerja:**

1.  Meminta pengguna memasukkan nama dan memanggil `autentikasiuser()`. Hasilnya disimpan ke variabel global `userid`.
2.  Jika `userid` ditemukan (`!= -1`):
    - Meminta PIN dan menyimpannya ke variabel global `sandi`.
    - Memanggil `autentikasipin()` untuk memverifikasi.
    - Jika PIN benar, menampilkan pesan selamat datang. Jika gagal, menampilkan "sandi salah".
3.  Jika `userid` tidak ditemukan, menampilkan "user tidak ada".
    > 💡 **Catatan:** Fungsi ini sering dipanggil oleh fungsi transaksi lainnya untuk memastikan pengguna telah login. Data otentikasi pengguna (`userid` dan `sandi`) disimpan secara global.

---

### 3\. Fungsi Utama Transaksi

Ini adalah fungsi-fungsi yang menjalankan logika bisnis utama dari sistem ATM.

#### `ceksaldo()`

**Fungsi:** Menampilkan saldo terkini pengguna.

**Cara Kerja:**

1.  Memanggil `cekuser()` untuk alur otentikasi.
2.  Setelah `cekuser()` selesai, ia memverifikasi ulang otentikasi menggunakan `autentikasipin(userid, sandi)`.
3.  Jika otentikasi berhasil, ia mencetak nilai `saldo` dari _array_ global `saldo` menggunakan `userid` sebagai indeks.

#### `setortunai()`

**Fungsi:** Menambah saldo pengguna yang terotentikasi.

**Cara Kerja:**

1.  Memanggil `cekuser()` untuk otentikasi.
2.  Meminta jumlah setoran (`setor`).
3.  Melakukan validasi: jika jumlah setor `< 0`, menampilkan pesan error.
4.  Jika valid, ia memperbarui saldo: `saldo[userid] = saldo[userid] + setor;`.
5.  Menampilkan saldo baru.

#### `tariktunai()`

**Fungsi:** Mengurangi saldo pengguna yang terotentikasi.

**Cara Kerja:**

1.  Memanggil `cekuser()` untuk otentikasi.
2.  Meminta jumlah penarikan (`tarik`).
3.  Melakukan validasi: jika jumlah tarik `< 0` **ATAU** jumlah tarik `>` saldo yang tersedia, menampilkan pesan error.
4.  Jika valid, ia memperbarui saldo: `saldo[userid] = saldo[userid] - tarik;`.
5.  Menampilkan saldo baru.

#### `kirimtunai()`

**Fungsi:** Mengirim dana dari pengguna yang sedang login ke pengguna lain.

**Cara Kerja:**

1.  Memanggil `cekuser()` untuk otentikasi pengirim.
2.  Meminta nama penerima dan mencari indeksnya (`penerima`) menggunakan `autentikasiuser()`.
3.  Melakukan **Validasi Penerima**:
    - Pengirim (`userid`) tidak boleh sama dengan penerima (`penerima`).
    - Penerima harus ada di sistem (`penerima != -1`).
4.  Jika validasi penerima berhasil, ia meminta jumlah kiriman (`kirim`).
5.  Melakukan **Validasi Dana**: jumlah kirim `< 0` atau `>` saldo pengirim (`saldo[userid]`).
6.  Jika semua validasi berhasil, ia:
    - Mengurangi saldo pengirim: `saldo[userid] = saldo[userid] - kirim;`
    - Menambah saldo penerima: `saldo[penerima] = saldo[penerima] + kirim;`
7.  Menampilkan saldo pengirim setelah transfer.

---

### 4\. `main(int argc, char const *argv[])` (Logika Utama)

**Fungsi:** Mengontrol alur program dan menampilkan menu utama.

**Cara Kerja:**

1.  Menggunakan _loop_ **`while(true)`** (loop tak terbatas) untuk terus menampilkan menu.
2.  Menampilkan menu opsi (Cek Saldo, Setor, Tarik, Kirim, Exit) menggunakan `cout`.
3.  Membaca `pilihan` pengguna menggunakan `cin`.
4.  Menggunakan serangkaian pernyataan **`else if`** untuk memanggil fungsi transaksi yang sesuai (`ceksaldo()`, `setortunai()`, dll.) berdasarkan pilihan.
5.  Jika pilihan adalah **`5` (Exit)**, perintah `break` akan menghentikan _loop_ `while(true)`, dan program berakhir.

---

## 🛠️ Peningkatan Potensial

- **Keamanan PIN:** PIN disimpan dalam _array_ integer di kode sumber (**tidak aman**). Dalam sistem nyata, PIN harus di-_hash_ (misalnya menggunakan fungsi kriptografi) dan disimpan.
- **Struktur Data:** Menggunakan struktur (`struct`) atau kelas (`class`) terpisah untuk objek `User` alih-alih _array_ terpisah akan membuat data lebih terstruktur dan berorientasi objek (konsep **OOP**).
- **Validasi Input:** Tidak ada penanganan kesalahan (`try-catch` atau pemeriksaan `cin.fail()`) untuk input non-angka. Jika pengguna memasukkan huruf saat diminta memasukkan PIN atau jumlah, program dapat mengalami _crash_ atau berperilaku tidak terduga.
- **Efisiensi Otentikasi:** Saat ini, hampir semua fungsi memanggil `cekuser()`, yang berarti pengguna harus memasukkan nama dan PIN setiap kali melakukan transaksi. Dalam aplikasi nyata, otentikasi hanya dilakukan sekali di awal, dan `userid` dipertahankan untuk semua transaksi berikutnya.
