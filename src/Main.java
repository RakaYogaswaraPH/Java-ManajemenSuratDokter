import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static List<Surat> daftarSurat = new ArrayList<>();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (pilihan) {
                case 1:
                    buatSuratBaru(scanner);
                    break;
                case 2:
                    bacaDaftarSurat();
                    break;
                case 3:
                    System.out.println("Terima Kasih");;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 3);



    }
    private static void tampilkanMenu() {
        System.out.println("\n===== Menu Surat Dokter =====");
        System.out.println("1. Buat Surat Baru");
        System.out.println("2. Baca Daftar Surat");
        System.out.println("3. Keluar");
        System.out.print("Pilihan Anda: ");

}

public static void buatSuratBaru(Scanner scanner){
    // Input data rekam medis pasien
    System.out.print("Nama Pasien   : ");
    String namaPasien = scanner.nextLine();

    System.out.print("Umur          : ");
    int umurPasien = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Alamat        : ");
    String alamatPasien = scanner.nextLine();

    System.out.print("Diagnosis     : ");
    String diagnosis = scanner.nextLine();

    System.out.print("Tindakan      : ");
    String tindakan = scanner.nextLine();

    // Membuat objek Dokter
    Dokter dokter = new Dokter("Dr. Vincent Halim,", "Sp.PD");

    // Membuat objek Surat
    Surat surat = new Surat(dokter, namaPasien, umurPasien , alamatPasien , diagnosis, tindakan);

    // Mencetak surat ke file
    try (FileWriter writer = new FileWriter("Surat Dokter - "+ namaPasien+".txt")) {
        writer.write(surat.toString());
        System.out.println("\nSurat telah dicetak ke file ("+ namaPasien + ".txt)");
        daftarSurat.add(surat);
    } catch (IOException e) {
        System.err.println("Gagal mencetak surat: " + e.getMessage());
    }

    // Mencetak isi surat
    System.out.println("\n" + surat);}

    public static void bacaDaftarSurat() {
        if (daftarSurat.isEmpty()) {
            System.out.println("\nBelum ada surat yang dibuat.");
            return;
        }

        System.out.println("\nDaftar Surat:");
        for (int i = 0; i < daftarSurat.size(); i++) {
            Surat surat = daftarSurat.get(i);
            System.out.println((i + 1) + ". " + surat.getNamaPasien());
        }

        System.out.print("Pilih surat untuk dibaca (masukkan nomor atau 0 untuk kembali): ");
        Scanner scanner = new Scanner(System.in);
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (pilihan > 0 && pilihan <= daftarSurat.size()) {
            Surat surat = daftarSurat.get(pilihan - 1);
            System.out.println("\n" + surat);
        } else if (pilihan == 0) {
            System.out.println("Kembali ke menu utama.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }
}

class Dokter {
    private final String nama;
    private final String spesialisasi;

    public Dokter(String nama, String spesialisasi) {
        this.nama = nama;
        this.spesialisasi = spesialisasi;
    }

    public String getNama() {
        return nama;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

}

class Surat {
        private final Dokter dokter;
        private final String namaPasien;
        private final int umurPasien;
        private final String alamatPasien;
        private final String diagnosis;
        private final String tindakan;

    public Surat(Dokter dokter, String namaPasien, int umurPasien,String alamatPasien, String diagnosis, String tindakan) {
            this.dokter = dokter;
            this.namaPasien = namaPasien;
            this.umurPasien = umurPasien;
            this.alamatPasien = alamatPasien;
            this.diagnosis = diagnosis;
            this.tindakan = tindakan;
        }

    public String getNamaPasien() {
        return namaPasien;
    }

    private String getTanggalSekarang() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MMMM - yyyy", new Locale("id", "ID")); // Ubah Locale menjadi "id", "ID"
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("==============================================\n");
        builder.append("    Surat Dokter Dr. Vincent Halim, Sp.PD\n");
        builder.append("==============================================\n");
        builder.append("Dokter          : ").append(dokter.getNama()).append(" ").append(dokter.getSpesialisasi()).append("\n");
        builder.append("Nama Pasien     : ").append(namaPasien).append("\n");
        builder.append("Umur            : ").append(umurPasien).append(" tahun\n");
        builder.append("Alamat          : ").append(alamatPasien).append("\n");
        builder.append("\n");
        builder.append("Diagnosis       : ").append(diagnosis).append("\n");
        builder.append("Tindakan        : ").append(tindakan).append("\n");
        builder.append("Dibuat Tanggal  : ").append(getTanggalSekarang()).append(" (Berlaku Selama 3 Hari)").append("\n");
        builder.append("\n");
        builder.append("==== Untuk Informasi Lebih Lanjut Hubungi ====\n");
        builder.append("No Telepon  : 0895 - 3017 - 8423 \n");
        builder.append("Email       : JayaMedika@gmail.com");

        return builder.toString();
    }

}
