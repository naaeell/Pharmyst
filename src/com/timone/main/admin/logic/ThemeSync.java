/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.admin.logic;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.timone.main.admin.component.formAbout;
import com.timone.main.admin.component.formReport;
import com.timone.main.admin.distributor.formAddDistributor;
import com.timone.main.admin.operational.formAddOperational;
import com.timone.main.admin.purchase.formAddPurchase;
import com.timone.main.admin.worker.formAddWorker;
import java.awt.EventQueue;

/**
 *
 * @author Fadel
 */
public class ThemeSync {
    private static formReport report;
    private static formAbout about;
    private static formAddDistributor addDistributor;
    private static formAddOperational addOperational;
    private static formAddPurchase addPurchase;
    private static formAddWorker addWorker;

    public static void reportThemeSync() {
        if (report == null) {
            report = new formReport();
            report.setLocationRelativeTo(null);
        }
        report.setVisible(true);
    }

    public static void aboutThemeSync() {
        if (about == null) {
            about = new formAbout();
            about.setLocationRelativeTo(null);
        }
        about.setVisible(true);
    }

    public static void addDistributorThemeSync() {
        if (addDistributor == null) {
            addDistributor = new formAddDistributor();
            addDistributor.setLocationRelativeTo(null);
        }
        addDistributor.setVisible(true);
    }

    public static void addOperationalThemeSync() {
        if (addOperational == null) {
            addOperational = new formAddOperational();
            addOperational.setLocationRelativeTo(null);
        }
        addOperational.setVisible(true);
    }

    public static void addPurchaseThemeSync() {
        if (addPurchase == null) {
            addPurchase = new formAddPurchase();
            addPurchase.setLocationRelativeTo(null);
        }
        addPurchase.setVisible(true);
    }

    public static void addWorkerThemeSync() {
        if (addWorker == null) {
            addWorker = new formAddWorker();
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
