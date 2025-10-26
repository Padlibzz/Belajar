import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ATMsimple {
    
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("+--------------------------------------------------------+");
            System.out.println("|                                                        |");
            System.out.println("|             [ ] BANK DIGITAL KELOMPOK 3 [ ]            |");
            System.out.println("|________________________________________________________|");
            System.out.println("|                                                        |");
            System.out.println("|           **SELAMAT DATANG DI ATM SEDERHANA**          |");
            System.out.println("|                                                        |");
            System.out.println("|========================================================|");
            System.out.println("|                             | ______ |       |         |");
            System.out.println("| 1. login                    |[      ]| [   ] |         |");
            System.out.println("| 2. singin                   |[______]| [   ] |         |");
            System.out.println("| 3. exit                     | [123]  | [___] |         |");
            System.out.println("+--------------------------------------------------------+");
            int pilihan = input.nextInt();
            input.nextLine();
            if (pilihan == 1) {
                atm.login();
                atm.menu();
            } else if (pilihan == 2) {
                atm.adduser();
                continue;
            } else if (pilihan == 3) {
                break;
            } else {
                System.out.println("masukan nomber valid");
                continue;
            }
        }

    }
}

class atm {
    private static ArrayList<String> user = new ArrayList<>(Arrays.asList("padli", "risca", "apif"));
    private static ArrayList<Integer> pin = new ArrayList<>(Arrays.asList(111, 222, 333));
    private static ArrayList<Long> saldo = new ArrayList<>(Arrays.asList(1000000L, 2000000L, 3000000L));
    private static int userid;

    public static Scanner input = new Scanner(System.in);

    public static void menu() {
        while (true) {
            System.out.println("+--------------------------------------------------------+");
            System.out.println("|                                                        |");
            System.out.println("|             [ ] BANK DIGITAL KELOMPOK 3 [ ]            |");
            System.out.println("|________________________________________________________|");
            System.out.println("|                                                        |");
            System.out.println("|           **SELAMAT DATANG DI ATM SEDERHANA**          |");
            System.out.println("|                                                        |");
            System.out.println("|========================================================|");
            System.out.println("|                             | ______ |       |         |");
            System.out.println("| 1. Cek Saldo                |[      ]| [   ] |         |");
            System.out.println("| 2. Setor Tunai              |[______]| [   ] |         |");
            System.out.println("| 3. Tarik Tunai              | [123]  | [___] |         |");
            System.out.println("| 4. Kirim Tunai              | [456]  | [   ] |         |"); 
            System.out.println("| 5. Buat Akun                | [789]  | [   ] |         |");
            System.out.println("| 6. **EXIT**                 | [ *#]  |       |         |");
            System.out.println("+--------------------------------------------------------+");

            int pilihan = input.nextInt();
            input.nextLine();
            if (pilihan == 6) {
                break;
            } else if (pilihan == 1) {
                ceksaldo();
            } else if (pilihan == 2) {
                depo();
            } else if (pilihan == 3) {
                withdraw();
            } else if (pilihan == 4) {
                transfer();
            } else if (pilihan == 5) {
                adduser();
            }
        }

    }

    public static int autentikasiuser(String username) {
        for (int i = 0; i < user.size(); i++) {
            if (username.equals(user.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static boolean autentikasipin(int userpin) {

        return userpin == pin.get(userid);
    }

    public static void login() {
        while (true) {
            System.out.print("Masukan nama : ");
            String username = input.nextLine();
            userid = autentikasiuser(username);
            if (userid == -1) {
                System.out.println("user tidak dapat ditemukan");
                continue;
            }else{
                System.out.print("Masukan pin : ");
                int userpin = input.nextInt();
                input.nextLine();
                if (autentikasipin(userpin)) {
                    System.out.println("Halo " + user.get(userid));
                    break;
                }else{
                    System.out.println("pin atau username salah");
                    continue;
                }
            }
        }
    }

    public static void adduser() {
        while (true) {
            System.out.print("masukan nama : ");
            String nama = input.nextLine();
            int ceknama = autentikasiuser(nama);
            if (ceknama == -1) {
                System.out.print("buat sandi : ");
                int sandi1 = input.nextInt();
                input.nextLine();
                System.out.print("konfirmasi sandi : ");
                int sandi2 = input.nextInt();
                input.nextLine();
                if (sandi1 != sandi2) {
                    System.out.println("sandi tidak sama");
                    continue;
                } else {
                    user.add(nama);
                    pin.add(sandi1);
                    saldo.add(0L);
                    System.out.println("user berhasil di buat");
                    break;
                }
            } else {
                System.out.println("nama sudah ada");
            }
        }
    }

    public static void ceksaldo() {
        System.out.println("saldomu adalah : " + saldo.get(userid));

    }

    public static void depo() {
        while (true) {
            System.out.print("masukan nominal deposit : ");
            long depo = input.nextLong();
            if (depo <= 0) {
                System.out.println("masukan nomor yang valid");
                continue;
            } else {
                depo = saldo.get(userid) + depo;
                saldo.set(userid, depo);
                System.out.println("sukses");
                break;
            }    
        }
        
    }

    public static void withdraw() {
        while (true) {
            System.out.print("masukan nominal withdraw : ");
            long withdraw = input.nextLong();
            if (withdraw < 0 && withdraw > saldo.get(userid)) {
                System.out.println("masukan nomor yang valid");
                continue;
            } else {
                withdraw = saldo.get(userid) - withdraw;
                saldo.set(userid, withdraw);
                System.out.println("sukses");
                break;
            }    
        }
    }

    public static void transfer() {
        while (true) {
            System.out.println("masukan nama penerima");
            String cekpenerima = input.nextLine();
            int penerima = autentikasiuser(cekpenerima);
            if (penerima == -1) {
                System.out.println("user tidak ada");
                continue;
            } else if (penerima == userid) {
                System.out.println("tidak dapat mengirim ke diri sendiri");
                continue;
            } else {
                System.out.print("masukan jumlah transfer : ");
                long transfer = input.nextLong();
                input.nextLine();
                if (transfer < 0 || transfer > saldo.get(userid)) {
                    System.out.println("masukan nomor yang valid");
                    continue;
                } else {
                    long terima = saldo.get(penerima) + transfer;
                    saldo.set(penerima, terima);
                    long beri = saldo.get(userid) - transfer;
                    saldo.set(userid, beri);
                    break;
                }
            }    
        }
        
    }
}
