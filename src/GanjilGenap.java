import java.util.Scanner;

public class GanjilGenap {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Input nama mahasiswa
        System.out.print("Nama: ");
        String namaMahasiswa = scanner.nextLine();

        // Input jurusan
        System.out.print("Jurusan: ");
        String jurusanKuliah = scanner.nextLine();

        // Input angka
        System.out.print("Masukkan sebuah angka: ");
        int angka = scanner.nextInt();

        // Menentukan ganjil atau genap
        if (angka % 2 == 0) {
            System.out.println(angka + " adalah angka genap.");
        } else {
            System.out.println(angka + " adalah angka ganjil.");
        }
        
        scanner.close();
    }
}