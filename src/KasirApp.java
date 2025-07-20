import java.util.ArrayList;
import java.util.Scanner;

class Barang {
    String nama;
    int stok;
    double hargaBeli;
    double hargaJual;
    
    Barang(String nama, int stok, double hargaBeli, double hargaJual) {
        this.nama = nama;
        this.stok = stok;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }
    
    void tampilkanInfo() {
        System.out.println(nama + " | Stok: " + stok + " | Harga Beli: Rp" + hargaBeli + " | Harga Jual: Rp" + hargaJual);
    }
    
    void tampilkanInfoJual() {
        System.out.println(nama + " | Stok: " + stok + " | Harga: Rp" + hargaJual);
    }
    
    double getKeuntungan() {
        return hargaJual - hargaBeli;
    }
}

class ItemBeli {
    String nama;
    int jumlah;
    double hargaSatuan;
    double subtotal;
    double diskon;
    double totalSetelahDiskon;
    
    ItemBeli(String nama, int jumlah, double hargaSatuan, double diskon) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
        this.diskon = diskon;
        this.subtotal = jumlah * hargaSatuan;
        this.totalSetelahDiskon = subtotal - (subtotal * diskon / 100);
    }
    
    void tampilkanItem() {
        System.out.println(nama + " | " + jumlah + " x Rp" + hargaSatuan + " = Rp" + subtotal);
        if (diskon > 0) {
            System.out.println("  Diskon " + diskon + "% = -Rp" + (subtotal * diskon / 100));
            System.out.println("  Total setelah diskon = Rp" + totalSetelahDiskon);
        }
    }
}

class Diskon {
    String kode;
    String deskripsi;
    double persentase;
    double minimumBelanja;
    
    Diskon(String kode, String deskripsi, double persentase, double minimumBelanja) {
        this.kode = kode;
        this.deskripsi = deskripsi;
        this.persentase = persentase;
        this.minimumBelanja = minimumBelanja;
    }
    
    void tampilkanDiskon() {
        System.out.println(kode + " - " + deskripsi + " (" + persentase + "%) | Min. Belanja: Rp" + minimumBelanja);
    }
}

public class KasirApp {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<ItemBeli> keranjang = new ArrayList<>();
    static ArrayList<Diskon> daftarDiskon = new ArrayList<>();
    static String username = "admin";
    static String password = "123";
    
    public static void main(String[] args) {
        // Isi data barang awal
        isiDataBarang();
        isiDataDiskon();
        
        System.out.println("=== SISTEM KASIR DENGAN DISKON ===");
        
        // Login
        if (login()) {
            menuUtama();
        } else {
            System.out.println("Login gagal!");
        }
        
        input.close();
    }
    
    static void isiDataBarang() {
        daftarBarang.add(new Barang("Beras", 50, 25000, 30000));
        daftarBarang.add(new Barang("Minyak Goreng", 100, 20000, 24000));
        daftarBarang.add(new Barang("Sabun Mandi", 30, 8000, 10500));
        daftarBarang.add(new Barang("Betadhine", 25, 14000, 17000));
        daftarBarang.add(new Barang("Susu Kaleng", 40, 50000, 62000));
    }
    
    static void isiDataDiskon() {
        daftarDiskon.add(new Diskon("MEMBER10", "Diskon Member 10%", 10, 50000));
        daftarDiskon.add(new Diskon("BELI5", "Diskon Pembelian 5%", 5, 100000));
        daftarDiskon.add(new Diskon("GROSIR15", "Diskon Grosir 15%", 15, 200000));
        daftarDiskon.add(new Diskon("PROMO20", "Diskon Promo Spesial 20%", 20, 300000));
    }
    
    static boolean login() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Username: ");
        String user = input.nextLine();
        System.out.print("Password: ");
        String pass = input.nextLine();
        
