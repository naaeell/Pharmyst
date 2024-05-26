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
public class OperationalLogic {

    public static void OperationTable(JTable jTable6, JTextField jTextField6) {
        try {
            // Mendapatkan koneksi ke database dari kelas DbConnection
            Connection conn = DbConnection.getConnection();

            // Membuat statement SQL untuk mengambil data biaya dengan pencarian
            String sql = "SELECT nama_biaya, deskripsi, total_biaya FROM operasional " +
                         "WHERE LOWER(nama_biaya) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameter pencarian secara dinamis
            String searchQuery = "%" + jTextField6.getText().toLowerCase() + "%";
            stmt.setString(1, searchQuery);

            ResultSet rs = stmt.executeQuery();

            // Mendapatkan model tabel yang ada
            DefaultTableModel model = (DefaultTableModel) jTable6.getModel();

            // Menghapus semua baris yang sudah ada dari model tabel
            model.setRowCount(0);

            // Memproses hasil kueri dan menambahkannya ke model tabel
            while (rs.next()) {
                Object[] row = {
                        rs.getString("nama_biaya"),
                        rs.getString("deskripsi"),
                        rs.getInt("total_biaya")
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

        jTable6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = jTable6.rowAtPoint(e.getPoint());
                if (r >= 0 && r < jTable6.getRowCount()) {
                    jTable6.setRowSelectionInterval(r, r);
                } else {
                    jTable6.clearSelection();
                }

                int rowIndex = jTable6.getSelectedRow();
                if (rowIndex < 0)
                    return;

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = new JPopupMenu();

                    // Tambahkan opsi yang ingin Anda tampilkan di sini
                    JMenuItem option1 = new JMenuItem("Update Nama Biaya Operasional");
                    JMenuItem option2 = new JMenuItem("Update Deskripsi Operasional");
                    JMenuItem option3 = new JMenuItem("Update Total Biaya Operasional");
                    JMenuItem option4 = new JMenuItem("Delete Operasional");

                    // Tambahkan action listener untuk setiap opsi
                    option1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 1 dipilih (Update Nama Biaya Operasional)
                            System.out.println("Opsi 1 dipilih pada baris: " + rowIndex);
                            // Mendapatkan nilai dari tabel
                            String namaBiaya = (String) jTable6.getValueAt(rowIndex, 0);
                            // Meminta pengguna untuk memasukkan nilai yang baru
                            String newNamaBiaya = JOptionPane.showInputDialog("Masukkan nama biaya yang baru:");
                            // Periksa apakah pengguna mengkonfirmasi perubahan dan nilai yang dimasukkan tidak kosong
                            if (newNamaBiaya != null && !newNamaBiaya.isEmpty()) {
                                try {
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk melakukan pembaruan
                                    String sql = "UPDATE operasional SET nama_biaya = ? WHERE nama_biaya = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setString(1, newNamaBiaya);
                                    stmt.setString(2, namaBiaya);
                                    // Eksekusi pernyataan
                                    int rowsUpdated = stmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        System.out.println("Nama biaya berhasil diperbarui.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        OperationTable(jTable6, jTextField6);
                                    } else {
                                        System.out.println("Gagal memperbarui nama biaya.");
                                    }
                                    // Menutup koneksi dan statement
                                    stmt.close();
                                    conn.close();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });

                    option2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 2 dipilih (Update Deskripsi Operasional)
                            System.out.println("Opsi 2 dipilih pada baris: " + rowIndex);
                            // Mendapatkan nilai dari tabel
                            String deskripsi = (String) jTable6.getValueAt(rowIndex, 1);
                            // Meminta pengguna untuk memasukkan nilai yang baru
                            String newDeskripsi = JOptionPane.showInputDialog("Masukkan deskripsi baru:");
                            // Periksa apakah pengguna mengkonfirmasi perubahan dan nilai yang dimasukkan tidak kosong
                            if (newDeskripsi != null && !newDeskripsi.isEmpty()) {
                                try {
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk melakukan pembaruan
                                    String sql = "UPDATE operasional SET deskripsi = ? WHERE deskripsi = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setString(1, newDeskripsi);
                                    stmt.setString(2, deskripsi);
                                    // Eksekusi pernyataan
                                    int rowsUpdated = stmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        System.out.println("Deskripsi berhasil diperbarui.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        OperationTable(jTable6, jTextField6);
                                    } else {
                                        System.out.println("Gagal memperbarui deskripsi.");
                                    }
                                    // Menutup koneksi dan statement
                                    stmt.close();
                                    conn.close();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });

                    option3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 3 dipilih (Update Total Biaya Operasional)
                            System.out.println("Opsi 3 dipilih pada baris: " + rowIndex);
                            // Mendapatkan nilai dari tabel
                            int totalBiaya = (int) jTable6.getValueAt(rowIndex, 2);
                            // Meminta pengguna untuk memasukkan nilai yang baru
                            String newTotalBiayaStr = JOptionPane.showInputDialog("Masukkan total biaya baru:");
                            // Periksa apakah pengguna mengkonfirmasi perubahan dan nilai yang dimasukkan adalah angka
                            if (newTotalBiayaStr != null && !newTotalBiayaStr.isEmpty()) {
                                try {
                                    int newTotalBiaya = Integer.parseInt(newTotalBiayaStr);
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk melakukan pembaruan
                                    String sql = "UPDATE operasional SET total_biaya = ? WHERE total_biaya = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setInt(1, newTotalBiaya);
                                    stmt.setInt(2, totalBiaya);
                                    // Eksekusi pernyataan
                                    int rowsUpdated = stmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        System.out.println("Total biaya berhasil diperbarui.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        OperationTable(jTable6, jTextField6);
                                    } else {
                                        System.out.println("Gagal memperbarui total biaya.");
                                    }
                                    // Menutup koneksi dan statement
                                    stmt.close();
                                    conn.close();
                                } catch (NumberFormatException | SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });

                    option4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 4 dipilih (Delete Operasional)
                            System.out.println("Opsi 4 dipilih pada baris: " + rowIndex);
                            // Meminta konfirmasi pengguna sebelum menghapus
                            int option = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                try {
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk menghapus data
                                    String sql = "DELETE FROM operasional WHERE nama_biaya = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setString(1, (String) jTable6.getValueAt(rowIndex, 0));
                                    // Eksekusi pernyataan
                                    int rowsDeleted = stmt.executeUpdate();
                                    if (rowsDeleted > 0) {
                                        System.out.println("Data berhasil dihapus.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        OperationTable(jTable6, jTextField6);
                                    } else {
                                        System.out.println("Gagal menghapus data.");
                                    }
                                    // Menutup koneksi dan statement
                                    stmt.close();
                                    conn.close();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });

                    // Tambahkan opsi ke menu popup
                    popup.add(option1);
                    popup.add(option2);
                    popup.add(option3);
                    popup.add(option4);

                    // Tampilkan menu popup di posisi klik mouse
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
}