/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.timone.gate;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.timone.connection.DbConnection;
import com.timone.main.admin.MainAdmin;
import com.timone.main.cashier.CashierForm;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import javax.swing.JOptionPane;


/**
 *
 * @author Fadel
 */
public class LoginPage extends javax.swing.JFrame {
    
    
    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        UIManager.put("TextComponent.arc", 10);
        initComponents();
        setLocationRelativeTo(null);
        // Menambahkan ActionListener ke JTextField
    jTextField1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            jPasswordField1.requestFocusInWindow(); // Memindahkan fokus ke JPasswordField
        }
    });

    // Menambahkan ActionListener ke JPasswordField
    jPasswordField1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Memanggil metode checkCredentials() saat tombol "Enter" ditekan pada JPasswordField
            checkPassword();
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Login");

        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        jTextField1.setDoubleBuffered(true);
        jTextField1.setFocusCycleRoot(true);

        jButton1.setText("Login");
        jButton1.setBorderPainted(false);
        jButton1.setDoubleBuffered(true);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPasswordField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");

        jButton2.setText("login dengan RFID");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDoubleBuffered(true);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.setRequestFocusEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jPasswordField1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        checkPassword();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        RfidPage.main(new String[]{});
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void checkPassword() {
        String username = jTextField1.getText(); 
        String password = jPasswordField1.getText(); 
        String kodeUser = null; // Variabel untuk menyimpan kode_user
        String nama = null; // Variabel untuk menyimpan nama pengguna

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmtAbout = conn.prepareStatement("SELECT * FROM about WHERE username=? AND password=?");
             PreparedStatement stmtAkun = conn.prepareStatement("SELECT kode_user, nama FROM akun_karyawan WHERE username=? AND password=?");
             PreparedStatement stmtRecovery = conn.prepareStatement("SELECT * FROM about WHERE keyword_recovery=?")) {

            // Periksa tabel "about" untuk kredensial
            stmtAbout.setString(1, username);
            stmtAbout.setString(2, password);
            try (ResultSet rsAbout = stmtAbout.executeQuery()) {
                if (rsAbout.next()) {
                    // Jika ditemukan di tabel "about", buka MainAdmin
                    MainAdmin.main(new String[]{});
                    this.dispose(); 
                    return;
                }
            }

            // Periksa tabel "akun_karyawan" untuk kredensial jika tidak ditemukan di "about"
            stmtAkun.setString(1, username);
            stmtAkun.setString(2, password);
            try (ResultSet rsAkun = stmtAkun.executeQuery()) {
                if (rsAkun.next()) {
                    kodeUser = rsAkun.getString("kode_user"); // Ambil kode_user dari hasil query
                    nama = rsAkun.getString("nama"); // Ambil nama dari hasil query
                    // Jika ditemukan di tabel "akun_karyawan", buka CashierForm
                    CashierForm cashierForm = new CashierForm();
                    cashierForm.setNama(nama); // Set nama pengguna di CashierForm
                    cashierForm.setLocationRelativeTo(null);
                    cashierForm.setVisible(true); // Pastikan CashierForm ditampilkan setelah semua pengaturan selesai
                    insertAbsensi(conn, kodeUser); // Masukkan log absensi menggunakan kode_user
                    this.dispose(); 
                    return;
                }
            }

            // Periksa tabel "about" untuk keyword_recovery
            stmtRecovery.setString(1, password);
            try (ResultSet rsRecovery = stmtRecovery.executeQuery()) {
                if (rsRecovery.next()) {
                    // Jika ditemukan di tabel "about" dengan keyword_recovery, munculkan JOptionPane
                    String newPassword = JOptionPane.showInputDialog(this, "Masukkan password baru :", "Recovery Admin", JOptionPane.PLAIN_MESSAGE);
                    if (newPassword != null && !newPassword.isEmpty()) {
                        // Lakukan pembaruan password di database
                        try (PreparedStatement stmtUpdatePassword = conn.prepareStatement("UPDATE about SET password = ? WHERE keyword_recovery = ?")) {
                            stmtUpdatePassword.setString(1, newPassword);
                            stmtUpdatePassword.setString(2, password);
                            int rowsUpdated = stmtUpdatePassword.executeUpdate();
                            if (rowsUpdated > 0) {
                                // Beritahu pengguna bahwa password telah diubah
                                JOptionPane.showMessageDialog(this, "Password admin telah berhasil diubah.");
                            } else {
                                JOptionPane.showMessageDialog(this, "Gagal mengubah password.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Password tidak boleh kosong.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }
            }


            // Jika tidak ditemukan di kedua tabel, tampilkan pesan kesalahan
            JOptionPane.showMessageDialog(this, "Username atau Password salah!");
            jTextField1.setText("");
            jPasswordField1.setText("");
            jTextField1.requestFocusInWindow();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertAbsensi(Connection conn, String kodeUser) throws SQLException {
        try {
            // Generate unique absensi ID
            String idAbsensi = generateUniqueAbsensiId(conn);

            // Check if absensi entry already exists for the kode_user on today's date
            boolean isAlreadyLogged = false;
            try (PreparedStatement stmtCheckAbsensi = conn.prepareStatement("SELECT COUNT(*) FROM absensi WHERE kode_user=? AND tanggal_kehadiran=?")) {
                stmtCheckAbsensi.setString(1, kodeUser);
                stmtCheckAbsensi.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                try (ResultSet rs = stmtCheckAbsensi.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        isAlreadyLogged = count > 0;
                    }
                }
            }

            // If absensi entry does not exist for the kode_user on today's date, insert a new absensi log
            if (!isAlreadyLogged) {
                try (PreparedStatement stmtInsertAbsensi = conn.prepareStatement("INSERT INTO absensi (id_absensi, kode_user, tanggal_kehadiran, waktu) VALUES (?, ?, ?, ?)")) {
                    stmtInsertAbsensi.setString(1, idAbsensi);
                    stmtInsertAbsensi.setString(2, kodeUser);
                    stmtInsertAbsensi.setDate(3, java.sql.Date.valueOf(LocalDate.now())); // Today's date
                    stmtInsertAbsensi.setTime(4, java.sql.Time.valueOf(LocalTime.now())); // Current time
                    stmtInsertAbsensi.executeUpdate();
                }
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }
    
        // Metode untuk menghasilkan ID Absensi unik dengan panjang 10 angka
        private String generateUniqueAbsensiId(Connection conn) throws SQLException {
            Random random = new Random();
            String idAbsensi;
            boolean isUnique;

            do {
                idAbsensi = String.format("%010d", random.nextInt(1_000_000_000));

                // Check if idAbsensi already exists in the database
                try (PreparedStatement stmtCheckId = conn.prepareStatement("SELECT COUNT(*) FROM absensi WHERE id_absensi=?")) {
                    stmtCheckId.setString(1, idAbsensi);
                    try (ResultSet rs = stmtCheckId.executeQuery()) {
                        isUnique = !rs.next() || rs.getInt(1) == 0;
                    }
                }
            } while (!isUnique);

            return idAbsensi;
        }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatGitHubIJTheme.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
