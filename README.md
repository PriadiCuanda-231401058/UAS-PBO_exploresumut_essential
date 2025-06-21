
# 🌄 Explore Sumut — Aplikasi Wisata Sumatera Utara

**Explore Sumut** adalah aplikasi berbasis Java yang dikembangkan untuk tugas akhir mata kuliah *Pemrograman Berorientasi Objek (PBO)*. Aplikasi ini membantu pengguna menjelajahi informasi seputar tempat wisata di Sumatera Utara.

---

## 📌 Fitur Aplikasi

- 🔐 **Login dan Register pengguna**
  - Data akun disimpan dalam tabel `users`
- 🗺️ **Lihat daftar tempat wisata**
  - Informasi wisata disimpan dalam tabel `tempat_wisata`
  - Menampilkan gambar, nama, lokasi, dan deskripsi tempat wisata
- ⭐ **Review dan Komentar**
  - Pengguna dapat memberikan ulasan terhadap tempat wisata
  - Data disimpan dalam tabel `review`
- 🖼️ **Antarmuka pengguna berbasis GUI**
  - Menggunakan Java Swing atau JavaFX (tergantung implementasi tim)

---

## 📁 Struktur Proyek

| Folder/File     | Deskripsi                                           |
|------------------|----------------------------------------------------|
| `src/main/`      | Berisi source code utama aplikasi                  |
| `target/`        | Folder hasil kompilasi (auto-generated oleh Maven) |
| `.idea/`         | Settingan project IntelliJ/IDEA                    |
| `pom.xml`        | Konfigurasi dependency dan build tool Maven        |
| `.gitignore`     | Menentukan file apa saja yang diabaikan Git        |

---

## ▶️ Cara Menjalankan

1. Pastikan Java dan Maven sudah terinstall.
2. Buka terminal/command prompt di folder proyek.
3. Jalankan perintah berikut:

```bash
mvn clean install
mvn javafx:run
```

> Atau bisa langsung dijalankan melalui IDE seperti IntelliJ IDEA atau NetBeans.

---

## 🗃️ Struktur Database

Database: `exploresumut`

| Tabel           | Deskripsi                                  |
|------------------|---------------------------------------------|
| `users`          | Menyimpan data akun pengguna                |
| `tempat_wisata`  | Menyimpan informasi tempat wisata           |
| `review`         | Menyimpan ulasan dan komentar dari pengguna |

---

## 👥 Tim Pengembang

| Nama                     | NIM        | Kontribusi                          |
|--------------------------|------------|-------------------------------------|
| Priadi Cuanda            | 231401058  | Fitur login/register                |
| Timothy Tanawi           | 231401079  | [Deskripsi kontribusi]              |
| Josh Pardosi             | 231401031  | [Deskripsi kontribusi]              |
| Azlinsyah Fadhilah Meran | 231401013  | Dokumentasi & pelengkap fitur       |

---

## 📝 Catatan Tambahan

> Dokumentasi ini akan diperbarui setelah fitur dan kode lengkap 100%.
