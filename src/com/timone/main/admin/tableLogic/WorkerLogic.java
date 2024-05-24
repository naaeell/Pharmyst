/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.tableLogic;
import com.timone.connection.DbConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fadel
 */
public class WorkerLogic {

    public static void WorkerTable(JTable jTable5, JTextField jTextField5) {
        try {
            // Mendapatkan koneksi ke database dari kelas DbConnection
            Connection conn = DbConnection.getConnection();

            // Membuat statement SQL untuk mengambil data karyawan dengan pencarian
            String sql = "SELECT nama, email, username, password, rfid FROM akun_karyawan " +
                         "WHERE LOWER(nama) LIKE ? OR LOWER(username) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameter pencarian secara dinamis
            String searchQuery = "%" + jTextField5.getText().toLowerCase() + "%";
            stmt.setString(1, searchQuery);
            stmt.setString(2, searchQuery);

            ResultSet rs = stmt.executeQuery();

            // Mendapatkan model tabel yang ada
            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();

            // Menghapus semua baris yang sudah ada dari model tabel
            model.setRowCount(0);

            // Memproses hasil kueri dan menambahkannya ke model tabel
            while (rs.next()) {
                Object[] row = {
                        rs.getString("nama"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("rfid")
                };
                model.addRow(row);
            }

            // Menutup koneksi dan statement
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jTable5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = jTable5.rowAtPoint(e.getPoint());
                if (r >= 0 && r < jTable5.getRowCount()) {
                    jTable5.setRowSelectionInterval(r, r);
                } else {
                    jTable5.clearSelection();
                }

                int rowIndex = jTable5.getSelectedRow();
                if (rowIndex < 0)
                    return;

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = new JPopupMenu();

                    // Tambahkan opsi yang ingin Anda tampilkan di sini
                    JMenuItem option1 = new JMenuItem("Opsi 1");
                    JMenuItem option2 = new JMenuItem("Opsi 2");

                    // Tambahkan action listener untuk setiap opsi
                    option1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 1 dipilih
                            System.out.println("Opsi 1 dipilih pada baris: " + rowIndex);
                        }
                    });

                    option2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 2 dipilih
                            System.out.println("Opsi 2 dipilih pada baris: " + rowIndex);
                        }
                    });

                    // Tambahkan opsi ke menu popup
                    popup.add(option1);
                    popup.add(option2);

                    // Tampilkan menu popup di posisi klik mouse
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
}