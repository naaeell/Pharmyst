/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.connection;
import com.timone.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Fadel
 */
public class testing {
    public static void main(String[] args) {
        try {
            // Mendapatkan koneksi ke database
            Connection conn = DBConnection.getConnection();

            // Membuat statement
            Statement statement = conn.createStatement();

            // Mengeksekusi query SQL untuk mendapatkan semua data dari tabel barang
            String query = "SELECT * FROM barang";
            ResultSet resultSet = statement.executeQuery(query);

            // Menampilkan hasil query
            while (resultSet.next()) {
                // Mendapatkan nilai dari setiap kolom dalam baris
                String kodeBarang = resultSet.getString("kode_barang");
                String namaBarang = resultSet.getString("nama_barang");
                /*int kategoriObat = resultSet.getInt("kategori obat");
                int bentukObat = resultSet.getInt("bentuk obat");
                int satuanObat = resultSet.getInt("satuan obat");
                String kadaluwarsa = resultSet.getString("kadaluwarsa");*/
                int kuantitas = resultSet.getInt("kuantitas");
                //int harga = resultSet.getInt("harga");

                // Menampilkan nilai dari setiap kolom
                System.out.println(kodeBarang + " " + namaBarang + " " + /* kategoriObat + " " + bentukObat + " " + satuanObat + " " + kadaluwarsa + " " + */ kuantitas /* + " " + harga*/);
            }

            // Menutup koneksi dan statement
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menampilkan data: " + e.getMessage());
        }
    }
}
