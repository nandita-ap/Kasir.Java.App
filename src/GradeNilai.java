import java.util.Scanner;

public class GradeNilai {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        // Input nama mahasiswa
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        
        // Input jurusan kuliah
        System.out.print("Jurusan: ");
        String jurusan = scanner.nextLine();
        
        // Input nilai
        System.out.print("Masukkan nilai Anda: ");
        int nilai = scanner.nextInt();
        
        // Variabel untuk grade
        String grade;

        // Penentuan grade
        if (nilai >= 80 && nilai <= 100) {
            grade = "A";
        } else if (nilai >= 70 && nilai < 80) {
            grade = "B";
        } else if (nilai >= 60 && nilai < 70) {
            grade = "C";
        } else if (nilai >= 50 && nilai < 60) {
            grade = "D";
        } else {
            grade = "E";
        }
        
        // Output
        System.out.println("Grade Anda: " + grade);
        
        // Tutup scanner
        scanner.close();
    }
}
