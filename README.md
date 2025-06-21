  
# 🌄 Explore Sumut — Aplikasi Wisata Sumatera Utara

**Explore Sumut** adalah aplikasi berbasis Java yang dikembangkan untuk tugas akhir mata kuliah *Laboratoriam Praktikum Pemrograman Berorientasi Objek (PBO)*. Aplikasi ini membantu pengguna menjelajahi informasi seputar tempat wisata di Sumatera Utara.

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
  - Menggunakan JavaFX

---

## 🔄 Alur Aplikasi

1. **Home** → User memilih Login atau Register
2. **Login/Register** → User melakukan autentikasi
3. **Dashboard** → User melihat list wisata dan melakukan pencarian
4. **Detail** → User melihat detail wisata dan memberikan ulasan

-- 

## 🛠️ Development Notes

### Design Patterns Used
- **Singleton Pattern** - UserSession
- **DAO Pattern** - WisataDAO, UlasanDAO

### Database Schema
- `users` - Tabel user
- `tempat_wisata` - Tabel tempat wisata
- `review` - Tabel ulasan

### Dependencies
- JavaFX untuk UI
- BCrypt untuk password hashing
- MySQL Connector untuk database
- ControlsFX untuk komponen UI tambahan

### 📁 Struktur Direktori
```
exploresumut/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── uas/
│       │               ├── controller/     # Controller untuk UI
│       │               ├── DAO/            # Data Access Object
│       │               ├── database/       # Konfigurasi database
│       │               ├── model/          # Model data
│       │               └── util/           # Utility classes
│       └── resources/
│           └── com/
│               └── example/
│                   └── uas/
│                       ├── css/            # Stylesheet
│                       ├── images/         # Gambar dan icon
│                       └── view/           # File FXML untuk UI
├── target/              # Output build
├── pom.xml             # Konfigurasi Maven
└── README.md           # Dokumentasi ini
```

## 🔧 Teknologi yang Digunakan

- **Java 23** - Bahasa pemrograman utama
- **JavaFX 17.0.6** - Framework UI
- **MySQL** - Database
- **Maven** - Build tool dan dependency management
- **BCrypt** - Enkripsi password
- **JDBC** - Koneksi database

## 📂 Penjelasan File dan Fungsi

### 🎯 Main Application
- **`MainApp.java`** - Entry point aplikasi, menginisialisasi JavaFX dan memuat halaman home

### 🗄️ Database
- **`DBConnection.java`** - Kelas untuk mengelola koneksi database MySQL dengan konfigurasi SSL

### 📊 Model Classes

#### Core Models
- **`TempatWisata.java`** - Model utama untuk data tempat wisata dengan atribut:
  - `id` - ID unik tempat wisata
  - `name` - Nama tempat wisata
  - `category` - Kategori
  - `location` - Lokasi tempat wisata
  - `description` - Deskripsi lengkap
  - `imageUrls` - List URL gambar

#### Specialized Models (Inheritance)
- **`WisataAlam.java`** - Extends TempatWisata, menambahkan `jenisAlam`
- **`WisataBudaya.java`** - Extends TempatWisata, menambahkan `budayaTerkait`
- **`WisataKuliner.java`** - Extends TempatWisata, menambahkan `makananKhas`

#### User Management
- **`User.java`** - Model untuk menyimpan username yang sedang login
- **`UserSession.java`** - Singleton pattern untuk mengelola session user dengan:
  - `username` - Username yang login
  - `userId` - ID user
  - `role` - Role user (admin/user)

#### Review System
- **`Ulasan.java`** - Model untuk ulasan dengan atribut:
  - `id` - ID ulasan
  - `wisataId` - ID tempat wisata
  - `userName` - Nama user yang memberikan ulasan
  - `rating` - Rating 1-5 bintang
  - `comment` - Komentar ulasan
  - `reviewTime` - Waktu ulasan dibuat

### 🎮 Controller Classes

#### Authentication Controllers
- **`HomeController.java`** - Controller untuk halaman utama:
  - `initialize()` - Menginisialisasi koneksi database
  - `goToLogin()` - Navigasi ke halaman login
  - `goToRegister()` - Navigasi ke halaman register

