import java.util.ArrayList;
import java.util.Scanner;

public class DataMahasiswa {
    public static void main(String[] args) {
        ArrayList<String> mahasiswa = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Tambah Nama Mahasiswa");
            System.out.println("2. Ubah Nama Mahasiswa");
            System.out.println("3. Hapus Nama Mahasiswa");
            System.out.println("4. Tampilkan Semua Mahasiswa");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");
            
            while (!input.hasNextInt()) {
                System.out.println("Input harus angka!");
                input.next(); // skip input salah
                System.out.print("Pilihan Anda: ");
            }

            pilihan = input.nextInt();
            input.nextLine(); // Buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama mahasiswa: ");
                    String nama = input.nextLine();
                    mahasiswa.add(nama);
                    System.out.println("Data ditambahkan.");
                    break;

                case 2:
                    System.out.print("Masukkan nama mahasiswa yang ingin diubah: ");
                    String namaLama = input.nextLine();
                    if (mahasiswa.contains(namaLama)) {
                        System.out.print("Masukkan nama baru: ");
                        String namaBaru = input.nextLine();
                        int index = mahasiswa.indexOf(namaLama);
                        mahasiswa.set(index, namaBaru);
                        System.out.println("Data berhasil diubah.");
                    } else {
                        System.out.println("Nama tidak ditemukan.");
                    }
                    break;

                case 3:
                    System.out.print("Masukkan nama mahasiswa yang ingin dihapus: ");
                    String namaHapus = input.nextLine();
                    if (mahasiswa.remove(namaHapus)) {
                        System.out.println("Data berhasil dihapus.");
                    } else {
                        System.out.println("Nama tidak ditemukan.");
                    }
                    break;

                case 4:
                    if (mahasiswa.isEmpty()) {
                        System.out.println("Belum ada data mahasiswa.");
                    } else {
                        System.out.println("Daftar Nama Mahasiswa:");
                        for (int i = 0; i < mahasiswa.size(); i++) {
                            System.out.println((i + 1) + ". " + mahasiswa.get(i));
                        }
                    }
                    break;

                case 5:
                    System.out.println("Anda telah keluar. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);

        input.close();
    }
}
