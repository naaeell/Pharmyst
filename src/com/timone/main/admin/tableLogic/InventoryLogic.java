/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.tableLogic;

import com.timone.connection.DbConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fadel
 */
public class InventoryLogic {

    public static void inventoryTable(JTable jTable1, JTextField jTextField1) {
        try {
        // Mendapatkan koneksi ke database dari kelas DbConnection
            Connection conn = DbConnection.getConnection();

            String sql = "SELECT " +
                 "    barang.kode_barang, " +
                 "    barang.nama_barang, " +
                 "    kategori_obat.nama_kategori, " +
                 "    bentuk_obat.nama_bentuk_obat, " +
                 "    barang.satuan_obat, " +
                 "    barang.kadaluarsa, " +
                 "    barang.kuantitas, " +
                 "    barang.harga_pcs " +
                 "FROM " +
                 "    barang " +
                 "INNER JOIN kategori_obat ON barang.kode_kategori_obat = kategori_obat.kode_kategori_obat " +
                 "INNER JOIN bentuk_obat ON barang.kode_bentuk_obat = bentuk_obat.kode_bentuk_obat " +
                 "WHERE " +
                 "    LOWER(barang.kode_barang) LIKE ? OR " +
                 "    LOWER(barang.nama_barang) LIKE ? OR " +
                 "    LOWER(kategori_obat.nama_kategori) LIKE ? OR " +
                 "    LOWER(bentuk_obat.nama_bentuk_obat) LIKE ? OR " +
                 "    LOWER(barang.satuan_obat) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
        String searchQuery = "%" + jTextField1.getText().toLowerCase() + "%"; // Mengubah ke huruf kecil
                for (int i = 1; i <= 5; i++) {
                    stmt.setString(i, searchQuery);
                }
            ResultSet rs = stmt.executeQuery();


        // Mendapatkan model tabel yang ada
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Menghapus semua baris yang sudah ada dari model tabel
            model.setRowCount(0);

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
                            rs.getInt("harga_pcs")
                    };
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

                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu popup = new JPopupMenu();

                    // Tambahkan opsi yang ingin Anda tampilkan di sini
                JMenuItem option1 = new JMenuItem("Tandai Stok Kosong");
                JMenuItem option2 = new JMenuItem("Update Stok");
                JMenuItem option3 = new JMenuItem("Hapus Barang");
                JMenuItem option4 = new JMenuItem("Detail Barang");

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
                    
                    option3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 2 dipilih
                            System.out.println("Opsi 3 dipilih pada baris: " + rowIndex);
                        }
                    });
                    
                    option4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Tindakan yang akan dilakukan ketika opsi 2 dipilih
                            System.out.println("Opsi 4 dipilih pada baris: " + rowIndex);
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

    
    // Method untuk menambah bulan pada tanggal (buat InventoryTable)
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
