// Kelas Workout
public class Workout {
    private String nama;
    private String gambar;
    private String deskripsi; // Menambahkan deskripsi

    public Workout(String nama, String gambar, String deskripsi) {
        this.nama = nama;
        this.gambar = gambar;
        this.deskripsi = deskripsi; // Menyimpan deskripsi
    }

    public String getnama() {
        return nama;
    }

    public String getgambar() {
        return gambar;
    }

    public String getDeskripsi() {
        return deskripsi; // Menambahkan getter untuk deskripsi
    }

    @Override
    public String toString() {
        return nama;
    }
}
