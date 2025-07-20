public class InfoPrinter {

    // Method printInfo yang menerima satu parameter (nama)
    public void printInfo(String name) {
        System.out.println("Nama: " + name);
    }

    // Method printInfo yang menerima dua parameter (nama dan umur)
    public void printInfo(String name, int age) {
        System.out.println("Nama: " + name + ", Umur: " + age);
    }

    public static void main(String[] args) {
        InfoPrinter printer = new InfoPrinter();

        // Memanggil method printInfo dengan satu parameter
        printer.printInfo("Nandita Eka Aryta Putri");

        // Memanggil method printInfo dengan dua parameter
        printer.printInfo("Nandita Eka Aryta Putri", 20);
    }
}