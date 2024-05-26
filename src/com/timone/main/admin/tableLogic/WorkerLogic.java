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
                    JMenuItem option1 = new JMenuItem("Update Nama Karyawan");
                    JMenuItem option2 = new JMenuItem("Update Username Karyawan");
                    JMenuItem option3 = new JMenuItem("Update Password Karyawan");
                    JMenuItem option4 = new JMenuItem("Update Email Karyawan");
                    JMenuItem option5 = new JMenuItem("Update Kode Akses Karyawan");
                    JMenuItem option6 = new JMenuItem("Delete Karyawan");
                    
                    // Tambahkan action listener untuk setiap opsi
                    option1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 1 dipilih (Update Nama Karyawan)
                            System.out.println("Opsi 1 dipilih pada baris: " + rowIndex);
                            // Mendapatkan nilai dari tabel
                            String nama = (String) jTable5.getValueAt(rowIndex, 0);
                            // Meminta pengguna untuk memasukkan nilai yang baru
                            String newNama = JOptionPane.showInputDialog("Masukkan nama karyawan yang baru:");
                            // Periksa apakah pengguna mengkonfirmasi perubahan dan nilai yang dimasukkan tidak kosong
                            if (newNama != null && !newNama.isEmpty()) {
                                try {
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk melakukan pembaruan
                                    String sql = "UPDATE akun_karyawan SET nama = ? WHERE nama = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setString(1, newNama);
                                    stmt.setString(2, nama);
                                    // Eksekusi pernyataan
                                    int rowsUpdated = stmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        System.out.println("Nama karyawan berhasil diperbarui.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        WorkerTable(jTable5, jTextField5);
                                    } else {
                                        System.out.println("Gagal memperbarui nama karyawan.");
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
                            // Tindakan yang akan dilakukan ketika opsi 2 dipilih (Update Username Karyawan)
                            System.out.println("Opsi 2 dipilih pada baris: " + rowIndex);
                            // Mendapatkan nilai dari tabel
                            String username = (String) jTable5.getValueAt(rowIndex, 1);
                            // Meminta pengguna untuk memasukkan nilai yang baru
                            String newUsername = JOptionPane.showInputDialog("Masukkan username karyawan yang baru:");
                            // Periksa apakah pengguna mengkonfirmasi perubahan dan nilai yang dimasukkan tidak kosong
                            if (newUsername != null && !newUsername.isEmpty()) {
                                try {
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk melakukan pembaruan
                                    String sql = "UPDATE akun_karyawan SET username = ? WHERE username = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setString(1, newUsername);
                                    stmt.setString(2, username);
                                    // Eksekusi pernyataan
                                    int rowsUpdated = stmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        System.out.println("Username karyawan berhasil diperbarui.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        WorkerTable(jTable5, jTextField5);
                                    } else {
                                        System.out.println("Gagal memperbarui username karyawan.");
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
                            // Tindakan yang akan dilakukan ketika opsi 3 dipilih (Update Password Karyawan)
                            System.out.println("Opsi 3 dipilih pada baris: " + rowIndex);
                            // Mendapatkan nilai dari tabel
                            String password = (String) jTable5.getValueAt(rowIndex, 2);
                            // Meminta pengguna untuk memasukkan nilai yang baru
                            String newPassword = JOptionPane.showInputDialog("Masukkan password karyawan yang baru:");
                            // Periksa apakah pengguna mengkonfirmasi perubahan dan nilai yang dimasukkan tidak kosong
                            if (newPassword != null && !newPassword.isEmpty()) {
                                try {
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk melakukan pembaruan
                                    String sql = "UPDATE akun_karyawan SET password = ? WHERE password = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setString(1, newPassword);
                                    stmt.setString(2, password);
                                    // Eksekusi pernyataan
                                    int rowsUpdated = stmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        System.out.println("Password karyawan berhasil diperbarui.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        WorkerTable(jTable5, jTextField5);
                                    } else {
                                        System.out.println("Gagal memperbarui password karyawan.");
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

                    option4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 4 dipilih (Update Email Karyawan)
                            System.out.println("Opsi 4 dipilih pada baris: " + rowIndex);
                            // Mendapatkan nilai dari tabel
                            String email = (String) jTable5.getValueAt(rowIndex, 3);
                            // Meminta pengguna untuk memasukkan nilai yang baru
                            String newEmail = JOptionPane.showInputDialog("Masukkan email karyawan yang baru:");
                            // Periksa apakah pengguna mengkonfirmasi perubahan dan nilai yang dimasukkan tidak kosong
                            if (newEmail != null && !newEmail.isEmpty()) {
                                try {
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk melakukan pembaruan
                                    String sql = "UPDATE akun_karyawan SET email = ? WHERE email = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setString(1, newEmail);
                                    stmt.setString(2, email);
                                    // Eksekusi pernyataan
                                    int rowsUpdated = stmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        System.out.println("Email karyawan berhasil diperbarui.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        WorkerTable(jTable5, jTextField5);
                                    } else {
                                        System.out.println("Gagal memperbarui email karyawan.");
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

                    option5.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 5 dipilih (Update Kode Akses Karyawan)
                            System.out.println("Opsi 5 dipilih pada baris: " + rowIndex);
                            // Mendapatkan nilai dari tabel
                            String rfid = (String) jTable5.getValueAt(rowIndex, 4);
                            // Meminta pengguna untuk memasukkan nilai yang baru
                            String newRFID = JOptionPane.showInputDialog("Masukkan kode akses karyawan yang baru:");
                            // Periksa apakah pengguna mengkonfirmasi perubahan dan nilai yang dimasukkan tidak kosong
                            if (newRFID != null && !newRFID.isEmpty()) {
                                try {
                                    // Mendapatkan koneksi ke database dari kelas DbConnection
                                    Connection conn = DbConnection.getConnection();
                                    // Membuat statement SQL untuk melakukan pembaruan
                                    String sql = "UPDATE akun_karyawan SET rfid = ? WHERE rfid = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    // Set parameter pembaruan
                                    stmt.setString(1, newRFID);
                                    stmt.setString(2, rfid);
                                    // Eksekusi pernyataan
                                    int rowsUpdated = stmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        System.out.println("Kode akses karyawan berhasil diperbarui.");
                                        // Memperbarui tampilan tabel jika diperlukan
                                        WorkerTable(jTable5, jTextField5);
                                    } else {
                                        System.out.println("Gagal memperbarui kode akses karyawan.");
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

                    option6.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Action to be performed when option 6 is selected (Delete Employee)
                            System.out.println("Option 6 selected at row: " + rowIndex);
                            // Requesting user confirmation before deletion
                            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee's data?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                try {
                                    // Getting database connection from DbConnection class
                                    Connection conn = DbConnection.getConnection();

                                    // Getting the username from the second column (index 1) of jTable5
                                    String username = (String) jTable5.getValueAt(rowIndex, 1); // Assuming the username is in column index 1

                                    // SQL statement to fetch kode_user from akun_karyawan table based on username
                                    String selectKodeUserSQL = "SELECT kode_user FROM akun_karyawan WHERE username = ?";
                                    PreparedStatement stmtSelectKodeUser = conn.prepareStatement(selectKodeUserSQL);
                                    // Set parameter username
                                    stmtSelectKodeUser.setString(1, username);
                                    // Execute the statement to fetch kode_user
                                    ResultSet rs = stmtSelectKodeUser.executeQuery();

                                    // Getting kode_user
                                    String kodeUser = null;
                                    if (rs.next()) {
                                        kodeUser = rs.getString("kode_user");
                                    }
                                    rs.close();
                                    stmtSelectKodeUser.close();

                                    if (kodeUser != null) {
                                        // Deleting the attendance data for the employee from the absensi table
                                        String deleteAbsensiSQL = "DELETE FROM absensi WHERE kode_user = ?";
                                        PreparedStatement stmtDeleteAbsensi = conn.prepareStatement(deleteAbsensiSQL);
                                        stmtDeleteAbsensi.setString(1, kodeUser);
                                        int rowsDeletedAbsensi = stmtDeleteAbsensi.executeUpdate();
                                        stmtDeleteAbsensi.close();

                                        if (rowsDeletedAbsensi > 0) {
                                            System.out.println("Employee's attendance data successfully deleted.");
                                        } else {
                                            System.out.println("No employee attendance data was deleted.");
                                        }

                                        // Deleting the employee data from the akun_karyawan table
                                        String deleteKaryawanSQL = "DELETE FROM akun_karyawan WHERE kode_user = ?";
                                        PreparedStatement stmtDeleteKaryawan = conn.prepareStatement(deleteKaryawanSQL);
                                        stmtDeleteKaryawan.setString(1, kodeUser);
                                        int rowsDeletedKaryawan = stmtDeleteKaryawan.executeUpdate();
                                        stmtDeleteKaryawan.close();

                                        if (rowsDeletedKaryawan > 0) {
                                            System.out.println("Employee data successfully deleted.");
                                            // Updating the table view if needed
                                            WorkerTable(jTable5, jTextField5);
                                        } else {
                                            System.out.println("Failed to delete employee data.");
                                        }
                                    } else {
                                        System.out.println("Cannot find kode_user for username: " + username);
                                    }

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
                    popup.add(option5);
                    popup.add(option6);

                    // Tampilkan menu popup di posisi klik mouse
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
}