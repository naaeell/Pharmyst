-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 26, 2024 at 01:31 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pharmyst`
--

-- --------------------------------------------------------

--
-- Table structure for table `about`
--

CREATE TABLE `about` (
  `id_about` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_pemilik` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_usaha` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `no_telp_usaha` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `alamat` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `rfid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `keyword_recovery` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `about`
--

INSERT INTO `about` (`id_about`, `nama_pemilik`, `nama_usaha`, `no_telp_usaha`, `alamat`, `username`, `password`, `rfid`, `keyword_recovery`) VALUES
('ID147952', 'Fadelio Naufza', 'Apotek Dewata', '085183003136', 'sancefila residence blok h2', 'fadel', '54321', '123', 'fadel05');

-- --------------------------------------------------------

--
-- Table structure for table `absensi`
--

CREATE TABLE `absensi` (
  `id_absensi` int NOT NULL,
  `kode_user` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `tanggal_kehadiran` date NOT NULL,
  `waktu` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `absensi`
--

INSERT INTO `absensi` (`id_absensi`, `kode_user`, `tanggal_kehadiran`, `waktu`) VALUES
(128955979, 'KRW8149754629', '2024-05-26', '20:21:45'),
(832521952, 'KRW8149754629', '2024-05-28', '20:22:01');

--
-- Triggers `absensi`
--
DELIMITER $$
CREATE TRIGGER `after_absensi_delete` AFTER DELETE ON `absensi` FOR EACH ROW BEGIN
    DELETE FROM akun_karyawan WHERE kode_user = OLD.kode_user;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `akun_karyawan`
--

CREATE TABLE `akun_karyawan` (
  `kode_user` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `rfid` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `akun_karyawan`
--

INSERT INTO `akun_karyawan` (`kode_user`, `username`, `password`, `nama`, `email`, `rfid`) VALUES
('KRW8149754629', '123', '123', 'apel', '123', '123');

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode_barang` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_barang` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `satuan_obat` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kadaluarsa` date NOT NULL,
  `kuantitas` int NOT NULL,
  `harga_jual` int NOT NULL,
  `harga_beli` int NOT NULL,
  `laba` int NOT NULL,
  `jumlah_terjual` int NOT NULL DEFAULT '0',
  `kode_bentuk_obat` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kode_kategori_obat` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `satuan_obat`, `kadaluarsa`, `kuantitas`, `harga_jual`, `harga_beli`, `laba`, `jumlah_terjual`, `kode_bentuk_obat`, `kode_kategori_obat`) VALUES
('asdfsadfsd', 'asdfsadfd', '67', '2025-05-09', 567, 1200, 1000, 200, 0, 'BTK2486297', 'KTR253614');

--
-- Triggers `barang`
--
DELIMITER $$
CREATE TRIGGER `update_laba` BEFORE UPDATE ON `barang` FOR EACH ROW BEGIN
    IF NEW.harga_jual != OLD.harga_jual THEN
        -- Update the laba column in the barang table
        SET NEW.laba = NEW.harga_jual - NEW.harga_beli;
        
        -- Update the laba_pcs column in the pembelian table
        UPDATE pembelian
        SET laba_pcs = NEW.harga_jual - NEW.harga_beli
        WHERE kode_barang = NEW.kode_barang;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bentuk_obat`
--

CREATE TABLE `bentuk_obat` (
  `kode_bentuk_obat` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_bentuk_obat` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `bentuk_obat`
--

INSERT INTO `bentuk_obat` (`kode_bentuk_obat`, `nama_bentuk_obat`) VALUES
('BTK134679', 'Tablet'),
('BTK215487', 'Obat oles'),
('BTK2486297', 'Obat tetes'),
('BTK253614', 'Kapsul'),
('BTK316497', 'Obat cair'),
('BTK319575', 'Obat suntik'),
('BTK369814', 'Supositoria'),
('BTK649758', 'Tablet bukal atau sublingual'),
('BTK659814', 'Implan atau obat tempel'),
('BTK976848', 'Inhaler');

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `kode_detail` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `kode_penjualan` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kode_barang` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_barang` varchar(60) COLLATE utf8mb4_bin NOT NULL,
  `jumlah_terjual` int NOT NULL,
  `total_harga` int NOT NULL,
  `laba_pcs` int NOT NULL,
  `laba_total` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Triggers `detail_penjualan`
--
DELIMITER $$
CREATE TRIGGER `update_jumlah_terjual` AFTER INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
    UPDATE barang 
    SET jumlah_terjual = jumlah_terjual + NEW.jumlah_terjual
    WHERE kode_barang = NEW.kode_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_stok` AFTER INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
    DECLARE sold_qty INT;
    
    -- Ambil jumlah terjual dari data yang baru saja dimasukkan
    SET sold_qty = NEW.jumlah_terjual;
    
    -- Kurangi kuantitas barang dari tabel barang
    UPDATE barang
    SET kuantitas = kuantitas - sold_qty
    WHERE kode_barang = NEW.kode_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `distributor`
--

CREATE TABLE `distributor` (
  `kode_distributor` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_distributor` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `alamat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kontak_utama` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nomor_utama` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `distributor`
--

INSERT INTO `distributor` (`kode_distributor`, `nama_distributor`, `alamat`, `kontak_utama`, `nomor_utama`, `email`) VALUES
('DB235689', 'PT. Anugerah Pharmindo Lestari', 'World Trade Center (WTC) 1 Building, 15th Floor, Jl. Jend. Sudirman Kav. 29 - 30, Jakarta 12920, Indonesia', 'Denny Fikri', '02121684084', 'didik.haryadi@aplcare.com');

-- --------------------------------------------------------

--
-- Table structure for table `kategori_obat`
--

CREATE TABLE `kategori_obat` (
  `kode_kategori_obat` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_kategori` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `kategori_obat`
--

INSERT INTO `kategori_obat` (`kode_kategori_obat`, `nama_kategori`) VALUES
('KTR124578', 'Antasida'),
('KTR235689', 'Analgesik'),
('KTR253614', 'Anticemas'),
('KTR259746', 'Anti-emetik'),
('KTR312556', 'Ekspektoran'),
('KTR346857', 'Antijamur'),
('KTR482615', 'Antikonvulsan'),
('KTR524469', 'Antineoplastik'),
('KTR562147', 'Antivirus'),
('KTR629537', 'Antihistamin'),
('KTR634582', 'Antipiretik'),
('KTR639584', 'Dekongestan'),
('KTR6548899', 'Sitotoksik'),
('KTR663398', 'Antipsikotik'),
('KTR685724', 'Antidiare'),
('KTR784596', 'Antikoagulan dan trombolitik'),
('KTR789654', 'Anti-inflamasi'),
('KTR789954', 'Obat tidur'),
('KTR856326', 'Beta-blocker'),
('KTR952648', 'Antibiotik'),
('KTR976513', 'Antihipertensi'),
('KTR978564', 'Bronkodilator'),
('KTR984523', 'Anti-aritmia'),
('KTR987562', 'Antidepresan'),
('KTR987855', 'Kortikosteroid');

-- --------------------------------------------------------

--
-- Table structure for table `laporan`
--

CREATE TABLE `laporan` (
  `kode_laporan` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kode_operasional` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `id_about` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `penjualan_bersih` int NOT NULL,
  `harga_pokok_penjualan` int NOT NULL,
  `pembelian_bersih` int NOT NULL,
  `persediaan_akhir` int NOT NULL,
  `laba_kotor` int NOT NULL,
  `laba_bersih` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `operasional`
--

CREATE TABLE `operasional` (
  `kode_operasional` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_biaya` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `deskripsi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `total_biaya` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `operasional`
--

INSERT INTO `operasional` (`kode_operasional`, `nama_biaya`, `deskripsi`, `total_biaya`) VALUES
('OP0876065573', 'alpukat', 'alpukat', 5000),
('OP1366758301', 'gaji diko', 'bayar gaji', 5000000),
('OP4715164541', 'apel keju', 'asdasd', 90000),
('OP6193203410', 'listrik', 'pembayaran listrik', 30000),
('OP8865447098', 'assddd', 'bayar air', 150000);

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `kode_pemesanan` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `tanggal_pemesanan` date NOT NULL,
  `kode_distributor` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kode_barang` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `jumlah_pembelian` int NOT NULL,
  `harga_total` int NOT NULL,
  `laba_pcs` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`kode_pemesanan`, `tanggal_pemesanan`, `kode_distributor`, `kode_barang`, `jumlah_pembelian`, `harga_total`, `laba_pcs`) VALUES
('sadfasdfasdf234', '2024-05-23', 'DB235689', 'asdfsadfsd', 567, 567000, 200);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `kode_penjualan` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `kode_user` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `print_invoice`
--

CREATE TABLE `print_invoice` (
  `id_about` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kode_penjualan` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kode_barang` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `jumlah_terjual` int NOT NULL,
  `total_harga` int NOT NULL,
  `jumlah_harga` int NOT NULL,
  `total_bayar` int NOT NULL,
  `kembali` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `about`
--
ALTER TABLE `about`
  ADD PRIMARY KEY (`id_about`);

--
-- Indexes for table `absensi`
--
ALTER TABLE `absensi`
  ADD PRIMARY KEY (`id_absensi`),
  ADD KEY `kode_user` (`kode_user`);

--
-- Indexes for table `akun_karyawan`
--
ALTER TABLE `akun_karyawan`
  ADD PRIMARY KEY (`kode_user`);

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`),
  ADD KEY `kode bentuk obat` (`kode_bentuk_obat`,`kode_kategori_obat`),
  ADD KEY `kode kategori obat` (`kode_kategori_obat`);

--
-- Indexes for table `bentuk_obat`
--
ALTER TABLE `bentuk_obat`
  ADD PRIMARY KEY (`kode_bentuk_obat`);

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD PRIMARY KEY (`kode_detail`),
  ADD KEY `kode_penjualan` (`kode_penjualan`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indexes for table `distributor`
--
ALTER TABLE `distributor`
  ADD PRIMARY KEY (`kode_distributor`);

--
-- Indexes for table `kategori_obat`
--
ALTER TABLE `kategori_obat`
  ADD PRIMARY KEY (`kode_kategori_obat`);

--
-- Indexes for table `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`kode_laporan`),
  ADD KEY `kode_operasional` (`kode_operasional`),
  ADD KEY `id_about` (`id_about`);

--
-- Indexes for table `operasional`
--
ALTER TABLE `operasional`
  ADD PRIMARY KEY (`kode_operasional`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`kode_pemesanan`),
  ADD KEY `kode barang` (`kode_barang`,`kode_distributor`),
  ADD KEY `kode distributor` (`kode_distributor`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`kode_penjualan`),
  ADD KEY `kode_user` (`kode_user`);

--
-- Indexes for table `print_invoice`
--
ALTER TABLE `print_invoice`
  ADD KEY `id_about` (`id_about`,`kode_penjualan`,`kode_barang`),
  ADD KEY `kode_penjualan` (`kode_penjualan`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `absensi`
--
ALTER TABLE `absensi`
  ADD CONSTRAINT `absensi_ibfk_1` FOREIGN KEY (`kode_user`) REFERENCES `akun_karyawan` (`kode_user`);

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`kode_bentuk_obat`) REFERENCES `bentuk_obat` (`kode_bentuk_obat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `barang_ibfk_2` FOREIGN KEY (`kode_kategori_obat`) REFERENCES `kategori_obat` (`kode_kategori_obat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode_barang`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`kode_penjualan`) REFERENCES `penjualan` (`kode_penjualan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `laporan`
--
ALTER TABLE `laporan`
  ADD CONSTRAINT `laporan_ibfk_2` FOREIGN KEY (`kode_operasional`) REFERENCES `operasional` (`kode_operasional`),
  ADD CONSTRAINT `laporan_ibfk_3` FOREIGN KEY (`id_about`) REFERENCES `about` (`id_about`);

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_2` FOREIGN KEY (`kode_distributor`) REFERENCES `distributor` (`kode_distributor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pembelian_ibfk_3` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode_barang`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`kode_user`) REFERENCES `akun_karyawan` (`kode_user`);

--
-- Constraints for table `print_invoice`
--
ALTER TABLE `print_invoice`
  ADD CONSTRAINT `print_invoice_ibfk_4` FOREIGN KEY (`id_about`) REFERENCES `about` (`id_about`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
