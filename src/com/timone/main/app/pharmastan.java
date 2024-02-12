/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.app;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.timone.main.setup.Agreement;
import com.timone.main.setup.Setup;
import java.awt.ComponentOrientation;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Fadel
 */
public class pharmastan {
    private static pharmastan app;
    private static Setup setupForm;
    private static Agreement agreementForm;
    
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

    private void setContentPane(Agreement agreementForm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private ComponentOrientation getComponentOrientation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setContentPane(Setup setupForm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
