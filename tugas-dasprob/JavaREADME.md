kTentu\! Saya akan menyempurnakan dokumentasi sebelumnya dengan menambahkan *snippet* kode yang relevan di setiap bagiannya. Dengan cara ini, pembaca dapat melihat langsung baris kode mana yang sedang dijelaskan. 💡

Saya akan menggunakan bahasa Indonesia dan format yang mudah dicerna.

-----

# 📖 Dokumentasi Kode: ATM Sederhana (Java)

Proyek ini adalah simulasi dasar sistem ATM (Anjungan Tunai Mandiri) yang ditulis dalam bahasa pemrograman Java. Dokumentasi ini bertujuan untuk menjelaskan setiap bagian kode, sehingga mudah dipahami bahkan oleh pemula.

## 1\. Persiapan Awal dan Impor Alat (`import`)

Bagian ini adalah langkah "memanggil" alat-alat yang dibutuhkan agar program dapat berjalan.

| Barisan Kode | Alat yang Dipanggil | Penjelasan & Kegunaan |
| :--- | :--- | :--- |
| `import java.util.Scanner;` | **Scanner** | Alat untuk **membaca masukan** dari pengguna (ketikan di keyboard), misalnya saat memasukkan PIN atau nominal uang. |
| `import java.util.ArrayList;` | **ArrayList** | "Kotak Penyimpanan Fleksibel" untuk menyimpan daftar nama, PIN, dan saldo yang ukurannya bisa bertambah atau berkurang. |
| `import java.util.Arrays;` | **Arrays** | Alat bantu untuk membuat daftar awal yang kemudian dimasukkan ke dalam `ArrayList`. |

<br>

## 2\. Kelas `atm` (Gudang Data & Logika Transaksi)

Ini adalah "otak" dari sistem ATM. Semua data pengguna disimpan di sini, dan semua aksi transaksi dijalankan.

### 2.1. Penyimpanan Data Awal

Ini adalah tempat data-data pengguna (contoh) disimpan dalam *array list*.

```java
private static ArrayList<String> user = new ArrayList<>(Arrays.asList("padli", "risca", "apif"));
private static ArrayList<Integer> pin = new ArrayList<>(Arrays.asList(111, 222, 333));
private static ArrayList<Long> saldo = new ArrayList<>(Arrays.asList(1000000L, 2000000L, 3000000L));
private static int userid;
```

  * **`user`, `pin`, `saldo`**: Tiga daftar ini adalah penyimpanan utama. Penting untuk diingat bahwa setiap data (nama, PIN, dan saldo) berada di **nomor urut (index)** yang sama. Contoh: Padli (Index 0), PIN 111 (Index 0), Saldo Rp 1.000.000 (Index 0).
  * **`userid`**: Menyimpan **nomor urut** pengguna yang sedang *login*. Ini akan memastikan transaksi hanya terjadi pada akun yang benar.

<br>

### 2.2. Fungsi Autentikasi (Pengecekan Identitas)

Fungsi-fungsi ini memastikan hanya pengguna yang sah yang bisa mengakses menu transaksi.

#### 🔎 `autentikasiuser(String username)`: Mencari Nama Pengguna

Fungsi ini mencari apakah nama yang dimasukkan ada di dalam daftar `user`.

```java
public static int autentikasiuser(String username) {
    for (int i = 0; i < user.size(); i++) {
        if (username.equals(user.get(i))) {
            return i; // Nama ditemukan, kembalikan nomor urutnya (index)
        }
    }
    return -1; // Nama tidak ditemukan
}
```

#### 🔒 `autentikasipin(int userpin)`: Mencocokkan PIN

Fungsi ini membandingkan PIN yang diketik dengan PIN yang tersimpan di *index* (`userid`) pengguna yang baru saja diverifikasi.

```java
public static boolean autentikasipin(int userpin) {
    // Membandingkan PIN yang dimasukkan dengan PIN yang tersimpan pada userid saat ini
    return userpin == pin.get(userid); 
}
```

#### 🚪 `login()`: Proses Masuk

Fungsi ini menggabungkan kedua autentikasi di atas untuk memungkinkan pengguna masuk.

```java
public static void login() {
    while (true) {
        // ... (Meminta input nama dan PIN)
        userid = autentikasiuser(username); // Cek nama
        if (userid == -1) {
            System.out.println("user tidak dapat ditemukan");
            continue;
        } else {
            // ... (Meminta input PIN)
            if (autentikasipin(userpin)) { // Cek PIN
                System.out.println("Halo " + user.get(userid));
                break; // Berhasil login
            } else {
                System.out.println("pin atau username salah");
                continue;
            }
        }
    }
}
```

<br>

### 2.3. Fungsi Manajemen Akun

#### ✍️ `adduser()`: Membuat Akun Baru (Sign In)

