import java.util.Scanner;

public class Kalkulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta input dari pengguna
        System.out.print("Nama: ");
        String namaMahasiswa = scanner.nextLine();

        System.out.print("Jurusan: ");
        String jurusanKuliah = scanner.nextLine();

        System.out.print("Masukkan angka pertama: ");
        double angkaPertama = scanner.nextDouble();

        System.out.print("Masukkan angka kedua: ");
        double angkaKedua = scanner.nextDouble();

        // Melakukan operasi aritmatika
        double hasilPenjumlahan = angkaPertama + angkaKedua;
        double hasilPengurangan = angkaPertama - angkaKedua;
        double hasilPerkalian = angkaPertama * angkaKedua;
        double hasilPembagian = angkaKedua != 0 ? angkaPertama / angkaKedua : 0; // Cek pembagi nol

        // Menampilkan hasil
        System.out.println("\nHasil:");
        System.out.println("Nama Mahasiswa: " + namaMahasiswa);
        System.out.println("Jurusan: " + jurusanKuliah);
        System.out.println("Hasil Penjumlahan: " + hasilPenjumlahan);
        System.out.println("Hasil Pengurangan: " + hasilPengurangan);
        System.out.println("Hasil Perkalian: " + hasilPerkalian);
        System.out.println("Hasil Pembagian: " + (angkaKedua != 0 ? hasilPembagian : "Tidak dapat dibagi dengan nol"));
    }
}