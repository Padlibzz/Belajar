#include <iostream>
#include <vector>

using namespace std;

class ATM {
private:
    vector<string> user = {"padli", "risca", "apif"};
    vector<int> pin = {111, 222, 333};
    vector<long> saldo = {1000000, 2000000, 3000000};
    int userid;
    int sandi;

public:
    void jalankanMenu();
    int autentikasiuser(string nama);
    bool autentikasipin(int userid, int sandi);
    void cekuser();
    void ceksaldo();
    void setortunai();
    void tariktunai();
    void kirimtunai();
};

int main() {
    ATM myAtm;
    myAtm.jalankanMenu();
    
    return 0;
}

void ATM::jalankanMenu() {
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

int ATM::autentikasiuser(string nama){
    for (int i = 0; i < user.size(); i++) {
        if (nama == user[i]) {
            return i;
        }
    }
    return -1;
}

bool ATM::autentikasipin(int userid,int sandi){
    if (userid >= 0 && userid < user.size()) {
        return sandi == pin[userid];
    }
    return false;
}

void ATM::cekuser(){
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
            userid = -1; 
            sandi = 0;
        }

    }else{
        cout << "user tidak ada\n";
    }
}

void ATM::ceksaldo(){
    cekuser();
    if (userid != -1 && autentikasipin(userid, sandi)){
        cout << "saldo anda adalah : " << saldo[userid] << "\n";
    }
}

void ATM::setortunai(){
    long setor;
    cekuser();
    if (userid == -1) return;

    cout << "Masukan jumlah yang mau di setor : \n";
    cin >> setor;
    if (setor <= 0) {
        cout << "masukan nominal yang benar\n";
    }else{
        saldo[userid] = saldo[userid] + setor;
        cout << "saldo anda jadi : " << saldo[userid] << "\n";
    }
}

void ATM::tariktunai(){
    long tarik;
    cekuser();
    if (userid == -1) return;
    
    cout << "Masukan jumlah yang mau di tarik : \n";
    cin >> tarik;
    if (tarik <= 0 || tarik > saldo[userid]) {
        cout << "masukan nominal yang benar atau saldo tidak mencukupi\n";
    }else{
        saldo[userid] = saldo[userid] - tarik;
        cout << "saldo anda jadi : " << saldo[userid] << "\n";
    }
}

void ATM::kirimtunai(){
    long kirim;
    string penerimainput;
    cekuser();
    if (userid == -1) return;

    cout << "mau kirim kemana : ";
    cin >> penerimainput;
    cout << "\n";
    int penerima = autentikasiuser(penerimainput);
    
    if(penerima == userid) {
        cout << "Tidak bisa kirim ke diri sendiri \n";
        return;
    }
    if(penerima == -1){
        cout << "user penerima tidak ada\n";
        return;
    }
    
    cout << "Jumlah yang di kirim : ";
    cin >> kirim;
    cout << "\n";
    
    if (kirim <= 0 || kirim > saldo[userid]) {
        cout << "masukan nominal yang benar atau saldo tidak mencukupi\n";
    }else{
        saldo[userid] = saldo[userid] - kirim;
        saldo[penerima] = saldo[penerima] + kirim;
        cout << "Berhasil kirim ke " << user[penerima] << ".\n";
        cout << "saldo anda jadi : " << saldo[userid] << "\n";
    }
}