Fungsi ini memproses pendaftaran pengguna baru ke dalam sistem.

```java
public static void adduser() {
    while (true) {
        // ... (Meminta input nama baru)
        int ceknama = autentikasiuser(nama);
        if (ceknama == -1) { // Nama belum ada
            // ... (Meminta buat sandi dan konfirmasi sandi)
            if (sandi1 != sandi2) {
                System.out.println("sandi tidak sama");
                continue;
            } else {
                user.add(nama);    // Tambahkan nama ke daftar
                pin.add(sandi1);   // Tambahkan PIN ke daftar
                saldo.add(0L);     // Beri saldo awal 0
                System.out.println("user berhasil di buat");
                break;
            }
        } else {
            System.out.println("nama sudah ada");
        }
    }
}
```

<br>

### 2.4. Fungsi Transaksi Keuangan

Fungsi-fungsi ini melakukan perhitungan dan memodifikasi data saldo.

#### 🗒️ `ceksaldo()`: Menampilkan Saldo

Fungsi yang paling sederhana, hanya menampilkan data saldo.

```java
public static void ceksaldo() {
    System.out.println("saldomu adalah : " + saldo.get(userid));
}
```

#### 📥 `depo()`: Setor Tunai (Deposit)

Menambahkan nominal ke saldo pengguna saat ini.

```java
public static void depo() {
    while (true) {
        // ... (Meminta nominal deposit)
        if (depo <= 0) { // Cek validasi
            System.out.println("masukan nomor yang valid");
            continue;
        } else {
            long saldo_baru = saldo.get(userid) + depo; // Hitung saldo baru
            saldo.set(userid, saldo_baru);              // Update (ganti) saldo lama dengan saldo baru
            System.out.println("sukses");
            break;
        }
    }
}
```

#### 📤 `withdraw()`: Tarik Tunai

Mengurangi saldo pengguna saat ini.

```java
public static void withdraw() {
    while (true) {
        // ... (Meminta nominal withdraw)
        // Cek validasi: nominal tidak negatif DAN nominal tidak melebihi saldo
        if (withdraw < 0 && withdraw > saldo.get(userid)) { 
            System.out.println("masukan nomor yang valid");
            continue;
        } else {
            long saldo_baru = saldo.get(userid) - withdraw; // Hitung saldo baru
            saldo.set(userid, saldo_baru);                  // Update (ganti) saldo lama
            System.out.println("sukses");
            break;
        }
    }
}
```

#### 💸 `transfer()`: Kirim Tunai

Memindahkan sejumlah uang dari akun pengirim ke akun penerima.

```java
public static void transfer() {
    while (true) {
        // ... (Meminta nama penerima)
        int penerima = autentikasiuser(cekpenerima);
        
        if (penerima == -1) { // Penerima tidak ditemukan
            System.out.println("user tidak ada");
            continue;
        } else if (penerima == userid) { // Cek jangan transfer ke diri sendiri
            System.out.println("tidak dapat mengirim ke diri sendiri");
            continue;
        } else {
            // ... (Meminta jumlah transfer)
            // Cek validasi: nominal tidak negatif ATAU tidak melebihi saldo pengirim
            if (transfer < 0 || transfer > saldo.get(userid)) {
                System.out.println("masukan nomor yang valid");
                continue;
            } else {
                // UPDATE SALDO PENERIMA
                long terima = saldo.get(penerima) + transfer;
                saldo.set(penerima, terima); 
                
                // UPDATE SALDO PENGIRIM
                long beri = saldo.get(userid) - transfer;
                saldo.set(userid, beri);
                break;
            }
        }
    }
}
```

<br>

-----

## 3\. Kelas `ATMsimple` (Antarmuka Utama)

Kelas ini bertugas menampilkan menu di awal program dan mengatur alur masuk ke dalam sistem.

#### ▶️ `main(String[] args)`: Program Dimulai

Fungsi utama yang menjalankan menu awal (Login, Sign In, Exit).

```java
public static void main(String[] args) {
    while (true) {
        // ... (Barisan kode untuk mencetak tampilan menu awal)
        
        int pilihan = input.nextInt(); // Menerima pilihan
        input.nextLine();              // Membersihkan input
        
        if (pilihan == 1) {
            atm.login(); // Masuk
            atm.menu();  // Tampilkan menu transaksi setelah login berhasil
        } else if (pilihan == 2) {
            atm.adduser(); // Buat akun baru
            continue;
        } else if (pilihan == 3) {
            break; // Keluar dari program
        } else {
            System.out.println("masukan nomber valid");
            continue;
        }
    }
}
```

<br>

Dokumentasi ini sekarang menyediakan konteks visual dan penjelasan langkah demi langkah untuk setiap blok kode, yang sangat membantu bagi siapa pun yang ingin memahami logika program ATM sederhana Anda. Selamat belajar\! 🎉