- **`LoginController.java`** - Controller untuk halaman login:
  - `handleLogin()` - Memproses login dengan validasi password menggunakan BCrypt
  - `goToRegister()` - Navigasi ke halaman register
  - `showAlert()` - Menampilkan pesan error

- **`RegisterController.java`** - Controller untuk halaman register:
  - `handleRegister()` - Memproses registrasi dengan validasi password
  - `isValidPassword()` - Validasi password (min 8 karakter, huruf besar/kecil, angka, simbol)
  - `goToLogin()` - Navigasi ke halaman login

#### Main Application Controllers
- **`DashboardController.java`** - Controller utama untuk dashboard:
  - `initialize()` - Menginisialisasi kategori dan memuat data wisata
  - `loadAllWisata()` - Memuat semua data wisata
  - `displayWisataList()` - Menampilkan list wisata dengan rating
  - `createWisataBox()` - Membuat komponen UI untuk setiap wisata
  - `openDetailPage()` - Membuka halaman detail wisata
  - `onSearch()` - Pencarian berdasarkan nama, kategori, lokasi
  - `onReset()` - Reset filter pencarian

- **`DetailController.java`** - Controller untuk halaman detail wisata:
  - `setWisataId()` - Set ID wisata dan memuat detail
  - `loadWisataDetail()` - Memuat detail lengkap wisata
  - `loadReviews()` - Memuat ulasan wisata
  - `onSubmitReview()` - Menambahkan ulasan baru
  - `clearReviewForm()` - Membersihkan form ulasan

#### Utility Controllers
- **`Function.java`** - Helper class untuk navigasi antar halaman:
  - `moveTo()` - Method untuk berpindah halaman dengan fullscreen

- **`AdminController.java`** - Controller untuk halaman admin (masih kosong)

### 🗃️ Data Access Objects (DAO)

- **`WisataDAO.java`** - DAO untuk operasi data tempat wisata:
  - `getAllWisata()` - Mengambil semua data wisata
  - `searchWisata()` - Pencarian wisata dengan filter
  - `getWisataById()` - Mengambil wisata berdasarkan ID
  - `getAllCategories()` - Mengambil semua kategori wisata

- **`UlasanDAO.java`** - DAO untuk operasi data ulasan:
  - `getReviewsByWisataId()` - Mengambil ulasan berdasarkan ID wisata
  - `insertReview()` - Menambahkan ulasan baru
  - `getAverageRatingByWisataId()` - Menghitung rating rata-rata wisata
  - `getMaxId()` - Mengambil ID maksimum untuk auto-increment

### 🎨 User Interface (FXML)

#### Authentication Views
- **`home.fxml`** - Halaman utama dengan:
  - Logo dan judul ExploreSumut
  - Tombol Login dan Register
  - Tips perjalanan
  - Background gradient yang menarik

- **`login.fxml`** - Halaman login dengan:
  - Form username dan password
  - Tombol login
  - Link ke halaman register
  - Design split-pane dengan gradient

- **`register.fxml`** - Halaman register dengan:
  - Form username dan password
  - Tombol register
  - Link ke halaman login
  - Design yang konsisten dengan login

#### Main Application Views
- **`dashboard.fxml`** - Dashboard utama dengan:
  - Search bar untuk pencarian wisata
  - Filter kategori dan lokasi
  - Container untuk list wisata
  - Scrollable content

- **`detail.fxml`** - Halaman detail wisata dengan:
  - Informasi lengkap wisata
  - Gallery gambar
  - List ulasan
  - Form untuk menambah ulasan

- **`admin.fxml`** - Halaman admin (masih kosong)
---

## ▶️ Cara Menjalankan

1. Buka melalui IntelliJ
2. Pastikan Java dan Maven sudah terinstall.
3. Download file jar yang dibutuhkan Bcrypt dan mySQL.
4. Masukan file tersebut kedalam dependencies melalui Project Structures.
5. Pastikan memiliki file ca.pem atau keystore.jks yang akan digunakan untuk validasi kredensial ke database.
6. Pilih run dari file MainApplication.java.
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
## 📝 TODO/Future Improvements

- [ ] Implementasi halaman admin
- [ ] Upload gambar wisata
- [ ] Sistem booking wisata
- [ ] Peta lokasi wisata
- [ ] Notifikasi real-time
- [ ] Export data ke PDF/Excel
- [ ] Multi-language support
