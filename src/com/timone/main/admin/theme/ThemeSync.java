/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.theme;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.timone.main.admin.component.FormAbout;
import com.timone.main.admin.component.FormAddDistributor;
import com.timone.main.admin.component.FormAddOperational;
import com.timone.main.admin.component.FormAddPurchase;
import com.timone.main.admin.component.FormAddWorker;
import java.awt.EventQueue;

/**
 *
 * @author Fadel
 */
public class ThemeSync {
    private static FormAbout about;
    private static FormAddDistributor addDistributor;
    private static FormAddOperational addOperational;
    private static FormAddPurchase addPurchase;
    private static FormAddWorker addWorker;

    public static void aboutThemeSync() {
        if (about == null) {
            about = new FormAbout();
            about.setLocationRelativeTo(null);
        }
        about.setVisible(true);
    }

    public static void addDistributorThemeSync() {
        if (addDistributor == null) {
            addDistributor = new FormAddDistributor();
            addDistributor.setLocationRelativeTo(null);
        }
        addDistributor.setVisible(true);
    }

    public static void addOperationalThemeSync() {
        if (addOperational == null) {
            addOperational = new FormAddOperational();
            addOperational.setLocationRelativeTo(null);
        }
        addOperational.setVisible(true);
    }

    public static void addPurchaseThemeSync() {
        if (addPurchase == null) {
            addPurchase = new FormAddPurchase();
            addPurchase.setLocationRelativeTo(null);
        }
        addPurchase.setVisible(true);
    }

    public static void addWorkerThemeSync() {
        if (addWorker == null) {
            addWorker = new FormAddWorker();
            addWorker.setLocationRelativeTo(null);
        }
        addWorker.setVisible(true);
    }

    public static void themeChanger() {
        boolean isDarkTheme = FlatLaf.isLafDark();
        if (isDarkTheme) {
            EventQueue.invokeLater(() -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatGitHubIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });
        } else {
            EventQueue.invokeLater(() -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatGitHubDarkIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });
        }
    }
}
