/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.runable;

import com.timone.gate.SetupPage;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.timone.connection.DbConnection;
import com.timone.gate.LoginPage;
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
        FlatGitHubIJTheme.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Mendapatkan koneksi ke database
                    Connection conn = DbConnection.getConnection();
                    
                    // Membuat statement SQL untuk mengambil jumlah data dari tabel barang
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM about");
                    rs.next();
                    int rowCount = rs.getInt("total");
                    
                    // Menutup sumber daya
                    rs.close();
                    stmt.close();
                    conn.close();
                    
                    // Memeriksa apakah tabel barang kosong atau tidak
                    if (rowCount == 0) {
                        // Jika tabel kosong, luncurkan SetupPage
                        SetupPage.main(new String[]{});
                    } else {
                        // Jika tidak kosong, luncurkan LoginPage
                        LoginPage.main(new String[]{});
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
