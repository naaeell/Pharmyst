package com.timone.main.app;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.timone.main.setup.Agreement;
import com.timone.main.setup.Setup;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main application class.
 */
public class application extends javax.swing.JFrame {
    
    private static application app;
    private static Setup setupForm;
    private static Agreement agreementForm;
    
    public application() {
        initComponents();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            UIManager.put("TextComponent.arc", 10);
            UIManager.put("Button.arc", 999);
            Setup setupForm = new Setup();
            setupForm.setLocationRelativeTo(null);
            setupForm.setVisible(true);
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

     public static void next() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(agreementForm);
        agreementForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(agreementForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    
    public static void decline() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(setupForm);
        setupForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(setupForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
}
