package com.timone.setup.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.timone.setup.login.Rfid;
import com.timone.setup.manager.FormsManager;
import raven.toast.Notifications;
import javax.swing.*;
import java.awt.*;

public class startRfid extends JFrame {

    public startRfid() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(440, 514));
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(new Rfid());
        Notifications.getInstance().setJFrame(this);
        FormsManager.getInstance().initRfid(this);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new startRfid().setVisible(true));
    }
}
