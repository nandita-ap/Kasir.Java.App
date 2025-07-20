import java.util.Scanner;

public class PersegiPanjang{
    public static void main(String[] args) {
        // Buat objek Scanner untuk input
        Scanner scanner = new Scanner(System.in);

        // Variabel untuk nama mahasiswa dan jurusan
        String nama, jurusan;
        double panjang, lebar;

        // Input nama mahasiswa dan jurusan kuliah
        System.out.print("Nama: ");
        nama = scanner.nextLine();
        System.out.print("Jurusan: ");
        jurusan = scanner.nextLine();

        // Input panjang dan lebar
        System.out.print("Masukkan panjang: ");
        panjang = scanner.nextDouble();
        System.out.print("Masukkan lebar: ");
        lebar = scanner.nextDouble();

        // Hitung luas dan keliling
        double luas = panjang * lebar;
        double keliling = 2 * (panjang + lebar);

        // Tampilkan hasil
        System.out.println("Luas persegi panjang: " + luas);
        System.out.println("Keliling persegi panjang: " + keliling);
        
        // Tutup scanner
        scanner.close();
    }
}
