/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.timone.main.cashier;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.timone.connection.DBConnection;
import com.timone.main.cashier.component.labelLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.UIManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author Fadel
 */
public class CashierForm extends javax.swing.JFrame {
    
    

    
            
    public CashierForm() {
        
        UIManager.put( "TextComponent.arc", 10 );
        initComponents();
        
        // Menambahkan ActionListener ke JTextField1
        jTextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField2.requestFocusInWindow();
            }
        });

        // Menambahkan ActionListener ke JTextField2
        jTextField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formLogic();
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField1.requestFocusInWindow();
            }
        });

        // Menambahkan ActionListener ke JTextField3
        jTextField3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    uploadData();
                } catch (SQLException ex) {
                    Logger.getLogger(CashierForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Menambahkan DocumentListener ke JTextField3
        Document document = jTextField3.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Plain text components don't fire these events
            }
        });

        // Menambahkan filter untuk jTextField1 agar hanya menerima huruf dan angka
        ((AbstractDocument) jTextField1.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (pattern.matcher(newText).matches()) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // Menambahkan filter untuk jTextField2 agar hanya menerima angka
        ((AbstractDocument) jTextField2.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern pattern = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (pattern.matcher(newText).matches()) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // Menambahkan filter untuk jTextField3 agar hanya menerima angka
        ((AbstractDocument) jTextField3.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern pattern = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (pattern.matcher(newText).matches()) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        
        // Di bagian inisialisasi komponen Anda
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (tableModel.getRowCount() == 0) {
                    jTextField3.setEnabled(false);
                } else {
                    jTextField3.setEnabled(true);
                }
            }
        });

        // Set jTextField3 menjadi defaultnya disabled
        jTextField3.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("No Transaksi :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Tanggal :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Kasir :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        String transactionCode = labelLogic.generateTransactionCode();
        jLabel4.setText(transactionCode);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        String currentDateTime = labelLogic.getCurrentDateTime();
        jLabel5.setText(currentDateTime);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Total    Rp 0");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Kode Item :");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Jumlah :");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Satuan", "Qty", "Harga (pcs)", "Total Harga"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(40);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton1.setText("Print");
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

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton3.setText("Clear");
        jButton3.setBorderPainted(false);
        jButton3.setDoubleBuffered(true);
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.setRequestFocusEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Bayar");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(110, 110, 110))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            uploadData();
        } catch (SQLException ex) {
            Logger.getLogger(CashierForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clearText();
    }//GEN-LAST:event_jButton3ActionPerformed
    
    public void uploadData() throws SQLException {
        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.out.println("Gagal mendapatkan koneksi.");
            return;
        }

        try {
            String kodePenjualan = jLabel4.getText();
            Date tanggalTransaksiDate = new Date(); // Mendapatkan tanggal hari ini

            // Konversi format tanggal ke java.sql.Date yang diterima oleh database
            java.sql.Date tanggalTransaksi = new java.sql.Date(tanggalTransaksiDate.getTime());

            String namaKaryawan = jLabel6.getText();

            String kodeUserQuery = "SELECT kode_user FROM akun_karyawan WHERE nama = ?";
            PreparedStatement kodeUserStatement = conn.prepareStatement(kodeUserQuery);
            kodeUserStatement.setString(1, namaKaryawan);
            ResultSet resultSet = kodeUserStatement.executeQuery();

            String kodeUser = null;
            if (resultSet.next()) {
                kodeUser = resultSet.getString("kode_user");
            }

            if (kodeUser == null) {
                System.out.println("Karyawan tidak ditemukan dalam database.");
                return;
            }

            String insertQuery = "INSERT INTO penjualan (kode_penjualan, tanggal_transaksi, kode_user) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, kodePenjualan);
            insertStatement.setDate(2, tanggalTransaksi);
            insertStatement.setString(3, kodeUser);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data penjualan berhasil diunggah ke database.");
                // Panggil metode untuk menyimpan detail penjualan
                saveDetailToDatabase(conn, kodePenjualan);
                // Panggil metode untuk menyimpan ke tabel print_invoice
                uploadToPrintInvoice(conn, kodePenjualan);
            } else {
                System.out.println("Gagal mengunggah data penjualan ke database.");
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan SQL: " + e.getMessage());
        }
    }

    private void saveDetailToDatabase(Connection conn, String kodePenjualan) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        try {
            double labaPcs; // Deklarasi variabel labaPcs
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String kodeBarang = (String) tableModel.getValueAt(i, 1);
                int qty = (int) tableModel.getValueAt(i, 4);
                String hargaTotalStr = (String) tableModel.getValueAt(i, 6);
                double hargaTotal = Double.parseDouble(hargaTotalStr);

                // Ambil laba_pcs dari tabel pembelian
                labaPcs = getLabaPcs(conn, kodeBarang); // Inisialisasi variabel labaPcs

                // Hitung laba_total
                double labaTotal = qty * labaPcs;

                // Simpan ke tabel detail_penjualan
                String query = "INSERT INTO detail_penjualan (kode_penjualan, kode_barang, jumlah_terjual, total_harga, laba_pcs, laba_total) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, kodePenjualan);
                statement.setString(2, kodeBarang);
                statement.setInt(3, qty);
                statement.setDouble(4, hargaTotal);
                statement.setDouble(5, labaPcs);
                statement.setDouble(6, labaTotal);
                statement.executeUpdate();
            }
            System.out.println("Data detail penjualan berhasil diunggah ke database.");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan SQL saat menyimpan detail penjualan: " + e.getMessage());
        }
    }
    
    private void uploadToPrintInvoice(Connection conn, String kodePenjualan) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        try {
            double totalHarga = 0.0;
            if (tableModel.getRowCount() > 0) { // Periksa apakah tabel tidak kosong
                // Kosongkan tabel print_invoice sebelum menyimpan data baru
                clearPrintInvoiceTable(conn);
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String kodeBarang = (String) tableModel.getValueAt(i, 1);
                    int qty = (int) tableModel.getValueAt(i, 4);

                    // Mengambil hargaPcs dari tabel barang berdasarkan kodeBarang
                    double hargaPcs = getHargaPcsFromBarangTable(conn, kodeBarang);

                    // Menghitung totalHarga untuk setiap barang
                    double totalHargaBarang = qty * hargaPcs;

                    // Menambahkan totalHargaBarang ke totalHarga
                    totalHarga += totalHargaBarang;

                    // Simpan ke tabel print_invoice
                    String query = "INSERT INTO print_invoice (id_about, kode_penjualan, kode_barang, jumlah_terjual, total_harga, jumlah_harga, total_bayar, kembali) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setInt(1, getIdAboutFromDatabase(conn));
                    statement.setString(2, kodePenjualan);
                    statement.setString(3, kodeBarang);
                    statement.setInt(4, qty);
                    statement.setDouble(5, totalHargaBarang);
                    statement.setDouble(6, totalHarga);
                    statement.setDouble(7, Double.parseDouble(jTextField3.getText())); // Menggunakan total_bayar dari jTextField3
                    statement.setDouble(8, Double.parseDouble(jTextField3.getText()) - totalHarga); // Menghitung dan gunakan nilai kembali
                    statement.executeUpdate();
                }
                System.out.println("Data untuk print invoice berhasil diunggah ke database.");
                // Setelah berhasil diunggah, jalankan kode cetak laporan
                printInvoiceReport();
                clearText();
            } else {
                System.out.println("Tidak ada data dalam tabel untuk diunggah ke print invoice.");
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan SQL saat mengunggah data untuk print invoice: " + e.getMessage());
        }
    }

    private void printInvoiceReport() {
        try {
            Connection conn = DBConnection.getConnection();
            File namaFile = new File("src/com/timone/print/component/Invoice.jasper");
            JasperPrint jp = JasperFillManager.fillReport(namaFile.getPath(), null, conn);

            // Cetak laporan langsung
            JasperPrintManager.printReport(jp, false); // false berarti tidak menampilkan dialog pencetakan

            // Optional: Tutup koneksi
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error displaying report: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearPrintInvoiceTable(Connection conn) throws SQLException {
        String query = "DELETE FROM print_invoice";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.executeUpdate();
        System.out.println("Tabel print_invoice berhasil dikosongkan.");
    }



    private double getHargaPcsFromBarangTable(Connection conn, String kodeBarang) throws SQLException {
        double hargaPcs = 0.0;
        String query = "SELECT harga_pcs FROM barang WHERE kode_barang = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, kodeBarang);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            hargaPcs = resultSet.getDouble("harga_pcs");
        }
        return hargaPcs;
    }

    private int getIdAboutFromDatabase(Connection conn) {
        int idAbout = -1; // Inisialisasi dengan nilai default

        try {
            // Buat query SQL untuk mengambil id_about dari tabel about
            String query = "SELECT id_about FROM about";
            PreparedStatement statement = conn.prepareStatement(query);

            // Eksekusi query
            ResultSet resultSet = statement.executeQuery();

            // Jika hasil query tidak kosong, ambil nilai id_about
            if (resultSet.next()) {
                idAbout = resultSet.getInt("id_about");
            } else {
                System.out.println("Tidak ada id_about yang sesuai ditemukan dalam database.");
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan SQL saat mengambil id_about: " + e.getMessage());
        }

        return idAbout;
    }

    
    private double getLabaPcs(Connection conn, String kodeBarang) throws SQLException {
        double labaPcs = 0.0;
        String query = "SELECT laba_pcs FROM pembelian WHERE kode_barang = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, kodeBarang);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            labaPcs = resultSet.getDouble("laba_pcs");
        }
        return labaPcs;
    }
    
    public void clearText(){
        jTextField1.setText(""); // set blank text biar bersih coy
        jTextField2.setText("");
        jTextField3.setText("");
        jLabel7.setText("Total    Rp 0");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Menghapus semua baris dari model tabel
        String transactionCode = labelLogic.generateTransactionCode();
        jLabel4.setText(transactionCode);
    }
    
    public void setNama(String nama) {
        jLabel6.setText(nama);
    }
    
    public static String generateTransactionCode() {
        String prefix = "TR";
        StringBuilder sb = new StringBuilder(prefix);

        // generate 10 karakter numerik
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            // buat limit biar bisa generate hanya dari 0 dan 9
            char digit = (char) (random.nextInt(10) + '0');
            sb.append(digit);
        }
        return sb.toString();
    }

    //buat nampilin tanggal
    public static String getCurrentDateTime() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return now.format(formatter);
    }
    
    private void formLogic() {
        // Mendapatkan nilai dari jTextField1
        String kodeBarang = jTextField1.getText().trim();

        if (kodeBarang.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kode barang tidak boleh kosong");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT kode_barang, nama_barang, satuan_obat, harga_pcs, kuantitas, kadaluarsa FROM barang WHERE kode_barang = ?");
        ) {
            pst.setString(1, kodeBarang);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int kuantitas = rs.getInt("kuantitas");
                    LocalDate tanggalKadaluarsa = rs.getDate("kadaluarsa").toLocalDate();
                    LocalDate tanggalHariIni = LocalDate.now();
                    LocalDate tanggalKadaluarsaMin3Bulan = tanggalHariIni.plusMonths(3);

                    if (kuantitas <= 0) {
                        JOptionPane.showMessageDialog(null, "Stok barang habis");
                        return;
                    }

                    if (tanggalKadaluarsa.isBefore(tanggalHariIni) || tanggalKadaluarsa.isBefore(tanggalKadaluarsaMin3Bulan)) {
                        JOptionPane.showMessageDialog(null, "Barang tidak bisa dijual karena akan expired dalam 3 bulan");
                        return;
                    }

                    int qty = jTextField2.getText().isEmpty() ? 1 : Integer.parseInt(jTextField2.getText());

                    if (qty == 0) {
                        JOptionPane.showMessageDialog(null, "Kuantitas tidak boleh nol");
                        return;
                    }

                    int hargaPcs = rs.getInt("harga_pcs");

                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    int rowIndex = -1;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, 1).equals(kodeBarang)) {
                            rowIndex = i;
                            break;
                        }
                    }

                    if (rowIndex != -1) {
                        qty += (int) model.getValueAt(rowIndex, 4);
                        model.removeRow(rowIndex);
                    }

                    double totalHarga = hargaPcs * qty;

                    if (totalHarga < 0) {
                        totalHarga = 0;
                    }

                    String totalHargaString = String.format("%.0f", totalHarga);

                    Object[] row = {model.getRowCount() + 1, kodeBarang, rs.getString("nama_barang"), rs.getString("satuan_obat"), qty, hargaPcs, totalHargaString};
                    model.addRow(row);

                    double totalSemua = 0;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        totalSemua += Double.parseDouble(model.getValueAt(i, 6).toString());
                    }

                    NumberFormat formatter = new DecimalFormat("#,###");
                    String totalRupiah = "Total    Rp " + formatter.format(totalSemua);
                    jLabel7.setText(totalRupiah);
                } else {
                    JOptionPane.showMessageDialog(null, "Kode barang tidak ditemukan");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Masukkan jumlah barang dalam angka");
        }
    }




  // Add method for change calculation
  private void calculateChange() {
        try {
            double amountPaid = Double.parseDouble(jTextField3.getText());
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            double totalHarga = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                totalHarga += Double.parseDouble(model.getValueAt(i, 6).toString());
            }
            double change = amountPaid - totalHarga;
            NumberFormat formatter = new DecimalFormat("#,###");
            String changeString = formatter.format(change);
            jLabel7.setText(String.format("Kembali    Rp %s", changeString));
        } catch (NumberFormatException ex) {
            // Handle non-numeric input
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            double totalHarga = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                totalHarga += Double.parseDouble(model.getValueAt(i, 6).toString());
            }
            NumberFormat formatter = new DecimalFormat("#,###");
            String totalRupiah = "Total    Rp " + formatter.format(totalHarga);
            jLabel7.setText(totalRupiah);
        }
    }



    public static void main(String args[]) {
        FlatGitHubIJTheme.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CashierForm home = new CashierForm();
                home.setLocationRelativeTo(null); // Memposisikan jendela di tengah layar
                
                home.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
