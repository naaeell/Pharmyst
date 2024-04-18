/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.timone.gate;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.timone.connection.DBConnection;
import com.timone.main.admin.mainAdmin;
import com.timone.main.cashier.CashierForm;
import javax.swing.UIManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fadel
 */
public class RfidPage extends javax.swing.JFrame {
    
    private String rfidCode;
    /**
     * Creates new form LoginPage
     */
    public RfidPage() {
        UIManager.put("TextComponent.arc", 10);
        initComponents();
        setLocationRelativeTo(null);
        jPasswordField1.requestFocusInWindow();
        jPasswordField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Ketika tombol dilepas, cek kode RFID
                rfidCode = String.valueOf(jPasswordField1.getPassword());
                checkRFID();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Scan disini");

        jButton1.setIcon(new FlatSVGIcon("com/timone/icon/svg/rfid.svg", 2.3f));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setDefaultCapable(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.setRolloverEnabled(false);
        jButton1.setVerifyInputWhenFocusTarget(false);

        jPasswordField1.setEditable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void checkRFID() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        conn = DBConnection.getConnection();
        
        // Periksa tabel 'about' untuk kode RFID
        String aboutQuery = "SELECT * FROM about WHERE rfid = ?";
        pstmt = conn.prepareStatement(aboutQuery);
        pstmt.setString(1, rfidCode);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            // Jika kode RFID ditemukan di tabel 'about', buka MainAdmin
            mainAdmin.main(new String[]{});
            
            this.dispose();
            return; // Keluar dari metode setelah membuka MainAdmin
        }

        // Periksa tabel 'akun_karyawan' untuk kode RFID
        String karyawanQuery = "SELECT * FROM akun_karyawan WHERE rfid = ?";
        pstmt = conn.prepareStatement(karyawanQuery);
        pstmt.setString(1, rfidCode);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            // Jika kode RFID ditemukan di tabel 'akun_karyawan', buka CashierForm
            CashierForm.main(new String[]{});
            insertAbsensi(conn, rs.getString("kode_user")); // Masukkan log absensi dengan kode_user
            this.dispose();
            return; // Keluar dari metode setelah membuka CashierForm
        }

        // Jika kode RFID tidak ditemukan di kedua tabel
        jPasswordField1.setText("");
        JOptionPane.showMessageDialog(this, "Akses tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memeriksa kode RFID.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Tutup semua sumber daya
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
    private void insertAbsensi(Connection conn, String kodeUser) {
        try {
            // Periksa apakah entri absensi sudah ada untuk kode_user pada tanggal hari ini
            boolean isAlreadyLogged = false;
            String checkAbsensiQuery = "SELECT COUNT(*) FROM absensi WHERE kode_user=? AND tanggal_kehadiran=?";
            try (PreparedStatement stmtCheckAbsensi = conn.prepareStatement(checkAbsensiQuery)) {
                stmtCheckAbsensi.setString(1, kodeUser);
                stmtCheckAbsensi.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                try (ResultSet rs = stmtCheckAbsensi.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        isAlreadyLogged = count > 0;
                    }
                }
            }

            // Jika belum ada entri absensi untuk kode_user pada tanggal hari ini, sisipkan log absensi baru
            if (!isAlreadyLogged) {
                String insertAbsensiQuery = "INSERT INTO absensi (kode_user, tanggal_kehadiran, waktu) VALUES (?, ?, ?)";
                try (PreparedStatement stmtInsertAbsensi = conn.prepareStatement(insertAbsensiQuery)) {
                    stmtInsertAbsensi.setString(1, kodeUser);
                    stmtInsertAbsensi.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now())); // Tanggal sekarang
                    stmtInsertAbsensi.setTime(3, java.sql.Time.valueOf(java.time.LocalTime.now())); // Waktu sekarang
                    stmtInsertAbsensi.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyisipkan log absensi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    FlatGitHubIJTheme.setup();
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            RfidPage rfidPage = new RfidPage();
            rfidPage.setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
