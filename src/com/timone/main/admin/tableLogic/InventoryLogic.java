/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.tableLogic;

import com.timone.connection.DbConnection;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Fadel
 */
public class InventoryLogic {

    public static void inventoryTable(JTable jTable1, JTextField jTextField1) {
        try {
            // Mendapatkan koneksi ke database dari kelas DbConnection
            Connection conn = DbConnection.getConnection();

            // Membuat statement SQL tanpa logika status barang
            String sql = "SELECT " +
                         "    barang.kode_barang, " +
                         "    barang.nama_barang, " +
                         "    kategori_obat.nama_kategori, " +
                         "    bentuk_obat.nama_bentuk_obat, " +
                         "    barang.satuan_obat, " +
                         "    barang.kadaluarsa, " +
                         "    barang.kuantitas, " +
                         "    barang.harga_jual " +
                         "FROM " +
                         "    barang " +
                         "INNER JOIN kategori_obat ON barang.kode_kategori_obat = kategori_obat.kode_kategori_obat " +
                         "INNER JOIN bentuk_obat ON barang.kode_bentuk_obat = bentuk_obat.kode_bentuk_obat " +
                         "WHERE " +
                         "    barang.kode_barang LIKE ? OR " +
                         "    barang.nama_barang LIKE ? OR " +
                         "    kategori_obat.nama_kategori LIKE ? OR " +
                         "    bentuk_obat.nama_bentuk_obat LIKE ? OR " +
                         "    barang.satuan_obat LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String searchQuery = "%" + jTextField1.getText() + "%";
            for (int i = 1; i <= 5; i++) {
                stmt.setString(i, searchQuery);
            }
            ResultSet rs = stmt.executeQuery();

            // Menghapus semua baris yang sudah ada dari model tabel
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // List untuk menyimpan baris data
            List<Object[]> rowDataList = new ArrayList<>();

            // Memproses hasil kueri
            while (rs.next()) {
                // Menentukan status berdasarkan tanggal kadaluarsa
                String status;
                if (rs.getDate("kadaluarsa").before(new Date())) {
                    status = "Expired";
                } else if (rs.getDate("kadaluarsa").before(addMonths(new Date(), 3))) {
                    status = "Mendekati Expired";
                } else if (rs.getInt("kuantitas") <= 0) {
                    status = "Stok habis";
                } else if (rs.getInt("kuantitas") <= 15) {
                    status = "Stok akan habis";
                } else if (rs.getDate("kadaluarsa").before(addMonths(new Date(), 6))) {
                    status = "Expired dalam 6 bulan";
                } else {
                    status = "Aman";
                }

                // Format tanggal kadaluarsa
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                String kadaluarsaFormatted = dateFormat.format(rs.getDate("kadaluarsa"));

                Object[] row = {
                        status,
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("nama_kategori"),
                        rs.getString("nama_bentuk_obat"),
                        rs.getString("satuan_obat"),
                        kadaluarsaFormatted,
                        rs.getInt("kuantitas"),
                        rs.getInt("harga_jual")
                };

                // Tambahkan baris ke list
                rowDataList.add(row);
            }

            // Urutkan list berdasarkan status
            rowDataList.sort((row1, row2) -> {
                String status1 = (String) row1[0];
                String status2 = (String) row2[0];

                // Memberikan prioritas untuk status "Expired" atau "Stok habis"
                if (status1.equals("Expired") || status1.equals("Stok habis")) {
                    if (status2.equals("Expired") || status2.equals("Stok habis")) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else if (status2.equals("Expired") || status2.equals("Stok habis")) {
                    return 1;
                }

                // Memberikan prioritas untuk status "Mendekati Expired"
                else if (status1.equals("Mendekati Expired")) {
                    if (status2.equals("Expired") || status2.equals("Stok habis") || status2.equals("Expired dalam 6 bulan") || status2.equals("Stok akan habis")) {
                        return -1;
                    } else if (status2.equals("Mendekati Expired")) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else if (status2.equals("Mendekati Expired")) {
                    return 1;
                }

                // Memberikan prioritas untuk status "Expired dalam 6 bulan" atau "Stok akan habis"
                else if (status1.equals("Expired dalam 6 bulan") || status1.equals("Stok akan habis")) {
                    if (status2.equals("Expired dalam 6 bulan") || status2.equals("Stok akan habis")) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else if (status2.equals("Expired dalam 6 bulan") || status2.equals("Stok akan habis")) {
                    return 1;
                }

                // Untuk status default
                else {
                    return 0;
                }
            });

            // Tambahkan baris yang sudah diurutkan ke model tabel
            for (Object[] row : rowDataList) {
                model.addRow(row);
            }

            // Setelah model tabel diisi ulang, panggil method setRowColor() untuk menerapkan render warna
            setRowColor(jTable1);

            // Menutup koneksi
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = jTable1.rowAtPoint(e.getPoint());
                if (r >= 0 && r < jTable1.getRowCount()) {
                    jTable1.setRowSelectionInterval(r, r);
                } else {
                    jTable1.clearSelection();
                }

                int rowIndex = jTable1.getSelectedRow();
                if (rowIndex < 0)
                    return;

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = new JPopupMenu();

                    // Tambahkan opsi yang ingin Anda tampilkan di sini
                    JMenuItem option1 = new JMenuItem("Tandai Stok Kosong");
                    JMenuItem option2 = new JMenuItem("Stock Opname");
                    JMenuItem option3 = new JMenuItem("Update Harga Jual");
                    JMenuItem option4 = new JMenuItem("Hapus Barang");

                    // Tambahkan action listener untuk setiap opsi
                    option1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menandai stok kosong?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                // Dapatkan kode_barang dari baris yang dipilih
                                String kodeBarang = (String) jTable1.getValueAt(rowIndex, 1);
                                // Panggil metode untuk memperbarui stok di database
                                updateStokKosong(kodeBarang);
                                // Perbarui tampilan tabel setelah perubahan di database
                                inventoryTable(jTable1, jTextField1);
                            }
                        }
                    });

                    option2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int stokSaatIni = (int) jTable1.getValueAt(rowIndex, 7);
                            if (stokSaatIni == 0) {
                                JOptionPane.showMessageDialog(null, "Stok saat ini 0, tidak bisa melakukan stock opname!", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin melakukan stock opname?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                // Dapatkan kode_barang dari baris yang dipilih
                                String kodeBarang = (String) jTable1.getValueAt(rowIndex, 1);

                                // Minta input jumlah stok baru
                                String stokBaruStr = JOptionPane.showInputDialog(null, "Masukkan jumlah stok baru (tidak boleh melebihi " + stokSaatIni + "):", "Input Stok", JOptionPane.PLAIN_MESSAGE);
                                if (stokBaruStr != null && !stokBaruStr.trim().isEmpty()) {
                                    try {
                                        int stokBaru = Integer.parseInt(stokBaruStr);
                                        if (stokBaru <= stokSaatIni) {
                                            // Panggil metode untuk memperbarui stok di database
                                            updateStokOpname(kodeBarang, stokBaru);
                                            // Perbarui tampilan tabel setelah perubahan di database
                                            inventoryTable(jTable1, jTextField1);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Jumlah stok baru tidak boleh melebihi stok saat ini!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null, "Jumlah stok baru harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    });
                    
                    option3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin mengupdate harga jual?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                // Dapatkan kode_barang dari baris yang dipilih
                                String kodeBarang = (String) jTable1.getValueAt(rowIndex, 1);
                                // Panggil metode untuk memperbarui stok di database
                                updateHargaJual(kodeBarang);
                                // Perbarui tampilan tabel setelah perubahan di database
                                inventoryTable(jTable1, jTextField1);
                            }
                        }
                    });
                    
                    option4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus barang? ini juga akan menghapus riwayat pembelian", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                // Dapatkan kode_barang dari baris yang dipilih
                                String kodeBarang = (String) jTable1.getValueAt(rowIndex, 1);
                                // Panggil metode untuk menghapus barang dari database
                                deleteBarang(kodeBarang);
                                // Perbarui tampilan tabel setelah perubahan di database
                                inventoryTable(jTable1, jTextField1);
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
    
    public static void deleteBarang(String kodeBarang) {
        try {
            Connection conn = DbConnection.getConnection();
            String sqlDelete = "DELETE FROM barang WHERE kode_barang = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
            pstmt.setString(1, kodeBarang);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Metode untuk memperbarui stok barang menjadi 0 di database
    public static void updateStokKosong(String kodeBarang) {
        try {
            Connection conn = DbConnection.getConnection();
            String sqlUpdate = "UPDATE barang SET kuantitas = 0 WHERE kode_barang = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setString(1, kodeBarang);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Metode untuk memperbarui harga jual
    public static void updateHargaJual(String kodeBarang) {
        // Ambil input harga jual baru dari JOptionPane
        String inputHargaJual = JOptionPane.showInputDialog("Masukkan harga jual baru untuk kode barang " + kodeBarang + ":");
        
        try {
            // Parse input ke tipe data yang sesuai (misalnya, double)
            double hargaJualBaru = Double.parseDouble(inputHargaJual);
            
            // Update harga jual dalam basis data
            Connection conn = DbConnection.getConnection();
            String sqlUpdate = "UPDATE barang SET harga_jual = ? WHERE kode_barang = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setDouble(1, hargaJualBaru);
            pstmt.setString(2, kodeBarang);
            pstmt.executeUpdate();
            
            // Tutup PreparedStatement dan Connection
            pstmt.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Harga jual berhasil diperbarui.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input tidak valid. Harap masukkan angka yang valid.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memperbarui harga jual.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Metode untuk memperbarui stok barang dengan jumlah baru di database
    public static void updateStokOpname(String kodeBarang, int stokBaru) {
        try {
            Connection conn = DbConnection.getConnection();
            String sqlUpdate = "UPDATE barang SET kuantitas = ? WHERE kode_barang = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setInt(1, stokBaru);
            pstmt.setString(2, kodeBarang);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method untuk menambah bulan ke tanggal
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static void setRowColor(JTable jTable1) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Mengambil nilai dari kolom status pada baris saat ini
                String status = (String) table.getModel().getValueAt(row, 0);

                // Memberikan warna latar belakang berdasarkan status
                if (status.equals("Expired") || status.equals("Mendekati Expired") || status.equals("Stok habis")) {
                    c.setBackground(Color.decode("#ff6961")); // Merah
                } else if (status.equals("Expired dalam 6 bulan") || status.equals("Stok akan habis")) {
                    c.setBackground(Color.decode("#ff964f")); // Orange
                } else {
                    c.setBackground(table.getBackground()); // Warna default untuk status lainnya
                }

                // Memberi warna teks putih pada baris dengan status selain "Aman"
                if (!status.equals("Aman")) {
                    c.setForeground(Color.WHITE);
                } else {
                    c.setForeground(table.getForeground()); // Mengembalikan warna teks default jika status adalah "Aman"
                }

                return c;
            }
        };

        // Mengatur renderer untuk semua kolom
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}
