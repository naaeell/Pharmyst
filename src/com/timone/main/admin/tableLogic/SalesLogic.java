/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.tableLogic;
import com.timone.connection.DbConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author Fadel
 */
public class SalesLogic {
    public static void salesTable(JTable jTable2, JTextField jTextField2) {
        try {
            // Mendapatkan koneksi ke database dari kelas DbConnection
            Connection conn = DbConnection.getConnection();

            // Membuat statement SQL untuk mengambil data penjualan
            String sql = "SELECT dp.kode_penjualan, p.tanggal_transaksi, k.username AS user, dp.kode_barang, b.nama_barang, dp.jumlah_terjual AS barang_terjual, dp.laba_pcs, dp.laba_total " +
                    "FROM detail_penjualan dp " +
                    "JOIN penjualan p ON dp.kode_penjualan = p.kode_penjualan " +
                    "JOIN barang b ON dp.kode_barang = b.kode_barang " +
                    "JOIN akun_karyawan k ON p.kode_user = k.kode_user " +
                    "WHERE dp.kode_penjualan LIKE ? OR p.tanggal_transaksi LIKE ? OR k.username LIKE ? OR dp.kode_barang LIKE ? OR b.nama_barang LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String searchQuery = "%" + jTextField2.getText() + "%";
            for (int i = 1; i <= 5; i++) {
                stmt.setString(i, searchQuery);
            }
            ResultSet rs = stmt.executeQuery();

            // Mendapatkan model tabel yang ada
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

            // Menghapus semua baris yang sudah ada dari model tabel
            model.setRowCount(0);

            // Memproses hasil kueri dan menambahkannya ke model tabel
            while (rs.next()) {
                Object[] row = {
                        rs.getString("kode_penjualan"),
                        rs.getDate("tanggal_transaksi"),
                        rs.getString("user"),
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("barang_terjual"),
                        rs.getInt("laba_pcs"),
                        rs.getInt("laba_total")
                };
                model.addRow(row);
            }

            // Menutup koneksi dan sumber daya
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Menangani eksepsi dengan mencetak jejaknya
            e.printStackTrace();
        }
    }
}
