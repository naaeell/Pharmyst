/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.cashier;

/**
 *
 * @author Fadel
 */
import com.timone.connection.DBConnection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PenjualanUploader {
    private Connection connection;

    public PenjualanUploader(Connection connection) {
        this.connection = connection;
    }

    // Mengambil kode_user dari nama (jLabel6) dari tabel akun_karyawan
    public String getKodeUser(String nama) {
        String kodeUser = "";
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT kode_user FROM akun_karyawan WHERE nama = ?");
            stmt.setString(1, nama);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                kodeUser = rs.getString("kode_user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kodeUser;
    }

    // Mengambil laba_pcs dari tabel pembelian
    public double getLabaPcs(String kodeBarang) {
        double labaPcs = 0.0;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT laba_pcs FROM pembelian WHERE kode_barang = ?");
            stmt.setString(1, kodeBarang);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                labaPcs = rs.getDouble("laba_pcs");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labaPcs;
    }

    // Menampilkan JOptionPane dan mengambil total_bayar dari pengguna
    public double getTotalBayar() {
        String input = JOptionPane.showInputDialog(null, "Masukkan Total Bayar:");
        return Double.parseDouble(input);
    }

    // Menghitung kembali
    public double hitungKembali(double totalHarga, double totalBayar) {
        return totalBayar - totalHarga;
    }

    // Memasukkan data ke dalam database
    public void uploadData(String kodePenjualan, String tanggal, String nama, DefaultTableModel model) {
        String kodeUser = getKodeUser(nama);
        double totalHarga = 0.0;

        try {
            // Memulai transaksi
            connection.setAutoCommit(false);

            // Memasukkan data ke dalam tabel detail_penjualan
            PreparedStatement stmtDetailPenjualan = connection.prepareStatement("INSERT INTO detail_penjualan (kode_penjualan, kode_barang, jumlah_terjual, harga_total, laba_pcs, laba_total) VALUES (?, ?, ?, ?, ?, ?)");

            for (int i = 0; i < model.getRowCount(); i++) {
                String kodeBarang = (String) model.getValueAt(i, 1);
                int qty = (int) model.getValueAt(i, 4);
                double hargaTotal = Double.parseDouble((String) model.getValueAt(i, 5));
                double potensi = Double.parseDouble((String) model.getValueAt(i, 6));

                // Mengambil laba_pcs dari tabel pembelian
                double labaPcs = getLabaPcs(kodeBarang);

                // Menghitung laba_total
                double labaTotal = qty * labaPcs;

                stmtDetailPenjualan.setString(1, kodePenjualan);
                stmtDetailPenjualan.setString(2, kodeBarang);
                stmtDetailPenjualan.setInt(3, qty);
                stmtDetailPenjualan.setDouble(4, hargaTotal);
                stmtDetailPenjualan.setDouble(5, labaPcs);
                stmtDetailPenjualan.setDouble(6, labaTotal);
                stmtDetailPenjualan.executeUpdate();

                // Mengakumulasi total harga
                totalHarga += hargaTotal;
            }

            // Memasukkan data ke dalam tabel penjualan
            PreparedStatement stmtPenjualan = connection.prepareStatement("INSERT INTO penjualan (kode_penjualan, tanggal_transaksi, kode_user, total_harga) VALUES (?, ?, ?, ?)");
            stmtPenjualan.setString(1, kodePenjualan);
            stmtPenjualan.setString(2, tanggal);
            stmtPenjualan.setString(3, kodeUser);
            stmtPenjualan.setDouble(4, totalHarga);
            stmtPenjualan.executeUpdate();

            // Mengakhiri transaksi
            connection.commit();

            // Menampilkan pesan kembali
            double totalBayar = getTotalBayar();
            double kembali = hitungKembali(totalHarga, totalBayar);
            JOptionPane.showMessageDialog(null, "Total kembali: " + kembali);

        } catch (Exception e) {
            try {
                // Jika terjadi kesalahan, rollback transaksi
                connection.rollback();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // Mengatur kembali mode otomatis ke true setelah selesai
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}