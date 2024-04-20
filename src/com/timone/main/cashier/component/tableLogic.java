/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.cashier.component;

import com.timone.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fadel
 */
public class tableLogic {
    
    public static void updateTable(DefaultTableModel model, JTextField jTextField1, JTextField jTextField2, JTextField jTextField3, JTable jTable1, JLabel jLabel7) {
    String kodeBarang = jTextField1.getText();

    try {
        Connection conn = DBConnection.getConnection();

        String query = "SELECT kode_barang, nama_barang, satuan_obat, harga_pcs FROM barang WHERE kode_barang = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, kodeBarang);
        ResultSet rs = pst.executeQuery();
            
        if (rs.next()) {
            int qty = (jTextField2.getText().isEmpty()) ? 1 : Integer.parseInt(jTextField2.getText());
            if (qty == 0) {
                return;
            }

            int potonganPersen = (jTextField3.getText().isEmpty()) ? 0 : Integer.parseInt(jTextField3.getText());

            double hargaPcsDouble = rs.getDouble("harga_pcs");
            int hargaPcs = (int) hargaPcsDouble;

            int rowIndex = -1;
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 1).equals(kodeBarang)) {
                    rowIndex = i;
                    break;
                }
            }

            if (rowIndex != -1) {
                qty += (int) model.getValueAt(rowIndex, 4);
                potonganPersen += (int) model.getValueAt(rowIndex, 6);
                model.removeRow(rowIndex);
            }

            double totalHargaDouble = hargaPcs * qty;
            double totalHarga = Math.round(totalHargaDouble);

            double potonganHarga = 0;
            if (potonganPersen != 0) {
                potonganHarga = totalHarga * (potonganPersen / 100.0);
            }

            double totalHargaSetelahPotongan = totalHarga - potonganHarga;

            if (totalHargaSetelahPotongan < 0) {
                totalHargaSetelahPotongan = 0;
            }

            String totalHargaString = String.format("%.0f", totalHargaSetelahPotongan);

            Object[] row = {model.getRowCount() + 1, kodeBarang, rs.getString("nama_barang"), rs.getString("satuan_obat"), qty, hargaPcs, potonganPersen, totalHargaString};
            model.addRow(row);

            double totalSemua = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                totalSemua += Double.parseDouble(model.getValueAt(i, 7).toString());
            }
            NumberFormat formatter = new DecimalFormat("#,###");
            String totalRupiah = "Rp " + formatter.format(totalSemua);
            jLabel7.setText(totalRupiah);
        } else {
            JOptionPane.showMessageDialog(null, "Kode barang tidak ditemukan");
        }

        rs.close();
        pst.close();
        conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
    }
}
}
