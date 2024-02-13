package com.timone.setup.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.timone.main.MainAdmin;
import net.miginfocom.swing.MigLayout;
import com.timone.setup.component.PasswordStrengthStatus;
import com.timone.setup.manager.FormsManager;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;

public class Register extends JPanel {
    
    public static MainAdmin menu;
    public Register() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtName = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        cmdRegister = new JButton("Submit");

        cmdRegister.addActionListener(e -> {
            // Menginstansiasi objek menu jika belum diinstansiasi sebelumnya
            if (menu == null) {
                menu = new MainAdmin();
            }

            // Menampilkan menu
            menu.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        });
        
        passwordStrengthStatus = new PasswordStrengthStatus();

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 45 30 45 30", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your name");
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username or email");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");
        

        cmdRegister.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");

        JLabel lbTitle = new JLabel("Let's create an account");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");

        passwordStrengthStatus.initPasswordField(txtPassword);

        panel.add(lbTitle, "gapy -10");
        panel.add(new JLabel("Name"), "gapy 10");
        panel.add(txtName);
        panel.add(new JLabel("Username or Email"), "gapy 10");
        panel.add(txtUsername);
        panel.add(new JLabel("Password"), "gapy 8");
        panel.add(txtPassword);
        panel.add(passwordStrengthStatus, "gapy 0");
        panel.add(cmdRegister, "gapy 20");
        add(panel);
    }

    private JTextField txtName;
    private JTextField txtLastName;
    private JRadioButton jrMale;
    private JRadioButton jrFemale;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private ButtonGroup groupGender;
    private JButton cmdRegister;
    private PasswordStrengthStatus passwordStrengthStatus;
}
