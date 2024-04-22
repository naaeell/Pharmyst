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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Fadel
 */
public class PurchaseLogic {

    public static void PurchaseTable(JTable jTable3) {
        try {
            // Mendapatkan koneksi ke database dari kelas DbConnection
            Connection conn = DbConnection.getConnection();

            // Membuat statement SQL untuk mengambil data pembelian
            String sql = "SELECT " +
                         "pembelian.kode_pemesanan, " +
                         "pembelian.tanggal_pemesanan, " +
                         "distributor.nama_distributor, " +
                         "pembelian.kode_barang, " +
                         "barang.nama_barang, " +
                         "kategori_obat.nama_kategori, " +
                         "bentuk_obat.nama_bentuk_obat, " +
                         "barang.satuan_obat, " +
                         "pembelian.jumlah_pembelian, " +
                         "pembelian.harga_total " +
                         "FROM pembelian " +
                         "INNER JOIN barang ON pembelian.kode_barang = barang.kode_barang " +
                         "INNER JOIN distributor ON pembelian.kode_distributor = distributor.kode_distributor " +
                         "INNER JOIN kategori_obat ON barang.kode_kategori_obat = kategori_obat.kode_kategori_obat " +
                         "INNER JOIN bentuk_obat ON barang.kode_bentuk_obat = bentuk_obat.kode_bentuk_obat";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Mendapatkan model tabel yang ada
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();

            // Menghapus semua baris yang sudah ada dari model tabel
            model.setRowCount(0);

            // Memproses hasil kueri dan menambahkannya ke model tabel
            while (rs.next()) {
                Object[] row = {
                        rs.getString("kode_pemesanan"),
                        rs.getDate("tanggal_pemesanan"),
                        rs.getString("nama_distributor"),
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("nama_kategori"),
                        rs.getString("nama_bentuk_obat"),
                        rs.getString("satuan_obat"),
                        rs.getInt("jumlah_pembelian"),
                        rs.getInt("harga_total")
                };
                model.addRow(row);
            }

            // Menutup koneksi
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jTable3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = jTable3.rowAtPoint(e.getPoint());
                if (r >= 0 && r < jTable3.getRowCount()) {
                    jTable3.setRowSelectionInterval(r, r);
                } else {
                    jTable3.clearSelection();
                }

                int rowIndex = jTable3.getSelectedRow();
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
