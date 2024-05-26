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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Fadel
 */
public class DistributorLogic {

    public static void DistributorTable(JTable jTable4, JTextField jTextField4) {
        try {
            // Mendapatkan koneksi ke database dari kelas DbConnection
            Connection conn = DbConnection.getConnection();

            // Membuat statement SQL untuk mengambil data distributor berdasarkan pencarian
            String sql = "SELECT kode_distributor, nama_distributor, alamat, kontak_utama, email, nomor_utama FROM distributor " +
                    "WHERE LOWER(nama_distributor) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set parameter pencarian secara dinamis
            String searchQuery = "%" + jTextField4.getText().toLowerCase() + "%";
            stmt.setString(1, searchQuery);

            ResultSet rs = stmt.executeQuery();

            // Mendapatkan model tabel yang ada
            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();

            // Menghapus semua baris yang sudah ada dari model tabel
            model.setRowCount(0);

            // Memproses hasil kueri dan menambahkannya ke model tabel
            while (rs.next()) {
                Object[] row = {
                        rs.getString("kode_distributor"),
                        rs.getString("nama_distributor"),
                        rs.getString("alamat"),
                        rs.getString("kontak_utama"),
                        rs.getString("email"),
                        rs.getString("nomor_utama")
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

        jTable4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = jTable4.rowAtPoint(e.getPoint());
                if (r >= 0 && r < jTable4.getRowCount()) {
                    jTable4.setRowSelectionInterval(r, r);
                } else {
                    jTable4.clearSelection();
                }

                int rowIndex = jTable4.getSelectedRow();
                if (rowIndex < 0)
                    return;

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = new JPopupMenu();

                    JMenuItem option1 = new JMenuItem("Update Nama Distributor");
                    JMenuItem option2 = new JMenuItem("Update Alamat Distributor");
                    JMenuItem option3 = new JMenuItem("Update Kontak Person Distributor");
                    JMenuItem option4 = new JMenuItem("Update Email Distributor");
                    JMenuItem option5 = new JMenuItem("Update Telepon Distributor");
                    JMenuItem option6 = new JMenuItem("Delete Distributor");

                    // Update Nama Distributor
                    option1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String newName = JOptionPane.showInputDialog(null, "Masukkan Nama Baru:");
                            if (newName != null && !newName.trim().isEmpty()) {
                                String distributorID = (String) jTable4.getValueAt(rowIndex, 0);
                                try {
                                    updateDistributorName(distributorID, newName);
                                } catch (SQLException ex) {
                                    Logger.getLogger(DistributorLogic.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                jTable4.setValueAt(newName, rowIndex, 1); // Assuming name is in column 1
                            }
                        }
                    });

                    // Update Alamat Distributor
                    option2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String newAddress = JOptionPane.showInputDialog(null, "Masukkan Alamat Baru:");
                            if (newAddress != null && !newAddress.trim().isEmpty()) {
                                String distributorID = (String) jTable4.getValueAt(rowIndex, 0);
                                try {
                                    updateDistributorAddress(distributorID, newAddress);
                                } catch (SQLException ex) {
                                    Logger.getLogger(DistributorLogic.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                jTable4.setValueAt(newAddress, rowIndex, 2); // Assuming address is in column 2
                            }
                        }
                    });

                    // Update Kontak Person Distributor
                    option3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String newContactPerson = JOptionPane.showInputDialog(null, "Masukkan Kontak Person Baru:");
                            if (newContactPerson != null && !newContactPerson.trim().isEmpty()) {
                                String distributorID = (String) jTable4.getValueAt(rowIndex, 0);
                                try {
                                    updateDistributorContactPerson(distributorID, newContactPerson);
                                } catch (SQLException ex) {
                                    Logger.getLogger(DistributorLogic.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                jTable4.setValueAt(newContactPerson, rowIndex, 3); // Assuming contact person is in column 3
                            }
                        }
                    });

                    // Update Email Distributor
                    option4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String newEmail = JOptionPane.showInputDialog(null, "Masukkan Email Baru:");
                            if (newEmail != null && !newEmail.trim().isEmpty()) {
                                String distributorID = (String) jTable4.getValueAt(rowIndex, 0);
                                try {
                                    updateDistributorEmail(distributorID, newEmail);
                                } catch (SQLException ex) {
                                    Logger.getLogger(DistributorLogic.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                jTable4.setValueAt(newEmail, rowIndex, 4); // Assuming email is in column 4
                            }
                        }
                    });

                    // Update Telepon Distributor
                    option5.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String newPhone = JOptionPane.showInputDialog(null, "Masukkan Telepon Baru:");
                            if (newPhone != null && !newPhone.trim().isEmpty()) {
                                String distributorID = (String) jTable4.getValueAt(rowIndex, 0);
                                try {
                                    updateDistributorPhone(distributorID, newPhone);
                                } catch (SQLException ex) {
                                    Logger.getLogger(DistributorLogic.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                jTable4.setValueAt(newPhone, rowIndex, 5); // Assuming phone is in column 5
                            }
                        }
                    });

                    // Delete Distributor
                    option6.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int confirm = JOptionPane.showConfirmDialog(null, 
                                "Apakah Anda yakin ingin menghapus distributor ?", 
                                "Konfirmasi Hapus", 
                                JOptionPane.YES_NO_OPTION);

                            if (confirm == JOptionPane.YES_OPTION) {
                                String distributorID = (String) jTable4.getValueAt(rowIndex, 0);
                                deleteDistributor(distributorID);
                                ((DefaultTableModel) jTable4.getModel()).removeRow(rowIndex);
                            }
                        }
                    });

                    popup.add(option1);
                    popup.add(option2);
                    popup.add(option3);
                    popup.add(option4);
                    popup.add(option5);
                    popup.add(option6);

                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    // Method untuk menghapus distributor dari database
    private static void deleteDistributor(String distributorID) {
        try {
            // Lakukan koneksi ke database dan jalankan query penghapusan
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM distributor WHERE kode_distributor = ?");
            stmt.setString(1, distributorID);
            stmt.executeUpdate();
            conn.close();
            System.out.println("Distributor dengan ID " + distributorID + " berhasil dihapus dari database.");
        } catch (SQLException ex) {
            System.out.println("Gagal menghapus distributor dari database: " + ex.getMessage());
        }
    }
    
    // Methods to update database
    private static void updateDistributorName(String id, String newName) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String sql = "UPDATE distributor SET nama_distributor = ? WHERE kode_distributor = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newName);
            stmt.setString(2, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateDistributorAddress(String id, String newAddress) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String sql = "UPDATE distributor SET alamat = ? WHERE kode_distributor = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newAddress);
            stmt.setString(2, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateDistributorContactPerson(String id, String newContactPerson) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String sql = "UPDATE distributor SET kontak_utama = ? WHERE kode_distributor = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newContactPerson);
            stmt.setString(2, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateDistributorEmail(String id, String newEmail) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String sql = "UPDATE distributor SET email = ? WHERE kode_distributor = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newEmail);
            stmt.setString(2, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateDistributorPhone(String id, String newPhone) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String sql = "UPDATE distributor SET nomor_utama = ? WHERE kode_distributor = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPhone);
            stmt.setString(2, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
