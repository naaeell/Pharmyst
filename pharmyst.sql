-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 16, 2024 at 07:37 AM
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
  `id_about` int NOT NULL,
  `nama_pemilik` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `nama_usaha` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `no_telp_usaha` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `alamat` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `rfid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `about`
--

INSERT INTO `about` (`id_about`, `nama_pemilik`, `nama_usaha`, `no_telp_usaha`, `alamat`, `username`, `password`, `rfid`) VALUES
(1223123, 'Fadel', 'Apotek Dewata', '081515278601', 'Jl. Kaca Piring No.46, Gebang Tengah, Gebang, Kec. Patrang, Kabupaten Jember, Jawa Timur 68117', 'Fadel', '123', '123');

-- --------------------------------------------------------

--
-- Table structure for table `absensi`
--

CREATE TABLE `absensi` (
  `kode_user` char(15) COLLATE utf8mb4_bin NOT NULL,
  `tanggal_kehadiran` date NOT NULL,
  `waktu` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `absensi`
--

INSERT INTO `absensi` (`kode_user`, `tanggal_kehadiran`, `waktu`) VALUES
('82039485', '2024-04-14', '06:51:11'),
('203984756', '2024-04-15', '18:32:32'),
('82039485', '2024-04-15', '21:09:25');

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
('203984756', 'farel', '123', 'farel', 'farel@gmail.com', '234523452'),
('82039485', 'fadel', 'abc123', 'fadel', 'fadel@email.com', '9834769283745');

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode_barang` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_barang` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `satuan_obat` varchar(25) COLLATE utf8mb4_bin NOT NULL,
  `kadaluarsa` date NOT NULL,
  `kuantitas` int NOT NULL,
  `harga_pcs` int NOT NULL,
  `kode_bentuk_obat` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kode_kategori_obat` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `satuan_obat`, `kadaluarsa`, `kuantitas`, `harga_pcs`, `kode_bentuk_obat`, `kode_kategori_obat`) VALUES
('BRG202983745', 'obat sakit perut', '40', '2024-11-22', 0, 20000, '09823459', '8974369'),
('BRG2345235', 'apel hijau', '50', '2024-11-14', 30, 20000, '09823459', '8974369'),
('BRG23894', 'jambu', '56', '2024-05-02', 0, 25000, '09823459', '8974369'),
('BRG29830457', 'semangka', '34', '2025-01-17', 10, 25000, '09823459', '8974369'),
('BRG64765476', 'odading', '30', '2024-08-23', 20, 25000, '09823459', '8974369'),
('BRG8876978', 'obat meriang', '80', '2023-11-17', 0, 250000, '09823459', '8974369'),
('BRG9084726', 'awple', '50', '2024-06-13', 20, 500000, '09823459', '8974369');

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
('09823459', 'tablet');

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `kode_penjualan` char(15) COLLATE utf8mb4_bin NOT NULL,
  `kode_barang` char(15) COLLATE utf8mb4_bin NOT NULL,
  `jumlah_terjual` int NOT NULL,
  `total_harga` int NOT NULL,
  `laba_pcs` int NOT NULL,
  `laba_total` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`kode_penjualan`, `kode_barang`, `jumlah_terjual`, `total_harga`, `laba_pcs`, `laba_total`) VALUES
('PJ234577', 'BRG2345235', 20, 250000, 2000, 35000),
('PJ2390845', 'BRG29830457', 60, 650000, 90000, 80000),
('PJ234577', 'BRG23894', 20, 250000, 500, 40000);

-- --------------------------------------------------------

--
-- Table structure for table `distributor`
--

