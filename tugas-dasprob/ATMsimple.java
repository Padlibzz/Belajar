import java.util.Scanner;

public class ATMsimple{
	private static Scanner input = new Scanner(System.in);
	private static String[] user ={"padli","risca","apif"};
	private static int[] pin ={111,222,333};
	private static double[] saldo ={1000000,2000000,3000000};
	private static int userid;
	private static int sandi;

	public static int autentikasiuser(String nama){
		for (int i = 0; i < user.length; i++) {
			if (nama.equals(user[i])) {
				return i;
			}
		}
		return -1;
	}

	public static boolean autentikasipin(int userid,int sandi){
		return sandi == pin[userid];
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
            	System.out.println("Selamat datang, " + user[userid]);
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
			System.out.println("saldo anda adalah : " + saldo[userid]);
		}
	}
	public static void setortunai(){
		cekuser();
		System.out.print("Masukan jumlah yang mau di setor : ");
		double setor = input.nextDouble();
		if (setor < 0) {
			System.out.println("masukan nominal yang benar");
		}else{
			saldo[userid] = saldo[userid] + setor;
			System.out.println("saldo anda jadi : " + saldo[userid]);
		}
		
	}

	public static void tariktunai(){
		cekuser();
		System.out.print("Masukan jumlah yang mau di tarik : ");
		double tarik = input.nextDouble();
		if (tarik < 0 || tarik > saldo[userid]) {
			System.out.println("masukan nominal yang benar");
		}else{
			saldo[userid] = saldo[userid] - tarik;
			System.out.println("saldo anda jadi : " + saldo[userid]);
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
			double kirim = input.nextDouble();
			if (kirim < 0 || kirim > saldo[userid]) {
				System.out.println("masukan nominal yang benar");
			}else{
				saldo[userid] = saldo[userid] - kirim;
				saldo[penerima] = saldo[penerima] + kirim;
				System.out.println("saldo anda jadi : " + saldo[userid]);
			}
		}
	}	

	public static void main(String[] args) {
		int pilihan = 0;
		while(true){
			System.out.println("------------------------------");
			System.out.println("|=======ATM SEDERHANA========|");
			System.out.println("|1.Cek Saldo                 |");
			System.out.println("|2.Setor Tunai               |");
			System.out.println("|3.Tarik Tunai               |");
			System.out.println("|4.Kirim Tunai               |");
			System.out.println("|5.Exit                      |");
			System.out.println("------------------------------");
			
			pilihan = input.nextInt();
			input.nextLine();

			if (pilihan == 5){
				break;	
				}else if(pilihan == 1){
					ceksaldo();
				}else if(pilihan == 2){
					setortunai();
				}else if(pilihan == 3){
					tariktunai();
				}else if(pilihan == 4){
					kirimtunai();
				}			
			}
		}
	}