        return user.equals(username) && pass.equals(password);
    }
    
    static void menuUtama() {
        boolean jalan = true;
        
        while (jalan) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Kasir (Transaksi)");
            System.out.println("2. Input Barang");
            System.out.println("3. Cek Stok");
            System.out.println("4. Kelola Diskon");
            System.out.println("5. Laporan Keuntungan");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            
            String pilihan = input.nextLine();
            
            switch (pilihan) {
                case "1" -> menuKasir();
                case "2" -> inputBarang();
                case "3" -> cekStok();
                case "4" -> kelolaDiskon();
                case "5" -> laporanKeuntungan();
                case "6" -> {
                    System.out.println("Terima kasih!");
                    jalan = false;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    static void menuKasir() {
        keranjang.clear(); // Kosongkan keranjang untuk transaksi baru
        boolean transaksi = true;
        
        System.out.println("\n=== KASIR - TRANSAKSI BARU ===");
        
        while (transaksi) {
            System.out.println("\n1. Tambah item");
            System.out.println("2. Lihat keranjang");
            System.out.println("3. Bayar");
            System.out.println("4. Batal");
            System.out.print("Pilih: ");
            
            String pilihan = input.nextLine();
            
            switch (pilihan) {
                case "1" -> tambahItem();
                case "2" -> lihatKeranjang();
                case "3" -> {
                    if (!keranjang.isEmpty()) {
                        prosesBayar();
                        transaksi = false;
                    } else {
                        System.out.println("Keranjang kosong!");
                    }
                }
                case "4" -> {
                    System.out.println("Transaksi dibatalkan");
                    // Kembalikan stok
                    for (ItemBeli item : keranjang) {
                        for (Barang barang : daftarBarang) {
                            if (barang.nama.equals(item.nama)) {
                                barang.stok += item.jumlah;
                                break;
                            }
                        }
                    }
                    transaksi = false;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    static void tambahItem() {
        System.out.println("\n=== DAFTAR BARANG ===");
        for (int i = 0; i < daftarBarang.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarBarang.get(i).tampilkanInfoJual();
        }
        
        System.out.print("Pilih barang (1-" + daftarBarang.size() + "): ");
        String pilihanStr = input.nextLine();
        
        try {
            int pilihan = Integer.parseInt(pilihanStr) - 1;
            
            if (pilihan >= 0 && pilihan < daftarBarang.size()) {
                Barang barang = daftarBarang.get(pilihan);
                
                System.out.print("Jumlah beli: ");
                String jumlahStr = input.nextLine();
                int jumlah = Integer.parseInt(jumlahStr);
                
                if (jumlah > 0 && jumlah <= barang.stok) {
                    // Tanyakan diskon item
                    System.out.print("Berikan diskon untuk item ini? (0 jika tidak ada): ");
                    String diskonStr = input.nextLine();
                    double diskonItem = 0;
                    
                    try {
                        diskonItem = Double.parseDouble(diskonStr);
                        if (diskonItem < 0 || diskonItem > 100) {
                            diskonItem = 0;
                            System.out.println("Diskon tidak valid, menggunakan 0%");
                        }
                    } catch (NumberFormatException e) {
                        diskonItem = 0;
                    }
                    
                    // Tambah ke keranjang
                    keranjang.add(new ItemBeli(barang.nama, jumlah, barang.hargaJual, diskonItem));
                    // Kurangi stok
                    barang.stok -= jumlah;
                    System.out.println("Item berhasil ditambahkan!");
                } else {
                    System.out.println("Jumlah tidak valid atau stok tidak cukup!");
                }
            } else {
                System.out.println("Pilihan barang tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }
    
    static void lihatKeranjang() {
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang kosong!");
            return;
        }
        
        System.out.println("\n=== KERANJANG BELANJA ===");
        double totalBelanja = 0;
        
        for (int i = 0; i < keranjang.size(); i++) {
            System.out.print((i + 1) + ". ");
            keranjang.get(i).tampilkanItem();
            totalBelanja += keranjang.get(i).totalSetelahDiskon;
            System.out.println();
        }
        
        System.out.println("------------------------");
        System.out.println("Total: Rp" + totalBelanja);
    }
    
    static void prosesBayar() {
        lihatKeranjang();
        
        double totalBelanja = 0;
        for (ItemBeli item : keranjang) {
            totalBelanja += item.totalSetelahDiskon;
        }
        
        // Cek diskon khusus berdasarkan total belanja
        System.out.println("\n=== CEK DISKON TAMBAHAN ===");
        System.out.println("Total belanja: Rp" + totalBelanja);
        
        Diskon diskonTerpilih = null;
        System.out.println("\nDiskon yang tersedia:");
        for (Diskon diskon : daftarDiskon) {
            if (totalBelanja >= diskon.minimumBelanja) {
                diskon.tampilkanDiskon();
            }
        }
        
        System.out.print("\nMasukkan kode diskon (atau tekan Enter untuk skip): ");
        String kodeDiskon = input.nextLine().trim();
        
        double diskonTambahan = 0;
        if (!kodeDiskon.isEmpty()) {
            for (Diskon diskon : daftarDiskon) {
                if (diskon.kode.equalsIgnoreCase(kodeDiskon) && totalBelanja >= diskon.minimumBelanja) {
                    diskonTerpilih = diskon;
                    diskonTambahan = totalBelanja * diskon.persentase / 100;
                    break;
                }
            }
            
            if (diskonTerpilih == null) {
                System.out.println("Kode diskon tidak valid atau minimum belanja tidak terpenuhi!");
            }
        }
        
        double totalFinal = totalBelanja - diskonTambahan;
        
        System.out.println("\n=== RINCIAN PEMBAYARAN ===");
        System.out.println("Subtotal: Rp" + totalBelanja);
        if (diskonTerpilih != null) {
            System.out.println("Diskon " + diskonTerpilih.kode + " (" + diskonTerpilih.persentase + "%): -Rp" + diskonTambahan);
        }
        System.out.println("Total yang harus dibayar: Rp" + totalFinal);
        System.out.print("Jumlah bayar: Rp");
        
        try {
            String bayarStr = input.nextLine();
            double bayar = Double.parseDouble(bayarStr);
            
            if (bayar >= totalFinal) {
                double kembalian = bayar - totalFinal;
                
                // Cetak struk
                cetakStruk(totalBelanja, diskonTambahan, totalFinal, bayar, kembalian, diskonTerpilih);
                
                System.out.println("Transaksi berhasil!");
                System.out.println("Tekan Enter untuk kembali ke menu...");
                input.nextLine();
            } else {
                System.out.println("Uang tidak cukup!");
                // Kembalikan stok jika pembayaran gagal
                for (ItemBeli item : keranjang) {
                    for (Barang barang : daftarBarang) {
                        if (barang.nama.equals(item.nama)) {
                            barang.stok += item.jumlah;
                            break;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }
    
    static void cetakStruk(double subtotal, double diskonTambahan, double total, double bayar, double kembalian, Diskon diskonKhusus) {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("              STRUK BELANJA");
        System.out.println("=".repeat(45));
        
        for (ItemBeli item : keranjang) {
            System.out.println(item.nama);
            System.out.println("  " + item.jumlah + " x " + item.hargaSatuan + " = " + item.subtotal);
            if (item.diskon > 0) {
                System.out.println("  Diskon item " + item.diskon + "% = -" + (item.subtotal * item.diskon / 100));
                System.out.println("  Subtotal item = " + item.totalSetelahDiskon);
            }
            System.out.println();
        }
        
        System.out.println("-".repeat(45));
        System.out.println("Subtotal : Rp" + subtotal);
        if (diskonKhusus != null) {
            System.out.println("Diskon " + diskonKhusus.kode + " (" + diskonKhusus.persentase + "%) : -Rp" + diskonTambahan);
        }
        System.out.println("Total    : Rp" + total);
        System.out.println("Bayar    : Rp" + bayar);
        System.out.println("Kembali  : Rp" + kembalian);
        System.out.println("=".repeat(45));
        System.out.println("         Terima kasih!");
        System.out.println("=".repeat(45));
    }
    
    static void inputBarang() {
        System.out.println("\n=== INPUT BARANG BARU ===");
        System.out.print("Nama barang: ");
        String nama = input.nextLine();
        
        System.out.print("Stok: ");
        try {
            String stokStr = input.nextLine();
            int stok = Integer.parseInt(stokStr);
            
            System.out.print("Harga beli: Rp");
            String hargaBeliStr = input.nextLine();
            double hargaBeli = Double.parseDouble(hargaBeliStr);
            
            System.out.print("Harga jual: Rp");
            String hargaJualStr = input.nextLine();
            double hargaJual = Double.parseDouble(hargaJualStr);
            
            if (stok >= 0 && hargaBeli >= 0 && hargaJual >= 0) {
                if (hargaJual > hargaBeli) {
                    daftarBarang.add(new Barang(nama, stok, hargaBeli, hargaJual));
                    System.out.println("Barang berhasil ditambahkan!");
                    System.out.println("Keuntungan per unit: Rp" + (hargaJual - hargaBeli));
                } else {
                    System.out.println("Peringatan: Harga jual harus lebih tinggi dari harga beli untuk mendapat keuntungan!");
                    System.out.print("Tetap tambahkan? (y/n): ");
                    String konfirmasi = input.nextLine();
                    if (konfirmasi.equalsIgnoreCase("y")) {
                        daftarBarang.add(new Barang(nama, stok, hargaBeli, hargaJual));
                        System.out.println("Barang berhasil ditambahkan!");
                    }
                }
            } else {
                System.out.println("Stok dan harga harus positif!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
        
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
    
    static void cekStok() {
        boolean menu = true;
        
        while (menu) {
            System.out.println("\n=== CEK STOK ===");
            System.out.println("1. Lihat semua barang");
            System.out.println("2. Tambah stok");
            System.out.println("3. Edit harga");
            System.out.println("4. Hapus barang");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            
            String pilihan = input.nextLine();
            
            switch (pilihan) {
                case "1" -> lihatSemuaBarang();
                case "2" -> tambahStok();
                case "3" -> editHarga();
                case "4" -> hapusBarang();
                case "5" -> menu = false;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    static void lihatSemuaBarang() {
        System.out.println("\n=== DAFTAR SEMUA BARANG ===");
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang!");
        } else {
            for (int i = 0; i < daftarBarang.size(); i++) {
                System.out.print((i + 1) + ". ");
                daftarBarang.get(i).tampilkanInfo();
                System.out.println("   Keuntungan per unit: Rp" + daftarBarang.get(i).getKeuntungan());
            }
        }
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
    
    static void tambahStok() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang!");
            return;
        }
        
        System.out.println("\n=== TAMBAH STOK ===");
        for (int i = 0; i < daftarBarang.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarBarang.get(i).tampilkanInfo();
        }
        
        System.out.print("Pilih barang (1-" + daftarBarang.size() + "): ");
        try {
            String pilihanStr = input.nextLine();
            int pilihan = Integer.parseInt(pilihanStr) - 1;
            
            if (pilihan >= 0 && pilihan < daftarBarang.size()) {
                System.out.print("Tambah stok berapa: ");
                String tambahStr = input.nextLine();
                int tambah = Integer.parseInt(tambahStr);
                
                if (tambah > 0) {
                    daftarBarang.get(pilihan).stok += tambah;
                    System.out.println("Stok berhasil ditambah!");
                } else {
                    System.out.println("Jumlah harus positif!");
                }
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
        
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
    
    static void editHarga() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang!");
            return;
        }
        
        System.out.println("\n=== EDIT HARGA ===");
        for (int i = 0; i < daftarBarang.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarBarang.get(i).tampilkanInfo();
        }
        
        System.out.print("Pilih barang (1-" + daftarBarang.size() + "): ");
        try {
            String pilihanStr = input.nextLine();
            int pilihan = Integer.parseInt(pilihanStr) - 1;
            
            if (pilihan >= 0 && pilihan < daftarBarang.size()) {
                Barang barang = daftarBarang.get(pilihan);
                
                System.out.println("Harga beli saat ini: Rp" + barang.hargaBeli);
                System.out.print("Harga beli baru (Enter untuk tidak ubah): ");
                String hargaBeliStr = input.nextLine();
                if (!hargaBeliStr.isEmpty()) {
                    double hargaBeliBaru = Double.parseDouble(hargaBeliStr);
                    if (hargaBeliBaru >= 0) {
                        barang.hargaBeli = hargaBeliBaru;
                    }
                }
                
                System.out.println("Harga jual saat ini: Rp" + barang.hargaJual);
                System.out.print("Harga jual baru (Enter untuk tidak ubah): ");
                String hargaJualStr = input.nextLine();
                if (!hargaJualStr.isEmpty()) {
                    double hargaJualBaru = Double.parseDouble(hargaJualStr);
                    if (hargaJualBaru >= 0) {
                        barang.hargaJual = hargaJualBaru;
                    }
                }
                
                System.out.println("Harga berhasil diupdate!");
                System.out.println("Keuntungan per unit sekarang: Rp" + barang.getKeuntungan());
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
        
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
    
    static void hapusBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang!");
            return;
        }
        
        System.out.println("\n=== HAPUS BARANG ===");
        for (int i = 0; i < daftarBarang.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarBarang.get(i).tampilkanInfo();
        }
        
        System.out.print("Pilih barang yang akan dihapus (1-" + daftarBarang.size() + "): ");
        try {
            String pilihanStr = input.nextLine();
            int pilihan = Integer.parseInt(pilihanStr) - 1;
            
            if (pilihan >= 0 && pilihan < daftarBarang.size()) {
                System.out.print("Yakin hapus " + daftarBarang.get(pilihan).nama + "? (y/n): ");
                String yakin = input.nextLine();
                
                if (yakin.equalsIgnoreCase("y")) {
                    daftarBarang.remove(pilihan);
                    System.out.println("Barang berhasil dihapus!");
                } else {
                    System.out.println("Penghapusan dibatalkan");
                }
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
        
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
    
    static void kelolaDiskon() {
        boolean menu = true;
        
        while (menu) {
            System.out.println("\n=== KELOLA DISKON ===");
            System.out.println("1. Lihat semua diskon");
            System.out.println("2. Tambah diskon");
            System.out.println("3. Hapus diskon");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            
            String pilihan = input.nextLine();
            
            switch (pilihan) {
                case "1" -> lihatSemuaDiskon();
                case "2" -> tambahDiskon();
                case "3" -> hapusDiskon();
                case "4" -> menu = false;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    static void lihatSemuaDiskon() {
        System.out.println("\n=== DAFTAR SEMUA DISKON ===");
        if (daftarDiskon.isEmpty()) {
            System.out.println("Tidak ada diskon!");
        } else {
            for (int i = 0; i < daftarDiskon.size(); i++) {
                System.out.print((i + 1) + ". ");
                daftarDiskon.get(i).tampilkanDiskon();
            }
        }
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
    
    static void tambahDiskon() {
        System.out.println("\n=== TAMBAH DISKON BARU ===");
        System.out.print("Kode diskon: ");
        String kode = input.nextLine().toUpperCase();
        
        System.out.print("Deskripsi: ");
        String deskripsi = input.nextLine();
        
        try {
            System.out.print("Persentase diskon (%): ");
            String persentaseStr = input.nextLine();
            double persentase = Double.parseDouble(persentaseStr);
            
            System.out.print("Minimum belanja: Rp");
            String minimumStr = input.nextLine();
            double minimum = Double.parseDouble(minimumStr);
            
            if (persentase > 0 && persentase <= 100 && minimum >= 0) {
                daftarDiskon.add(new Diskon(kode, deskripsi, persentase, minimum));
                System.out.println("Diskon berhasil ditambahkan!");
            } else {
                System.out.println("Persentase harus 1-100 dan minimum belanja harus positif!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
        
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
    
    static void hapusDiskon() {
        if (daftarDiskon.isEmpty()) {
            System.out.println("Tidak ada diskon!");
            return;
        }
        
        System.out.println("\n=== HAPUS DISKON ===");
        for (int i = 0; i < daftarDiskon.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarDiskon.get(i).tampilkanDiskon();
        }
        
        System.out.print("Pilih diskon yang akan dihapus (1-" + daftarDiskon.size() + "): ");
        try {
            String pilihanStr = input.nextLine();
            int pilihan = Integer.parseInt(pilihanStr) - 1;
            
            if (pilihan >= 0 && pilihan < daftarDiskon.size()) {
                System.out.print("Yakin hapus diskon " + daftarDiskon.get(pilihan).kode + "? (y/n): ");
                String yakin = input.nextLine();
                
                if (yakin.equalsIgnoreCase("y")) {
                    daftarDiskon.remove(pilihan);
                    System.out.println("Diskon berhasil dihapus!");
                } else {
                    System.out.println("Penghapusan dibatalkan");
                }
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
        
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
    
    static void laporanKeuntungan() {
        System.out.println("\n=== LAPORAN KEUNTUNGAN BARANG ===");
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang!");
            return;
        }
        
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│                        LAPORAN KEUNTUNGAN                     │");
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-15s │ %-12s │ %-12s │ %-12s │%n", "Nama Barang", "Harga Beli", "Harga Jual", "Keuntungan");
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        double totalKeuntunganPotensial = 0;
        
        for (Barang barang : daftarBarang) {
            double keuntunganPerUnit = barang.getKeuntungan();
            double keuntunganPotensial = keuntunganPerUnit * barang.stok;
            totalKeuntunganPotensial += keuntunganPotensial;
            
            System.out.printf("│ %-15s │ Rp%-10.0f │ Rp%-10.0f │ Rp%-10.0f │%n", 
                barang.nama, barang.hargaBeli, barang.hargaJual, keuntunganPerUnit);
            System.out.printf("│ %-15s │ %-12s │ %-12s │ %-12s │%n", 
                "Stok: " + barang.stok, "", "", "Pot: Rp" + (int)keuntunganPotensial);
            System.out.println("├────────────────────────────────────────────────────────────────┤");
        }
        
        System.out.printf("│ %-47s │ Rp%-10.0f │%n", "TOTAL KEUNTUNGAN POTENSIAL", totalKeuntunganPotensial);
        System.out.println("└────────────────────────────────────────────────────────────────┘");
        
        // Analisis keuntungan
        System.out.println("\n=== ANALISIS KEUNTUNGAN ===");
        Barang barangTerbaikKeuntungan = null;
        Barang barangTerburukKeuntungan = null;
        double keuntunganTerbaik = Double.MIN_VALUE;
        double keuntunganTerburuk = Double.MAX_VALUE;
        
        for (Barang barang : daftarBarang) {
            double keuntungan = barang.getKeuntungan();
            if (keuntungan > keuntunganTerbaik) {
                keuntunganTerbaik = keuntungan;
                barangTerbaikKeuntungan = barang;
            }
            if (keuntungan < keuntunganTerburuk) {
                keuntunganTerburuk = keuntungan;
                barangTerburukKeuntungan = barang;
            }
        }
        
        if (barangTerbaikKeuntungan != null) {
            System.out.println("Keuntungan terbaik: " + barangTerbaikKeuntungan.nama + " (Rp" + keuntunganTerbaik + " per unit)");
        }
        if (barangTerburukKeuntungan != null) {
            if (keuntunganTerburuk < 0) {
                System.out.println("⚠️  RUGI: " + barangTerburukKeuntungan.nama + " (Rp" + keuntunganTerburuk + " per unit)");
            } else {
                System.out.println("Keuntungan terendah: " + barangTerburukKeuntungan.nama + " (Rp" + keuntunganTerburuk + " per unit)");
            }
        }
        
        System.out.println("Tekan Enter untuk kembali...");
        input.nextLine();
    }
}