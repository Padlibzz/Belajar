#include <iostream>

using namespace std;

string user[] ={"padli","risca","apif"};
int pin[] ={111,222,333};
double saldo[] ={1000000,2000000,3000000};
int userid;
int sandi;
int userlength = sizeof(user) / sizeof(user[0]);

int autentikasiuser(string nama);
bool autentikasipin(int userid,int sandi);
void cekuser();
void ceksaldo();
void setortunai();
void tariktunai();
void kirimtunai();

int main(int argc, char const *argv[])
{
	int pilihan = 0;
		while(true){
			cout << "------------------------------\n";
			cout << "|=======ATM SEDERHANA========|\n";
			cout << "|1.Cek Saldo                 |\n";
			cout << "|2.Setor Tunai               |\n";
			cout << "|3.Tarik Tunai               |\n";
			cout << "|4.Kirim Tunai               |\n";
			cout << "|5.Exit                      |\n";
			cout << "------------------------------\n";
			
			cin >> pilihan;
			cout << "\n";

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

int autentikasiuser(string nama){
	for (int i = 0; i < userlength; i++) {
		if (nama == user[i]) {
			return i;
		}
	}
	return -1;
}

bool autentikasipin(int userid,int sandi){
		return sandi == pin[userid];
	}
void cekuser(){
	string userinput;
	cout << "masukan nama : ";
	cin >> userinput;
	cout << "\n";
	userid = autentikasiuser(userinput);
	if (userid != -1) {
		cout << "--------------------------------\n"; 
		cout << "Masukan Sandi : "; 
		cin >> sandi;
		cout << "\n";
		if (autentikasipin(userid,sandi)) {
			cout << "--------------------------------\n";
           	cout << "Selamat datang, " + user[userid] << endl;
		}else{
				cout << "sandi salah\n";
		}

	}else{
			cout << "user tidak ada\n";
		}
}

void ceksaldo(){
		cekuser();
		if (autentikasipin(userid, sandi)){
			cout << "saldo anda adalah : " << saldo[userid] << "\n";
		}
	}
void setortunai(){
	double setor;
	cekuser();
	cout << "Masukan jumlah yang mau di setor : \n";
	cin >> setor;
	if (setor < 0) {
		cout << "masukan nominal yang benar\n";
	}else{
		saldo[userid] = saldo[userid] + setor;
		cout << "saldo anda jadi : " << saldo[userid] << "\n";
	}		
}

void tariktunai(){
		double tarik;
		cekuser();
		cout << "Masukan jumlah yang mau di tarik : \n";
		cin >> tarik;
		if (tarik < 0 || tarik > saldo[userid]) {
			cout << "masukan nominal yang benar\n";
		}else{
			saldo[userid] = saldo[userid] - tarik;
			cout << "saldo anda jadi : " << saldo[userid] << "\n";
		}
	}

void kirimtunai(){
		double kirim;
		string penerimainput;
		cekuser();
		cout << "mau kirim kemana : ";
		cin >> penerimainput;
		cout << "\n";
		int penerima = autentikasiuser(penerimainput);
		
		if(penerima == userid) {
			cout << "Tidak bisa kirim ke diri sendiri \n";
			return;
		}
		if(penerima == -1){
			cout << "user tidak ada\n";
			return;
		}
		if(penerima != -1 && penerima != userid){
			cout << "Jumlah yang di kirim : ";
			cin >> kirim;
			cout << "\n";
			if (kirim < 0 || kirim > saldo[userid]) {
				cout << "masukan nominal yang benar\n";
			}else{
				saldo[userid] = saldo[userid] - kirim;
				saldo[penerima] = saldo[penerima] + kirim;
				cout << "saldo anda jadi : " << saldo[userid] << "\n";
			}
		}
	}	