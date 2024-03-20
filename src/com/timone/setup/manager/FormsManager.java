package com.timone.setup.manager;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.timone.setup.main.startApp;
import com.timone.setup.main.startLogin;


import javax.swing.*;
import java.awt.*;

public class FormsManager {
    private startApp appStart;
    private startLogin loginStart;
    
    private static FormsManager instance;

    public static FormsManager getInstance() {
        if (instance == null) {
            instance = new FormsManager();
        }
        return instance;
    }

    private FormsManager() {

    }
    
    public void initApp(startApp application) {
        this.appStart = application;
    }
    
    public void initLogin(startLogin application) {
        this.loginStart = application;
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
    
    public void showForm(JComponent form) {
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            appStart.setContentPane(form);
            appStart.revalidate();
            appStart.repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }


}
