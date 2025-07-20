public class Greeting {

    // Method greetUser  yang menerima satu parameter bertipe String
    public static void greetUser (String name) {
        // Menampilkan sapaan dengan nama pengguna
        System.out.println("Halo, " + name + "!");
    }

    public static void main(String[] args) {
        // Contoh penggunaan method greetUser 
        greetUser ("Nandita Eka Aryta Putri"); // Output: Halo, Nandita Eka Aryta Putri!
    }
}