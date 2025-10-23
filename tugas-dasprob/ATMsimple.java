import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ATMsimple{
	public static void main(String[] args) {
		logikaATM.menu();

	}
}

class logikaATM{
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<String> user = new ArrayList<>(Arrays.asList("padli","risca","apif"));
    private static ArrayList<Integer> pin = new ArrayList<>(Arrays.asList(111,222,333));
    private static ArrayList<Long> saldo = new ArrayList<>(Arrays.asList(1000000L,2000000L,3000000L));
	private static int userid;
	private static int sandi;

	public static void tambahuser(){
		System.out.print("masukan nama : ");
		String newuser = input.nextLine();
		System.out.print("buat sandi pin : ");
		int newpin = input.nextInt();
		long newsaldo = 0;
		user.add(newuser);
		pin.add(newpin);
		saldo.add(newsaldo);
	}

	public static int autentikasiuser(String nama){
		for (int i = 0; i < user.size(); i++) {
			if (nama.equals(user.get(i))) {
				return i;
			}
		}
		return -1;
	}

	public static boolean autentikasipin(int userid,int sandi){
		return sandi == pin.get(userid);
	}

	public static void cekuser(){
		System.out.print("masukan nama : ");
		String userinput = input.nextLine();
		userid = autentikasiuser(userinput);
		if (userid != -1) {
			System.out.println("--------------------------------");
			System.out.println("Masukan Sandi");
			sandi = input.nextInt();
			if (autentikasipin(userid,sandi)) {
				System.out.println("--------------------------------");
            	System.out.println("Selamat datang, " + user.get(userid));
			}else{
				System.out.println("sandi salah");
			}
		}else{
			System.out.println("user tidak ada");
		}

	}
	public static void ceksaldo(){
		cekuser();
		if (autentikasipin(userid,sandi)) {
			System.out.println("saldo anda adalah : " + saldo.get(userid));
		}
	}
	public static void setortunai(){
		cekuser();
		System.out.print("Masukan jumlah yang mau di setor : ");
		long setor = input.nextLong();
		if (setor < 0) {
			System.out.println("masukan nominal yang benar");
		}else{
			long saldobaru = saldo.get(userid) + setor;
			saldo.set(userid, saldobaru);
			System.out.println("saldo anda jadi : " + saldo.get(userid));
		}
		
	}

	public static void tariktunai(){
		cekuser();
		System.out.print("Masukan jumlah yang mau di tarik : ");
		long tarik = input.nextLong();
		if (tarik < 0 || tarik > saldo.get(userid)) {
			System.out.println("masukan nominal yang benar");
		}else{
			long tarikbaru = saldo.get(userid) - tarik;
			saldo.set(userid, tarikbaru);
			System.out.println("saldo anda jadi : " + saldo.get(userid));
		}
	}

	public static void kirimtunai(){
		cekuser();
		System.out.print("mau kirim kemana : ");
		input.nextLine();
		String penerimainput = input.nextLine();
		System.out.println();
		int penerima = autentikasiuser(penerimainput);
		
		if(penerima == userid) {
			System.out.println("Tidak bisa kirim ke diri sendiri");
			return;
		}
		if(penerima == -1){
			System.out.println("user tidak ada");
			return;
		}
		if(penerima != -1 && penerima != userid){
			System.out.print("Jumlah yang di kirim : ");
			long kirim = input.nextLong();
			if (kirim < 0 || kirim > saldo.get(userid)) {
				System.out.println("masukan nominal yang benar");
			}else{
				long saldok = saldo.get(userid) - kirim;
				saldo.set(userid, saldok);
				long saldom = saldo.get(penerima) + kirim;
				saldo.set(penerima, saldom);
				System.out.println("saldo anda jadi : " + saldo.get(userid));
			}
		}
	}	

	public static void menu() {
		int pilihan = 0;
		while(true){
			System.out.println("  ________________________________________________________   ");
            System.out.println("//========================================================\\ ");
            System.out.println("||             [ ] BANK DIGITAL INDONESIA [ ]             || ");
            System.out.println("||________________________________________________________|| ");
            System.out.println("||                                                        || ");
            System.out.println("||           **SELAMAT DATANG DI ATM SEDERHANA**          || ");
            System.out.println("||                                                        || ");
            System.out.println("||========================================================|| ");
            System.out.println("|| 1. Cek Saldo                |[      ]| [   ] |         || ");
            System.out.println("|| 2. Setor Tunai              |[______]| [   ] |         || ");
            System.out.println("|| 3. Tarik Tunai              | [123]  | [___] |         || ");
            System.out.println("|| 4. Kirim Tunai              | [456]  | [   ] |         || "); 
            System.out.println("|| 5. Buat Akun                | [789]  | [   ] |         || ");
            System.out.println("|| 6. **EXIT**                 | [ *#]  |       |         || ");
            System.out.println("||========================================================|| ");
            System.out.println("\\_______________________________________________________// ");
            System.out.println(" \\_____________________________________________________//  ");
			
			pilihan = input.nextInt();
			input.nextLine();

			if (pilihan == 6){
				break;	
				}else if(pilihan == 1){
					ceksaldo();
				}else if(pilihan == 2){
					setortunai();
				}else if(pilihan == 3){
					tariktunai();
				}else if(pilihan == 4){
					kirimtunai();
				}else if(pilihan == 5){
					tambahuser();
				}					
			}
		}
	}
