/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.tableLogic;
import com.timone.connection.DbConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Fadel
 */
public class AttendanceLogic {

    public static void AbsenTable(JTable jTable9) {
        try {
            // Mendapatkan koneksi ke database dari kelas DbConnection
            Connection conn = DbConnection.getConnection();

            // Membuat statement SQL untuk mengambil data absen beserta nama dari tabel barang
            String sql = "SELECT A.tanggal_kehadiran, A.waktu, B.nama AS Nama, B.username AS Username, B.email AS Email " +
                         "FROM absensi A " +
                         "INNER JOIN akun_karyawan B ON A.kode_user = B.kode_user " +
                         "ORDER BY A.tanggal_kehadiran DESC"; // Menambahkan ORDER BY untuk mengurutkan berdasarkan tanggal_kehadiran secara menurun

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Mendapatkan model tabel yang ada untuk jTable9
            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();

            // Menghapus semua baris yang sudah ada dari model tabel jTable9
            model.setRowCount(0);

            // Memproses hasil kueri dan menambahkannya ke model tabel jTable9
            while (rs.next()) {
                Object[] row = {
                        rs.getString("tanggal_kehadiran"),
                        rs.getString("waktu"),
                        rs.getString("Nama"), // Mengambil nama dari tabel barang menggunakan alias Nama
                        rs.getString("Username"),
                        rs.getString("Email")
                };
                model.addRow(row);
            }

            // Menutup koneksi
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
