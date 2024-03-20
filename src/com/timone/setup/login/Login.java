package com.timone.setup.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.timone.menu.dashboard.MainAdmin;
import static com.timone.setup.login.Activation.openWebpage;
import com.timone.setup.manager.FormsManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class Login extends JPanel {
    
    public static MainAdmin menu;
    public static Login logIn;
    
    public Login() {
        init();
        cmdLogin.addActionListener(e -> {
            // Menginstansiasi objek menu jika belum diinstansiasi sebelumnya
            if (menu == null) {
                menu = new MainAdmin();
                // Menempatkan frame di tengah layar
                menu.setLocationRelativeTo(null);
            }

            // Menampilkan menu
            menu.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        });
    }

    private void init() {
        UIManager.put( "TextComponent.arc", 10 );
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        chrfid = new JCheckBox("Enable RFID login");
        cmdLogin = new JButton("Login");
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 45 30 45 30", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");
        

        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username or email");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");

        JLabel lbTitle = new JLabel("Welcome back!");
        JLabel description = new JLabel("Please sign in to access your account");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Username"), "gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel("Password"), "gapy 8");
        panel.add(txtPassword);
        panel.add(rfid(), "gapy 8");
        panel.add(cmdLogin, "gapy 15");

        add(panel);
    }
    
    private Component rfid() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        
        panel.add(chrfid, "grow 0");
        return panel;
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chrfid;
    private JButton cmdLogin;
}
