/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.tableLogic;
import com.timone.connection.DbConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Fadel
 */
public class PurchaseLogic {

    public static void PurchaseTable(JTable jTable3, JTextField jTextField3) {
        try {
            // Get connection to the database from DbConnection class
            Connection conn = DbConnection.getConnection();

            // Dynamically build the SQL query based on search criteria
            String sql = "SELECT pembelian.kode_pemesanan, pembelian.tanggal_pemesanan, distributor.nama_distributor, " +
                         "pembelian.kode_barang, barang.nama_barang, kategori_obat.nama_kategori, " +
                         "bentuk_obat.nama_bentuk_obat, barang.satuan_obat, pembelian.jumlah_pembelian, pembelian.harga_total " +
                         "FROM pembelian " +
                         "INNER JOIN barang ON pembelian.kode_barang = barang.kode_barang " +
                         "INNER JOIN distributor ON pembelian.kode_distributor = distributor.kode_distributor " +
                         "INNER JOIN kategori_obat ON barang.kode_kategori_obat = kategori_obat.kode_kategori_obat " +
                         "INNER JOIN bentuk_obat ON barang.kode_bentuk_obat = bentuk_obat.kode_bentuk_obat " +
                         "WHERE LOWER(pembelian.kode_pemesanan) LIKE ? OR " +
                         "LOWER(pembelian.tanggal_pemesanan) LIKE ? OR " +
                         "LOWER(distributor.nama_distributor) LIKE ? OR " +
                         "LOWER(pembelian.kode_barang) LIKE ? OR " +
                         "LOWER(barang.nama_barang) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set search query parameters dynamically
            String searchQuery = "%" + jTextField3.getText().toLowerCase() + "%";
            for (int i = 1; i <= 5; i++) {
                stmt.setString(i, searchQuery);
            }

            ResultSet rs = stmt.executeQuery();

            // Get the table model
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();

            // Clear existing rows from the model
            model.setRowCount(0);

            // Process query results and add them to the table model
            while (rs.next()) {
                Object[] row = {
                        rs.getString("kode_pemesanan"),
                        rs.getDate("tanggal_pemesanan"),
                        rs.getString("nama_distributor"),
                        rs.getString("nama_barang"),
                        rs.getString("nama_kategori"),
                        rs.getString("nama_bentuk_obat"),
                        rs.getString("satuan_obat"),
                        rs.getInt("jumlah_pembelian"),
                        rs.getInt("harga_total")
                };
                model.addRow(row);
            }

            // Close connections
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle exceptions gracefully
            e.printStackTrace();
        }
    }
}