CREATE TABLE `distributor` (
  `kode_distributor` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_distributor` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `alamat` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `kontak_utama` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nomor_utama` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `distributor`
--

INSERT INTO `distributor` (`kode_distributor`, `nama_distributor`, `alamat`, `kontak_utama`, `nomor_utama`, `email`) VALUES
('7145683', 'medika pratama', 'jalan cendana', 'yudi', '081222222222', 'medikapratama@email.com');

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
('8974369', 'depresan');

-- --------------------------------------------------------

--
-- Table structure for table `laporan`
--

CREATE TABLE `laporan` (
  `kode_laporan` char(15) COLLATE utf8mb4_bin NOT NULL,
  `penjualan_bersih` int NOT NULL,
  `persediaan_awal` int NOT NULL,
  `pembelian_bersih` int NOT NULL,
  `persediaan_akhir` int NOT NULL,
  `laba_kotor_penjualan` int NOT NULL,
  `kode_operasional` char(15) COLLATE utf8mb4_bin NOT NULL,
  `laba_bersih` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `operasional`
--

CREATE TABLE `operasional` (
  `kode_operasional` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nama_biaya` varchar(60) COLLATE utf8mb4_bin NOT NULL,
  `tanggal` date NOT NULL,
  `deskripsi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `total_biaya` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `operasional`
--

INSERT INTO `operasional` (`kode_operasional`, `nama_biaya`, `tanggal`, `deskripsi`, `total_biaya`) VALUES
('OP23094234', 'sewa ruko', '2024-04-16', 'sewa ruko lt 12 elite full infrastruktur mewah\r\n', 2000000);

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `kode_pemesanan` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `tanggal_pemesanan` date NOT NULL,
  `harga_total` int NOT NULL,
  `kode_barang` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Jumlah_pembelian` int NOT NULL,
  `kode_distributor` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`kode_pemesanan`, `tanggal_pemesanan`, `harga_total`, `kode_barang`, `Jumlah_pembelian`, `kode_distributor`) VALUES
('KJ23495', '2024-04-14', 200000, 'BRG202983745', 0, '7145683');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `kode_penjualan` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `kode_user` char(15) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`kode_penjualan`, `tanggal_transaksi`, `kode_user`) VALUES
('PJ234577', '2024-04-23', '82039485'),
('PJ2390845', '2024-04-16', '82039485');

-- --------------------------------------------------------

--
-- Table structure for table `print_invoice`
--

CREATE TABLE `print_invoice` (
  `id_about` int NOT NULL,
  `kode_penjualan` char(15) COLLATE utf8mb4_bin NOT NULL,
  `kode_barang` char(15) COLLATE utf8mb4_bin NOT NULL,
  `jumlah_terjual` int NOT NULL,
  `total_harga` int NOT NULL,
  `jumlah_harga` int NOT NULL,
  `total_bayar` int NOT NULL,
  `kembali` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `print_invoice`
--

INSERT INTO `print_invoice` (`id_about`, `kode_penjualan`, `kode_barang`, `jumlah_terjual`, `total_harga`, `jumlah_harga`, `total_bayar`, `kembali`) VALUES
(1223123, 'PJ234577', 'BRG23894', 20, 250000, 69000, 23456, 34566),
(1223123, 'PJ234577', 'BRG2345235', 3456, 3456346, 34566, 345646, 68790879),
(1223123, 'PJ234577', 'BRG8876978', 789060, 687096, 678969, 678969, 6799997),
(1223123, 'PJ234577', 'BRG9084726', 456777, 45675467, 3453456, 34575467, 4854888);

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
  ADD KEY `kode_barang` (`kode_barang`),
  ADD KEY `kode_penjualan` (`kode_penjualan`);

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
  ADD KEY `kode_operasional` (`kode_operasional`);

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
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode_barang`),
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`kode_penjualan`) REFERENCES `penjualan` (`kode_penjualan`);

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode_barang`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pembelian_ibfk_2` FOREIGN KEY (`kode_distributor`) REFERENCES `distributor` (`kode_distributor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`kode_user`) REFERENCES `akun_karyawan` (`kode_user`);

--
-- Constraints for table `print_invoice`
--
ALTER TABLE `print_invoice`
  ADD CONSTRAINT `print_invoice_ibfk_1` FOREIGN KEY (`id_about`) REFERENCES `about` (`id_about`),
  ADD CONSTRAINT `print_invoice_ibfk_2` FOREIGN KEY (`kode_penjualan`) REFERENCES `penjualan` (`kode_penjualan`),
  ADD CONSTRAINT `print_invoice_ibfk_3` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode_barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
