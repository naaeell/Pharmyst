package com.timone.setup.manager;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.timone.setup.main.startLogin;
import com.timone.setup.main.startRfid;
import com.timone.setup.main.startSetup;

import javax.swing.*;
import java.awt.*;

public class FormsManager {
    private startSetup setupStart;
    private startLogin loginStart;
    private startRfid rfidStart;
    
    private static FormsManager instance;

    public static FormsManager getInstance() {
        if (instance == null) {
            instance = new FormsManager();
        }
        return instance;
    }

    private FormsManager() {

    }

    public void initSetup(startSetup application) {
        this.setupStart = application;
    }
    
    public void initLogin(startLogin application) {
        this.loginStart = application;
    }
    
    public void initRfid(startRfid application) {
        this.rfidStart = application;
    }

    public void showFormSetup(JComponent form) {
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            setupStart.setContentPane(form);
            setupStart.revalidate();
            setupStart.repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }
    
    public void showFormLogin(JComponent form) {
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            loginStart.setContentPane(form);
            loginStart.revalidate();
            loginStart.repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }
    
    public void showFormRfid(JComponent form) {
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            rfidStart.setContentPane(form);
            rfidStart.revalidate();
            rfidStart.repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }

}
