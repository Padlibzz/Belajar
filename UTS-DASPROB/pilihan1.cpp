#include <iostream>
#include <vector>
using namespace std;

class Data {
private:
    string nama,sekolah,gender;
    int tb;
    float bb;

public:
    void setnama(string nama){ this->nama = nama; }
    void setsekolah(string sekolah){ this->sekolah = sekolah; }
    void setgender(string gender){ this->gender = gender; }
    void settb(int tb){ this->tb = tb; }
    void setbb(float bb){ this->bb = bb; }

    string getnama(){ return nama; }
    string getsekolah(){ return sekolah; }
    string getgender(){ return gender; }
    int gettb(){ return tb; }
    float getbb(){ return bb; }
};

class DataManif {
private:
    vector<Data> daftar;

public:
    void addData(){
        string nama, sekolah, gender;
        int tb;
        float bb;

        cout << "Masukkan nama     : ";
        cin >> nama;

        do {
            cout << "Masukkan sekolah (unsap1 / unsap2): ";
            cin >> sekolah;
            if (sekolah != "unsap1" && sekolah != "unsap2") {
                cout << "Input tidak valid! Harus 'unsap1' atau 'unsap2'. Coba lagi.\n";
            }
        } while (sekolah != "unsap1" && sekolah != "unsap2");

        do {
            cout << "Masukkan gender (laki-laki / perempuan): ";
            cin >> gender;
            if (gender != "laki-laki" && gender != "perempuan") {
                cout << "Input tidak valid! Harus 'laki-laki' atau 'perempuan'. Coba lagi.\n";
            }
        } while (gender != "laki-laki" && gender != "perempuan");

        cout << "Masukkan tinggi badan (cm): ";
        cin >> tb;
        cout << "Masukkan berat badan (kg): ";
        cin >> bb;

        Data data;
        data.setnama(nama);
        data.setsekolah(sekolah);
        data.setgender(gender);
        data.settb(tb);
        data.setbb(bb);

        daftar.push_back(data);
        cout << "Data berhasil ditambahkan!\n";
    }

    
    float bbideal(string gender, int tb){
        if (gender == "laki-laki"){
            float a = (tb - 100) * 10.0 / 100.0;
            return (tb - 100) - a;
        }
        if (gender == "perempuan"){
            float a = (tb - 100) * 15.0 / 100.0;
            return (tb - 100) - a;
        }
        return -1;
    }

    void LookData(){
        string nama;
        cout << "Masukkan nama yang ingin dicari: ";
        cin >> nama;

        bool cek = false;
        for (auto &i : daftar){
            if (nama == i.getnama()){
                cout << "\n=== DATA DITEMUKAN ===\n";
                cout << "Nama      : " << i.getnama() << endl;
                cout << "Sekolah   : " << i.getsekolah() << endl;
                cout << "Gender    : " << i.getgender() << endl;
                cout << "Tinggi    : " << i.gettb() << " cm" << endl;
                cout << "Berat     : " << i.getbb() << " kg" << endl;
                cout << "BB Ideal  : " << bbideal(i.getgender(), i.gettb()) << " kg\n";
                cek = true;
                break;
            }
        }
        if (!cek)
            cout << "Nama tidak terdata.\n";
    }

    void RataRataSekolah(){
        if (daftar.empty()){
            cout << "Belum ada data yang dimasukkan.\n";
            return;
        }

        cout << "\n=== RATA-RATA PER SEKOLAH DAN GENDER ===\n";

        int totalTB = 0; float totalBB = 0, totalIdeal = 0; int count = 0;

        totalTB = totalBB = totalIdeal = count = 0;
        for (auto &i : daftar){
            if (i.getsekolah() == "unsap1" && i.getgender() == "laki-laki"){
                totalTB += i.gettb();
                totalBB += i.getbb();
                totalIdeal += bbideal(i.getgender(), i.gettb());
                count++;
            }
        }
        cout << "\n[unsap1 - laki-laki]\n";
        if (count > 0){
            cout << "  Rata-rata TB       : " << (float)totalTB / count << endl;
            cout << "  Rata-rata BB       : " << totalBB / count << endl;
            cout << "  Rata-rata BB Ideal : " << totalIdeal / count << endl;
        } else cout << "  Tidak ada data.\n";

        totalTB = totalBB = totalIdeal = count = 0;
        for (auto &i : daftar){
            if (i.getsekolah() == "unsap1" && i.getgender() == "perempuan"){
                totalTB += i.gettb();
                totalBB += i.getbb();
                totalIdeal += bbideal(i.getgender(), i.gettb());
                count++;
            }
        }
        cout << "\n[unsap1 - perempuan]\n";
        if (count > 0){
            cout << "  Rata-rata TB       : " << (float)totalTB / count << endl;
            cout << "  Rata-rata BB       : " << totalBB / count << endl;
            cout << "  Rata-rata BB Ideal : " << totalIdeal / count << endl;
        } else cout << "  Tidak ada data.\n";

        totalTB = totalBB = totalIdeal = count = 0;
        for (auto &i : daftar){
            if (i.getsekolah() == "unsap2" && i.getgender() == "laki-laki"){
                totalTB += i.gettb();
                totalBB += i.getbb();
                totalIdeal += bbideal(i.getgender(), i.gettb());
                count++;
            }
        }
        cout << "\n[unsap2 - laki-laki]\n";
        if (count > 0){
            cout << "  Rata-rata TB       : " << (float)totalTB / count << endl;
            cout << "  Rata-rata BB       : " << totalBB / count << endl;
            cout << "  Rata-rata BB Ideal : " << totalIdeal / count << endl;
        } else cout << "  Tidak ada data.\n";

        totalTB = totalBB = totalIdeal = count = 0;
        for (auto &i : daftar){
            if (i.getsekolah() == "unsap2" && i.getgender() == "perempuan"){
                totalTB += i.gettb();
                totalBB += i.getbb();
                totalIdeal += bbideal(i.getgender(), i.gettb());
                count++;
            }
        }
        cout << "\n[unsap2 - perempuan]\n";
        if (count > 0){
            cout << "  Rata-rata TB       : " << (float)totalTB / count << endl;
            cout << "  Rata-rata BB       : " << totalBB / count << endl;
            cout << "  Rata-rata BB Ideal : " << totalIdeal / count << endl;
        } else cout << "  Tidak ada data.\n";
    }
};

int main(){
    DataManif Manif;
    int pilihan;

    do {
        cout << "\n=== MENU ===\n";
        cout << "1. Tambah Data\n";
        cout << "2. Lihat Data Berdasarkan Nama\n";
        cout << "3. Keluar\n";
        cout << "4. Lihat Rata-Rata per Sekolah dan Gender\n";
        cout << "Pilih: ";
        cin >> pilihan;

        switch (pilihan) {
            case 1:
                Manif.addData();
                break;
            case 2:
                Manif.LookData();
                break;
            case 3:
                cout << "Program selesai.\n";
                break;
            case 4:
                Manif.RataRataSekolah();
                break;
            default:
                cout << "Pilihan tidak valid.\n";
        }

    } while (pilihan != 3);

    return 0;
}
