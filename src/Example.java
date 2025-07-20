class Example {
    // Method untuk mengubah nilai bilangan bulat
    public static void modifyPrimitive(int number) {
        number = number + 17; // Mengubah nilai lokal
        System.out.println("Di dalam method modifyPrimitive: " + number);
    }

    // Method untuk mengubah atribut objek
    public static void modifyObject(MyObject obj) {
        obj.value = obj.value + 17; // Mengubah atribut objek
        System.out.println("Di dalam method modifyObject: " + obj.value);
    }

    public static void main(String[] args) {
        // Contoh dengan tipe data primitif
        int originalNumber = 5;
        System.out.println("Sebelum modifyPrimitive: " + originalNumber);
        modifyPrimitive(originalNumber); // Mengirimkan salinan nilai
        System.out.println("Setelah modifyPrimitive: " + originalNumber); // Nilai tidak berubah

        // Contoh dengan objek
        MyObject myObj = new MyObject(5);
        System.out.println("Sebelum modifyObject: " + myObj.value);
        modifyObject(myObj); // Mengirimkan salinan referensi objek
        System.out.println("Setelah modifyObject: " + myObj.value); // Nilai berubah
    }
}

class MyObject {
    int value;

    MyObject(int value) {
        this.value = value;
    }
}