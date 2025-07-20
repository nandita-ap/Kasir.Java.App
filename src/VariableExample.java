public class VariableExample {
    // Variabel global (instance variable)
    private int globalVariable = 211;

    // Method untuk menunjukkan penggunaan variabel lokal dan global
    public void displayVariables() {
        // Variabel lokal
        int localVariable = 8;

        // Menampilkan nilai variabel lokal dan global
        System.out.println("Nilai variabel lokal: " + localVariable);
        System.out.println("Nilai variabel global: " + globalVariable);
    }

    public void modifyGlobalVariable() {
        // Mengubah nilai variabel global
        globalVariable += 8;
        System.out.println("Nilai variabel global setelah diubah: " + globalVariable);
    }

    public static void main(String[] args) {
        VariableExample example = new VariableExample();

        // Menampilkan nilai variabel lokal dan global
        example.displayVariables();

        // Mengubah dan menampilkan nilai variabel global
        example.modifyGlobalVariable();

        // Menampilkan kembali nilai variabel lokal dan global
        example.displayVariables();
    }
}