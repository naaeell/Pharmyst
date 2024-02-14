package com.timone.setup.manager;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.timone.setup.main.startLogin;
import com.timone.setup.main.startSetup;

import javax.swing.*;
import java.awt.*;

public class FormsManager {
    private startSetup start;
    private startLogin startLogin;
    
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
        this.start = application;
    }
    
    public void initLogin(startLogin application) {
        this.startLogin = application;
    }

    public void showForm(JComponent form) {
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            start.setContentPane(form);
            start.revalidate();
            start.repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }
}
