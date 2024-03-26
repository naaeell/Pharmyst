package com.timone.setup.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.timone.setup.login.Activation;
import com.timone.setup.login.Login;
import com.timone.setup.manager.FormsManager;
import raven.toast.Notifications;
import javax.swing.*;
import java.awt.*;

public class startApp extends JFrame {

    public startApp() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(440, 514));
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(new Activation());
        Notifications.getInstance().setJFrame(this);
        FormsManager.getInstance().initApp(this);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("timone.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new startApp().setVisible(true));
    }
}
