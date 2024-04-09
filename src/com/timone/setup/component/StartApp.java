/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.setup.component;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.timone.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Fadel
 */
public class StartApp {
    
    public static void main(String args[]) {
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Mendapatkan koneksi ke database
                    Connection conn = DBConnection.getConnection();
                    
                    // Membuat statement SQL untuk mengambil jumlah data dari tabel barang
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM barang");
                    rs.next();
                    int rowCount = rs.getInt("total");
                    
                    // Menutup sumber daya
                    rs.close();
                    stmt.close();
                    conn.close();
                    
                    // Memeriksa apakah tabel barang kosong atau tidak
                    if (rowCount == 0) {
                        // Jika tabel kosong, luncurkan RegisterPage
                        new RegisterPage().setVisible(true);
                    } else {
                        // Jika tidak kosong, luncurkan LoginPage
                        new LoginPage().setVisible(true);
                    }
                    
                } catch (SQLException e) {
                    // Menangani kesalahan SQL
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memeriksa database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
