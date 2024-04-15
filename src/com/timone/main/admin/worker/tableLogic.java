/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.worker;

/**
 *
 * @author Fadel
 */
import com.timone.connection.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class tableLogic {
    private JTable jTable5; // assuming jTable5 is your JTable instance

    public tableLogic() {
        jTable5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // double-click detected
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    
                    if (column != -1 && row != -1) {
                        DefaultTableModel model = (DefaultTableModel) target.getModel();
                        String oldValue = (String) model.getValueAt(row, column);
                        String newValue = JOptionPane.showInputDialog("Enter new value:", oldValue);
                        if (newValue != null && !newValue.isEmpty()) {
                            model.setValueAt(newValue, row, column);
                            updateDatabase(row, column, newValue);
                            JOptionPane.showMessageDialog(null, "Update successful.");
                        }
                    }
                }
            }
        });
    }

    private void updateDatabase(int row, int column, String newValue) {
        try {
            Connection conn = DBConnection.getConnection();
            String columnName = ""; // Set this based on column index
            switch (column) {
                case 0:
                    columnName = "nama";
                    break;
                case 1:
                    columnName = "email";
                    break;
                case 2:
                    columnName = "username";
                    break;
                case 3:
                    columnName = "password";
                    break;
                case 4:
                    columnName = "rfid";
                    break;
                default:
                    break;
            }
            String sql = "UPDATE akun_karyawan SET " + columnName + " = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newValue);
            pstmt.setInt(2, row + 1); // assuming id is in consecutive order starting from 1
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Update failed. Please try again.");
        }
    }
}
