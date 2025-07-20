public class Calculator {

    // Method tambah yang menerima dua parameter bilangan bulat
    public static int tambah(int a, int b) {
        // Mengembalikan hasil penjumlahan a dan b
        return a + b;
    }

    public static void main(String[] args) {
        // Penggunaan method tambah
        int hasil1 = tambah(5, 10); // Hasil: 15
        int hasil2 = tambah(20, 30); // Hasil: 50

        // Menampilkan hasil penjumlahan
        System.out.println("Hasil 1: " + hasil1);
        System.out.println("Hasil 2: " + hasil2);
    }
}