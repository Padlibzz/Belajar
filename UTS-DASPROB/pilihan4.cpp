#include <iostream>
#include <string>
using namespace std;

string kualitasPanen(float berat) {
    if (berat >= 1000) return "A (Sangat Baik)";
    else if (berat >= 700) return "B (Baik)";
    else if (berat >= 400) return "C (Cukup)";
    else return "D (Kurang)";
}

int main() {
    int jumlahLot;
    float hargaPerKg;

    cin >> jumlahLot;
    cin >> hargaPerKg;

    string namaLot[jumlahLot];
    float beratLot[jumlahLot];

    float totalBerat = 0;
    float totalKeuntungan = 0;

    for (int i = 0; i < jumlahLot; i++) {
        cin >> namaLot[i];
        cin >> beratLot[i];

        totalBerat += beratLot[i];
        totalKeuntungan += beratLot[i] * hargaPerKg;
    }

    for (int i = 0; i < jumlahLot; i++) {
        cout << namaLot[i] 
             << " " << beratLot[i] 
             << " " << kualitasPanen(beratLot[i]) << endl;
    }

    cout << totalBerat << endl;
    cout << totalKeuntungan << endl;

    return 0;
}
